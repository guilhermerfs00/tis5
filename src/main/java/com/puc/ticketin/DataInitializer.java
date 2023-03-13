package com.puc.ticketin;

import com.puc.ticketin.domain.entity.Ticket;
import com.puc.ticketin.domain.entity.User;
import com.puc.ticketin.domain.enums.RoleEnum;
import com.puc.ticketin.repository.ReactiveEventRepository;
import com.puc.ticketin.repository.ReactiveTicketRepository;
import com.puc.ticketin.repository.ReactiveUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.management.relation.Role;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.puc.ticketin.domain.enums.RoleEnum.ROLE_ADMIN;
import static com.puc.ticketin.domain.enums.RoleEnum.ROLE_USER;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer {

    private final ReactiveTicketRepository ticketRepository;
    private final ReactiveUserRepository reactiveUserRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(value = ApplicationReadyEvent.class)
    public void init() {
        log.info("start data initialization...");

        var initPosts = this.reactiveUserRepository.deleteAll()
                .thenMany(
                        Flux.just(ROLE_ADMIN, ROLE_USER)
                                .flatMap(role -> {
                                    List<RoleEnum> roles = ROLE_USER.equals(role) ?
                                            List.of(ROLE_USER) :
                                            Arrays.asList(ROLE_USER, ROLE_ADMIN);

                                    User user = User.builder()
                                            .roles(roles.stream().map(RoleEnum::getValue).collect(Collectors.toList()))
                                            .password(passwordEncoder.encode("123"))
                                            .email(role + "@gmail.com")
                                            .build();

                                    return this.reactiveUserRepository.save(user);
                                })
                );

        var initUsers = this.ticketRepository.deleteAll()
                .thenMany(
                        Flux.just("Ticket one", "Ticket two")
                                .flatMap(title ->
                                        this.ticketRepository.save(Ticket.builder()
                                                .value(BigDecimal.ONE)
                                                .build()
                                        )
                                )
                );

        initPosts.doOnSubscribe(data -> log.info("data:" + data))
                .thenMany(initUsers)
                .subscribe(
                        data -> log.info("data:" + data), err -> log.error("error:" + err),
                        () -> log.info("done initialization...")
                );

    }

}