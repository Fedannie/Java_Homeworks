package functional;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {
    @Test
    public void or() throws Exception {
        Predicate <Integer> mod10 = x -> (x % 10) == 0;
        Predicate <Integer> positive = x -> (x > 0);
        Predicate <Integer> oneOrAnother = mod10.or(positive);
        assertFalse(oneOrAnother.apply(-5));
        assertTrue(oneOrAnother.apply(-10));
        assertTrue(oneOrAnother.apply(30));
        assertTrue(oneOrAnother.apply(1));
    }

    @Test
    public void and() throws Exception {
        Predicate <Integer> mod10 = x -> (x % 10) == 0;
        Predicate <Integer> positive = x -> (x > 0);
        Predicate <Integer> both = mod10.and(positive);
        assertFalse(both.apply(5));
        assertFalse(both.apply(-10));
        assertTrue(both.apply(30));
        assertFalse(both.apply(1));
    }

    @Test
    public void not() throws Exception {
        Predicate <Integer> p1 = x -> (x > 1);
        Predicate <Integer> p2 = x -> (x < -1);
        Predicate <Integer> p3= p1.or(p2);
        assertTrue(p3.apply(4));
        assertTrue(p3.apply(-2));
        assertFalse(p3.apply(-1));
        Predicate <Integer> p4 = x -> x % 2 == 1;
        Predicate <Integer> p5 = p4.and(p1);
        assertFalse(p5.apply(4));
        assertTrue(p5.apply(3));
        assertFalse(p5.apply(-3));

        Predicate <Integer> p6 = p5.not();
        assertTrue(p6.apply(4));
        assertFalse(p6.apply(3));
        Predicate <Object> p7 = Predicate.ALWAYS_FALSE;
        assertFalse(p7.apply(0));
        p7 = Predicate.ALWAYS_TRUE;
        assertTrue(p7.apply(0));
    }

}