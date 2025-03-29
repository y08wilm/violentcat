/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.utils;

import java.util.function.Supplier;
import pisi.unitedmeows.yystal.ui.YWindow;

public class yuiprop<X> {
    private YWindow window;
    protected X value;

    public yuiprop(YWindow _window) {
        this.window = _window;
    }

    public yuiprop(YWindow _window, Supplier<X> _value) {
        this(_window);
        this.set(_value);
    }

    public void set(Supplier<X> _value) {
        this.window.executeOnThread(() -> {
            this.value = _value.get();
        });
    }

    public X get() {
        return this.value;
    }
}

