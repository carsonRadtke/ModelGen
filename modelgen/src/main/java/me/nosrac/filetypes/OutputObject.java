package me.nosrac.filetypes;

import java.io.PrintStream;

public interface OutputObject {

    public String getName();
    public void setName(String name);

    public <T extends LangObject> T[] getRootElements();
    public <T extends OutputObject> T[] getRefElements();

    public void print(PrintStream printStream);

}