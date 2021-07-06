package me.nosrac.filetypes.csharp;

import me.nosrac.filetypes.LangObject;

public class CSharpObject implements LangObject {

    private String type;
    private String name;
    private Boolean array;

    public CSharpObject(String type, String name, Boolean array) {
        this.type = type;
        this.name = name;
        this.array = array;
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

    @Override
    public Boolean isArray() {
        return this.array;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("public ");
        sb.append(this.type);
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
