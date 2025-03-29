/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.events;

import pisi.unitedmeows.yystal.clazz.delegate;
import pisi.unitedmeows.yystal.clazz.ref;

@FunctionalInterface
public interface WindowExitEvent
extends delegate {
    public void onWindowExit(ref<Boolean> var1);
}

