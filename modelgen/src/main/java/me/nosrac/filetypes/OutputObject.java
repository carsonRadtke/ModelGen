package me.nosrac.filetypes;

import me.nosrac.util.Emitter;

public interface OutputObject {

    public String getName();
    public void setName(String name);

    public Boolean isArray();
    public void isArray(Boolean array);

    public <T extends LangObject> T[] getRootElements();
    public <T extends OutputObject> T[] getRefElements();

    public void print(Emitter emitter);

}
