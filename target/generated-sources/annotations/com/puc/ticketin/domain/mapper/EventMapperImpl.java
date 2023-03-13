package com.puc.ticketin.domain.mapper;

import com.puc.ticketin.api.response.EventResponse;
import com.puc.ticketin.api.response.PageResponse;
import com.puc.ticketin.domain.bo.EventBO;
import com.puc.ticketin.domain.entity.Event;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-13T18:42:11-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventBO entityToBo(Event entity) {
        if ( entity == null ) {
            return null;
        }

        EventBO eventBO = new EventBO();

        eventBO.setId( entity.getId() );
        eventBO.setName( entity.getName() );
        eventBO.setCreatedDate( entity.getCreatedDate() );

        return eventBO;
    }

    @Override
    public PageResponse<EventResponse> mapBoToResponsePage(PageResponse<EventBO> boPageResponse) {
        if ( boPageResponse == null ) {
            return null;
        }

        PageResponse<EventResponse> pageResponse = new PageResponse<EventResponse>();

        pageResponse.setPageNumber( boPageResponse.getPageNumber() );
        pageResponse.setPageSize( boPageResponse.getPageSize() );
        pageResponse.setTotalPages( boPageResponse.getTotalPages() );
        pageResponse.setTotalElements( boPageResponse.getTotalElements() );
        pageResponse.setNumberOfElements( boPageResponse.getNumberOfElements() );
        pageResponse.setContent( eventBOListToEventResponseList( boPageResponse.getContent() ) );

        return pageResponse;
    }

    @Override
    public PageResponse<EventBO> mapEntityToPageBo(PageResponse<Event> entityPageResponse) {
        if ( entityPageResponse == null ) {
            return null;
        }

        PageResponse<EventBO> pageResponse = new PageResponse<EventBO>();

        pageResponse.setPageNumber( entityPageResponse.getPageNumber() );
        pageResponse.setPageSize( entityPageResponse.getPageSize() );
        pageResponse.setTotalPages( entityPageResponse.getTotalPages() );
        pageResponse.setTotalElements( entityPageResponse.getTotalElements() );
        pageResponse.setNumberOfElements( entityPageResponse.getNumberOfElements() );
        pageResponse.setContent( eventListToEventBOList( entityPageResponse.getContent() ) );

        return pageResponse;
    }

    protected EventResponse eventBOToEventResponse(EventBO eventBO) {
        if ( eventBO == null ) {
            return null;
        }

        EventResponse eventResponse = new EventResponse();

        eventResponse.setName( eventBO.getName() );
        eventResponse.setCreatedDate( eventBO.getCreatedDate() );

        return eventResponse;
    }

    protected List<EventResponse> eventBOListToEventResponseList(List<EventBO> list) {
        if ( list == null ) {
            return null;
        }

        List<EventResponse> list1 = new ArrayList<EventResponse>( list.size() );
        for ( EventBO eventBO : list ) {
            list1.add( eventBOToEventResponse( eventBO ) );
        }

        return list1;
    }

    protected List<EventBO> eventListToEventBOList(List<Event> list) {
        if ( list == null ) {
            return null;
        }

        List<EventBO> list1 = new ArrayList<EventBO>( list.size() );
        for ( Event event : list ) {
            list1.add( entityToBo( event ) );
        }

        return list1;
    }
}
