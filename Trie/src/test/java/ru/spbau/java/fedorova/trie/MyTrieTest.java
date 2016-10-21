package ru.spbau.java.fedorova.trie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyTrieTest {
    MyTrie mt;
    @Before
    public void prepare() {
        mt = new MyTrie();
        mt.add("to");
        mt.add("tea");
        mt.add("ted");
        mt.add("ten");
        mt.add("inn");
        mt.add("AAA");
    }

    @Test
    public void add() throws Exception {
        assertTrue(mt.add("tea"));
        assertFalse(mt.add("AAB"));
        assertFalse(mt.add("AAC"));
        assertFalse(mt.add("INN"));
    }

    @Test
    public void contains() throws Exception {
        assertTrue(mt.contains("AAA"));
        assertFalse(mt.contains("aaa"));
        assertFalse(mt.contains("Ten"));
        assertTrue(mt.contains("ted"));
        assertTrue(mt.contains("ten"));
        assertTrue(mt.contains("to"));
        assertTrue(mt.contains("tea"));
    }

    @Test
    public void remove() throws Exception {
        MyTrie mmt = new MyTrie();
        mmt.add("Hello");
        assertTrue(mmt.remove("Hello"));
        assertEquals(0, mmt.size());
        assertTrue(mt.remove("AAA"));
        assertFalse(mt.remove("a"));
        assertFalse(mt.remove("Ten"));
        assertFalse(mt.remove("te"));
        assertTrue(mt.remove("tea"));
        assertFalse(mt.remove("in"));
    }

    @Test
    public void size() throws Exception {
        assertEquals(6, mt.size());
        mt.add("B");
        mt.add("D");
        assertEquals(8, mt.size());
        //assertEquals(true, mt.contains("ten"));//
        assertTrue(mt.remove("ten"));//
        //assertEquals(true, mt.contains("to"));//
        assertTrue(mt.remove("to"));//
        //assertEquals(true, mt.contains("tea"));
        assertTrue(mt.remove("tea"));
        //assertEquals(true, mt.contains("inn"));
        assertTrue(mt.remove("inn"));
        assertEquals(4, mt.size());
    }

    @Test
    public void howManyStartsWithPrefix() throws Exception {
        assertEquals(3, mt.howManyStartsWithPrefix("te"));
        assertEquals(0, mt.howManyStartsWithPrefix("B"));
        assertEquals(4, mt.howManyStartsWithPrefix("t"));
        assertEquals(1, mt.howManyStartsWithPrefix("inn"));
    }

}