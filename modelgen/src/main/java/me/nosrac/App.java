package me.nosrac;

import me.nosrac.util.ModelGenerator;
import me.nosrac.util.ProgramArgs;

public final class App {

    public static void main(String[] args) {

        ProgramArgs programArgs = ProgramArgs.handleArgs(args);

        try {
            ModelGenerator.generate(programArgs);
        } catch (Exception ex) {
            System.out.println("Something went wrong :(");
            System.out.println(ex.getMessage());
        }
        
    }

}
