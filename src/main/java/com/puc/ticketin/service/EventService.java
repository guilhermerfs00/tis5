package com.puc.ticketin.service;//package com.example.demo.service;

import com.puc.ticketin.api.request.EventFilterRequest;
import com.puc.ticketin.api.response.PageResponse;
import com.puc.ticketin.domain.mapper.PageResponseMapper;
import com.puc.ticketin.domain.bo.EventBO;
import com.puc.ticketin.domain.exception.EventException;
import com.puc.ticketin.domain.mapper.EventMapper;
import com.puc.ticketin.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.puc.ticketin.domain.exception.EventException.Reason.EVENT_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final PageResponseMapper pageResponseMapper;
    private final EventMapper eventMapper;

    public Mono<PageResponse<EventBO>> listAllPagineted(Pageable page, EventFilterRequest filterRequest) {
        log.info("Consultando Eventos por {}...", filterRequest);
        return repository.listAllPaginated(page, filterRequest)
                .switchIfEmpty(Mono.error(new EventException("Nenhum evento encontrado", EVENT_NOT_FOUND)))
                .map(pageResponseMapper::eventToPageResponse)
                .map(eventMapper::mapEntityToPageBo)
                .doOnSuccess(current -> log.debug("Consulta | Retornando p'agina <{}> com <{}> registros",
                        current.getPageNumber(),
                        current.getContent().size())
                );

    }


}
