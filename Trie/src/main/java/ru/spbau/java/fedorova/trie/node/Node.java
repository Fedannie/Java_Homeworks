package ru.spbau.java.fedorova.trie.node;

/**
 * Created on 21.09.2016.
 * @author Fedorova Anna
 */
public class Node {
    private Node[] ch;
    private boolean term = false;
    private int size = 0;
    private static int N = 58;

    public Node() {
        this.ch = new Node[N];
        for (int i = 0; i < 52; i++) {
            this.ch[i] = null;
        }
    }

    private int getIndex(char ch) {
        return (ch - 'A');
    }

    public void setChar(char ch) {
        int index = getIndex(ch);
        if (this.ch[index] != null) {
            return;
        }
        this.ch[index] = new Node();
    }

    public void remChar(char ch) {
        int index = getIndex(ch);
        this.ch[index] = null;
    }

    public int getSize() {
        return this.size;
    }

    public void setTerm() {
        this.term = true;
    }

    public void setUnTerm() {
        this.term = false;
    }

    public boolean getTerm() {
        return this.term;
    }

    public Node getNext(char ch) {
        return this.ch[getIndex(ch)];
    }

    public void incSize() {
        this.size++;
    }

    public void decSize() {
        this.size--;
    }

    public int getNextRelatedChar(int ind) {
        for (int i = ind; i < N; i++) {
            if (this.ch[i] != null) {
                return i;
            }
        }
        return '.';
    }
}
