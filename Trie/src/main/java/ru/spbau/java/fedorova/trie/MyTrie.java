package ru.spbau.java.fedorova.trie;

import ru.spbau.java.fedorova.trie.node.Node;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created on 21.09.2016.
 * @author Fedorova Anna
 */
public class MyTrie implements Trie, StreamSerializable{
    private Node head;

    public MyTrie() {
        head = new Node();
    }

    public void serialize(OutputStream out) throws IOException {
        write(head, out);
    }

    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        head = read(in);
    }

    public Node read(InputStream in) throws IOException {
        Node head = new Node();
        head.setSize(in.read());
        head.setTerm(in.read() != 0);
        for (char ch = (char) in.read(); ch != 0; ch = (char) in.read()) {
            head.setNext(ch, read(in));
        }
        return head;
    }

    private void write(Node head, OutputStream out) throws IOException {
        out.write(head.writeAll());
    }


    public boolean add(String element) {
        if (contains(element)) {
            return true;
        }
        Node cur = head;
        for (int ind = 0; ind < element.length(); ind++) {
            if (cur.getNext(element.charAt(ind)) == null) {
                cur.setNextByChar(element.charAt(ind));
            }
            cur.incSize();
            cur = cur.getNext(element.charAt(ind));
        }
        cur.setTerm(true);
        cur.incSize();
        return false;
    }

    public boolean contains(String element) {
        Node cur = head;
        for (int ind = 0; ind < element.length(); ind++) {
            if (cur.getNext(element.charAt(ind)) == null) {
                return false;
            }
            cur = cur.getNext(element.charAt(ind));
        }
        return (cur.getTerm());
    }

    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }
        Node cur = head;
        for (int ind = 0; ind < element.length(); ind++) {
            cur.decSize();
            cur = cur.getNext(element.charAt(ind));
        }
        cur.decSize();
        cur = cur.getPrevious();
        for (int ind = element.length() - 1; ind >= 0; ind--) {
            if (cur.getTerm()) {
                break;
            }
            cur.deleteNode(element.charAt(ind));
            cur = cur.getPrevious();
        }
        return true;
    }

    public int size() {
        return head.getSize();
    }

    public int howManyStartsWithPrefix(String prefix) {
        Node cur = head;
        for (int ind = 0; ind < prefix.length(); ind++) {
            if (cur.getNext(prefix.charAt(ind)) == null) {
                return 0;
            }
            cur = cur.getNext(prefix.charAt(ind));
        }
        return cur.getSize();
    }
}
