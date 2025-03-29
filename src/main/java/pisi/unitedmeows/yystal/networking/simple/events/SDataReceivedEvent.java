/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.events;

import pisi.unitedmeows.yystal.clazz.delegate;
import pisi.unitedmeows.yystal.networking.simple.server.YSocketClient;

public interface SDataReceivedEvent
extends delegate {
    public void onClientDataReceived(YSocketClient var1, byte[] var2);
}

