/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.bot.gateway;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.bot.events.MessageCreateEvent;
import pisi.unitedmeows.violentcat.bot.events.MessageDeleteEvent;
import pisi.unitedmeows.violentcat.bot.events.MessageReactionAddEvent;
import pisi.unitedmeows.violentcat.bot.events.MessageReactionRemoveEvent;
import pisi.unitedmeows.violentcat.bot.events.MessageUpdateEvent;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Reaction;
import pisi.unitedmeows.violentcat.shared.packet.NetworkHandler;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VGuildCreatePacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VHeartbeatConfirmPacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VHeartbeatPacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VMessageCreatePacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VMessageDeletePacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VMessageReactionAddPacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VMessageReactionRemovePacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VMessageUpdatePacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterProcessor;

public class BotNetworkHandler
extends NetworkHandler {
    private DiscordBot discordBot;

    public BotNetworkHandler(DiscordBot _discordBot) {
        super(BotNetworkHandler.class);
        this.discordBot = _discordBot;
    }

    @RegisterProcessor(value=PacketHeaders.GUILD_CREATE)
    public void messageProcessor(VGuildCreatePacket packet) {
        packet.guild().bind(this.discordBot);
        this.discordBot.guilds().add(packet.guild());
        System.out.println(packet.guild());
    }

    @RegisterProcessor(value=PacketHeaders.MESSAGE_CREATE)
    public void messageCreateProcessor(VMessageCreatePacket packet) {
        Message message = packet.message();
        message.bind(this.discordBot);
        this.discordBot.eventSystem().fire(new MessageCreateEvent(message));
    }

    @RegisterProcessor(value=PacketHeaders.MESSAGE_UPDATE)
    public void messageUpdateProcessor(VMessageUpdatePacket packet) {
        Message message = packet.message();
        message.bind(this.discordBot);
        this.discordBot.eventSystem().fire(new MessageUpdateEvent(message));
    }

    @RegisterProcessor(value=PacketHeaders.MESSAGE_DELETE)
    public void messageDeleteProcessor(VMessageDeletePacket packet) {
        Message message = packet.message();
        message.bind(this.discordBot);
        this.discordBot.eventSystem().fire(new MessageDeleteEvent(message.id(), message.channelId()));
    }

    @RegisterProcessor(value=PacketHeaders.MESSAGE_REACTION_ADD)
    public void reactionAddProcessor(VMessageReactionAddPacket packet) {
        Reaction reaction = packet.reaction();
        reaction.bind(this.discordBot);
        this.discordBot.eventSystem().fire(new MessageReactionAddEvent(reaction));
    }

    @RegisterProcessor(value=PacketHeaders.MESSAGE_REACTION_REMOVE)
    public void reactionRemoveProcessor(VMessageReactionRemovePacket packet) {
        Reaction reaction = packet.reaction();
        reaction.bind(this.discordBot);
        this.discordBot.eventSystem().fire(new MessageReactionRemoveEvent(reaction));
    }

    @RegisterProcessor(value=PacketHeaders.HEARTBEAT_INTERVAL)
    public void heartbeatProcessor(VHeartbeatPacket packet) {
        this.discordBot.gateway().setupHeartbeat((int)packet.heartbeatInterval());
        this.discordBot.gateway().beatNext();
    }

    @RegisterProcessor(value=PacketHeaders.HEARTBEAT_CONFIRM)
    public void heartbeatConfirm(VHeartbeatConfirmPacket packet) {
        this.discordBot.gateway().sequence(packet.sequence());
        this.discordBot.gateway().beatNext();
    }
}

