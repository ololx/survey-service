package org.project.sample.survey.service.service.validation.constraint;

import org.project.sample.survey.service.service.validation.validator.LinkedEntityMustExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LinkedEntityMustExistValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LinkedEntityMustExist {

    String message() default "Указана не зарегистрированная в системе сущность";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
