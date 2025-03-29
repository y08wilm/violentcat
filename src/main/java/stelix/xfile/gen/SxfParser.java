/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.antlr.v4.runtime.NoViableAltException
 *  org.antlr.v4.runtime.Parser
 *  org.antlr.v4.runtime.ParserRuleContext
 *  org.antlr.v4.runtime.RecognitionException
 *  org.antlr.v4.runtime.RuntimeMetaData
 *  org.antlr.v4.runtime.TokenStream
 *  org.antlr.v4.runtime.Vocabulary
 *  org.antlr.v4.runtime.VocabularyImpl
 *  org.antlr.v4.runtime.atn.ATN
 *  org.antlr.v4.runtime.atn.ATNDeserializer
 *  org.antlr.v4.runtime.atn.ParserATNSimulator
 *  org.antlr.v4.runtime.atn.PredictionContextCache
 *  org.antlr.v4.runtime.dfa.DFA
 *  org.antlr.v4.runtime.tree.ParseTreeListener
 *  org.antlr.v4.runtime.tree.ParseTreeVisitor
 *  org.antlr.v4.runtime.tree.RuleNode
 *  org.antlr.v4.runtime.tree.TerminalNode
 */
package stelix.xfile.gen;

import java.util.List;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import stelix.xfile.gen.SxfListener;
import stelix.xfile.gen.SxfVisitor;

