package com.puc.ticketin.repository;

import com.puc.ticketin.domain.entity.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ReactiveEventRepository extends ReactiveMongoRepository<Event, String> {


    Mono<Event> findByName(String name);
}
