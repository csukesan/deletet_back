package com.deletet.deletet3.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {

        final String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if(matcher.matches())
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
