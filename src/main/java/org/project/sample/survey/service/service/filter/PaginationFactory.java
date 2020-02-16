package org.project.sample.survey.service.service.filter;

import org.springframework.data.domain.PageRequest;

interface PaginationFactory {

    default PageRequest getPageable(Integer paginationLow, Integer paginationHight) {
        return PageRequest.of(paginationLow, paginationHight);
    }
}
