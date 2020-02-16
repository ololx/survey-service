package org.project.sample.survey.service.service.validation.validator;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.payload.QuestionDetail;
import org.project.sample.survey.service.model.payload.SurveyDetail;
import org.project.sample.survey.service.model.payload.SurveyServiceDetail;
import org.project.sample.survey.service.repository.QuestionRepository;
import org.project.sample.survey.service.repository.SurveyRepository;
import org.project.sample.survey.service.service.validation.constraint.EntityMustExist;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
@Component
public class EntityMustExistValidator implements ConstraintValidator<EntityMustExist, SurveyServiceDetail> {

    SurveyRepository surveyRepository;

    QuestionRepository questionRepository;

    @Override
    public void initialize(EntityMustExist constraintAnnotation) {

    }

    @Override
    public boolean isValid(SurveyServiceDetail detail, ConstraintValidatorContext ctx) {

        if(detail.getUid() == null) {
            return true;
        }

        if(detail instanceof QuestionDetail) {
            return isValid((QuestionDetail) detail);
        } else if(detail instanceof SurveyDetail) {
            return isValid((SurveyDetail) detail);
        }

        return true;
    }

    public boolean isValid(QuestionDetail detail) {
        return questionRepository.existsByUid(detail.getUid());
    }

    public boolean isValid(SurveyDetail detail) {
        return surveyRepository.existsByUid(detail.getUid());
    }
}
