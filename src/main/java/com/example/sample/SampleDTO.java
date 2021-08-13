package com.example.sample;

import com.example.sample.attribute.AttributeDTO;
import com.example.sample.validation.TypeMatchesRole;
import com.example.sample.validation.UniqueAttributes;
import com.example.sample.validation.UniqueName;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Introspected
@Valid
public class SampleDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 128)
    @UniqueName
    private String name;

    @UniqueAttributes
    private List<@TypeMatchesRole AttributeDTO> attributes;
}
