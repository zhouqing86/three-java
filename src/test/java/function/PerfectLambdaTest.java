package function;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PerfectLambdaTest {
    @Test
    public void testLambdaFindFirst() throws Exception {
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .stream()
                .map(num -> {
                    System.out.println("num="+num);
                    return num;
                })
                .filter(num -> num > 5)
                .findFirst();
    }
}