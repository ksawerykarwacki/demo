package com.example.sample;

import com.example.sample.attribute.AttributeDTO;
import com.example.sample.attribute.Role;
import com.example.sample.attribute.Type;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;

import java.util.List;

import static io.restassured.RestAssured.given;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SampleE2ETest {

    @Inject
    EmbeddedServer server;

    @Inject
    SampleRepository repository;

    @BeforeEach
    void before() {
        repository.deleteAll();
        RestAssured.port = server.getPort();
    }

    @Test
    void shouldFailWithoutName() {
        SampleDTO sampleDTO = SampleDTO.builder().build();

        given()
                .body(sampleDTO).contentType(MediaType.APPLICATION_JSON)
                .when()
                .post("/api/sample")
                .then()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.getCode());
    }

    @Test
    void shouldFailOnDuplicatedName() {
        SampleDTO sampleDTO = SampleDTO.builder()
                .name("Some name")
                .build();

        given()
                .body(sampleDTO).contentType(MediaType.APPLICATION_JSON)
                .when()
                .post("/api/sample")
                .then()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .statusCode(HttpStatus.CREATED.getCode());

        given()
                .body(sampleDTO).contentType(MediaType.APPLICATION_JSON)
                .when()
                .post("/api/sample")
                .then()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .statusCode(HttpStatus.BAD_REQUEST.getCode());
    }

    @Test
    void shouldFailWithDuplicatedAttributeName() {
        AttributeDTO attribute = AttributeDTO.builder()
                .name("someAttribute")
                .label("Some Attribute")
                .role(Role.ATTRIBUTE)
                .type(Type.TEXT)
                .build();
        SampleDTO sampleDTO = SampleDTO.builder()
                .name("Some name")
                .attributes(List.of(attribute, attribute))
                .build();

        given()
                .body(sampleDTO).contentType(MediaType.APPLICATION_JSON)
                .when()
                .post("/api/sample")
                .then()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .statusCode(HttpStatus.BAD_REQUEST.getCode());
    }

    @Test
    void shouldFailWithAttributeWithoutLabel() {
        AttributeDTO attribute = AttributeDTO.builder()
                .name("someAttribute")
                .role(Role.ATTRIBUTE)
                .type(Type.TEXT)
                .build();
        SampleDTO sampleDTO = SampleDTO.builder()
                .name("Some name")
                .attributes(List.of(attribute))
                .build();

        given()
                .body(sampleDTO).contentType(MediaType.APPLICATION_JSON)
                .when()
                .post("/api/sample")
                .then()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .statusCode(HttpStatus.BAD_REQUEST.getCode());
    }

    @Test
    void shouldFailWithWhenKeyAttributeIsNotText() {
        AttributeDTO attribute = AttributeDTO.builder()
                .name("someAttribute")
                .label("Some Attribute")
                .role(Role.KEY)
                .type(Type.INTEGER)
                .build();
        SampleDTO sampleDTO = SampleDTO.builder()
                .name("Some name")
                .attributes(List.of(attribute))
                .build();

        given()
                .body(sampleDTO).contentType(MediaType.APPLICATION_JSON)
                .when()
                .post("/api/sample")
                .then()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .statusCode(HttpStatus.BAD_REQUEST.getCode());
    }
}
