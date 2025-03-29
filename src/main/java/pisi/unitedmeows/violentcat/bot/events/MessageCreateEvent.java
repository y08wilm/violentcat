/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.bot.events;

import pisi.unitedmeows.eventapi.event.Event;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;

public class MessageCreateEvent
extends Event {
    protected Message message;

    public MessageCreateEvent(Message _message) {
        this.message = _message;
    }

    public Message message() {
        return this.message;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MessageCreateEvent{");
        sb.append("message=").append(this.message);
        sb.append('}');
        return sb.toString();
    }
}

