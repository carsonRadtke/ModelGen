package me.nosrac.util;

import java.io.PrintStream;

public class Emitter {
    
    private PrintStream stream;

    public Emitter(PrintStream printStream) {

        this.stream = printStream;

    }

    public void emit(String string) {

        this.stream.println(string);

    }

}
