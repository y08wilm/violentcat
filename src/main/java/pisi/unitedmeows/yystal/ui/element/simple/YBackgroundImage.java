/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.element.simple;

import java.awt.image.BufferedImage;
import pisi.unitedmeows.yystal.ui.YUI;
import pisi.unitedmeows.yystal.ui.element.YElement;
import pisi.unitedmeows.yystal.ui.texture.YTexture;
import pisi.unitedmeows.yystal.ui.utils.RenderMethods;

public class YBackgroundImage
extends YElement {
    private int textureId = -1;
    private Object texture;

    public YBackgroundImage(BufferedImage image) {
        this.texture = image;
    }

    public YBackgroundImage(YTexture _texture) {
        this.textureId = _texture.textureId();
    }

    public YBackgroundImage(String textureName) {
        this.textureId = YUI.texture(textureName).textureId();
    }

    @Override
    public void setup() {
        if (this.texture instanceof BufferedImage) {
            this.textureId = YUI.loadTexture((BufferedImage)this.texture).textureId();
        }
    }

    @Override
    public void draw() {
        RenderMethods.drawImage(this.textureId, this.container.renderX(), this.container.renderY(), this.container.size().x(), this.container.size().y());
    }

    @Override
    public boolean isMouseOver(float mouseX, float mouseY) {
        return false;
    }
}

