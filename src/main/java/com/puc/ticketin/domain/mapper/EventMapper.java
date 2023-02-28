package com.puc.ticketin.domain.mapper;


import com.puc.ticketin.api.response.EventResponse;
import com.puc.ticketin.api.response.PageResponse;
import com.puc.ticketin.domain.bo.EventBO;
import com.puc.ticketin.domain.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventBO entityToBo(Event entity);

    PageResponse<EventResponse> mapBoToResponsePage(PageResponse<EventBO> boPageResponse);

    PageResponse<EventBO> mapEntityToPageBo(PageResponse<Event> entityPageResponse);
}
