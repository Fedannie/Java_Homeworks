package ru.spbau.java.fedorova.trie;

import ru.spbau.java.fedorova.trie.node.Node;

import java.io.*;

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
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(head);
    }

    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        head = (Node) objectInputStream.readObject();

    }

    public boolean add(String element) {
        if (this.contains(element)) {
            return true;
        }
        Node cur = head;
        for (int ind = 0; ind < element.length(); ind++) {
            if (cur.getNext(element.charAt(ind)) == null) {
                cur.setChar(element.charAt(ind));
            }
            cur.incSize();
            cur = cur.getNext(element.charAt(ind));
        }
        cur.setTerm();
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
        if (!cur.getTerm()){
            return false;
        }
        return true;
    }

    public boolean remove(String element) {
        if (!this.contains(element)) {
            return false;
        }
        Node cur = head;
        Node last = head;
        int last_ind = 0;
        for (int ind = 0; ind < element.length(); ind++) {
            cur.decSize();
            if (cur.getSize() > 0) {
                last = cur;
                last_ind = ind;
            }
            cur = cur.getNext(element.charAt(ind));
        }
        if (cur.getSize() > 1) {
            cur.setUnTerm();
        } else {
            last.remChar(element.charAt(last_ind));
        }
        return true;
    }

    public int size() {
        return this.head.getSize();
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
