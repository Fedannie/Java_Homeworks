package ru.spbau.java.fedorova.trie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 23.09.2016.
 * @author  Fedorova Anna
 */
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
        assertEquals(true, mt.add("tea"));
        assertEquals(false, mt.add("AAB"));
        assertEquals(false, mt.add("AAC"));
        assertEquals(false, mt.add("INN"));
    }

    @Test
    public void contains() throws Exception {
        assertEquals(true, mt.contains("AAA"));
        assertEquals(false, mt.contains("aaa"));
        assertEquals(false, mt.contains("Ten"));
        assertEquals(true, mt.contains("ted"));
        assertEquals(true, mt.contains("ten"));
        assertEquals(true, mt.contains("to"));
        assertEquals(true, mt.contains("tea"));
    }

    @Test
    public void remove() throws Exception {
        assertEquals(true, mt.remove("AAA"));
        assertEquals(false, mt.remove("a"));
        assertEquals(false, mt.remove("Ten"));
        assertEquals(false, mt.remove("te"));
        assertEquals(true, mt.remove("tea"));
        assertEquals(false, mt.remove("in"));
    }

    @Test
    public void size() throws Exception {
        assertEquals(6, mt.size());
        mt.add("B");
        mt.add("D");
        assertEquals(8, mt.size());
        //assertEquals(true, mt.contains("ten"));//
        assertEquals(true, mt.remove("ten"));//
        //assertEquals(true, mt.contains("to"));//
        assertEquals(true, mt.remove("to"));//
        //assertEquals(true, mt.contains("tea"));
        assertEquals(true, mt.remove("tea"));
        //assertEquals(true, mt.contains("inn"));
        assertEquals(true, mt.remove("inn"));
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