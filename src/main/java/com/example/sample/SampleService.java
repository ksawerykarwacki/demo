package com.example.sample;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class SampleService {

    @Inject
    private SampleRepository repository;

    @Inject
    private SampleMapper mapper;

    Iterable<SampleDTO> list() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    SampleDTO save(SampleDTO dto) {
        return mapper.toDto(this.repository.save(mapper.toModel(dto)));
    }
}
