package fr.ysaintmartin.budgetzen.utils.annotation;

import fr.ysaintmartin.budgetzen.utils.validator.EnumValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValue {

    String message() default "Invalid enum value";

    Class<? extends Enum<?>> enumType();
}
