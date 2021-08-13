package com.example.sample.attribute;

import com.example.sample.validation.TypeMatchesRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Introspected
@TypeMatchesRole
public class AttributeDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonIgnore
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 128)
    private String name;

    @NotBlank
    @Size(min = 1, max = 128)
    private String label;

    @NotNull
    private Type type;

    @NotNull
    private Role role;
}
