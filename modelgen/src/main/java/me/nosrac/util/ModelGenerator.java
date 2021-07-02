package me.nosrac.util;

import java.io.File;
import java.io.PrintStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import me.nosrac.antlr4.JSONLexer;
import me.nosrac.antlr4.JSONParser;
import me.nosrac.filetypes.csharp.CSharpJSONVisitor;
import me.nosrac.filetypes.csharp.CSharpOutputFile;

public class ModelGenerator {

    private static List<String> acceptedLanguages = List.of("C#", "CSHARP");

    public static void generate(ProgramArgs args) throws Exception {

        if (!isValidSourceType(args.getSourceType()) || !isValidLanguage(args.getLanguage())) {
            ProgramArgs.printUsage();
            return;
        }

        switch (args.getSourceType().toUpperCase()) {
            case "URL":
                generateFromURL(args.getSource(), args.getDestination(), args.getLanguage());
                break;
            case "FILE":
                generateFromFile(args.getSource(), args.getDestination(), args.getLanguage());
                break;
            default:
                /* impossible */
                break;
        }

    }

    private static void generateFromURL(String url, String outputFile, String outputLang) {

        if (url == null || outputFile == null) {
            ProgramArgs.printUsage();
            return;
        }

        String responseBody = makeHTTPRequest(url);

        generate(responseBody, outputFile, outputLang);

    }

    private static String makeHTTPRequest(String url) {

        String ret;

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(HttpRequest.newBuilder(new URI(url)).GET().build(),
                    BodyHandlers.ofString());

            ret = response.body();
        } catch (Exception ex) {
            ret = null;
        }

        return ret;
    }

    private static void generateFromFile(String inputFile, String outputFile, String outputLang) {

        if (inputFile == null || outputFile == null) {
            ProgramArgs.printUsage();
            return;
        }

    }

    private static boolean isValidSourceType(String sourceType) {

        if (sourceType == null)
            return false;

        return sourceType.toUpperCase().equals("URL") || sourceType.toUpperCase().equals("FILE");

    }

    private static boolean isValidLanguage(String language) {

        if (language == null)
            return false;

        return ModelGenerator.acceptedLanguages.contains(language.toUpperCase());

    }

    private static void generate(String json, String outputFile, String lang) {

        JSONLexer jsonLexer = new JSONLexer(CharStreams.fromString(json));
        CommonTokenStream commonTokenStream = new CommonTokenStream(jsonLexer);
        JSONParser jsonParser = new JSONParser(commonTokenStream);

        ParseTree parseTree = jsonParser.json();
        
        switch (lang.toUpperCase()) {

            case "C#":
            case "CSHARP":
                generateCSharp(parseTree, outputFile);
                break;

        }
    }

    private static void generateCSharp(ParseTree parseTree, String outputFile) {

        CSharpJSONVisitor visitor = new CSharpJSONVisitor();
        CSharpOutputFile csharpOutput = visitor.visit(parseTree);

        File file = new File(outputFile);

        PrintStream printStream = null;
        try {
            printStream = new PrintStream(file);
        }
        catch (Exception ex) { }

        Emitter emitter = new Emitter(printStream);

        csharpOutput.printHeader(emitter);
        csharpOutput.printBody(emitter);
        csharpOutput.printFooter(emitter);

        printStream.close();
    }

}
