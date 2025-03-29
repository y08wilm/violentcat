/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.antlr.v4.runtime.tree.ParseTreeVisitor
 */
package stelix.xfile.gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import stelix.xfile.gen.SxfParser;

public interface SxfVisitor<T>
extends ParseTreeVisitor<T> {
    public T visitFile(SxfParser.FileContext var1);

    public T visitBlock(SxfParser.BlockContext var1);

    public T visitVariable(SxfParser.VariableContext var1);

    public T visitNamed_basicvar(SxfParser.Named_basicvarContext var1);

    public T visitUnnamed_object(SxfParser.Unnamed_objectContext var1);

    public T visitBlock_elements(SxfParser.Block_elementsContext var1);

    public T visitVariable_types(SxfParser.Variable_typesContext var1);

    public T visitNumber(SxfParser.NumberContext var1);

    public T visitStruct(SxfParser.StructContext var1);

    public T visitVar_integer(SxfParser.Var_integerContext var1);

    public T visitVar_double(SxfParser.Var_doubleContext var1);

    public T visitVar_false(SxfParser.Var_falseContext var1);

    public T visitVar_true(SxfParser.Var_trueContext var1);

    public T visitVar_null(SxfParser.Var_nullContext var1);

    public T visitBool(SxfParser.BoolContext var1);

    public T visitString_literal(SxfParser.String_literalContext var1);
}

