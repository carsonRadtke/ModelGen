package me.nosrac;

import me.nosrac.util.ModelGenerator;
import me.nosrac.util.ProgramArgs;

public final class App {

    public static void main(String[] args) {

        ProgramArgs programArgs = ProgramArgs.handleArgs("_ --type url --source https://jsonplaceholder.typicode.com/todos/1 --dest /Users/carson/code/ModelGen/test_output.cs --lang csharp".split(" "));

        try {
            ModelGenerator.generate(programArgs);
        } catch (Exception ex) {
            System.out.println("Something went wrong :(");
        }
        
    }

}
