package me.nosrac.filetypes.csharp;

import me.nosrac.antlr4.JSONBaseVisitor;
import me.nosrac.antlr4.JSONParser;

public class CSharpJSONVisitor extends JSONBaseVisitor<CSharpOutputObject> {

    @Override
    public CSharpOutputObject visitJson(JSONParser.JsonContext ctx) {
        return this.visit(ctx.value());
    }

    @Override
    public CSharpOutputObject visitObj(JSONParser.ObjContext ctx) {
        CSharpOutputObject ret = new CSharpOutputObject();

        for (JSONParser.PairContext pair : ctx.pair())
            ret = this.aggregateResult(ret, this.visit(pair));

        return ret;
    }

    @Override
    public CSharpOutputObject visitPair(JSONParser.PairContext ctx) {
        
        CSharpOutputObject ret = new CSharpOutputObject();

        String name = ctx.STRING().getText();
        name = name.substring(1, name.length()-1);

        CSharpOutputObject valCtx = this.visit(ctx.value());

        if (valCtx == null) {
            ret.add(new CSharpObject(getType(ctx.value()), name, isArray(ctx.value())));
        } else {
            valCtx.isArray(isArray(ctx.value()));
            valCtx.setName(name);
            ret.add(valCtx);
        }

        return ret;
    }

    @Override
    public CSharpOutputObject visitArr(JSONParser.ArrContext ctx) {
        
        CSharpOutputObject ret = new CSharpOutputObject();

        for (JSONParser.ValueContext value : ctx.value())
            ret = this.aggregateResult(ret, this.visit(value));

        return ret;

    }

    @Override
    public CSharpOutputObject visitValue(JSONParser.ValueContext ctx) {

        CSharpOutputObject ret;

        JSONParser.ObjContext objCtx = ctx.obj();
        JSONParser.ArrContext arrCtx = ctx.arr();

        if (objCtx != null) {
            ret = this.visit(objCtx);
        } else if (arrCtx != null) {
            ret = this.visit(arrCtx);
        } else {
            ret = null;
        }

        return ret;
    }

    @Override
    public CSharpOutputObject aggregateResult(CSharpOutputObject prevResult, CSharpOutputObject nextResult) {

        CSharpOutputObject ret = nextResult;

        if (prevResult == null)
            return ret;

        for (CSharpObject cso : prevResult.getRootElements())
            ret.add(cso);

        for (CSharpOutputObject csoo : prevResult.getRefElements())
            ret.add(csoo);

        return ret;
    }

    private String getType(JSONParser.ValueContext valCtx) {

        String valTxt = valCtx.getText();

        if (valCtx.STRING() != null) {
            return "String";
        } else if (valCtx.NUMBER() != null) {
            return valTxt.contains(".") ? "float" : "int";
        } else if (valTxt.equals("true") || valTxt.equals("false")) {
            return "Boolean";
        } else if (valTxt.equals("null")) {
            return "Object";
        }

        return null;

    }

    private Boolean isArray(JSONParser.ValueContext valCtx) {
        return valCtx.arr() != null;
    }

}
