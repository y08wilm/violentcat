/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.events;

import pisi.unitedmeows.yystal.clazz.delegate;
import pisi.unitedmeows.yystal.clazz.ref;
import pisi.unitedmeows.yystal.networking.simple.server.YSocketClient;

public interface SEchoEvent
extends delegate {
    public void onDataReceived(YSocketClient var1, byte[] var2, ref<Boolean> var3);
}

