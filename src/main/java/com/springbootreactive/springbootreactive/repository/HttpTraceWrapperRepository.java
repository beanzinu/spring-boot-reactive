package com.springbootreactive.springbootreactive.repository;

import com.springbootreactive.springbootreactive.domain.HttpTraceWrapper;
import org.springframework.data.repository.Repository;

import java.util.stream.Stream;

public interface HttpTraceWrapperRepository extends Repository<HttpTraceWrapper,String> {
    Stream<HttpTraceWrapper> findAll();

    void save(HttpTraceWrapper trace);
}
