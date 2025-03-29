/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.element.simple;

import java.awt.Color;
import pisi.unitedmeows.yystal.ui.element.YElement;
import pisi.unitedmeows.yystal.ui.utils.RenderMethods;

public class YBackgroundColor
extends YElement {
    private int color;

    public YBackgroundColor(Color _color) {
        this.color = _color.getRGB();
    }

    @Override
    public void draw() {
        RenderMethods.drawRect(this.container.renderX(), this.container.renderY(), this.container.renderX() + this.container.size().x(), this.container.renderY() + this.container.size().y(), this.color);
    }

    @Override
    public boolean isMouseOver(float mouseX, float mouseY) {
        return false;
    }
}

