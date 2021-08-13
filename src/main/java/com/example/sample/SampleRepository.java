package com.example.sample;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SampleRepository extends CrudRepository<Sample, UUID> {

    Optional<Sample> findByName(String name);
}
