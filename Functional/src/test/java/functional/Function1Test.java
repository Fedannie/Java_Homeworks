package functional;

import org.junit.Test;
import static org.junit.Assert.*;

public class Function1Test {
    @Test
    public void compose() throws Exception {
        Function1<Integer, Integer> multfive = x -> 5 * x;
        Function1<Integer, Integer> plusthree = x -> x + 3;
        Function1<Integer, Integer> f = plusthree.compose(multfive);
        assertEquals((Integer) 40, f.apply(5));
        assertEquals((Integer) 0, f.apply(-3));
    }

}