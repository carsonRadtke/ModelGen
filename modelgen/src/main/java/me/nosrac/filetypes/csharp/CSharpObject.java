package me.nosrac.filetypes.csharp;

import me.nosrac.filetypes.LangObject;

public class CSharpObject implements LangObject {

    private String type;
    private String name;

    public CSharpObject(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof CSharpObject)) return false;

        CSharpObject that = (CSharpObject) o;

        if (!this.name.equals(that.name)) return false;
        if (!this.type.equals(that.type)) return false;

        return true;
    }
    
}
