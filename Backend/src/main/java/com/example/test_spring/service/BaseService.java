package com.example.test_spring.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @param <Q> - requestDTO
 * @param <A> - responseDTO
 */
public interface BaseService<Q,A> {

    A getReferenceById(@PathVariable("id") String id);

    Boolean create(@RequestBody Q request);

    Boolean update(@RequestBody Q request);

    Boolean delete(@PathVariable("id") String id);

}