package run.halo.app.core.extension.reconciler;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import run.halo.app.content.permalinks.CategoryPermalinkPolicy;
import run.halo.app.core.extension.ExtensionClient;
import run.halo.app.core.extension.content.Category;
import run.halo.app.core.extension.content.Post;
import run.halo.app.core.extension.controller.ControllerBuilder;
import run.halo.app.core.extension.controller.Controller;
import run.halo.app.core.extension.controller.Reconciler;
import run.halo.app.infra.utils.JsonUtils;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Reconciler for {@link Category}.
 *
 * @author guqing
 * @since 2.0.0
 */
@Component
public class CategoryReconciler implements Reconciler<Reconciler.Request> {
    private static final String FINALIZER_NAME = "category-protection";
    private final ExtensionClient client;
    private final CategoryPermalinkPolicy categoryPermalinkPolicy;

    public CategoryReconciler(ExtensionClient client,
        CategoryPermalinkPolicy categoryPermalinkPolicy) {
        this.client = client;
        this.categoryPermalinkPolicy = categoryPermalinkPolicy;
    }

    @Override
    public Result reconcile(Request request) {
        return client.fetch(Category.class, request.name())
            .map(category -> {
                if (category.isDeleted()) {
                    cleanUpResourcesAndRemoveFinalizer(request.name());
                    return new Result(false, null);
                }
                addFinalizerIfNecessary(category);

                reconcileStatusPermalink(request.name());

                reconcileStatusPosts(request.name());
                return new Result(true, Duration.ofMinutes(1));
            })
            .orElseGet(() -> new Result(false, null));
    }

    @Override
    public Controller setupWith(ControllerBuilder builder) {
        return builder
            .extension(new Category())
            .build();
    }

    private void addFinalizerIfNecessary(Category oldCategory) {
        Set<String> finalizers = oldCategory.getMetadata().getFinalizers();
        if (finalizers != null && finalizers.contains(FINALIZER_NAME)) {
            return;
        }
        client.fetch(Category.class, oldCategory.getMetadata().getName())
            .ifPresent(category -> {
                Set<String> newFinalizers = category.getMetadata().getFinalizers();
                if (newFinalizers == null) {
                    newFinalizers = new HashSet<>();
                    category.getMetadata().setFinalizers(newFinalizers);
                }
                newFinalizers.add(FINALIZER_NAME);
                client.update(category);
            });
    }

    private void cleanUpResources(Category category) {
        // remove permalink from permalink indexer
        categoryPermalinkPolicy.onPermalinkDelete(category);
    }

    private void cleanUpResourcesAndRemoveFinalizer(String categoryName) {
        client.fetch(Category.class, categoryName).ifPresent(category -> {
            cleanUpResources(category);
            if (category.getMetadata().getFinalizers() != null) {
                category.getMetadata().getFinalizers().remove(FINALIZER_NAME);
            }
            client.update(category);
        });
    }

    private void reconcileStatusPermalink(String name) {
        client.fetch(Category.class, name)
            .ifPresent(category -> {
                Category oldCategory = JsonUtils.deepCopy(category);
                categoryPermalinkPolicy.onPermalinkDelete(oldCategory);

                category.getStatusOrDefault()
                    .setPermalink(categoryPermalinkPolicy.permalink(category));
                categoryPermalinkPolicy.onPermalinkAdd(category);

                if (!oldCategory.equals(category)) {
                    client.update(category);
                }
            });
    }

    private void reconcileStatusPosts(String name) {
        client.fetch(Category.class, name).ifPresent(category -> {
            Category oldCategory = JsonUtils.deepCopy(category);

            populatePosts(category);

            if (!oldCategory.equals(category)) {
                client.update(category);
            }
        });
    }

    private void populatePosts(Category category) {
        String name = category.getMetadata().getName();
        List<String> categoryNames = listChildrenByName(name)
            .stream()
            .map(item -> item.getMetadata().getName())
            .toList();

        List<Post> posts = client.list(Post.class, post -> !post.isDeleted(), null);

        // populate post to status
        List<Post.CompactPost> compactPosts = posts.stream()
            .filter(post -> includes(post.getSpec().getCategories(), categoryNames))
            .map(post -> Post.CompactPost.builder()
                .name(post.getMetadata().getName())
                .visible(post.getSpec().getVisible())
                .published(post.isPublished())
                .build())
            .toList();
        category.getStatusOrDefault().setPostCount(compactPosts.size());

        long visiblePostCount = compactPosts.stream()
            .filter(post -> Objects.equals(true, post.getPublished())
                && Post.VisibleEnum.PUBLIC.equals(post.getVisible()))
            .count();
        category.getStatusOrDefault().setVisiblePostCount((int) visiblePostCount);
    }

    /**
     * whether {@code categoryRefs} contains elements in {@code categoryNames}.
     *
     * @param categoryRefs category left to judge
     * @param categoryNames category right to judge
     * @return true if {@code categoryRefs} contains elements in {@code categoryNames}.
     */
    private boolean includes(@Nullable List<String> categoryRefs, List<String> categoryNames) {
        if (categoryRefs == null || categoryNames == null) {
            return false;
        }
        for (String categoryRef : categoryRefs) {
            if (categoryNames.contains(categoryRef)) {
                return true;
            }
        }
        return false;
    }

    private List<Category> listChildrenByName(String name) {
        List<Category> categories = client.list(Category.class, null, null);
        Map<String, Category> nameIdentityMap = categories.stream()
            .collect(Collectors.toMap(category -> category.getMetadata().getName(),
                Function.identity()));
        final List<Category> children = new ArrayList<>();

        Deque<String> deque = new ArrayDeque<>();
        deque.add(name);
        while (!deque.isEmpty()) {
            String itemName = deque.poll();
            Category category = nameIdentityMap.get(itemName);
            if (category == null) {
                continue;
            }
            children.add(category);
            List<String> childrenNames = category.getSpec().getChildren();
            if (childrenNames != null) {
                deque.addAll(childrenNames);
            }
        }
        return children;
    }
}
