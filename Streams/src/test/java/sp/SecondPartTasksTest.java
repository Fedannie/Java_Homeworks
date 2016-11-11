package sp;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static sp.SecondPartTasks.*;

public class SecondPartTasksTest {
    @Test
    public void testFindQuotes() {
        List<String> paths = Arrays.asList(getFile("first.txt"), getFile("second.txt"));
        assertEquals(
                findQuotes(paths, "first"),
                Arrays.asList("first line", "first empty line", "bla-la-la-first-la-la-la", "the very first line", "not first"));
        assertEquals(
                findQuotes(paths, "bla"),
                Arrays.asList("blala", "bla-la-la-first-la-la-la"));
        assertEquals(
                findQuotes(paths, "line"),
                Arrays.asList("first line", "second line", "first empty line", "second empty line", "the very first line", "the very second line"));
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, piDividedBy4(), 1e-4);
    }

    @Test
    public void testFindPrinter() {
        String author1 = "Author number 1";
        List<String> author1Comp = Arrays.asList("A short story 1", "A short story 2", "Not very short story 3");
        String author2 = "Author number 2";
        List<String> author2Comp = Arrays.asList("A long-long-long novel 1", "A short story 2", "Not very short story 3");
        String author3 = "Author number 3";
        List<String> author3Comp = Arrays.asList("First long-long-long interesting novel", "A song");
        Map<String, List<String>> AUTHOR_MAP = ImmutableMap.of(author1, author1Comp, author2, author2Comp, author3, author3Comp);

        assertEquals(
                findPrinter(AUTHOR_MAP),
                author2);
        assertEquals(
                findPrinter(Collections.emptyMap()),
                null);
    }

    @Test
    public void testCalculateGlobalOrder() {
        Map<String, Integer> firstOrder = ImmutableMap.of("Ball", new Integer(100), "Bike", new Integer(50), "Snowboard", new Integer(20));
        Map<String, Integer> secondOrder = ImmutableMap.of("Ball", new Integer(20), "Bike", new Integer(30), "Snowboard", new Integer(10));
        Map<String, Integer> thirdOrder = ImmutableMap.of("Ball", new Integer(109), "Bike", new Integer(60), "Snowboard", new Integer(15));
        List<Map<String, Integer>> orders = Arrays.asList(firstOrder, secondOrder, thirdOrder);
        Map<String, Integer> result = ImmutableMap.of("Ball", new Integer(229), "Bike", new Integer(140), "Snowboard", new Integer(45));
        assertEquals(
                calculateGlobalOrder(orders),
                result);
    }

    private static final boolean IS_WINDOWS = System.getProperty( "os.name" ).contains( "indow" );

    private static String getFile(String name) {
        URL resource = ClassLoader.getSystemClassLoader().getResource(name);
        if (resource == null) {
            return null;
        }
        return IS_WINDOWS ? resource.getFile().substring(1) : resource.getFile();
    }

}