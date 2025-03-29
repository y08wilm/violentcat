/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.antlr.v4.runtime.tree.ParseTreeListener
 */
package stelix.xfile.gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import stelix.xfile.gen.SxfParser;

public interface SxfListener
extends ParseTreeListener {
    public void enterFile(SxfParser.FileContext var1);

    public void exitFile(SxfParser.FileContext var1);

    public void enterBlock(SxfParser.BlockContext var1);

    public void exitBlock(SxfParser.BlockContext var1);

    public void enterVariable(SxfParser.VariableContext var1);

    public void exitVariable(SxfParser.VariableContext var1);

    public void enterNamed_basicvar(SxfParser.Named_basicvarContext var1);

    public void exitNamed_basicvar(SxfParser.Named_basicvarContext var1);

    public void enterUnnamed_object(SxfParser.Unnamed_objectContext var1);

    public void exitUnnamed_object(SxfParser.Unnamed_objectContext var1);

    public void enterBlock_elements(SxfParser.Block_elementsContext var1);

    public void exitBlock_elements(SxfParser.Block_elementsContext var1);

    public void enterVariable_types(SxfParser.Variable_typesContext var1);

    public void exitVariable_types(SxfParser.Variable_typesContext var1);

    public void enterNumber(SxfParser.NumberContext var1);

    public void exitNumber(SxfParser.NumberContext var1);

    public void enterStruct(SxfParser.StructContext var1);

    public void exitStruct(SxfParser.StructContext var1);

    public void enterVar_integer(SxfParser.Var_integerContext var1);

    public void exitVar_integer(SxfParser.Var_integerContext var1);

    public void enterVar_double(SxfParser.Var_doubleContext var1);

    public void exitVar_double(SxfParser.Var_doubleContext var1);

    public void enterVar_false(SxfParser.Var_falseContext var1);

    public void exitVar_false(SxfParser.Var_falseContext var1);

    public void enterVar_true(SxfParser.Var_trueContext var1);

    public void exitVar_true(SxfParser.Var_trueContext var1);

    public void enterVar_null(SxfParser.Var_nullContext var1);

    public void exitVar_null(SxfParser.Var_nullContext var1);

    public void enterBool(SxfParser.BoolContext var1);

    public void exitBool(SxfParser.BoolContext var1);

    public void enterString_literal(SxfParser.String_literalContext var1);

    public void exitString_literal(SxfParser.String_literalContext var1);
}

