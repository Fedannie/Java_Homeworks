package ru.spbau.java.fedorova.first.list;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.java.fedorova.first.list.node.Node;

import static org.junit.Assert.*;

/**
 * Created on 19.09.2016.
 * @author Fedorova Anna
 */
public class LinkedListTest {
    LinkedList ll;
    @Before
    public void prepare() throws Exception {
        ll = new LinkedList();
        ll.add("First", "1");
        ll.add("Second", "2");
        ll.add("Third", "3");
        ll.remove("Third");
        ll.add("Forth", "4");
        ll.add("Fifth", "5");
        ll.add("Sixth", "6");
        ll.remove("First");
        ll.print();
    }
    @Test
    public void remove() throws Exception {
        assertEquals("6", ll.remove("Sixth"));
        assertEquals("4", ll.remove("Forth"));
        assertEquals(null, ll.remove("Sixth"));
        assertEquals(null, ll.remove("Smth Else"));
    }

    @Test
    public void find() throws Exception {
        assertEquals("6", ll.find("Sixth").getValue());
        assertEquals("5", ll.find("Fifth").getValue());
        assertEquals("2", ll.find("Second").getValue());
        assertEquals("4", ll.find("Forth").getValue());
        assertEquals(null, ll.find("First"));
    }
}