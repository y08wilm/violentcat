/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server.pack.events;

import pisi.unitedmeows.yystal.clazz.delegate;
import pisi.unitedmeows.yystal.networking.simple.server.YSocketClient;
import pisi.unitedmeows.yystal.networking.simple.server.pack.YSignal;

public interface YSSignalReceived
extends delegate {
    public void onSignalReceived(YSocketClient var1, YSignal var2);
}

