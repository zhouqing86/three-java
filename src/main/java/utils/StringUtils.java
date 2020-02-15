package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringUtils {
    public static List<String> genFourCharStrings(String str) {
        Objects.requireNonNull(str);

        if (str.length() <= 4 ) {
            return Arrays.asList(str);
        }

        String [] allChars = str.substring(3, str.length()).split("");
        String prefix = str.substring(0, 3);

        List<String> stringList = Arrays.asList(allChars)
                .stream()
                .map(element -> prefix + element)
                .collect(Collectors.toList());

        return stringList;
    }
}
