package ru.spbau.java.fedorova.first;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 371 on 19.09.2016.
 */
public class HashMapTest {
    HashMap hm;
    @Before
    public void prepare() throws Exception {
        hm = new HashMap();
        hm.put("First", "1");
        hm.put("Second", "2");
        hm.put("Third", "3");
        hm.put("Forth", "4");
    }

    @Test
    public void size() throws Exception {
        assertEquals(4, hm.size());
        hm.remove("Third");
        hm.put("Forth", "5");
        assertEquals(3, hm.size());
        hm.put("Fifth", "5");
        assertEquals(4, hm.size());
        hm.clear();
        assertEquals(0, hm.size());
    }

    @Test
    public void contains() throws Exception {
        assertTrue(hm.contains("First"));
        assertTrue(hm.contains("Forth"));
        assertFalse(hm.contains("Smth"));
    }

    @Test
    public void get() throws Exception {
        assertNull(hm.get("Fifth"));
        assertEquals("1", hm.get("First"));
        assertEquals("3", hm.get("Third"));
        assertNull(hm.get("Smth"));
    }

    @Test
    public void put() throws Exception {
        assertEquals(null, hm.put("Sixth", "6"));
        assertEquals("3", hm.put("Third", "10"));
        assertEquals("10", hm.put("Third", "3"));
        assertNull(hm.put("Tenth", "10"));
    }

    @Test
    public void remove() throws Exception {
        hm.put("Tenth", "10");
        assertEquals("10", hm.remove("Tenth"));
        assertNull(hm.remove("Smth"));
        assertEquals("1", hm.remove("First"));
    }

}