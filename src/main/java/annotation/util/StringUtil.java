package annotation.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String camelCaseToUnderline(String camel) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(camel);
        while(matcher.find()){
            String w = matcher.group().trim();
            camel = camel.replace(w,"_"+w);
        }
        return camel.toUpperCase();
    }
}
