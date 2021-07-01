package me.nosrac.util;

public class ProgramArgs {
    
    private String sourceType;
    public String getSourceType() { return this.sourceType; }

    private String source;
    public String getSource() { return this.source; }

    private String destination;
    public String getDestination() { return this.destination; }

    private String language;
    public String getLanguage() { return this.language; }

    private ProgramArgs() { }

    public static ProgramArgs handleArgs(String[] args) {

        ProgramArgs ret = new ProgramArgs();

        ret.sourceType = getValue(args, "--type");
        ret.source = getValue(args, "--source");
        ret.destination = getValue(args, "--dest");
        ret.language = getValue(args, "--lang");
        
        if (ret.checkArgs()) {
            printUsage();
            ret = null;
        }

        return ret;

    }

    public static void printUsage() {

        System.out.println("Usage: --type <type> --source <source> --dest <dest> --lang <lang>");
        System.out.println("\t<type>: url | path");
        System.out.println("\t<source>: points to location of type <type>");
        System.out.println("\t<dest>: points to location of output file(s)");
        System.out.println("\t<lang>: type of output language - only c# (for now)");

    }

    private static String getValue(String[] args, String key) {

        for (int i = 0; i < args.length; i++) {

            if (key.equals(args[i])) {
                if (i + 1 < args.length)
                    return args[i+1];
            }

        }

        return null;

    }

    private boolean checkArgs() {

        boolean ret = true;

        ret = ret && sourceType != null;
        ret = ret && source != null;
        ret = ret && destination != null;
        ret = ret && language != null;

        return ret;

    }

}
