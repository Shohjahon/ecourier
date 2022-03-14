package uz.logistics.ecourier.common;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Methods or classes with @Generated annotation are not
 *  included in jacoco code coverage report
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Generated {
}
