package org.project.sample.survey.service.service.validation.constraint;

import org.project.sample.survey.service.service.validation.validator.FinishDateMustBeGratherThanValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FinishDateMustBeGratherThanValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FinishDateMustBeGratherThan {

    String message() default "Дата окончания не может быть меньше даты начала";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
