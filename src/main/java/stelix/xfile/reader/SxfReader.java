/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.antlr.v4.runtime.CharStream
 *  org.antlr.v4.runtime.CharStreams
 *  org.antlr.v4.runtime.CommonTokenStream
 *  org.antlr.v4.runtime.TokenSource
 *  org.antlr.v4.runtime.TokenStream
 *  org.antlr.v4.runtime.tree.ParseTree
 */
package stelix.xfile.reader;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import stelix.xfile.Commons;
import stelix.xfile.SxfBlock;
import stelix.xfile.SxfStruct;
import stelix.xfile.gen.SxfLexer;
import stelix.xfile.gen.SxfParser;

public class SxfReader {
    public static SxfBlock toSxf(String data) {
        SxfParser sxfParser = new SxfParser((TokenStream)new CommonTokenStream((TokenSource)new SxfLexer((CharStream)CharStreams.fromString((String)data))));
        try {
            return SxfReader.readBlock(sxfParser.file().block());
        }
        catch (Exception ex) {
            return new SxfBlock();
        }
    }

    protected static SxfBlock readBlock(SxfParser.BlockContext block) {
        SxfBlock sxfBlock = new SxfBlock();
        for (ParseTree variable : block.block_elements().children) {
            if (!(variable instanceof SxfParser.VariableContext)) continue;
            SxfParser.VariableContext rawVariable = (SxfParser.VariableContext)variable;
            if (rawVariable.children.get(0) instanceof SxfParser.Named_basicvarContext) {
                SxfParser.Named_basicvarContext namedVar = (SxfParser.Named_basicvarContext)((Object)rawVariable.children.get(0));
                String name = Commons.removeQuotes(((ParseTree)namedVar.children.get(0)).getText());
                ParseTree value = namedVar.getChild(2);
                if (value instanceof SxfParser.BlockContext) {
                    sxfBlock.variables().put(name, SxfReader.readBlock((SxfParser.BlockContext)value));
                    continue;
                }
                sxfBlock.variables().put(name, SxfReader.readVariable((SxfParser.Variable_typesContext)value));
                continue;
            }
            if (!(rawVariable.children.get(0) instanceof SxfParser.Unnamed_objectContext)) continue;
            SxfParser.Unnamed_objectContext unnamedObject = (SxfParser.Unnamed_objectContext)rawVariable.getChild(0);
            if (unnamedObject.getChild(0) instanceof SxfParser.BlockContext) {
                sxfBlock.unmanagedObjects().add(SxfReader.readBlock((SxfParser.BlockContext)unnamedObject.getChild(0)));
                continue;
            }
            sxfBlock.unmanagedObjects().add(SxfReader.readStruct((SxfParser.StructContext)unnamedObject.getChild(0)));
        }
        return sxfBlock;
    }

    public static SxfStruct readStruct(SxfParser.StructContext structContext) {
        SxfStruct sxfStruct = new SxfStruct();
        for (ParseTree children : structContext.children) {
            if (!(children instanceof SxfParser.Variable_typesContext)) continue;
            SxfParser.Variable_typesContext variableType = (SxfParser.Variable_typesContext)children;
            sxfStruct.elements().add(SxfReader.readVariable(variableType));
        }
        return sxfStruct;
    }

    protected static Object readVariable(SxfParser.Variable_typesContext varType) {
        ParseTree varTypeRaw = (ParseTree)varType.children.get(0);
        if (varTypeRaw instanceof SxfParser.String_literalContext) {
            return Commons.removeQuotes(varTypeRaw.getText());
        }
        if (varTypeRaw instanceof SxfParser.NumberContext) {
            ParseTree numberChildren = varTypeRaw.getChild(0);
            if (numberChildren instanceof SxfParser.Var_integerContext) {
                return Integer.parseInt(numberChildren.getText());
            }
            return Double.parseDouble(numberChildren.getText());
        }
        if (varTypeRaw instanceof SxfParser.BoolContext) {
            ParseTree boolChildren = varType.getChild(0);
            return boolChildren instanceof SxfParser.Var_trueContext;
        }
        if (varTypeRaw instanceof SxfParser.StructContext) {
            return SxfReader.readStruct((SxfParser.StructContext)varTypeRaw);
        }
        if (varTypeRaw instanceof SxfParser.BlockContext) {
            return SxfReader.readBlock((SxfParser.BlockContext)varTypeRaw);
        }
        if (varTypeRaw instanceof SxfParser.Var_nullContext) {
            return null;
        }
        return null;
    }
}

