package me.nosrac;

import me.nosrac.util.ModelGenerator;
import me.nosrac.util.ProgramArgs;

public final class App {

    private static final boolean TEST = true;

    public static void main(String[] args) {

        String testType = "URL";
        String testSource = "https://jsonplaceholder.typicode.com/todos/1";
        String testDest = "/Users/carson/code/ModelGen/test_output.cs";
        String testLang = "CSHARP";

        String[] testArgs = String.format("_ --type %s --source %s --dest %s --lang %s", testType, testSource, testDest, testLang).split(" ");

        ProgramArgs programArgs = ProgramArgs.handleArgs(TEST ? testArgs : args);

        try {
            ModelGenerator.generate(programArgs);
        } catch (Exception ex) {
            System.out.println("Something went wrong :(");
        }
        
    }

}
