package org.project.sample.survey.service.service.validation.validator;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.payload.SurveyDetail;
import org.project.sample.survey.service.service.validation.constraint.FinishDateMustBeGratherThan;
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
public class FinishDateMustBeGratherThanValidator implements ConstraintValidator<FinishDateMustBeGratherThan, SurveyDetail> {

    @Override
    public void initialize(FinishDateMustBeGratherThan constraintAnnotation) {

    }

    @Override
    public boolean isValid(SurveyDetail detail, ConstraintValidatorContext ctx) {

        if(detail.getStartDate() == null
                || detail.getFinishDate() == null) {
            return true;
        }

        return (detail.getFinishDate().compareTo(detail.getStartDate()) > 0);
    }
}
