package me.nosrac;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import me.nosrac.antlr4.JSONLexer;
import me.nosrac.antlr4.JSONParser;
import me.nosrac.filetypes.csharp.CSharpJSONVisitor;
import me.nosrac.util.ModelGenerator;
import me.nosrac.util.ProgramArgs;

public final class App {

    public static void main(String[] args) {

        ProgramArgs programArgs = ProgramArgs.handleArgs(args);
        ModelGenerator.generate(programArgs);
        
    }

}
