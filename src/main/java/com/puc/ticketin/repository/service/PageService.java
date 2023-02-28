package com.puc.ticketin.repository.service;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PageService {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public <T> Mono toPage(Query query, Pageable pageable, Class<T> targetClass) {
        return this.reactiveMongoTemplate.count(query, targetClass)
                .flatMap(total -> this.reactiveMongoTemplate.find(query.with(pageable), targetClass)
                        .collectList().map(list -> new PageImpl<>(list, pageable, total)));
    }

    public PageService(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }
}
