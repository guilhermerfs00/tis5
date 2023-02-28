package com.puc.ticketin.domain.mapper;

import com.puc.ticketin.api.response.PageResponse;
import com.puc.ticketin.domain.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageResponseMapper {
    public PageResponse<Event> eventToPageResponse(Page<Event> page) {
        PageResponse<Event> pageResponse = new PageResponse();
        pageResponse.setPageNumber(page.getPageable().getPageNumber());
        pageResponse.setPageSize(page.getPageable().getPageSize());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setNumberOfElements(page.getNumberOfElements());
        pageResponse.setContent(page.getContent());
        return pageResponse;
    }

}
