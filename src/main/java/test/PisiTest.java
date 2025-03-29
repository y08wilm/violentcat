/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  pisi.unitedmeows.eventapi.event.Event
 *  pisi.unitedmeows.eventapi.event.Event$Weight
 *  pisi.unitedmeows.eventapi.event.listener.Listener
 *  pisi.unitedmeows.eventapi.system.BasicEventSystem
 */
package test;

import pisi.unitedmeows.eventapi.event.Event;
import pisi.unitedmeows.eventapi.event.listener.Listener;
import pisi.unitedmeows.eventapi.system.BasicEventSystem;
import test.PisiTestEvent;
import test.PisiTestEvent2;

public class PisiTest {
    public static BasicEventSystem basicEventSystem = new BasicEventSystem();
    public Listener<PisiTestEvent> listener = new Listener(event -> System.out.println("test")).listen(new Class[]{PisiTestEvent2.class, PisiTestEvent.class});
    public Listener<PisiTestEvent> listener2 = new Listener(event -> {}).ignoreCanceled().weight(Event.Weight.MEDIUM);
    public static long time;

    public static void main(String[] args) {
        PisiTest pisiTest = new PisiTest();
        basicEventSystem.subscribeAll((Object)pisiTest);
        PisiTest.startWatcher();
        for (int i = 5; i > 0; --i) {
            basicEventSystem.fire((Event)new PisiTestEvent2());
        }
        basicEventSystem.fire((Event)new PisiTestEvent());
        System.out.print("1M call took ");
        PisiTest.stopWatcher();
    }

    public static void startWatcher() {
        time = System.currentTimeMillis();
    }

    public static void stopWatcher() {
        long lastTime = System.currentTimeMillis();
        System.out.println(lastTime - time + "ms");
    }
}

