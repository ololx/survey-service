package org.project.sample.survey.service.service.filter;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface FilterFactory<T extends FilterFactory.SearchRequest> extends PaginationFactory, SortFactory {

    interface SearchRequest {

        boolean isSortable();

        boolean isPageable();

        String getSortingFields();

        String getSortingDirection();

        Integer getPaginationLow();

        Integer getPaginationHight();
    }

    default PageRequest getPageable(Integer paginationLow, Integer paginationHight,
                                    String sortingFields, String sortingDirection) {
        return PageRequest.of(paginationLow, paginationHight, getSortable(sortingFields, sortingDirection));
    }

    default Pageable getPageable(T request) {

        if(!request.isPageable()) {
            return Pageable.unpaged();
        }

        if(!request.isSortable()) {
            return getPageable(request.getPaginationLow(), request.getPaginationHight());
        } else {
            return getPageable(
                    request.getPaginationLow(),
                    request.getPaginationHight(),
                    request.getSortingFields(),
                    request.getSortingDirection()
            );
        }
    }

    default Sort getSortable(T request) {

        if(!request.isSortable()) {
            return Sort.unsorted();
        }

        return getSortable(request.getSortingFields(), request.getSortingDirection());
    }
}
