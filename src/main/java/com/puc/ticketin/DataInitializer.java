package com.puc.ticketin;

import com.puc.ticketin.domain.entity.Ticket;
import com.puc.ticketin.domain.entity.User;
import com.puc.ticketin.repository.TicketRepository;
import com.puc.ticketin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.puc.ticketin.domain.enums.RoleEnum.ROLE_ADMIN;
import static com.puc.ticketin.domain.enums.RoleEnum.ROLE_USER;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @EventListener(value = ApplicationReadyEvent.class)
    public void init() {
        log.info("start data initialization...");


        var initPosts = this.userRepository.deleteAll()
                .thenMany(
                        Flux.just("user", "admin")
                                .flatMap(username -> {
                                    List<String> roles = "user".equals(username) ?
                                            List.of(ROLE_USER.getValue()) :
                                            Arrays.asList(ROLE_USER.getValue(), ROLE_ADMIN.getValue());

                                    User user = User.builder()
                                            .roles(roles)
                                            .username(username)
                                            .password(passwordEncoder.encode("analinda"))
                                            .email(username + "@gmail.com")
                                            .build();

                                    return this.userRepository.save(user);
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