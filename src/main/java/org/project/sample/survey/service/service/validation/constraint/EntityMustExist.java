package org.project.sample.survey.service.service.validation.constraint;

import org.project.sample.survey.service.service.validation.validator.EntityMustExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EntityMustExistValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityMustExist {

    String message() default "Указана не зарегистрированная в системе сущность";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
