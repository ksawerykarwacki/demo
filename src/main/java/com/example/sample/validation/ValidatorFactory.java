package com.example.sample.validation;

import com.example.sample.attribute.AttributeDTO;
import com.example.sample.attribute.Role;
import com.example.sample.attribute.Type;
import io.micronaut.context.annotation.Factory;
import io.micronaut.validation.validator.constraints.ConstraintValidator;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Factory
public class ValidatorFactory {

    @Singleton
    ConstraintValidator<UniqueAttributes, List<AttributeDTO>> uniqueAttributesValidator() {
        return (value, annotationMetadata, context) -> {
            if (value != null) {
                var duplicates = value.stream().collect(Collectors.groupingBy(AttributeDTO::getName, Collectors.counting()));
                duplicates.entrySet().removeIf(entry -> entry.getValue()==1);
                if(duplicates.keySet().size()>0) {
                    context.messageTemplate("Attribute names duplicated: "+ Arrays.toString(duplicates.keySet().toArray()));
                    return false;
                }
            }
            return true;
        };
    }

    @Singleton
    ConstraintValidator<TypeMatchesRole, AttributeDTO> typeMatchesRoleValidator() {
        return (value, annotationMetadata, context) -> {
            if (value != null) {
                if (value.getRole() == Role.KEY && value.getType() != Type.TEXT) {
                    return false;
                }
            }
            return true;
        };
    }
}
