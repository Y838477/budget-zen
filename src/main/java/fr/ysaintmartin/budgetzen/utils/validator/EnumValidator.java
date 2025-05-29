package fr.ysaintmartin.budgetzen.utils.validator;

import fr.ysaintmartin.budgetzen.utils.annotation.EnumValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<EnumValue, String> {

    private Class<? extends Enum<?>> enumType;

    @Override
    public void initialize(EnumValue value) {
        enumType = value.enumType();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return Arrays.stream(enumType.getEnumConstants())
                .anyMatch(enumValue -> value.equalsIgnoreCase(enumValue.name()));

    }
}
