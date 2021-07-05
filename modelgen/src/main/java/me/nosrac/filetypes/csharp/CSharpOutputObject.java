package me.nosrac.filetypes.csharp;

import java.io.PrintStream;
import java.util.ArrayList;

import me.nosrac.filetypes.OutputObject;

public class CSharpOutputObject implements OutputObject {

    public static final CSharpOutputObject GeneratedClass = new CSharpOutputObject();

    private String name;
    private ArrayList<CSharpObject> rootElements;
    private ArrayList<CSharpOutputObject> refElements;

    private CSharpOutputObject() {
        this("Generated");
    }

    public CSharpOutputObject(String name) {
        if (name == null) { System.err.println("name was null"); }

        this.name = name;

        this.rootElements = new ArrayList<CSharpObject>();
        this.refElements = new ArrayList<CSharpOutputObject>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CSharpObject[] getRootElements() {
        return (CSharpObject[]) this.rootElements.toArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public CSharpOutputObject[] getRefElements() {
        return (CSharpOutputObject[]) this.refElements.toArray();
    }

    public void add(CSharpObject langObject) {
        if (langObject == null) { System.err.println("langObject was null"); }
        if (langObject.getName() == null) { System.err.println("langObject name was null"); }
        if (langObject.getType() == null) { System.err.println("langObject type was null"); }
        
        this.rootElements.add(langObject);
    }

    public void add(CSharpOutputObject outputObject) {
        if (outputObject == null) { System.err.println("outputObject was null"); }
        if (outputObject.getName() == null) { System.err.println("outputObject name was null"); }

        this.refElements.add(outputObject);
    }

    @Override
    public void print(PrintStream printStream) {
        
        printStream.println("public class " + this.name + "Class {");

        for (CSharpObject cso : rootElements) {

            StringBuilder sb = new StringBuilder();

            sb.append("public ");
            sb.append(cso.getType());
            sb.append(" ");
            sb.append(cso.getName());
            sb.append(" { get; set; }");

            printStream.println(sb.toString());

        }

        for (CSharpOutputObject csoo: refElements) {

            StringBuilder sb = new StringBuilder();

            sb.append("\tpublic ");
            sb.append(csoo.getName());
            sb.append("Class");
            sb.append(csoo.getName());
            sb.append(" { get; set; }");

            printStream.println(sb.toString());
        }
        
        printStream.println("\tpublic " + this.name + "() {}");
        printStream.println("}");

        for (CSharpOutputObject csoo: refElements) {

            csoo.print(printStream);

        }

    }


    
}
