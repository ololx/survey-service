package org.project.sample.survey.service.service.filter;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.payload.SearchFilter;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor
@Service
public final class QuestionFilterFactory extends QuestionSpecificationFactory implements FilterFactory<SearchFilter> {
}
