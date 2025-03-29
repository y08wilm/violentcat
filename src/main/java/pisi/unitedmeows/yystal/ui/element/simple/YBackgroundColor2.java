/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.element.simple;

import java.awt.Color;
import pisi.unitedmeows.yystal.ui.element.YElement;
import pisi.unitedmeows.yystal.ui.utils.RenderMethods;
import pisi.unitedmeows.yystal.ui.utils.Vertex2f;

public class YBackgroundColor2
extends YElement {
    private int startColor;
    private int endColor;

    public YBackgroundColor2(Color _startColor, Color _endColor) {
        this.startColor = _startColor.getRGB();
        this.endColor = _endColor.getRGB();
    }

    @Override
    public void draw() {
        Vertex2f size = this.container.size();
        RenderMethods.drawRect(this.container.renderX(), this.container.renderY(), size.x(), size.y(), this.startColor, this.endColor);
    }

    @Override
    public boolean isMouseOver(float mouseX, float mouseY) {
        return false;
    }
}

