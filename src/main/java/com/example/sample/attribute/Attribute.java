package com.example.sample.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Introspected
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Attribute {
    @Id
    @GeneratedValue
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 128)
    private String name;

    @NotBlank
    @Size(min = 1, max = 128)
    private String label;

    @NotBlank
    @Size(min = 1, max = 20)
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotBlank
    @Size(min = 1, max = 20)
    @Enumerated(EnumType.STRING)
    private Role role;

}
