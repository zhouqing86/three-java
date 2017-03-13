package medium;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class Java8Test {
    public static String keyString;
    public static int sum;

    @Before
    public void setUp() throws Exception {
        keyString = "";
        sum = 0;
    }

    @Test
    public void testForEachMap() throws Exception {
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);
        items.forEach((k,v) -> {
            Java8Test.keyString += k;
            Java8Test.sum += v;
        });
        assertEquals("ABCDEF", Java8Test.keyString);
        assertEquals(210, Java8Test.sum);
    }

    @Test
    public void testFunctionalInterface() throws Exception {
        MyRunnalbe runnalbe = () -> "Hello World!";
        assertEquals("Hello World!", runnalbe.hello());
    }

    private static boolean isPrime(int number) {
        return number > 1
                && IntStream.range(2, number).noneMatch(
                index -> number % index == 0);
    }

    @Test
    public void testIsPrime() throws Exception {
        assertTrue(isPrime(5));
        assertFalse(isPrime(22));
    }

    private static boolean isPrimeWithMyPredicate(int number) {
        IntPredicate isDivisible = (index) -> number % index == 0;
        return number > 1
                && IntStream.range(2, number).noneMatch(isDivisible);
    }

    @Test
    public void testIsPrimeWithPredicate() throws Exception {
        assertTrue(isPrime(5));
        assertFalse(isPrime(22));
    }

    @Test
    public void TestStreamFromList() throws Exception {
        List<Integer> ints = Lists.newArrayList(1,3,5,7,8,9);
        List<Integer> filterInts = ints.stream().filter(n -> n % 2 != 0).collect(Collectors.toList());
        assertEquals(5, filterInts.size());
        assertFalse(filterInts.contains(8));
    }

    @Test
    public void testInitAStream() throws Exception {
        Stream<Integer> stream = Stream.of(1,2,3,4);
        stream = Stream.of(new Integer[]{1,2,3,4});
        Stream.generate(() -> {return "abc";});
        Stream.iterate("abc", (i) -> i);
        LongStream is = Arrays.stream(new long[]{1,2,3,4});
        IntStream is2 = "abc".chars();
    }
}

@FunctionalInterface
interface MyPredicate {
    boolean method(Integer index, Integer number);
}

@FunctionalInterface
interface MyRunnalbe {
    String hello();
}