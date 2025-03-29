/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.events;

import pisi.unitedmeows.yystal.clazz.delegate;

@FunctionalInterface
public interface CDataReceivedEvent
extends delegate {
    public void onDataReceived(byte[] var1);
}

