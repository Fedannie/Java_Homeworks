package ru.spbau.java.fedorova.trie;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created on 21.09.2016.
 * @author Fedorova Anna
 */
public interface StreamSerializable {
    void serialize(OutputStream out) throws IOException;
    /**
     * Replace current state with data from input stream
     */
    void deserialize(InputStream in) throws IOException, ClassNotFoundException ;
}