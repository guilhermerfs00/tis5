package com.puc.ticketin.repository;///*
import com.puc.ticketin.domain.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ReactiveUserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByUsername(String username);

}
