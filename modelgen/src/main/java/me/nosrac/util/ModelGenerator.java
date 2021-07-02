package me.nosrac.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

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

        System.out.println(responseBody);

    }
    
    private static String makeHTTPRequest(String url) {

        String ret;

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(
                HttpRequest
                    .newBuilder(new URI(url))
                    .GET()
                    .build(),
                BodyHandlers.ofString()
            );

            ret = response.body();
        }
        catch (Exception ex) {
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

}
