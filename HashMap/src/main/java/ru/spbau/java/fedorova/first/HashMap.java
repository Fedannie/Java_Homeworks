package ru.spbau.java.fedorova.first;
import ru.spbau.java.fedorova.first.list.LinkedList;
import ru.spbau.java.fedorova.first.list.node.Node;

/**
 * Created on 17.09.2016.
 * @author Fedorova Anna
 */
public class HashMap {
    final private LinkedList[] list;
    private int size = 0;
    final private int capacity = 1000;
    public HashMap() {
        list = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            list[i] = new LinkedList();
        }
    }

    public int size() {
        return size;
    }

    private int hashCode(String str) {
        return Math.abs(str.hashCode()) % capacity;
    }

    private boolean findKey(int hash, String key) {
        return (list[hash].find(key) != null);
    }

    public boolean contains(String key) {
        int hash = hashCode(key);
        return findKey(hash, key);
    }

    public String get(String key) {
        int hash = hashCode(key);
        Node curList = list[hash].find(key);
        if (curList == null) {
            return null;
        } else {
            return curList.getValue();
        }
    }

    public String put(String key, String value) {
        int hash = hashCode(key);
        Node curList = list[hash].find(key);
        if (curList == null) {
            list[hash].add(key, value);
            size++;
            return null;
        } else {
            String prev = curList.getValue();
            curList.setValue(value);
            return prev;
        }
    }

    public String remove(String key) {
        int hash = hashCode(key);
        Node curList = list[hash].find(key);
        if (curList == null) {
            return null;
        } else {
            String ans = curList.getValue();
            list[hash].remove(key);
            size--;
            return ans;
        }
    }

    public void clear() {
        for (LinkedList a : list)
            a.clear();
        size = 0;
    }
}