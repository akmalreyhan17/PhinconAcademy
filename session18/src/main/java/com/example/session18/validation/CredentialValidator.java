package com.example.session18.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.session18.model.Credentials;
import com.example.session18.model.Order;
import com.example.session18.model.User;

public class CredentialValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz) || Order.class.equals(clazz);
    }

    // @Override
    // public void validate(Object target, Errors errors) {
    //     Credentials cred = (Credentials) target;

    //     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty",
    //             "Password is required");
    //     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.empty",
    //             "Confirm Password is required");

    //     // Cross-field validation: Check if password and confirmPassword match
    //     if (!cred.getPassword().equals(cred.getConfirmPassword())) {
    //         errors.rejectValue("confirmPassword", "passwords.not.match",
    //                 "Passwords do not match");
    //     }
    // }

    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order) target;

        // Cross-object validation: Check if user1 match user2
        if (areUserEqual(order.getAccountHolder(), order.getCustomer())) {
            errors.reject("useres.not.match", 
            "Billing and Shipping useres must be the same");
        }
    }

    private boolean areUserEqual(User user1, User user2) {
        if (user1 == null || user2 == null) {
            return false;
        }
        return user1.getName().equals(user2.getName());
    }

}
