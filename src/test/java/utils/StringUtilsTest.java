package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {
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
        assertEquals("""
                {
                  "id": 1001,
                  "name": "John Doe",
                  "age": 34
                }
                """, formattedTextBlock);
    }
}