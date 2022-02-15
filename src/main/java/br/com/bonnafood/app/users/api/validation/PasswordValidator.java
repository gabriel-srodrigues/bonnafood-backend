package br.com.bonnafood.app.users.api.validation;

import br.com.bonnasys.core.i18n.I18nUtils;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String MESSAGE_PATTERN = I18nUtils.getText("password_message_validator", Locale.ENGLISH);
    private Pattern pattern;

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        ((ConstraintValidatorContextImpl) constraintValidatorContext)
                .addMessageParameter("passwordValidation", MESSAGE_PATTERN);

        if (password != null) {
            Matcher matcher = pattern.matcher(password);
            return matcher.find();
        }
        return true;
    }
}
