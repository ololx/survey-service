package org.project.sample.survey.service.service.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.entity.Question;
import org.project.sample.survey.service.model.payload.QuestionDetail;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionSpecificationFactory extends CustomSpecificationBuilder<Question> {

    public Specification<Question> getSpecification(QuestionDetail request) {
        this.withMore("uid", 0)
                    .withEquals("uid", request.getUid())
                .withEquals("surveyUid", request.getSurveyUid())
                .withEquals("uidRoute", request.getNumber())
                .withEquals("text", request.getText());

        return this.build();
    }
}
