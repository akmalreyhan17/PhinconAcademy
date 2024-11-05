package com.example.session18.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.session18.model.Account;
import com.example.session18.model.User;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        // Check if name is empty or null
        ValidationUtils.rejectIfEmptyOrWhitespace(
            errors, "name", "name.empty", "Name is required");

        // Check if age is within valid range
        if (user.getAge() < 18 || user.getAge() > 100) {
            errors.rejectValue("age", "age.invalid", "Age must be between 18 and 100");
        }

        // Check if email follows the correct pattern
        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "email.invalid", "Email is invalid");
        }
    }
    
}