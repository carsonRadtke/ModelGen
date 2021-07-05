package me.nosrac.filetypes.csharp;

import java.util.List;

import me.nosrac.antlr4.JSONBaseVisitor;
import me.nosrac.antlr4.JSONParser;

public class CSharpJSONVisitor extends JSONBaseVisitor<CSharpOutputObject> {

    @Override
    public CSharpOutputObject visitJson(JSONParser.JsonContext ctx) {
        return this.visit(ctx.value());
    }

    @Override
    public CSharpOutputObject visitObj(JSONParser.ObjContext ctx) {

        CSharpOutputObject ret = CSharpOutputObject.GeneratedClass;

        List<JSONParser.PairContext> pairs = ctx.pair();

        for (JSONParser.PairContext pair : pairs) {

            CSharpOutputObject pairRet = this.visit(pair);
            
            for (CSharpObject cso : pairRet.getRootElements())
                ret.add(cso);

            for(CSharpOutputObject csoo : pairRet.getRefElements())
                ret.add(csoo);
        }

        return ret;
    }

    @Override
    public CSharpOutputObject visitPair(JSONParser.PairContext ctx) {
        CSharpOutputObject ret = CSharpOutputObject.GeneratedClass;

        String name = ctx.STRING().getText();
        name = name.substring(1, name.length()-1);

        JSONParser.ValueContext value = ctx.value();

        if (value.obj() != null) {

            CSharpOutputObject objRet = this.visit(value.obj());
            objRet.setName(name);

            ret.add(objRet);

        } else if (value.arr() != null) {

            CSharpOutputObject arrRet = this.visit(value.arr());
            arrRet.setName(name);

            ret.add(arrRet);

        } else {
            
            String type;

            if (value.STRING() != null) {
                type = "String";
            } else if (value.NUMBER() != null) {
                type = value.NUMBER().getText().contains(".") ? "float" : "int";
            } else if (value.getText() == "null") {
                type = "Object";
            } else {
                type = "Bool";
            }

            ret.add(new CSharpObject(name, type));

        }

        
        return ret;
    }

    @Override
    public CSharpOutputObject visitArr(JSONParser.ArrContext ctx) {
        CSharpOutputObject ret = visitChildren(ctx);

        List<JSONParser.ValueContext> values = ctx.value();

        for (JSONParser.ValueContext value : values) {

            CSharpOutputObject valueRet = this.visit(value);
            
            for (CSharpObject cso : valueRet.getRootElements())
                ret.add(cso);

            for(CSharpOutputObject csoo : valueRet.getRefElements())
                ret.add(csoo);

        }

        return ret;
    }

    @Override
    public CSharpOutputObject visitValue(JSONParser.ValueContext ctx) {
        
        CSharpOutputObject ret = CSharpOutputObject.GeneratedClass;

        return ret;
    }

}
