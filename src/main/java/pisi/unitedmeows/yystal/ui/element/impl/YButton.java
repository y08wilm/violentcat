/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.element.impl;

import java.awt.Color;
import java.util.function.Supplier;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.clazz.event;
import pisi.unitedmeows.yystal.clazz.prop;
import pisi.unitedmeows.yystal.parallel.repeaters.Repeater;
import pisi.unitedmeows.yystal.parallel.utils.YTimer;
import pisi.unitedmeows.yystal.ui.YWindow;
import pisi.unitedmeows.yystal.ui.animation.Transition;
import pisi.unitedmeows.yystal.ui.element.YElement;
import pisi.unitedmeows.yystal.ui.events.ButtonClickEvent;
import pisi.unitedmeows.yystal.ui.font.TTFRenderer;
import pisi.unitedmeows.yystal.ui.utils.RenderMethods;

public class YButton
extends YElement {
    public prop<String> text = new prop<String>("Button");
    public prop<Float> fontSize = new prop<Float>(Float.valueOf(13.0f));
    public prop<Boolean> autoSize = new prop<Boolean>(false);
    public prop<Color> color = new prop<Color>(new Color(255, 255, 255));
    private boolean pressing;
    final int INACTIVE_BACK = -9144951;
    final int ACTIVE_BACK = new Color(9, 8, 8).getRGB();
    final int BORDER_COLOR = -6250587;
    private Transition transition = new Transition(() -> -9144951, () -> this.ACTIVE_BACK, 0.1f).state(() -> (Boolean)this.isMouseOver.get());
    public event<ButtonClickEvent> clickEvent = new event();
    private TTFRenderer ttfRenderer;
    private Repeater repeater = YYStal.repeater(50).onTick(() -> {});
    private YTimer timer = new YTimer(20L){

        @Override
        public void tick() {
        }
    };

    @Override
    public void setup() {
        boolean wasHidden = !this.show;
        this.show = false;
        this.font(() -> {
            TTFRenderer renderer = new TTFRenderer("comfortaa", this.fontSize.get().floatValue(), true);
            if (!wasHidden) {
                this.show = true;
            }
            return renderer;
        });
    }

    @Override
    public void draw() {
        int backColor = this.pressing ? -7929382 : this.transition.color();
        RenderMethods.drawRoundedBorderedRect(this.renderX(), this.renderY(), this.renderX() + this.size.x(), this.renderY() + this.size.y(), 1.0f, backColor, -6250587);
        this.ttfRenderer.drawStringCentered(this.text.get(), this.renderX() + this.size.x() / 2.0f + 2.0f, this.renderY() + this.size.y() / 2.0f + this.fontSize.get().floatValue() / 4.0f, this.color.get().getRGB(), this.fontSize.get().floatValue(), TTFRenderer.DrawType.NORMAL);
        this.transition.tick();
    }

    @Override
    public void _mouseRelease(int mouseButton) {
        this.clickEvent.fire(new Object[0]);
        this.pressing = false;
        super._mouseRelease(mouseButton);
    }

    @Override
    public void _mouseClick(int mouseButton) {
        this.pressing = true;
        super._mouseClick(mouseButton);
    }

    @Override
    public void tick() {
        if (this.autoSize.get().booleanValue()) {
            float width = this.ttfRenderer.width(this.text.get(), this.fontSize.get().floatValue()) + 16.0f;
            float height = this.ttfRenderer.height(this.text.get(), this.fontSize.get().floatValue()) / 6.0f;
            this.size(width, height);
        }
        super.tick();
    }

    public void font(Supplier<TTFRenderer> fontSupplier) {
        ((YWindow)this.currentWindow.get()).executeOnThread(() -> {
            this.ttfRenderer = (TTFRenderer)fontSupplier.get();
        });
    }
}

