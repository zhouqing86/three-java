package medium;


import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class GuavaTest {
    @Test
    public void testOptional() throws Exception {
        Optional<Integer> optInt = Optional.of(5);
        assertEquals(true, optInt.isPresent());
        assertEquals(new Integer(5), optInt.get());
    }

    @Test
    public void testStaticOrdering(){
        List list = Lists.newArrayList("peida","jerry","harry","eva","jhon","neron");

        Ordering naturalOrdering = Ordering.natural();
        Ordering usingToStringOrdering = Ordering.usingToString();

        assertEquals("[eva, harry, jerry, jhon, neron, peida]", naturalOrdering.sortedCopy(list).toString());
        assertTrue(naturalOrdering.isOrdered(naturalOrdering.sortedCopy(list)));
        assertFalse(naturalOrdering.reverse().isOrdered(naturalOrdering.sortedCopy(list)));
        assertEquals("[eva, harry, jerry, jhon, neron, peida]", usingToStringOrdering.sortedCopy(list).toString());

        assertEquals("[eva, harry, jerry]", naturalOrdering.leastOf(list, 3).toString());
        assertEquals("[peida, neron, jhon]", naturalOrdering.greatestOf(list, 3).toString());

    }

    @Test
    public void testCreateCustomizeOrdering() throws Exception {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        List list = Lists.newArrayList("peida","jerry","harry","eva","jhon","neron");
        assertEquals("[eva, jhon, peida, jerry, harry, neron]", byLengthOrdering.sortedCopy(list).toString());
        assertEquals("[peida, jerry, harry, neron, jhon, eva]", byLengthOrdering.reverse().sortedCopy(list).toString());

        Ordering<String> compoundOrdering = byLengthOrdering.compound(Ordering.usingToString());
        assertEquals("[eva, jhon, harry, jerry, neron, peida]", compoundOrdering.sortedCopy(list).toString());

    }

    @Test
    public void testOrderingOnResultOf() throws Exception {
        Ordering<Foo> ordering = Ordering.natural().onResultOf(new Function<Foo, String>() {
            @Override
            public String apply( Foo input) {
                return input.sortBy;
            }
        });
        List<Foo> foos = Lists.newArrayList(new Foo("b",1), new Foo("c",2), new Foo("a",3));
        List<Foo> fooList = ordering.sortedCopy(foos);
        assertEquals("a", fooList.get(0).sortBy);
        assertEquals("b", fooList.get(1).sortBy);
        assertEquals("c", fooList.get(2).sortBy);
    }

    @Test
    public void testObjectsEqualsMethod() throws Exception {
        assertTrue(Objects.equal("a", "a"));
        assertFalse(Objects.equal(null, "a"));
    }

    @Test
    public void testMultiSet() throws Exception {
        Multiset multiset = HashMultiset.create();
        String sentences = "this is a story, there is a good girl in the story.";
        Iterable<String> words = Splitter.onPattern("[^a-z]{1,}").omitEmptyStrings().trimResults().split(sentences);
        for (String word : words) {
            multiset.add(word);
        }
        assertEquals(2, multiset.count("story"));
        assertEquals(2, multiset.count("is"));

    }

    @Test
    public void testBiMap() throws Exception {
        BiMap<String,String> weekNameMap = HashBiMap.create();
        weekNameMap.put("星期一","Monday");
        weekNameMap.put("星期二","Tuesday");
        weekNameMap.put("星期三","Wednesday");
        weekNameMap.put("星期四","Thursday");
        weekNameMap.put("星期五","Friday");
        weekNameMap.put("星期六","Saturday");
        weekNameMap.put("星期日","Sunday");
        assertEquals("Sunday",weekNameMap.get("星期日"));
        assertEquals("星期日", weekNameMap.inverse().get("Sunday"));
    }

    @Test
    public void testMultiMap() throws Exception {
        Multimap<String, String> myMultimap = ArrayListMultimap.create();
        myMultimap.put("Fruits", "Bannana");
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");
        assertEquals(4, myMultimap.size());
        assertEquals(3, myMultimap.get("Fruits").size());
        assertEquals(1, myMultimap.get("Vegetables").size());
        myMultimap.remove("Fruits","Pear");
        assertEquals(2, myMultimap.get("Fruits").size());
        myMultimap.removeAll("Fruits");
        assertEquals(0, myMultimap.get("Fruits").size());
    }

    @Test
    public void testGuavaTable() throws Exception {
        Table<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1,2,"a");
        table.put(2,3,"b");
        table.put(3,4,"c");
        assertEquals(3, table.size());
        assertEquals("a", table.row(1).get(2));
    }

    @Test
    public void testIterators() throws Exception {
        List<String> list = Lists.newArrayList("Apple","Pear","Peach","Banana");
        Predicate<String> condition = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("P");
            }
        };
        boolean allIsStartsWithP = Iterators.all(list.iterator(), condition);
        assertFalse(allIsStartsWithP);
        assertEquals("Peach", Iterators.get(list.iterator(),2));
        assertEquals("Pear", Iterators.filter(list.iterator(),condition).next());
        assertEquals("Pear", Iterators.find(list.iterator(),condition));
        Iterator<Integer> countIterator = Iterators.transform(list.iterator(), new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        });
        assertEquals(new Integer(5), countIterator.next());
    }
}

class Foo {
    String sortBy;
    int notSortBy;
    public Foo(String sortBy, int notSortBy) {
        this.sortBy = sortBy;
    }
}