package ru.spbau.java.fedorova.first.list.node;

/**
 * Created on 17.09.2016.
 * @author Fedorova Anna
 */

public class Node {
    final private String key;
    private String value;
    private Node next;
    //public Node(){}
    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        next = null;
    }
    public String getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
    //public void setKey(String key) {
    //    this.key = key;
    //}
    public void setValue(String value) {
        this.value = value;
    }
    public Node getNext(){
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
}

