/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.shared.packet;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterProcessor;

public class NetworkHandler {
    private Map<PacketHeaders, Method> processorMap = new HashMap<PacketHeaders, Method>();

    public NetworkHandler(Class<? extends NetworkHandler> type) {
        for (Method method : type.getDeclaredMethods()) {
            RegisterProcessor registerProcessor = method.getAnnotation(RegisterProcessor.class);
            if (registerProcessor == null) continue;
            System.out.println("Registered Processor " + (Object)((Object)registerProcessor.value()));
            this.processorMap.put(registerProcessor.value(), method);
        }
    }

    public void process(PacketHeaders header, VPacket packet) {
        if (packet == null) {
            return;
        }
        try {
            Method processor = this.processorMap.getOrDefault((Object)header, null);
            if (processor != null) {
                if (!processor.isAccessible()) {
                    processor.setAccessible(true);
                }
                processor.invoke(this, packet);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

