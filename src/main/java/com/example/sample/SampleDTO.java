package com.example.sample;

import com.example.sample.attribute.AttributeDTO;
import com.example.sample.validation.TypeMatchesRole;
import com.example.sample.validation.UniqueAttributes;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class SampleDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 128)
    private String name;

    @UniqueAttributes
    private List<@TypeMatchesRole AttributeDTO> attributes;
}
