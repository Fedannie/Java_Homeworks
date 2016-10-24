package functional;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CollectionsTest {
    private ArrayList<Integer> squares;
    Iterable<String> stringNumbers;
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
        Predicate <String> notBig = x -> x.length() != 2;
        Iterable<String> filtered = Collections.filter(notBig, stringNumbers);
        ArrayList<String> ans = new ArrayList<String>();
        filtered.forEach(ans::add);
        assertEquals(13, ans.size());
        assertEquals("100", ans.get(3));
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
        Function2<String, String, String> concat = (x, y) -> y + x;
        String conc = Collections.foldl(stringNumbers, concat, "");
        String ans = "";
        for (int i = 1; i < 20; i++) {
            ans += ((Integer) (i * i)).toString();
        }
        assertEquals(ans, conc);
    }

    @Test
    public void foldr() throws Exception {
        Function2<String, String, String> concat = (x, y) -> x + y;
        String conc = Collections.foldr(stringNumbers, concat, "");
        String ans = "";
        for (int i = 1; i < 20; i++) {
            ans += ((Integer) (i * i)).toString();
        }
        assertEquals(ans, conc);
    }

}