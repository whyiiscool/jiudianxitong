package com.example.hotel_personapp.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    public String getDynamicPassword(StringBuilder str) {
        // 6是验证码的位数一般为六位
        Pattern continuousNumberPattern = Pattern.compile("\\d{4}");
        Matcher m = continuousNumberPattern.matcher(str);
        String dynamicPassword = "";
        while (m.find()) {
            System.out.print(m.group());
            dynamicPassword = m.group();
        }

        return dynamicPassword;
    }
}
