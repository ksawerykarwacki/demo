package com.example.sample.validation;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_USE, ElementType.TYPE_PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface TypeMatchesRole {

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_USE, ElementType.TYPE_PARAMETER, ElementType.LOCAL_VARIABLE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        TypeMatchesRole[] value();
    }
}
