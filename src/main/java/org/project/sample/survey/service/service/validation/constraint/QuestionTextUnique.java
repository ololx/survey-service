package org.project.sample.survey.service.service.validation.constraint;

import org.project.sample.survey.service.service.validation.validator.QuestionTextUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuestionTextUniqueValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuestionTextUnique {

    String message() default "Тексты вопросов повторяются в рамках одного опроса";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
