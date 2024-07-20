package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.text.MessageFormat;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {

    private static Stream<Arguments> enhanceStringArguments() {
        return Stream.of(
                Arguments.of(1, 2)
        );
    }

    @Test
    public void testEnhanceString() {
        int id = 1001;
        String name = "John Doe";
        int age = 34;

        String textBlock = """
                {
                  "id": %d,
                  "name": "%s",
                  "age": %d
                }
                """;

        String formattedTextBlock = textBlock.formatted(id, name, age);
        String expected = """
                {
                  "id": 1001,
                  "name": "John Doe",
                  "age": 34
                }
                """;
        assertEquals(expected, formattedTextBlock);

        Object[] formatArguments = {
                1001,     // id
                "John Doe",  // name
                34       // age
        };
        formattedTextBlock = textBlock.formatted(formatArguments);
        assertEquals(expected, formattedTextBlock);

        String json = """
                '{'
                  "id":{0, number, #},
                  "name": "{1}",
                  "age":{2, number, #}
                '}'
                """;
        formattedTextBlock = MessageFormat.format(json, formatArguments);
        assertEquals(expected, formattedTextBlock);
    }
}