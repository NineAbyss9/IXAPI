
package org.NineAbyss9.annotation;

import java.lang.annotation.*;

/**An {@linkplain Annotation} that remind something is unused.*/
@NotCheckUnused
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR,
        ElementType.TYPE, ElementType.TYPE_PARAMETER, ElementType.LOCAL_VARIABLE,
        ElementType.METHOD, ElementType.PARAMETER, ElementType.PACKAGE, ElementType.TYPE_USE,
        ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.SOURCE)
public @interface Unused {
}
