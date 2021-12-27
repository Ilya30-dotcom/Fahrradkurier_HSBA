package de.hsba.bi.fahrradkurier.common.validator.Username;

import de.hsba.bi.fahrradkurier.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UsernameValidator implements ConstraintValidator<ValidateUsername, String> {
    @Autowired
    UserService userService;

    @Override
    public void initialize(ValidateUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userService.isUserNameUnique(username);
    }
}
