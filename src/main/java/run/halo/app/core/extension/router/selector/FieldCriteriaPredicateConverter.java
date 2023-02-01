package run.halo.app.core.extension.router.selector;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import run.halo.app.core.extension.Extension;

import java.util.function.Predicate;

public class FieldCriteriaPredicateConverter<E extends Extension>
    implements Converter<SelectorCriteria, Predicate<E>> {

    @Override
    @NonNull
    public Predicate<E> convert(SelectorCriteria criteria) {
        // current we only support name field.
        return ext -> {
            if ("name".equals(criteria.key())) {
                var name = ext.getMetadata().getName();
                if (name == null) {
                    return false;
                }
                switch (criteria.operator()) {
                    case Equals, IN -> {
                        return criteria.values().contains(name);
                    }
                    case NotEquals -> {
                        return !criteria.values().contains(name);
                    }
                    default -> {
                        return false;
                    }
                }
            }
            return false;
        };
    }
}
