package com.puc.ticketin.repository;

import com.puc.ticketin.domain.entity.Event;
import com.puc.ticketin.domain.entity.Ticket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ReactiveTicketRepository extends ReactiveMongoRepository<Ticket, String> {


    Flux<Event> saveAll(List<Event> entity);
}
