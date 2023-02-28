package com.puc.ticketin.repository;

import com.puc.ticketin.api.request.EventFilterRequest;
import com.puc.ticketin.domain.entity.Event;
import com.puc.ticketin.repository.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EventRepository {

    public static final String FIELD_NAME = "name";
    private final ReactiveEventRepository repository;

    private final PageService pageService;


    public Mono<Event> save(Event entity) {
        return repository.save(entity);
    }

    public Flux<Event> saveAll(List<Event> entity) {
        return repository.saveAll(entity);
    }

    public Mono<Event> findById(String id) {
        return repository.findById(id);
    }

    public Mono<Event> findByName(String cnpj) {
        return repository.findByName(cnpj);
    }

    public Mono<Page<Event>> listAllPaginated(Pageable page, EventFilterRequest request) {
        var query = getDynamicFilterToListAllQuery(request);
        return pageService.toPage(query, page, Event.class);
    }

    protected Query getDynamicFilterToListAllQuery(EventFilterRequest filterRequest) {

        var query = new Query();

        addCriteriaIsToQuery(query, FIELD_NAME, filterRequest.getName());

        return query;
    }

    private void addCriteriaIsToQuery(Query query, String fieldName, Object criteriaField) {
        if (Objects.nonNull(criteriaField)) {
            var criteria = Criteria.where(fieldName).is(criteriaField);
            query.addCriteria(criteria);
        }
    }
}
