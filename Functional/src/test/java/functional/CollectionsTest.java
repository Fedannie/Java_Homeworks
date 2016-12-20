package functional;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CollectionsTest {
    private ArrayList<Integer> squares;
    private Iterable<String> stringNumbers;
    @Before
    public void make() {
        squares = new ArrayList<Integer>();
        for (int i = 1; i < 20; i++) {
                squares.add(i * i);
        }
        stringNumbers = Collections.map(Object::toString, squares);
    }
    @Test
    public void map() throws Exception {
        ArrayList<String> ans = new ArrayList<>();
        stringNumbers.forEach(ans::add);
        assertEquals(19, ans.size());
        for (int i = 1; i < 20; i++) {
            assertEquals(((Integer)(i * i)).toString(), ans.get(i - 1));
        }
    }

    @Test
    public void filter() throws Exception {
        ArrayList <Integer> a = new ArrayList<Integer>(Arrays.asList(1, 2, 0, 8, 11));
        Predicate <Integer> f = x -> x % 2 == 0;
        ArrayList <Integer> b = Collections.filter(f, a);
        assertEquals(b.size(), 3);
        assertEquals((long)b.get(0), 2);
        assertEquals((long)b.get(1), 0);
        assertEquals((long)b.get(2), 8);
    }

    @Test
    public void takeWhile() throws Exception {
        Predicate <String> mod5 = x -> !x.contains("5");
        Iterable<String> taken = Collections.takeWhile(mod5, stringNumbers);
        ArrayList<String> ans = new ArrayList<String>();
        taken.forEach(ans::add);
        assertEquals(4, ans.size());
        assertEquals("16", ans.get(3));
    }

    @Test
    public void takeUnless() throws Exception {
        Predicate <String> mod5 = x -> x.contains("5");
        Iterable<String> taken = Collections.takeUnless(mod5, stringNumbers);
        ArrayList<String> ans = new ArrayList<String>();
        taken.forEach(ans::add);
        assertEquals(4, ans.size());
        assertEquals("16", ans.get(3));
    }

    @Test
    public void foldl() throws Exception {
        Function2<Number, String, String> f = (x1, x2) -> "(" + x1 + " + " + x2 + ")";
        assertEquals("(5 + (4 + (3 + (2 + (1 + 0)))))", Collections.foldl(Arrays.asList(1, 2, 3, 4, 5), f, "0"));
    }

    @Test
    public void foldr() throws Exception {
        Function2<Number, String, String> f = (x1, x2) -> "(" + x1 + " + " + x2 + ")";
        assertEquals("(1 + (2 + (3 + (4 + (5 + 0)))))", Collections.foldr(Arrays.asList(1, 2, 3, 4, 5), f, "0"));
    }

}