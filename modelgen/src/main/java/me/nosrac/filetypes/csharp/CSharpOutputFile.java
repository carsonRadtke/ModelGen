package me.nosrac.filetypes.csharp;

import java.util.ArrayList;

import me.nosrac.filetypes.OutputFile;
import me.nosrac.util.Emitter;

public class CSharpOutputFile implements OutputFile {

    private ArrayList<CSharpFileMembers> members;

    public CSharpOutputFile() {
        this.members = new ArrayList<CSharpFileMembers>();
    }

    @Override
    public boolean equivalentTo(OutputFile other) {
        // TODO Auto-generated method stub
        return false;
    }

    public ArrayList<CSharpFileMembers> getMembers() {
        return this.members;
    }

    public void addMember(CSharpFileMembers member) {
        this.members.add(member);
    }

    public void addMembers(ArrayList<CSharpFileMembers> membersToAdd){
        this.members.addAll(membersToAdd);
    }

    @Override
    public void printHeader(Emitter emitter) {
        emitter.emit("public class GeneratedClass\n{");
    }

    @Override
    public void printBody(Emitter emitter) {
        
        for (CSharpFileMembers member : members)
            member.printLine(emitter);

    }

    @Override
    public void printFooter(Emitter emitter) {
        emitter.emit("}");
    }
    
}
