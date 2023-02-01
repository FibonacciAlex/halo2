package run.halo.app.core.extension.exception;

import org.springframework.http.HttpStatus;
import run.halo.app.core.extension.GroupVersionKind;

public class ExtensionNotFoundException extends ExtensionException {

    public ExtensionNotFoundException(GroupVersionKind gvk, String name) {
        super(HttpStatus.NOT_FOUND, "Extension " + gvk + "/" + name + " was not found.",
            null, null, new Object[] {gvk, name});
    }

}
