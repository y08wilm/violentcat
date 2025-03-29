/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.bot.events;

import pisi.unitedmeows.eventapi.event.Event;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Reaction;

public class MessageReactionRemoveEvent
extends Event {
    protected Reaction reaction;

    public MessageReactionRemoveEvent(Reaction _reaction) {
        this.reaction = _reaction;
    }

    public Reaction reaction() {
        return this.reaction;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MessageReactionRemoveEvent{");
        sb.append("reaction=").append(this.reaction);
        sb.append('}');
        return sb.toString();
    }
}