public class SxfParser
extends Parser {
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache;
    public static final int T__0 = 1;
    public static final int T__1 = 2;
    public static final int T__2 = 3;
    public static final int T__3 = 4;
    public static final int T__4 = 5;
    public static final int T__5 = 6;
    public static final int INTEGER = 7;
    public static final int SET = 8;
    public static final int SET_LONG = 9;
    public static final int BLOCK_START = 10;
    public static final int BLOCK_END = 11;
    public static final int SEPARATOR = 12;
    public static final int SP_START = 13;
    public static final int SP_END = 14;
    public static final int IDENTIFIER = 15;
    public static final int StringLiteral = 16;
    public static final int WS = 17;
    public static final int UnterminatedStringLiteral = 18;
    public static final int BlockComment = 19;
    public static final int RULE_file = 0;
    public static final int RULE_block = 1;
    public static final int RULE_variable = 2;
    public static final int RULE_named_basicvar = 3;
    public static final int RULE_unnamed_object = 4;
    public static final int RULE_block_elements = 5;
    public static final int RULE_variable_types = 6;
    public static final int RULE_number = 7;
    public static final int RULE_struct = 8;
    public static final int RULE_var_integer = 9;
    public static final int RULE_var_double = 10;
    public static final int RULE_var_false = 11;
    public static final int RULE_var_true = 12;
    public static final int RULE_var_null = 13;
    public static final int RULE_bool = 14;
    public static final int RULE_string_literal = 15;
    public static final String[] ruleNames;
    private static final String[] _LITERAL_NAMES;
    private static final String[] _SYMBOLIC_NAMES;
    public static final Vocabulary VOCABULARY;
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN = "\u0003\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\u0003\u0015p\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010\t\u0010\u0004\u0011\t\u0011\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0005\u0003'\n\u0003\u0003\u0003\u0003\u0003\u0003\u0004\u0003\u0004\u0005\u0004-\n\u0004\u0003\u0005\u0003\u0005\u0005\u00051\n\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u00057\n\u0005\u0003\u0006\u0003\u0006\u0005\u0006;\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0007\u0007@\n\u0007\f\u0007\u000e\u0007C\u000b\u0007\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0005\bK\n\b\u0003\t\u0003\t\u0005\tO\n\t\u0003\n\u0003\n\u0003\n\u0003\n\u0007\nU\n\n\f\n\u000e\nX\u000b\n\u0005\nZ\n\n\u0003\n\u0003\n\u0003\u000b\u0003\u000b\u0003\f\u0003\f\u0003\f\u0003\f\u0003\r\u0003\r\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0003\u0010\u0003\u0010\u0005\u0010l\n\u0010\u0003\u0011\u0003\u0011\u0003\u0011\u0002\u0002\u0012\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \u0002\u0004\u0003\u0002\u0004\u0005\u0003\u0002\u0006\u0007\u0002n\u0002\"\u0003\u0002\u0002\u0002\u0004$\u0003\u0002\u0002\u0002\u0006,\u0003\u0002\u0002\u0002\b0\u0003\u0002\u0002\u0002\n:\u0003\u0002\u0002\u0002\f<\u0003\u0002\u0002\u0002\u000eJ\u0003\u0002\u0002\u0002\u0010N\u0003\u0002\u0002\u0002\u0012P\u0003\u0002\u0002\u0002\u0014]\u0003\u0002\u0002\u0002\u0016_\u0003\u0002\u0002\u0002\u0018c\u0003\u0002\u0002\u0002\u001ae\u0003\u0002\u0002\u0002\u001cg\u0003\u0002\u0002\u0002\u001ek\u0003\u0002\u0002\u0002 m\u0003\u0002\u0002\u0002\"#\u0005\u0004\u0003\u0002#\u0003\u0003\u0002\u0002\u0002$&\u0007\f\u0002\u0002%'\u0005\f\u0007\u0002&%\u0003\u0002\u0002\u0002&'\u0003\u0002\u0002\u0002'(\u0003\u0002\u0002\u0002()\u0007\r\u0002\u0002)\u0005\u0003\u0002\u0002\u0002*-\u0005\b\u0005\u0002+-\u0005\n\u0006\u0002,*\u0003\u0002\u0002\u0002,+\u0003\u0002\u0002\u0002-\u0007\u0003\u0002\u0002\u0002.1\u0007\u0011\u0002\u0002/1\u0005 \u0011\u00020.\u0003\u0002\u0002\u00020/\u0003\u0002\u0002\u000216\u0003\u0002\u0002\u000223\u0007\n\u0002\u000237\u0005\u000e\b\u000245\u0007\u000b\u0002\u000257\u0005\u0004\u0003\u000262\u0003\u0002\u0002\u000264\u0003\u0002\u0002\u00027\t\u0003\u0002\u0002\u00028;\u0005\u0004\u0003\u00029;\u0005\u0012\n\u0002:8\u0003\u0002\u0002\u0002:9\u0003\u0002\u0002\u0002;\u000b\u0003\u0002\u0002\u0002<A\u0005\u0006\u0004\u0002=>\u0007\u000e\u0002\u0002>@\u0005\u0006\u0004\u0002?=\u0003\u0002\u0002\u0002@C\u0003\u0002\u0002\u0002A?\u0003\u0002\u0002\u0002AB\u0003\u0002\u0002\u0002B\r\u0003\u0002\u0002\u0002CA\u0003\u0002\u0002\u0002DK\u0005 \u0011\u0002EK\u0005\u0010\t\u0002FK\u0005\u001e\u0010\u0002GK\u0005\u0012\n\u0002HK\u0005\u0004\u0003\u0002IK\u0005\u001c\u000f\u0002JD\u0003\u0002\u0002\u0002JE\u0003\u0002\u0002\u0002JF\u0003\u0002\u0002\u0002JG\u0003\u0002\u0002\u0002JH\u0003\u0002\u0002\u0002JI\u0003\u0002\u0002\u0002K\u000f\u0003\u0002\u0002\u0002LO\u0005\u0014\u000b\u0002MO\u0005\u0016\f\u0002NL\u0003\u0002\u0002\u0002NM\u0003\u0002\u0002\u0002O\u0011\u0003\u0002\u0002\u0002PY\u0007\u000f\u0002\u0002QV\u0005\u000e\b\u0002RS\u0007\u000e\u0002\u0002SU\u0005\u000e\b\u0002TR\u0003\u0002\u0002\u0002UX\u0003\u0002\u0002\u0002VT\u0003\u0002\u0002\u0002VW\u0003\u0002\u0002\u0002WZ\u0003\u0002\u0002\u0002XV\u0003\u0002\u0002\u0002YQ\u0003\u0002\u0002\u0002YZ\u0003\u0002\u0002\u0002Z[\u0003\u0002\u0002\u0002[\\\u0007\u0010\u0002\u0002\\\u0013\u0003\u0002\u0002\u0002]^\u0007\t\u0002\u0002^\u0015\u0003\u0002\u0002\u0002_`\u0007\t\u0002\u0002`a\u0007\u0003\u0002\u0002ab\u0007\t\u0002\u0002b\u0017\u0003\u0002\u0002\u0002cd\t\u0002\u0002\u0002d\u0019\u0003\u0002\u0002\u0002ef\t\u0003\u0002\u0002f\u001b\u0003\u0002\u0002\u0002gh\u0007\b\u0002\u0002h\u001d\u0003\u0002\u0002\u0002il\u0005\u001a\u000e\u0002jl\u0005\u0018\r\u0002ki\u0003\u0002\u0002\u0002kj\u0003\u0002\u0002\u0002l\u001f\u0003\u0002\u0002\u0002mn\u0007\u0012\u0002\u0002n!\u0003\u0002\u0002\u0002\r&,06:AJNVYk";
    public static final ATN _ATN;

    private static String[] makeRuleNames() {
        return new String[]{"file", "block", "variable", "named_basicvar", "unnamed_object", "block_elements", "variable_types", "number", "struct", "var_integer", "var_double", "var_false", "var_true", "var_null", "bool", "string_literal"};
    }

    private static String[] makeLiteralNames() {
        return new String[]{null, "'.'", "'false'", "'FALSE'", "'true'", "'TRUE'", "'null'", null, "':'", "'=>'", "'{'", "'}'", "','", "'['", "']'"};
    }

    private static String[] makeSymbolicNames() {
        return new String[]{null, null, null, null, null, null, null, "INTEGER", "SET", "SET_LONG", "BLOCK_START", "BLOCK_END", "SEPARATOR", "SP_START", "SP_END", "IDENTIFIER", "StringLiteral", "WS", "UnterminatedStringLiteral", "BlockComment"};
    }

    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    public String getGrammarFileName() {
        return "Sxf.g4";
    }

    public String[] getRuleNames() {
        return ruleNames;
    }

    public String getSerializedATN() {
        return _serializedATN;
    }

    public ATN getATN() {
        return _ATN;
    }

    public SxfParser(TokenStream input) {
        super(input);
        this._interp = new ParserATNSimulator((Parser)this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public final FileContext file() throws RecognitionException {
        FileContext _localctx = new FileContext(this._ctx, this.getState());
        this.enterRule(_localctx, 0, 0);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(32);
            this.block();
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final BlockContext block() throws RecognitionException {
        BlockContext _localctx = new BlockContext(this._ctx, this.getState());
        this.enterRule(_localctx, 2, 1);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(34);
            this.match(10);
            this.setState(36);
            this._errHandler.sync((Parser)this);
            int _la = this._input.LA(1);
            if ((_la & 0xFFFFFFC0) == 0 && (1L << _la & 0x1A400L) != 0L) {
                this.setState(35);
                this.block_elements();
            }
            this.setState(38);
            this.match(11);
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final VariableContext variable() throws RecognitionException {
        VariableContext _localctx = new VariableContext(this._ctx, this.getState());
        this.enterRule(_localctx, 4, 2);
        try {
            this.setState(42);
            this._errHandler.sync((Parser)this);
            switch (this._input.LA(1)) {
                case 15: 
                case 16: {
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(40);
                    this.named_basicvar();
                    return _localctx;
                }
                case 10: 
                case 13: {
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(41);
                    this.unnamed_object();
                    return _localctx;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
            return _localctx;
        }
        finally {
            this.exitRule();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final Named_basicvarContext named_basicvar() throws RecognitionException {
        Named_basicvarContext _localctx = new Named_basicvarContext(this._ctx, this.getState());
        this.enterRule(_localctx, 6, 3);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(46);
            this._errHandler.sync((Parser)this);
            switch (this._input.LA(1)) {
                case 15: {
                    this.setState(44);
                    this.match(15);
                    break;
                }
                case 16: {
                    this.setState(45);
                    this.string_literal();
                    break;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
            this.setState(52);
            this._errHandler.sync((Parser)this);
            switch (this._input.LA(1)) {
                case 8: {
                    this.setState(48);
                    this.match(8);
                    this.setState(49);
                    this.variable_types();
                    return _localctx;
                }
                case 9: {
                    this.setState(50);
                    this.match(9);
                    this.setState(51);
                    this.block();
                    return _localctx;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
            return _localctx;
        }
        finally {
            this.exitRule();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final Unnamed_objectContext unnamed_object() throws RecognitionException {
        Unnamed_objectContext _localctx = new Unnamed_objectContext(this._ctx, this.getState());
        this.enterRule(_localctx, 8, 4);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(56);
            this._errHandler.sync((Parser)this);
            switch (this._input.LA(1)) {
                case 10: {
                    this.setState(54);
                    this.block();
                    return _localctx;
                }
                case 13: {
                    this.setState(55);
                    this.struct();
                    return _localctx;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
            return _localctx;
        }
        finally {
            this.exitRule();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final Block_elementsContext block_elements() throws RecognitionException {
        Block_elementsContext _localctx = new Block_elementsContext(this._ctx, this.getState());
        this.enterRule(_localctx, 10, 5);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(58);
            this.variable();
            this.setState(63);
            this._errHandler.sync((Parser)this);
            int _la = this._input.LA(1);
            while (_la == 12) {
                this.setState(59);
                this.match(12);
                this.setState(60);
                this.variable();
                this.setState(65);
                this._errHandler.sync((Parser)this);
                _la = this._input.LA(1);
            }
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final Variable_typesContext variable_types() throws RecognitionException {
        Variable_typesContext _localctx = new Variable_typesContext(this._ctx, this.getState());
        this.enterRule(_localctx, 12, 6);
        try {
            this.setState(72);
            this._errHandler.sync((Parser)this);
            switch (this._input.LA(1)) {
                case 16: {
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(66);
                    this.string_literal();
                    return _localctx;
                }
                case 7: {
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(67);
                    this.number();
                    return _localctx;
                }
                case 2: 
                case 3: 
                case 4: 
                case 5: {
                    this.enterOuterAlt(_localctx, 3);
                    this.setState(68);
                    this.bool();
                    return _localctx;
                }
                case 13: {
                    this.enterOuterAlt(_localctx, 4);
                    this.setState(69);
                    this.struct();
                    return _localctx;
                }
                case 10: {
                    this.enterOuterAlt(_localctx, 5);
                    this.setState(70);
                    this.block();
                    return _localctx;
                }
                case 6: {
                    this.enterOuterAlt(_localctx, 6);
                    this.setState(71);
                    this.var_null();
                    return _localctx;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
            return _localctx;
        }
        finally {
            this.exitRule();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final NumberContext number() throws RecognitionException {
        NumberContext _localctx = new NumberContext(this._ctx, this.getState());
        this.enterRule(_localctx, 14, 7);
        try {
            this.setState(76);
            this._errHandler.sync((Parser)this);
            switch (((ParserATNSimulator)this.getInterpreter()).adaptivePredict(this._input, 7, this._ctx)) {
                case 1: {
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(74);
                    this.var_integer();
                    return _localctx;
                }
                case 2: {
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(75);
                    this.var_double();
                    return _localctx;
                }
            }
            return _localctx;
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
            return _localctx;
        }
        finally {
            this.exitRule();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final StructContext struct() throws RecognitionException {
        StructContext _localctx = new StructContext(this._ctx, this.getState());
        this.enterRule(_localctx, 16, 8);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(78);
            this.match(13);
            this.setState(87);
            this._errHandler.sync((Parser)this);
            int _la = this._input.LA(1);
            if ((_la & 0xFFFFFFC0) == 0 && (1L << _la & 0x124FCL) != 0L) {
                this.setState(79);
                this.variable_types();
                this.setState(84);
                this._errHandler.sync((Parser)this);
                _la = this._input.LA(1);
                while (_la == 12) {
                    this.setState(80);
                    this.match(12);
                    this.setState(81);
                    this.variable_types();
                    this.setState(86);
                    this._errHandler.sync((Parser)this);
                    _la = this._input.LA(1);
                }
            }
            this.setState(89);
            this.match(14);
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    public final Var_integerContext var_integer() throws RecognitionException {
        Var_integerContext _localctx = new Var_integerContext(this._ctx, this.getState());
        this.enterRule(_localctx, 18, 9);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(91);
            this.match(7);
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    public final Var_doubleContext var_double() throws RecognitionException {
        Var_doubleContext _localctx = new Var_doubleContext(this._ctx, this.getState());
        this.enterRule(_localctx, 20, 10);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(93);
            this.match(7);
            this.setState(94);
            this.match(1);
            this.setState(95);
            this.match(7);
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final Var_falseContext var_false() throws RecognitionException {
        Var_falseContext _localctx = new Var_falseContext(this._ctx, this.getState());
        this.enterRule(_localctx, 22, 11);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(97);
            int _la = this._input.LA(1);
            if (_la != 2 && _la != 3) {
                this._errHandler.recoverInline((Parser)this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }
                this._errHandler.reportMatch((Parser)this);
                this.consume();
            }
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final Var_trueContext var_true() throws RecognitionException {
        Var_trueContext _localctx = new Var_trueContext(this._ctx, this.getState());
        this.enterRule(_localctx, 24, 12);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(99);
            int _la = this._input.LA(1);
            if (_la != 4 && _la != 5) {
                this._errHandler.recoverInline((Parser)this);
            } else {
                if (this._input.LA(1) == -1) {
                    this.matchedEOF = true;
                }
                this._errHandler.reportMatch((Parser)this);
                this.consume();
            }
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    public final Var_nullContext var_null() throws RecognitionException {
        Var_nullContext _localctx = new Var_nullContext(this._ctx, this.getState());
        this.enterRule(_localctx, 26, 13);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(101);
            this.match(6);
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final BoolContext bool() throws RecognitionException {
        BoolContext _localctx = new BoolContext(this._ctx, this.getState());
        this.enterRule(_localctx, 28, 14);
        try {
            this.setState(105);
            this._errHandler.sync((Parser)this);
            switch (this._input.LA(1)) {
                case 4: 
                case 5: {
                    this.enterOuterAlt(_localctx, 1);
                    this.setState(103);
                    this.var_true();
                    return _localctx;
                }
                case 2: 
                case 3: {
                    this.enterOuterAlt(_localctx, 2);
                    this.setState(104);
                    this.var_false();
                    return _localctx;
                }
                default: {
                    throw new NoViableAltException((Parser)this);
                }
            }
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
            return _localctx;
        }
        finally {
            this.exitRule();
        }
    }

    public final String_literalContext string_literal() throws RecognitionException {
        String_literalContext _localctx = new String_literalContext(this._ctx, this.getState());
        this.enterRule(_localctx, 30, 15);
        try {
            this.enterOuterAlt(_localctx, 1);
            this.setState(107);
            this.match(16);
        }
        catch (RecognitionException re) {
            _localctx.exception = re;
            this._errHandler.reportError((Parser)this, re);
            this._errHandler.recover((Parser)this, re);
        }
        finally {
            this.exitRule();
        }
        return _localctx;
    }

    static {
        int i;
        RuntimeMetaData.checkVersion((String)"4.9.2", (String)"4.9.3");
        _sharedContextCache = new PredictionContextCache();
        ruleNames = SxfParser.makeRuleNames();
        _LITERAL_NAMES = SxfParser.makeLiteralNames();
        _SYMBOLIC_NAMES = SxfParser.makeSymbolicNames();
        VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (i = 0; i < tokenNames.length; ++i) {
            SxfParser.tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                SxfParser.tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }
            if (tokenNames[i] != null) continue;
            SxfParser.tokenNames[i] = "<INVALID>";
        }
        _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (i = 0; i < _ATN.getNumberOfDecisions(); ++i) {
            SxfParser._decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public static class String_literalContext
    extends ParserRuleContext {
        public TerminalNode StringLiteral() {
            return this.getToken(16, 0);
        }

        public String_literalContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 15;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterString_literal(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitString_literal(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitString_literal(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class BoolContext
    extends ParserRuleContext {
        public Var_trueContext var_true() {
            return (Var_trueContext)this.getRuleContext(Var_trueContext.class, 0);
        }

        public Var_falseContext var_false() {
            return (Var_falseContext)this.getRuleContext(Var_falseContext.class, 0);
        }

        public BoolContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 14;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterBool(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitBool(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitBool(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class Var_nullContext
    extends ParserRuleContext {
        public Var_nullContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 13;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterVar_null(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitVar_null(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitVar_null(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class Var_trueContext
    extends ParserRuleContext {
        public Var_trueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 12;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterVar_true(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitVar_true(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitVar_true(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class Var_falseContext
    extends ParserRuleContext {
        public Var_falseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 11;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterVar_false(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitVar_false(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitVar_false(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class Var_doubleContext
    extends ParserRuleContext {
        public List<TerminalNode> INTEGER() {
            return this.getTokens(7);
        }

        public TerminalNode INTEGER(int i) {
            return this.getToken(7, i);
        }

        public Var_doubleContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 10;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterVar_double(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitVar_double(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitVar_double(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class Var_integerContext
    extends ParserRuleContext {
        public TerminalNode INTEGER() {
            return this.getToken(7, 0);
        }

        public Var_integerContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 9;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterVar_integer(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitVar_integer(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitVar_integer(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class StructContext
    extends ParserRuleContext {
        public TerminalNode SP_START() {
            return this.getToken(13, 0);
        }

        public TerminalNode SP_END() {
            return this.getToken(14, 0);
        }

        public List<Variable_typesContext> variable_types() {
            return this.getRuleContexts(Variable_typesContext.class);
        }

        public Variable_typesContext variable_types(int i) {
            return (Variable_typesContext)this.getRuleContext(Variable_typesContext.class, i);
        }

        public List<TerminalNode> SEPARATOR() {
            return this.getTokens(12);
        }

        public TerminalNode SEPARATOR(int i) {
            return this.getToken(12, i);
        }

        public StructContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 8;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterStruct(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitStruct(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitStruct(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class NumberContext
    extends ParserRuleContext {
        public Var_integerContext var_integer() {
            return (Var_integerContext)this.getRuleContext(Var_integerContext.class, 0);
        }

        public Var_doubleContext var_double() {
            return (Var_doubleContext)this.getRuleContext(Var_doubleContext.class, 0);
        }

        public NumberContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 7;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterNumber(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitNumber(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitNumber(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class Variable_typesContext
    extends ParserRuleContext {
        public String_literalContext string_literal() {
            return (String_literalContext)this.getRuleContext(String_literalContext.class, 0);
        }

        public NumberContext number() {
            return (NumberContext)this.getRuleContext(NumberContext.class, 0);
        }

        public BoolContext bool() {
            return (BoolContext)this.getRuleContext(BoolContext.class, 0);
        }

        public StructContext struct() {
            return (StructContext)this.getRuleContext(StructContext.class, 0);
        }

        public BlockContext block() {
            return (BlockContext)this.getRuleContext(BlockContext.class, 0);
        }

        public Var_nullContext var_null() {
            return (Var_nullContext)this.getRuleContext(Var_nullContext.class, 0);
        }

        public Variable_typesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 6;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterVariable_types(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitVariable_types(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitVariable_types(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class Block_elementsContext
    extends ParserRuleContext {
        public List<VariableContext> variable() {
            return this.getRuleContexts(VariableContext.class);
        }

        public VariableContext variable(int i) {
            return (VariableContext)this.getRuleContext(VariableContext.class, i);
        }

        public List<TerminalNode> SEPARATOR() {
            return this.getTokens(12);
        }

        public TerminalNode SEPARATOR(int i) {
            return this.getToken(12, i);
        }

        public Block_elementsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 5;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterBlock_elements(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitBlock_elements(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitBlock_elements(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class Unnamed_objectContext
    extends ParserRuleContext {
        public BlockContext block() {
            return (BlockContext)this.getRuleContext(BlockContext.class, 0);
        }

        public StructContext struct() {
            return (StructContext)this.getRuleContext(StructContext.class, 0);
        }

        public Unnamed_objectContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 4;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterUnnamed_object(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitUnnamed_object(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitUnnamed_object(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class Named_basicvarContext
    extends ParserRuleContext {
        public TerminalNode IDENTIFIER() {
            return this.getToken(15, 0);
        }

        public String_literalContext string_literal() {
            return (String_literalContext)this.getRuleContext(String_literalContext.class, 0);
        }

        public TerminalNode SET() {
            return this.getToken(8, 0);
        }

        public Variable_typesContext variable_types() {
            return (Variable_typesContext)this.getRuleContext(Variable_typesContext.class, 0);
        }

        public TerminalNode SET_LONG() {
            return this.getToken(9, 0);
        }

        public BlockContext block() {
            return (BlockContext)this.getRuleContext(BlockContext.class, 0);
        }

        public Named_basicvarContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 3;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterNamed_basicvar(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitNamed_basicvar(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitNamed_basicvar(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class VariableContext
    extends ParserRuleContext {
        public Named_basicvarContext named_basicvar() {
            return (Named_basicvarContext)this.getRuleContext(Named_basicvarContext.class, 0);
        }

        public Unnamed_objectContext unnamed_object() {
            return (Unnamed_objectContext)this.getRuleContext(Unnamed_objectContext.class, 0);
        }

        public VariableContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 2;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterVariable(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitVariable(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitVariable(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class BlockContext
    extends ParserRuleContext {
        public TerminalNode BLOCK_START() {
            return this.getToken(10, 0);
        }

        public TerminalNode BLOCK_END() {
            return this.getToken(11, 0);
        }

        public Block_elementsContext block_elements() {
            return (Block_elementsContext)this.getRuleContext(Block_elementsContext.class, 0);
        }

        public BlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 1;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterBlock(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitBlock(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitBlock(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }

    public static class FileContext
    extends ParserRuleContext {
        public BlockContext block() {
            return (BlockContext)this.getRuleContext(BlockContext.class, 0);
        }

        public FileContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public int getRuleIndex() {
            return 0;
        }

        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).enterFile(this);
            }
        }

        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof SxfListener) {
                ((SxfListener)listener).exitFile(this);
            }
        }

        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof SxfVisitor) {
                return (T) ((SxfVisitor)visitor).visitFile(this);
            }
            return (T)visitor.visitChildren((RuleNode)this);
        }
    }
}

