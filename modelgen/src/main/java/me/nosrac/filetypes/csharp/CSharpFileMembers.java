package me.nosrac.filetypes.csharp;

import me.nosrac.filetypes.FileMembers;
import me.nosrac.util.Emitter;

public class CSharpFileMembers implements FileMembers {

    public String name;
    public String type;

    @Override
    public void printLine(Emitter emitter) {
        
        emitter.emit("public " + type + name + " {get; set;}");

    }
    
}
