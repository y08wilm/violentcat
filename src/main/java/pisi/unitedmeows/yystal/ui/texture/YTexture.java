/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.texture;

import pisi.unitedmeows.yystal.ui.YUI;
import pisi.unitedmeows.yystal.ui.utils.RenderMethods;
import pisi.unitedmeows.yystal.utils.IDisposable;

public class YTexture
implements IDisposable {
    private int textureId;
    private boolean loaded;

    public YTexture(int _textureId) {
        this.textureId = _textureId;
        this.loaded = true;
    }

    @Override
    public void close() {
        YUI.freeTexture(this.textureId());
        this.loaded = false;
    }

    public int textureId() {
        return this.textureId;
    }

    public boolean loaded() {
        return this.loaded;
    }

    public void draw(float x, float y, float width, float height) {
        RenderMethods.drawImage(this.textureId(), x, y, width, height);
    }
}

