/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.element.impl;

import pisi.unitedmeows.yystal.clazz.prop;
import pisi.unitedmeows.yystal.ui.YWindow;
import pisi.unitedmeows.yystal.ui.element.YElement;
import pisi.unitedmeows.yystal.ui.font.TTFRenderer;
import pisi.unitedmeows.yystal.ui.utils.RenderMethods;
import pisi.unitedmeows.yystal.ui.utils.keyboard.YKeyboardTextInput;

public class YTextbox
extends YElement {
    private TTFRenderer fontRenderer;
    private prop<Float> fontSize = new prop<Float>(Float.valueOf(12.0f));
    private prop<String> placeHolder = new prop<String>("Text Input");
    private YKeyboardTextInput keyboardTextInput;

    @Override
    public void setup() {
        this.keyboardTextInput = new YKeyboardTextInput((YWindow)this.currentWindow.get());
        ((YWindow)this.currentWindow.get()).executeOnThread(() -> {
            boolean wasHidden = !this.show;
            this.show = false;
            this.fontRenderer = new TTFRenderer("samsung", this.fontSize.get().floatValue(), true);
            if (!wasHidden) {
                this.show = true;
            }
            if (this.size.x == 0.0f) {
                this.size.setX(100.0f);
            }
            this.size.setY(this.fontRenderer.height("YYSTAL", this.fontSize.get().floatValue()) / 6.0f);
        });
    }

    @Override
    public void _keyPress(int keyCode) {
        this.keyboardTextInput.press(keyCode);
    }

    @Override
    public void draw() {
        RenderMethods.drawRoundedBorderedRect(this.location.x(), this.location.y(), this.location.x + this.size.x(), this.location.y() + this.size.y(), 1.0f, -13552579, -2236963);
        if (!this.focused()) {
            this.fontRenderer.drawString(this.placeHolder.get(), this.location.x() + 5.0f, this.location.y() + this.size.y() / 2.0f + this.fontSize.get().floatValue() / 6.0f, -2236963, this.fontSize.get().floatValue());
        } else {
            this.fontRenderer.drawString(this.keyboardTextInput.toString(), this.location.x() + 5.0f, this.location.y() + this.size.y() / 2.0f + this.fontSize.get().floatValue() / 6.0f, -1, this.fontSize.get().floatValue());
        }
    }
}

