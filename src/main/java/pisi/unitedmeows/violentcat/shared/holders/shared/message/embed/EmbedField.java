/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.Expose
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.message.embed;

import com.google.gson.annotations.Expose;

public class EmbedField {
    @Expose
    protected String name;
    @Expose
    protected String value;
    @Expose
    protected boolean inline;

    public EmbedField name(String _name) {
        this.name = _name;
        return this;
    }

    public EmbedField value(String _value) {
        this.value = _value;
        return this;
    }

    public EmbedField inline(boolean _inline) {
        this.inline = _inline;
        return this;
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }

    public boolean isInline() {
        return this.inline;
    }
}

