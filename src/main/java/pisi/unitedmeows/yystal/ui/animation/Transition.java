/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.animation;

import java.util.function.Supplier;

public class Transition {
    Supplier<Boolean> state;
    private Supplier<Integer> startColor;
    private Supplier<Integer> endColor;
    private int color;
    private float offSet;

    public Transition(Supplier<Integer> _startColor, Supplier<Integer> _endColor, float _offSet) {
        this.startColor = _startColor;
        this.endColor = _endColor;
        this.offSet = _offSet;
        this.color = this.startColor.get();
    }

    public void tick() {
        this.color = this.state.get() != false ? Transition.fadeBetween(this.color, this.endColor.get(), this.offSet) : Transition.fadeBetween(this.color, this.startColor.get(), this.offSet);
    }

    public Transition state(Supplier<Boolean> _state) {
        this.state = _state;
        return this;
    }

    public int color() {
        return this.color;
    }

    private static int fadeBetween(int color1, int color2, float offset) {
        if (offset > 1.0f) {
            offset = 1.0f - offset % 1.0f;
        }
        double invert = 1.0f - offset;
        int r = (int)((double)(color1 >> 16 & 0xFF) * invert + (double)((float)(color2 >> 16 & 0xFF) * offset));
        int g = (int)((double)(color1 >> 8 & 0xFF) * invert + (double)((float)(color2 >> 8 & 0xFF) * offset));
        int b = (int)((double)(color1 & 0xFF) * invert + (double)((float)(color2 & 0xFF) * offset));
        int a = (int)((double)(color1 >> 24 & 0xFF) * invert + (double)((float)(color2 >> 24 & 0xFF) * offset));
        return (a & 0xFF) << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
    }
}

