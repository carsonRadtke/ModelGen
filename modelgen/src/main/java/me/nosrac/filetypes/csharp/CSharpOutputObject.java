package me.nosrac.filetypes.csharp;

import java.util.ArrayList;

import me.nosrac.filetypes.OutputObject;
import me.nosrac.util.Emitter;

public class CSharpOutputObject implements OutputObject {

    private Boolean array;
    private String name;
    private ArrayList<CSharpObject> rootElements;
    private ArrayList<CSharpOutputObject> refElements;

    public CSharpOutputObject() {
        this("Generated", false);
    }

    public CSharpOutputObject(String name, Boolean array) {
        if (name == null) {
            System.err.println("name was null");
        }

        this.name = name;

        this.rootElements = new ArrayList<CSharpObject>();
        this.refElements = new ArrayList<CSharpOutputObject>();

        this.array = array;
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
        CSharpOutputObject[] ret = new CSharpOutputObject[this.refElements.size()];

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
    public void print(Emitter emitter) {

        String className = String.format("%sClass", this.name);

        emitter.emit(String.format("public class %s {", className));
        emitter.indent();

        for (CSharpObject cso : rootElements)
            emitter.emit(cso.toString());

        for (CSharpOutputObject csoo : refElements)
            emitter.emit(csoo.toString());

        emitter.emit(String.format("public %s() {}", className));
        
        emitter.unindent();
        emitter.emit("}");

        for (CSharpOutputObject csoo : refElements)
            csoo.print(emitter);

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

    @Override
    public Boolean isArray() {
        return this.array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("public ");
        sb.append(this.name);
        sb.append("Class");
        if (this.array)
            sb.append("[]");
        sb.append(" ");
        sb.append(this.name);
        sb.append(" { get; set; }");

        return sb.toString();
    }

    @Override
    public void isArray(Boolean array) {
        this.array = array;
    }

}
