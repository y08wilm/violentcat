/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.eventapi.system;

import java.lang.reflect.Field;
import pisi.unitedmeows.eventapi.event.Event;
import pisi.unitedmeows.eventapi.event.listener.Listener;

public interface IEventSystem {
    public void subscribeAll(Object var1);

    public void subscribeAll(Object var1, Listener<?> ... var2);

    public void unsubscribeAll(Object var1);

    public void subscribe(Object var1, Listener<?> var2, Field var3);

    public void subscribe(Listener<?> var1, Object var2);

    public void unsubscribe(Listener<?> var1);

    public void fire(Event var1);
}

