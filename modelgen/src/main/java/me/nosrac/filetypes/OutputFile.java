package me.nosrac.filetypes;

import java.io.File;
import java.io.PrintStream;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import me.nosrac.util.Emitter;
import me.nosrac.util.Logger;

public class OutputFile {
   
    private OutputObject data;
    private String path;

    public <T extends OutputObject> OutputFile(String path, ParseTree parseTree, AbstractParseTreeVisitor<T> visitor) {
        this.path = path;
        this.data = visitor.visit(parseTree);
    }

    public void printData() {
        this.data.print(new Emitter(System.out));
    }

    public void saveData() {
        File outputFile = new File(this.path);
        PrintStream printStream;

        try {
            printStream = new PrintStream(outputFile);
        } catch (Exception ex) {
            Logger.logException(ex);
            return;
        }

        this.data.print(new Emitter(printStream));

        printStream.close();
    }
}
