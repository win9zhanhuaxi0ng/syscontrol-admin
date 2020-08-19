package com.demofactory.syscontrol.controller.admin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public boolean checkRegex(String regexParam,String checkParam){
        Pattern pattern = Pattern.compile(regexParam);
        Matcher matcher = pattern.matcher(checkParam);
        return matcher.matches();
    }
}
