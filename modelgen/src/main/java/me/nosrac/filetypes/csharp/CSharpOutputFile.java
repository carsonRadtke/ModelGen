package me.nosrac.filetypes.csharp;

import java.util.ArrayList;

import me.nosrac.filetypes.FileMembers;
import me.nosrac.filetypes.OutputFile;
import me.nosrac.util.Emitter;

public class CSharpOutputFile implements OutputFile {

    private ArrayList<CSharpFileMembers> members;

    @Override
    public boolean equivalentTo(OutputFile other) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public FileMembers[] getMembers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void printHeader(Emitter emitter) {
        // TODO Auto-generated method stub
    }

    @Override
    public void printBody(Emitter emitter) {
        
        for (CSharpFileMembers member : members)
            member.printLine(emitter);

    }

    @Override
    public void printFooter(Emitter emitter) {
        // TODO Auto-generated method stub
    }
    
}
