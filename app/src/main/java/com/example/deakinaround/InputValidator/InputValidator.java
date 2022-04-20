package com.example.deakinaround.InputValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    private static final String PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})";

    public boolean validateLength(int minLength, String input){
        if (input.length() < minLength){
            return false;
        }
        return true;
    }

    public boolean validateStringMatch(String string1, String string2){
        return string1.equals(string2);
    }

    public boolean validateDeakinEmail(String email){
        email = email.toLowerCase();
        return email.substring(email.lastIndexOf("@")+1).equals("deakin.edu.au");
    }

    public boolean validatePasswordComplexity(String password){
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
