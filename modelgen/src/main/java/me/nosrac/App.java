package me.nosrac;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import me.nosrac.antlr4.JSONLexer;
import me.nosrac.antlr4.JSONParser;
import me.nosrac.filetypes.csharp.CSharpJSONVisitor;

public final class App {

    public static void main(String[] args) {

        String input = "{\"name\": \"Carson Radtke\"}";
        
        JSONLexer jsonLexer = new JSONLexer(CharStreams.fromString(input));
        CommonTokenStream commonTokenStream = new CommonTokenStream(jsonLexer);
        JSONParser jsonParser = new JSONParser(commonTokenStream);

        ParseTree parseTree = jsonParser.json();
        CSharpJSONVisitor visitor = new CSharpJSONVisitor();

        visitor.visit(parseTree);        
    }

}
