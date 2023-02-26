package com.puc.ticketin.repository;

import com.puc.ticketin.domain.entity.Ticket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TicketRepository extends ReactiveMongoRepository<Ticket, String> {


}
