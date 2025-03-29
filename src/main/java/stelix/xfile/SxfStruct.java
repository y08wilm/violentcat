/*
 * Decompiled with CFR 0.152.
 */
package stelix.xfile;

import java.util.ArrayList;
import java.util.List;
import stelix.xfile.ISxfObject;
import stelix.xfile.SxfBlock;

public class SxfStruct
implements ISxfObject {
    private final List<Object> elements = new ArrayList<Object>();

    public List<Object> elements() {
        return this.elements;
    }

    public <X> X element(int index) {
        return (X)this.elements.get(index);
    }

    public <X extends SxfBlock> X elementAsBlock(int index) {
        return (X)((SxfBlock)this.element(index));
    }

    public <X extends SxfStruct> X elementAsStruct(int index) {
        return (X)((SxfStruct)this.element(index));
    }
}

