/*
 * Decompiled with CFR 0.152.
 */
package stelix.xfile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import stelix.xfile.Commons;
import stelix.xfile.SxfBlockBuilder;
import stelix.xfile.SxfBuildElement;
import stelix.xfile.SxfElementPack;
import stelix.xfile.WriteStyle;

public class SxfStructBuilder
extends SxfBuildElement {
    private final List<SxfElementPack<Object, SxfStructBuilder>> elements = new ArrayList<SxfElementPack<Object, SxfStructBuilder>>();

    protected SxfStructBuilder(SxfBuildElement _owner) {
        this.style = _owner == null ? WriteStyle.INLINE : _owner.style();
    }

    public static SxfStructBuilder create() {
        return new SxfStructBuilder(null);
    }

    public static SxfStructBuilder create(SxfBuildElement _owner) {
        return new SxfStructBuilder(_owner);
    }

    public SxfElementPack<Object, SxfStructBuilder> add(Object element) {
        SxfElementPack<Object, SxfStructBuilder> objectPack = new SxfElementPack<Object, SxfStructBuilder>(element, this);
        this.elements.add(objectPack);
        if (element instanceof SxfBuildElement) {
            ((SxfBuildElement)element).setOwner(this);
        }
        return objectPack;
    }

    public List<SxfElementPack<Object, SxfStructBuilder>> elements() {
        return this.elements;
    }

    @Override
    public void writeObject(int spaceCount, StringBuilder stringBuilder) {
        stringBuilder.append("[");
        Iterator<SxfElementPack<Object, SxfStructBuilder>> elementIterator = this.elements.iterator();
        if (elementIterator.hasNext()) {
            boolean hasNext;
            do {
                SxfElementPack<Object, SxfStructBuilder> pack = elementIterator.next();
                hasNext = elementIterator.hasNext();
                Object element = pack.down();
                if (element instanceof SxfBuildElement) {
                    if (this.style == WriteStyle.NORMAL) {
                        if (pack.comment() != null && !pack.comment().isEmpty()) {
                            stringBuilder.append(System.lineSeparator());
                            Commons.putSpace(stringBuilder, spaceCount + 1);
                            stringBuilder.append("$;").append(pack.comment()).append(" ;$");
                        }
                        stringBuilder.append(System.lineSeparator());
                        Commons.putSpace(stringBuilder, spaceCount + 1);
                    } else {
                        stringBuilder.append(" ");
                    }
                    if (element instanceof SxfStructBuilder) {
                        ((SxfStructBuilder)element).writeObject(spaceCount + 1, stringBuilder);
                    } else if (element instanceof SxfBlockBuilder) {
                        ((SxfBlockBuilder)element).writeObject(spaceCount + 1, stringBuilder);
                    }
                } else {
                    if (this.style == WriteStyle.NORMAL) {
                        stringBuilder.append(System.lineSeparator());
                        Commons.putSpace(stringBuilder, spaceCount + 1);
                    } else {
                        stringBuilder.append(" ");
                    }
                    stringBuilder.append(Commons.writeVar(element));
                    if (this.style == WriteStyle.NORMAL && pack.comment() != null && !pack.comment().isEmpty()) {
                        stringBuilder.append(" $; ").append(pack.comment()).append(" ;$");
                    }
                }
                if (!hasNext) continue;
                if (this.style == WriteStyle.NORMAL) {
                    stringBuilder.append(", ");
                    continue;
                }
                stringBuilder.append(", ");
            } while (hasNext);
        }
        if (this.style == WriteStyle.NORMAL) {
            stringBuilder.append(System.lineSeparator());
            Commons.putSpace(stringBuilder, spaceCount);
        } else {
            stringBuilder.append(" ");
        }
        stringBuilder.append("]");
    }

    @Override
    public SxfStructBuilder setStyle(WriteStyle _style) {
        return (SxfStructBuilder)super.setStyle(_style);
    }
}

