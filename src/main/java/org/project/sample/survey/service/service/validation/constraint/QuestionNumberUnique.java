package org.project.sample.survey.service.service.validation.constraint;

import org.project.sample.survey.service.service.validation.validator.QuestionNumberUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuestionNumberUniqueValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuestionNumberUnique {

    String message() default "Номера вопросов повторяются в рамках одного опроса";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
