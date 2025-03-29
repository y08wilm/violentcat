/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.element.impl;

import java.awt.Color;
import java.util.function.Supplier;
import pisi.unitedmeows.yystal.clazz.prop;
import pisi.unitedmeows.yystal.ui.YWindow;
import pisi.unitedmeows.yystal.ui.element.YElement;
import pisi.unitedmeows.yystal.ui.font.TTFRenderer;

public class YLabel
extends YElement {
    public prop<String> text = new prop();
    public prop<Color> color = new prop<Color>(Color.BLACK);
    public prop<Float> fontSize = new prop<Float>(Float.valueOf(15.0f));
    public prop<TTFRenderer.DrawType> drawType = new prop<TTFRenderer.DrawType>(TTFRenderer.DrawType.NORMAL);
    private TTFRenderer ttfRenderer;

    public YLabel() {
        this("Label");
    }

    public YLabel(String _text) {
        this.text.set(_text);
    }

    @Override
    public void setup() {
        boolean wasHidden = !this.show;
        this.show = false;
        this.font(() -> {
            TTFRenderer renderer = new TTFRenderer("arial", 16.0f, true);
            if (!wasHidden) {
                this.show = true;
            }
            return renderer;
        });
    }

    @Override
    public void draw() {
        this.ttfRenderer.drawString(this.text.get(), this.location().x, this.location().y, this.color.get().getRGB(), this.fontSize.get().floatValue(), this.drawType.get());
        this.size(this.ttfRenderer.width(this.text.get(), this.fontSize.get().floatValue()), this.fontSize.get().floatValue());
    }

    public void font(Supplier<TTFRenderer> fontSupplier) {
        ((YWindow)this.currentWindow.get()).executeOnThread(() -> {
            this.ttfRenderer = (TTFRenderer)fontSupplier.get();
        });
    }
}

