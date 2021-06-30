package me.nosrac.filetypes.csharp;

import java.util.ArrayList;
import java.util.List;

import me.nosrac.filetypes.FileMembers;
import me.nosrac.filetypes.OutputFile;

public class CSharpOutputFile implements OutputFile {

    private enum CSharpType {
        BOOL,
        FLOAT,
        INT,
        STRING
    }

    private class Member {
        public CSharpType type;
        public boolean nullable;
        public String name;
    }

    private ArrayList<Member> members;

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
    public void printHeader() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void printBody() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void printFooter() {
        // TODO Auto-generated method stub
        
    }
    
}
