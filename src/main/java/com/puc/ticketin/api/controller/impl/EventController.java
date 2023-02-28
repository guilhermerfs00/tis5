package com.puc.ticketin.api.controller.impl;

import com.puc.ticketin.api.controller.IEventController;
import com.puc.ticketin.api.request.EventFilterRequest;
import com.puc.ticketin.api.response.EventResponse;
import com.puc.ticketin.api.response.PageResponse;
import com.puc.ticketin.domain.mapper.EventMapper;
import com.puc.ticketin.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@Slf4j
public class EventController implements IEventController {
    private final EventService service;
    private final EventMapper eventMapper;

    @Override
    public Mono<PageResponse<EventResponse>> listPaginated(Pageable page, EventFilterRequest filterRequest) {
        return service.listAllPagineted(page, filterRequest)
                .map(eventMapper::mapBoToResponsePage)
                .doOnError(Mono::error);

    }

}


