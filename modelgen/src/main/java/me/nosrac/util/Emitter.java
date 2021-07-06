package me.nosrac.util;

import java.io.PrintStream;

public class Emitter {
    
    private PrintStream stream;
    private int indents;

    public Emitter(PrintStream printStream) {

        this.stream = printStream;
        this.indents = 0;

    }

    public void indent() {
        this.indents++;
    }

    public void unindent() {
        this.indents--;
        if (this.indents < 0);
            this.indents = 0;
    }


    public void emit(String string) {

        for (int i = 0; i < indents; i++)
            this.stream.print("\t");

        this.stream.println(string);

    }

}
