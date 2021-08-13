package com.example.sample;

import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface SampleMapper {

    SampleDTO toDto(Sample model);
    Sample toModel(SampleDTO dto);
}
