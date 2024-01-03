package com.young.zhang.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PreCheckString {
    public String checkLongthLess(String string, int longth) {
        if (string == null || longth <= 0) {
            return "Invalid verification conditions";
        }else {
            if (string.length() < longth) {
                return "The length of parameter " + string + " is less than " + longth + ", please check and retry.";
            }
        }
        return "";
    }
    public String checkLongthGreater(String string, int longth) {
        if (string == null || longth <= 0) {
            return "Invalid verification conditions";
        }else {
            if (string.length() > longth) {
                return "The length of parameter " + string + " is greater than " + longth + ", please check and retry.";
            }
        }
        return "";
    }

}
