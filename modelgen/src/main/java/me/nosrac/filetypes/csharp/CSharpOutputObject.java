package me.nosrac.filetypes.csharp;

import java.io.PrintStream;
import java.util.ArrayList;

import me.nosrac.filetypes.OutputObject;

public class CSharpOutputObject implements OutputObject {

    private String name;
    private ArrayList<CSharpObject> rootElements;
    private ArrayList<CSharpOutputObject> refElements;

    public CSharpOutputObject() {
        this("Generated");
    }

    public CSharpOutputObject(String name) {
        if (name == null) {
            System.err.println("name was null");
        }

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
        CSharpObject[] ret = new CSharpObject[this.rootElements.size()];

        int idx = 0;
        for (CSharpObject cso : this.rootElements)
            ret[idx++] = cso;

        return ret;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CSharpOutputObject[] getRefElements() {
        CSharpOutputObject[] ret = new CSharpOutputObject[this.rootElements.size()];

        int idx = 0;
        for (CSharpOutputObject csoo : this.refElements)
            ret[idx++] = csoo;

        return ret;
    }

    public void add(CSharpObject langObject) {
        if (langObject == null) {
            System.err.println("langObject was null");
            return;
        }
        if (langObject.getName() == null) {
            System.err.println("langObject name was null");
            return;
        }
        if (langObject.getType() == null) {
            System.err.println("langObject type was null");
            return;
        }

        if (this.rootElements.contains(langObject))
            return;

        this.rootElements.add(langObject);
    }

    public void add(CSharpOutputObject outputObject) {
        if (outputObject == null) {
            System.err.println("outputObject was null");
            return;
        }
        if (outputObject.getName() == null) {
            System.err.println("outputObject name was null");
            return;
        }

        if (this.refElements.contains(outputObject))
            return;

        this.refElements.add(outputObject);
    }

    @Override
    public void print(PrintStream printStream) {

        printStream.println("public class " + this.name + "Class {");

        for (CSharpObject cso : rootElements) {

            StringBuilder sb = new StringBuilder();

            sb.append("\tpublic ");
            sb.append(cso.getType());
            sb.append(" ");
            sb.append(cso.getName());
            sb.append(" { get; set; }");

            printStream.println(sb.toString());

        }

        for (CSharpOutputObject csoo : refElements) {

            StringBuilder sb = new StringBuilder();

            sb.append("\tpublic ");
            sb.append(csoo.getName());
            sb.append("Class ");
            sb.append(csoo.getName());
            sb.append(" { get; set; }");

            printStream.println(sb.toString());
        }

        printStream.println("\tpublic " + this.name + "Class() {}");
        printStream.println("}");

        for (CSharpOutputObject csoo : refElements) {

            csoo.print(printStream);

        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof CSharpOutputObject)) return false;

        CSharpOutputObject that = (CSharpOutputObject) o;

        if (this.refElements.size() != that.refElements.size()) return false;
        if (this.rootElements.size() != that.refElements.size()) return false;

        for (int i = 0; i < this.refElements.size(); i++)
            if (!this.refElements.get(i).equals(that.refElements.get(i))) return false;

        for (int i = 0; i < this.rootElements.size(); i++)
            if (!this.rootElements.get(i).equals(that.rootElements.get(i))) return false;

        return true;
    }

}
