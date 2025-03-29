/*
 * Decompiled with CFR 0.152.
 */
package stelix.xfile;

import stelix.xfile.SxfBlockBuilder;
import stelix.xfile.SxfStructBuilder;
import stelix.xfile.WriteStyle;

public abstract class SxfBuildElement {
    protected WriteStyle style = WriteStyle.NORMAL;
    private SxfBuildElement owner;

    public abstract void writeObject(int var1, StringBuilder var2);

    public SxfBuildElement setStyle(WriteStyle _style) {
        this.style = _style;
        return this;
    }

    public WriteStyle style() {
        return this.style;
    }

    protected void setOwner(SxfBuildElement _owner) {
        this.owner = _owner;
        if (this.owner != null) {
            this.style = this.owner.style;
        }
    }

    protected <X extends SxfBuildElement> X build() {
        if (this.owner == null) {
            return (X)this;
        }
        return (X)this.owner;
    }

    public SxfStructBuilder buildStruct() {
        return (SxfStructBuilder)this.build();
    }

    public SxfBlockBuilder buildBlock() {
        return (SxfBlockBuilder)this.build();
    }
}

