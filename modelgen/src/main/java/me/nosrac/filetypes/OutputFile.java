package me.nosrac.filetypes;

import me.nosrac.util.Emitter;

public interface OutputFile {
    
    public boolean equivalentTo(OutputFile other);

    public FileMembers[] getMembers();

    public void printHeader(Emitter emitter);

    public void printBody(Emitter emitter);

    public void printFooter(Emitter emitter);
}
