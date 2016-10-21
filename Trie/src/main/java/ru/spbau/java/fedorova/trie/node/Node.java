package ru.spbau.java.fedorova.trie.node;

import java.io.*;

public class Node implements Serializable{
    final private Node[] ch;
    private Node prev;
    private boolean term = false;
    private int size = 0;
    private final static int N = 58;

    public Node() {
        prev = null;
        ch = new Node[N];
    }


    public Node getPrevious() {
        return prev;
    }

    private int getIndex(char ch) {
        return (ch - 'A');
    }

    public void setNextByChar(char ch) {
        int index = getIndex(ch);
        if (this.ch[index] != null) {
            return;
        }
        this.ch[index] = new Node();
        this.ch[index].setPrev(this);
    }

    public void setNext(char ch, Node next) {
        int index = getIndex(ch);
        this.ch[index] = next;
        this.ch[index].setPrev(this);
    }

    public void setPrev(Node parent) {
        prev = parent;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int s) {
        size = s;
    }

    public void setTerm(boolean fin) {
        term = fin;
    }

    public boolean getTerm() {
        return term;
    }

    public Node getNext(char ch) {
        return this.ch[getIndex(ch)];
    }

    public void incSize() {
        size++;
    }

    public void decSize() {
        size--;
    }

    public void deleteNode (char ch) {
        Node deleted = this.ch[getIndex(ch)];
        if (deleted.size == 0) {
            this.ch[getIndex(ch)] = null;
        }
    }

    public byte[] writeAll() throws IOException {
        String answer = "";
        answer += size;
        answer += term ? 1 : 0;
        for (int ind = 0; ind < N; ind++) {
            if (ch[ind] != null) {
                answer += ind + 'A';
                answer += ch[ind].writeAll();
            }
        }
        return answer.getBytes();
    }
}
