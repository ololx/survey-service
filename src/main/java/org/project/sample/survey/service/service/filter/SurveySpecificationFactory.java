package org.project.sample.survey.service.service.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.entity.Survey;
import org.project.sample.survey.service.model.payload.SurveyDetail;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SurveySpecificationFactory extends CustomSpecificationBuilder<Survey> {

    public Specification<Survey> getSpecification(SurveyDetail request) {
        this.withMore("uid", 0)
                    .withEquals("uid", request.getUid())
                .withEquals("name", request.getName())
                .withEquals("startDate", request.getStartDate())
                .withEquals("finishDate", request.getFinishDate())
                .withEquals("relevance", request.getRelevance());

        return this.build();
    }
}
