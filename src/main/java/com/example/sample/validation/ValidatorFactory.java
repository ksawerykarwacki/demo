package com.example.sample.validation;

import com.example.sample.SampleRepository;
import com.example.sample.attribute.AttributeDTO;
import com.example.sample.attribute.Role;
import com.example.sample.attribute.Type;
import io.micronaut.context.annotation.Factory;
import io.micronaut.data.annotation.Repository;
import io.micronaut.validation.validator.constraints.ConstraintValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Factory
public class ValidatorFactory {

    @Inject
    SampleRepository repository;

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
                    context.messageTemplate("Key Attribute should be text");
                    return false;
                }
            }
            return true;
        };
    }

    @Singleton
    ConstraintValidator<UniqueName, String> uniqueNameValidator() {
        return (value, annotationMetadata, context) -> {
          var found = repository.findByName(value);
          return found.isEmpty();
        };
    }
}
