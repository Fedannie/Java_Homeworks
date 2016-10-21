package ru.spbau.java.fedorova.first.list;
import ru.spbau.java.fedorova.first.list.node.Node;

/**
 * Created on 17.09.2016.
 * @author Fedorova Anna
 */

public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        size = 0;
    }

    public Node getHead() {
        return head;
    }

    public void add(String key, String value) {
        Node a = new Node(key, value);
        if (tail == null) {
            head = a;
            tail = a;
        } else {
            a.setNext(head);
            head = a;
        }
        size++;
    }

    public String remove(String key) {
        String ans;
        if (size == 0){
            return null;
        }
        if (head.getKey().equals(key)) {
            ans = head.getValue();
            head = head.getNext();
        } else {
            Node tmp = head;
            while (tmp.getNext() != null && !tmp.getNext().getKey().equals(key)) {
                tmp = tmp.getNext();
            }
            if (tmp.getNext() == null) {
                ans = null;
            } else {
                ans = tmp.getNext().getValue();
                tmp.setNext(tmp.getNext().getNext());
            }
        }
        if (ans != null) {
            size--;
        }
        return ans;
    }

    public Node find(String key) {
        Node cur = head;
        if (size == 0) {
            return null;
        }
        while (cur != null) {
            if (cur.getKey().equals(key))
                return cur;
            cur = cur.getNext();
        }
        return null;
    }

    public void clear() {
        while (head != tail) {
            head = null;
            tail = null;
            size = 0;
        }
    }

    public void print() {
        System.out.println(this.size);
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.getKey() + " " + cur.getValue() + "    ");
            cur = cur.getNext();
        }
        System.out.println();
    }
}
