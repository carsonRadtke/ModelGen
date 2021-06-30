package me.nosrac.filetypes;

public interface OutputFile {
    
    public boolean equivalentTo(OutputFile other);

    public FileMembers[] getMembers();

    public void printHeader();

    public void printBody();

    public void printFooter();
}
