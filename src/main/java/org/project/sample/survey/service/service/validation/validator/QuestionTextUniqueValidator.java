package org.project.sample.survey.service.service.validation.validator;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.project.sample.survey.service.model.payload.QuestionDetail;
import org.project.sample.survey.service.repository.QuestionRepository;
import org.project.sample.survey.service.service.validation.constraint.QuestionTextUnique;
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
public class QuestionTextUniqueValidator implements ConstraintValidator<QuestionTextUnique, QuestionDetail> {

    QuestionRepository questionRepository;

    @Override
    public void initialize(QuestionTextUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(QuestionDetail detail, ConstraintValidatorContext ctx) {

        if(detail.getText() == null) {
            return true;
        }

        return !questionRepository.existsByTextIgnoreCase(detail.getText());
    }
}
