/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server.pack.events;

import pisi.unitedmeows.yystal.clazz.delegate;
import pisi.unitedmeows.yystal.networking.simple.server.pack.YSignal;

public interface YCSignalReceived
extends delegate {
    public void onDataReceived(YSignal var1);
}

