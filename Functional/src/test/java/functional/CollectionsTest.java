package functional;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CollectionsTest {
    private ArrayList<Integer> squares;
    private Iterable<String> stringNumbers;
    @Before
    public void make() {
        squares = new ArrayList<>();
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

        Function1<Object, Integer> toHashCode = Object::hashCode;
        ArrayList<Integer> res = Collections.map(toHashCode, squares);
        for (int i = 0; i < squares.size(); i++) {
            assertEquals(res.get(i), (Integer) new Integer((i + 1) * (i + 1)).hashCode());
        }
    }

    @Test
    public void filter() throws Exception {
        ArrayList <Integer> a = new ArrayList<>(Arrays.asList(1, 2, 0, 8, 11));
        Predicate <Integer> f = x -> x % 2 == 0;
        ArrayList <Integer> b = Collections.filter(f, a);
        assertEquals(b.size(), 3);
        assertEquals((long)b.get(0), 2);
        assertEquals((long)b.get(1), 0);
        assertEquals((long)b.get(2), 8);
    }

    @Test
    public void takeWhile() throws Exception {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ArrayList<Integer> takenArray = Collections.takeWhile(xw -> xw <= 5, array);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), takenArray);
    }

    @Test
    public void takeUnless() throws Exception {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ArrayList<Integer> takenArray = Collections.takeUnless(xw -> xw > 5, array);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), takenArray);
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