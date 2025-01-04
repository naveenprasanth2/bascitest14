package org.example.tesco.threading;

import java.io.Closeable;
import java.io.IOException;

public class TryClose implements Closeable, AutoCloseable {

    public TryClose() {

    }

    public void test() {
        throw new RuntimeException();
    }

    @Override
    public void close() throws IOException {
        System.out.println("inside close");
    }
}
