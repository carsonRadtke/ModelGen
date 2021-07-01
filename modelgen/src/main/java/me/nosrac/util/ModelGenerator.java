package me.nosrac.util;

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
