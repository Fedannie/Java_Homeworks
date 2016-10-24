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
        Predicate <Integer> positive = x -> (x > 0);
        Predicate <Integer> negative = positive.not();
        assertFalse(negative.apply(5));
        assertTrue(negative.apply(-10));
        assertFalse(negative.apply(30));
        assertTrue(negative.apply(0));
    }

}