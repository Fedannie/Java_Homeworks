package ru.spbau.java.fedorova.first;
import ru.spbau.java.fedorova.first.list.LinkedList;
import ru.spbau.java.fedorova.first.list.node.Node;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created on 17.09.2016.
 * @author Fedorova Anna
 */
public class HashMap {
    private LinkedList[] list;
    private int size;
    private int capacity = 1000;
    public HashMap() {
        list = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            list[i] = new LinkedList();
        }
    }

    public int size() {
        return this.size;
    }

    public int hashCode(String str) {
        return Math.abs(str.hashCode()) % capacity;
    }

    public boolean contains(String key) {
        int hash = hashCode(key);
        if (list[hash].find(key) != null) {
            return true;
        }
        return false;
    }

    public String get(String key) {
        int hash = hashCode(key);
        Node cur_list = list[hash].find(key);
        if (cur_list == null) {
            return null;
        } else {
            return cur_list.getValue();
        }
    }

    public String put(String key, String value) {
        int hash = hashCode(key);
        Node cur_list = list[hash].find(key);
        if (cur_list == null) {
            list[hash].add(key, value);
            return null;
        } else {
            String prev = cur_list.getValue();
            cur_list.setValue(value);
            return prev;
        }
    }

    public String remove(String key) {
        int hash = hashCode(key);
        Node cur_list = list[hash].find(key);
        if (cur_list == null) {
            return null;
        } else {
            String ans = cur_list.getValue();
            list[hash].remove(key);
            return ans;
        }
    }

    public void clear() {
        for (LinkedList a : list)
            a.clear();
    }
}
