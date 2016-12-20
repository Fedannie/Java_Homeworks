package functional;

import org.junit.Test;

import static org.junit.Assert.*;

public class Function2Test {
    @Test
    public void compose() throws Exception {
        Function2<String, String, String> concat = (x, y) -> x + y;
        Function1<String, Integer> cnt =  x -> x.length() - x.replace(" ", "").length();
        Function2<String, String, Integer> concatLength = concat.compose(cnt);
        assertEquals((Integer)9, concatLength.apply("Hello world.\n   ", "There are 9 spaces.  "));
    }

    @Test
    public void bind1() throws Exception {
        Function2<String, String, String> concat = (x, y) -> x + y;
        Function1<String, String> dr = concat.bind1("Hello, ");
        assertEquals("Hello, world!", dr.apply("world!"));
    }

    @Test
    public void bind2() throws Exception {
        Function2 <Integer, Integer, Integer> f = (x, y) -> x - y;
        Function1 <Integer, Integer> g = f.bind2(5);
        assertEquals(g.apply(0), new Integer(-5));
        assertEquals(g.apply(12), new Integer(7));
    }

    @Test
    public void curry() throws Exception {
        Function2 <Integer, Integer, Integer> f = (x, y) -> x + y;
        Function1 <Integer, Function1 <Integer, Integer> > g = f.curry();
        assertEquals(g.apply(3).apply(4), new Integer(7));
    }

}