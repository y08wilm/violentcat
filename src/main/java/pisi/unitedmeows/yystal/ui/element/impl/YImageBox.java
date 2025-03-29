/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.element.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import pisi.unitedmeows.yystal.clazz.prop;
import pisi.unitedmeows.yystal.ui.YUI;
import pisi.unitedmeows.yystal.ui.YWindow;
import pisi.unitedmeows.yystal.ui.element.YElement;
import pisi.unitedmeows.yystal.ui.texture.YTexture;
import pisi.unitedmeows.yystal.ui.utils.RenderMethods;
import pisi.unitedmeows.yystal.ui.utils.Vertex2f;

public class YImageBox
extends YElement {
    private YTexture imageTexture;
    public prop<BufferedImage> image = new prop<BufferedImage>(null){

        @Override
        public void set(BufferedImage newValue) {
            this.value = newValue;
            ((YWindow)YImageBox.this.currentWindow.get()).executeOnThread(() -> {
                if (YImageBox.this.imageTexture != null) {
                    YImageBox.this.imageTexture.close();
                }
                YImageBox.this.imageTexture = YUI.loadTexture(newValue);
            });
        }
    };

    public YImageBox() {
        this.size(new Vertex2f(100.0f, 100.0f));
    }

    @Override
    public void draw() {
        if (this.imageTexture != null) {
            RenderMethods.drawRect(this.renderX(), this.renderY(), this.renderX() + this.size().x(), this.renderY() + this.size().y(), Color.GREEN.getRGB());
        }
    }
}

