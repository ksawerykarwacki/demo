package com.example.sample;

import com.example.sample.attribute.Attribute;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Introspected
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sample {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Attribute> attributes;
}
