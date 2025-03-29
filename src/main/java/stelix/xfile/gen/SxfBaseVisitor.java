/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.antlr.v4.runtime.tree.AbstractParseTreeVisitor
 *  org.antlr.v4.runtime.tree.RuleNode
 */
package stelix.xfile.gen;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import stelix.xfile.gen.SxfParser;
import stelix.xfile.gen.SxfVisitor;

public class SxfBaseVisitor<T>
extends AbstractParseTreeVisitor<T>
implements SxfVisitor<T> {
    @Override
    public T visitFile(SxfParser.FileContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitBlock(SxfParser.BlockContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitVariable(SxfParser.VariableContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitNamed_basicvar(SxfParser.Named_basicvarContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitUnnamed_object(SxfParser.Unnamed_objectContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitBlock_elements(SxfParser.Block_elementsContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitVariable_types(SxfParser.Variable_typesContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitNumber(SxfParser.NumberContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitStruct(SxfParser.StructContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitVar_integer(SxfParser.Var_integerContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitVar_double(SxfParser.Var_doubleContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitVar_false(SxfParser.Var_falseContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitVar_true(SxfParser.Var_trueContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitVar_null(SxfParser.Var_nullContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitBool(SxfParser.BoolContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }

    @Override
    public T visitString_literal(SxfParser.String_literalContext ctx) {
        return (T)this.visitChildren((RuleNode)ctx);
    }
}

