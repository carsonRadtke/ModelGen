package me.nosrac.filetypes.csharp;

import me.nosrac.antlr4.JSONBaseVisitor;
import me.nosrac.antlr4.JSONParser;

public class CSharpJSONVisitor extends JSONBaseVisitor<CSharpOutputFile> {

    @Override
    public CSharpOutputFile visitJson(JSONParser.JsonContext ctx) {
        CSharpOutputFile ret = visitChildren(ctx);

        // System.out.println("json");

        return ret;
    }

    @Override
    public CSharpOutputFile visitObj(JSONParser.ObjContext ctx) {
        CSharpOutputFile ret = visitChildren(ctx);

        // System.out.println("obj");

        return ret;
    }

    @Override
    public CSharpOutputFile visitPair(JSONParser.PairContext ctx) {
        CSharpOutputFile ret = visitChildren(ctx);

        CSharpFileMembers newMember = new CSharpFileMembers();
        newMember.type = "String";
        newMember.name = ctx.STRING().getText().replace("\"", "");
        ret.addMember(newMember);

        // System.out.println("pair");

        return ret;
    }

    @Override
    public CSharpOutputFile visitArr(JSONParser.ArrContext ctx) {
        CSharpOutputFile ret = visitChildren(ctx);

        // System.out.println("arr");

        return ret;
    }

    @Override
    public CSharpOutputFile visitValue(JSONParser.ValueContext ctx) {
        CSharpOutputFile ret = visitChildren(ctx);

        // System.out.println("value");

        return ret;
    }

    @Override
    public CSharpOutputFile defaultResult() {
        return new CSharpOutputFile();
    }

    @Override
    protected CSharpOutputFile aggregateResult(CSharpOutputFile aggregate, CSharpOutputFile nextResult) {
        nextResult.addMembers(aggregate.getMembers());
        return nextResult;
    }

}
