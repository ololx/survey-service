package org.project.sample.survey.service.service.filter;

import org.springframework.data.domain.Sort;

interface SortFactory {

    default Sort getSortable(String sortingFields, String sortingDirection) {
        return Sort.by(Sort.Direction.valueOf(sortingDirection), sortingFields);
    }
}
