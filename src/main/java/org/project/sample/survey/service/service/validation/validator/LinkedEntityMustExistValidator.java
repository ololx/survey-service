package org.project.sample.survey.service.service.validation.validator;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.payload.QuestionDetail;
import org.project.sample.survey.service.repository.SurveyRepository;
import org.project.sample.survey.service.service.validation.constraint.LinkedEntityMustExist;
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
public class LinkedEntityMustExistValidator implements ConstraintValidator<LinkedEntityMustExist, QuestionDetail> {

    SurveyRepository surveyRepository;

    @Override
    public void initialize(LinkedEntityMustExist constraintAnnotation) {

    }

    @Override
    public boolean isValid(QuestionDetail detail, ConstraintValidatorContext ctx) {

        if(detail.getSurveyUid() == null) {
            return true;
        }

        return surveyRepository.existsByUid(detail.getSurveyUid());
    }
}
