package base;

import org.junit.Test;

import java.util.function.Function;

public class FunctionTest {
    @Test
    public void testBiFunction() throws Exception {
        Function<Integer,Integer> function = e -> e * e;
    }
}
