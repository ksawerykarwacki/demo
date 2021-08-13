package com.example.sample;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller("/api/sample")
public class SampleController {

    @Inject
    SampleService service;

    @Get()
    public Iterable<SampleDTO> list() {
        return service.list();
    }

    @Post
    public HttpResponse<SampleDTO> create(@Body @Valid SampleDTO dto) {
        return HttpResponse.created(service.save(dto));
    }
}
