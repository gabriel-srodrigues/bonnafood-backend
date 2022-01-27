package br.com.bonnafood.app.users.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private static final String PHONE_REGEX = "\\((\\d{2})\\) (\\d{4,5})-(\\d{4})";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (phone == null) return false;
        Matcher matcher = PHONE_PATTERN.matcher(phone);
        return matcher.find();
    }
}