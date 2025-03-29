/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.ConfigurationSection
 *  pisi.unitedmeows.eventapi.event.listener.Listener
 *  pisi.unitedmeows.violentcat.bot.DiscordBot
 *  pisi.unitedmeows.violentcat.bot.DiscordBotBuilder
 *  pisi.unitedmeows.violentcat.bot.bukkit.ConfigurationManager
 *  pisi.unitedmeows.violentcat.bot.events.MessageCreateEvent
 *  pisi.unitedmeows.violentcat.bot.events.MessageDeleteEvent
 *  pisi.unitedmeows.violentcat.bot.events.MessageReactionAddEvent
 *  pisi.unitedmeows.violentcat.bot.events.MessageReactionRemoveEvent
 *  pisi.unitedmeows.violentcat.bot.events.MessageUpdateEvent
 *  pisi.unitedmeows.violentcat.shared.holders.Availability
 *  pisi.unitedmeows.violentcat.shared.holders.Status
 *  pisi.unitedmeows.violentcat.shared.holders.shared.message.Attachment
 *  pisi.unitedmeows.violentcat.shared.holders.shared.message.Message
 *  pisi.unitedmeows.violentcat.shared.holders.shared.message.MessageReference
 *  pisi.unitedmeows.violentcat.shared.holders.shared.message.Reaction
 *  pisi.unitedmeows.violentcat.shared.holders.shared.user.BasicUser
 *  pisi.unitedmeows.yystal.utils.kThread
 */
package test;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.bukkit.configuration.ConfigurationSection;

import pisi.unitedmeows.eventapi.event.listener.Listener;
import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.bot.DiscordBotBuilder;
import pisi.unitedmeows.violentcat.bot.bukkit.ConfigurationManager;
import pisi.unitedmeows.violentcat.bot.events.MessageCreateEvent;
import pisi.unitedmeows.violentcat.bot.events.MessageDeleteEvent;
import pisi.unitedmeows.violentcat.bot.events.MessageReactionAddEvent;
import pisi.unitedmeows.violentcat.bot.events.MessageReactionRemoveEvent;
import pisi.unitedmeows.violentcat.bot.events.MessageUpdateEvent;
import pisi.unitedmeows.violentcat.shared.holders.Availability;
import pisi.unitedmeows.violentcat.shared.holders.Status;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Attachment;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.MessageReference;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Reaction;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.BasicUser;
import pisi.unitedmeows.yystal.utils.kThread;

public class Test {
	protected String token;
	protected String userId;
	protected DiscordBot discordBot;
	protected ConfigurationManager root;
	protected ConfigurationSection config;
	protected List<String> channelList;
	protected List<String> dontAtMentionTheseUserIds;
	protected boolean atMentionUsers;
	protected List<String> emojis = new ArrayList<String>();
	protected HashMap<String, String> userIdMap = new HashMap<>();
	protected HashMap<String, String> messageIdMap = new HashMap<>();
	protected HashMap<String, HashMap<String, String>> messageIdMap2 = new HashMap<>();
	protected HashMap<String, HashMap<String, String>> messageIdMap3 = new HashMap<>();
	protected HashMap<String, String> userIdEmojiMap = new HashMap<>();
	protected Listener<MessageCreateEvent> messageCreateListener;
	protected Listener<MessageUpdateEvent> messageUpdateListener;
	protected Listener<MessageDeleteEvent> messageDeleteListener;
	protected Listener<MessageReactionAddEvent> messageReactionAddListener;
	protected Listener<MessageReactionRemoveEvent> messageReactionRemoveListener;

	public Test() {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));
		System.out.println(new Date() + "");
		long startTime = System.currentTimeMillis();
		this.root = new ConfigurationManager();
		this.root.setup(new File("config.yml"));
		this.config = this.root.getData();
		Thread thread = new Thread(() -> {
			while (true) {
				this.root.saveData();
				try {
					Thread.sleep(20000L);
					continue;
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		});
		thread.start();
		this.emojis.add(":dog:");
		this.emojis.add(":cat:");
		this.emojis.add(":mouse:");
		this.emojis.add(":hamster:");
		this.emojis.add(":rabbit:");
		this.emojis.add(":fox:");
		this.emojis.add(":bear:");
		this.emojis.add(":panda_face:");
		this.emojis.add(":polar_bear:");
		this.emojis.add(":koala:");
		this.emojis.add(":tiger:");
		this.emojis.add(":lion_face:");
		this.emojis.add(":cow:");
		this.emojis.add(":pig:");
		this.emojis.add(":frog:");
		this.emojis.add(":monkey_face:");
		this.emojis.add(":chicken:");
		this.emojis.add(":penguin:");
		this.emojis.add(":hatching_chick:");
		this.emojis.add(":duck:");
		this.emojis.add(":eagle:");
		this.emojis.add(":owl:");
		this.emojis.add(":bat:");
		this.emojis.add(":wolf:");
		this.emojis.add(":boar:");
		this.emojis.add(":unicorn:");
		this.emojis.add(":bee:");
		this.emojis.add(":butterfly:");
		this.emojis.add(":snail:");
		this.emojis.add(":turtle:");
		Collections.shuffle(this.emojis);
		if (!this.config.contains("atMentionUsers")) {
			this.config.set("atMentionUsers", (Object) false);
		}
		if (!this.config.contains("token")) {
			this.config.set("token", (Object) "abc");
		}
		if (!this.config.contains("userId")) {
			this.config.set("userId", (Object) "123");
		}
		if (!this.config.contains("channelList")) {
			ArrayList<String> channelList = new ArrayList<String>();
			channelList.add("123");
			channelList.add("123");
			channelList.add("123");
			this.config.set("channelList", channelList);
		}
		if (!this.config.contains("dontAtMentionTheseUserIds")) {
			ArrayList<String> dontAtMentionTheseUserIds = new ArrayList<String>();
			dontAtMentionTheseUserIds.add("123");
			dontAtMentionTheseUserIds.add("123");
			dontAtMentionTheseUserIds.add("123");
			this.config.set("dontAtMentionTheseUserIds",
					(Object) dontAtMentionTheseUserIds);
		}
		this.channelList = this.config.getStringList("channelList");
		this.dontAtMentionTheseUserIds = this.config
				.getStringList("dontAtMentionTheseUserIds");
		for (String channel : this.channelList) {
			this.messageIdMap2.put(channel, new HashMap());
			this.messageIdMap3.put(channel, new HashMap());
		}
		this.atMentionUsers = this.config.getBoolean("atMentionUsers");
		this.token = this.config.getString("token");
		this.userId = this.config.getString("userId");
		this.discordBot = DiscordBotBuilder.create().token(this.token)
				.intents(65253).onMobile(true).build();
		this.discordBot.login();
		this.messageCreateListener = new Listener<MessageCreateEvent>(
				event -> {
					StringBuilder sb;
					System.out.println(event);
					String userId = event.message().author().id();
					String channelId = event.message().channelId();
					String message = event.message().content();
					switch (event.message().type()) {
					case 1: {
						if (this.atMentionUsers) {
							message = String.format(
									"has added <@%s> to the group",
									((BasicUser) event.message().mentions()
											.get((int) 0)).id);
							break;
						}
						message = String.format(
								"has added %s to the group",
								((BasicUser) event.message().mentions()
										.get((int) 0)).username);
						break;
					}
					case 2: {
						if (this.atMentionUsers) {
							message = String.format(
									"has removed <@%s> from the group",
									((BasicUser) event.message().mentions()
											.get((int) 0)).id);
							break;
						}
						message = String.format(
								"has removed %s from the group",
								((BasicUser) event.message().mentions()
										.get((int) 0)).username);
						break;
					}
					}
					String messageId = event.message().id();
					if (message.equals("?list")) {
						sb = new StringBuilder();
						for (Map.Entry<String, String> entry : this.userIdMap
								.entrySet()) {
							sb.append(String.format("%s %s",
									this.userIdEmojiMap.get(entry.getKey()),
									entry.getValue()));
							sb.append("\n");
						}
						Message message3 = Message.create((String) sb
								.toString());
						this.discordBot
								.send(channelId, message3)
								.then(message2 -> {
									this.messageIdMap2.get(channelId).put(
											messageId, message2.id());
									this.messageIdMap2.get(channelId).put(
											message2.id(), messageId);
									this.messageIdMap.put(message2.id(), userId);
								});
						return;
					}
					if (message.equals("?help")) {
						sb = new StringBuilder();
						sb.append("?help");
						sb.append("\n");
						sb.append("?list");
						sb.append("\n");
						sb.append("?who");
						Message message4 = Message.create((String) sb
								.toString());
						this.discordBot
								.send(channelId, message4)
								.then(message2 -> {
									this.messageIdMap2.get(channelId).put(
											messageId, message2.id());
									this.messageIdMap2.get(channelId).put(
											message2.id(), messageId);
									this.messageIdMap.put(message2.id(), userId);
								});
						return;
					}
					if (message.equals("?who")) {
						if (event.message().reference() == null) {
							Message msg2 = Message
									.create((String) "You have to reply to a msg when using this cmd.");
							this.discordBot.send(channelId, msg2).then(
									message2 -> {
										this.messageIdMap2.get(channelId).put(
												messageId, message2.id());
										this.messageIdMap2.get(channelId).put(
												message2.id(), messageId);
										this.messageIdMap.put(message2.id(),
												userId);
									});
						} else {
							for (Map.Entry entry : this.userIdMap.entrySet()) {
								if (!((String) entry.getValue())
										.equals(this.userIdMap
												.get(this.messageIdMap
														.get(event.message()
																.reference()
																.messageId()))))
									continue;
								Message message5 = Message
										.create((String) ((String) entry
												.getValue()));
								this.discordBot.send(channelId, message5).then(
										message2 -> {
											this.messageIdMap2.get(channelId)
													.put(messageId,
															message2.id());
											this.messageIdMap2.get(channelId)
													.put(message2.id(),
															messageId);
											this.messageIdMap.put(
													message2.id(), userId);
										});
							}
						}
						return;
					}
					if (!this.userIdEmojiMap.containsKey(userId)) {
						for (String string : this.emojis) {
							boolean bl = false;
							for (Map.Entry<String, String> entry : this.userIdEmojiMap
									.entrySet()) {
								if (!entry.getValue().equals(string))
									continue;
								bl = true;
								break;
							}
							if (bl)
								continue;
							this.userIdEmojiMap.put(userId, string);
						}
					}
					if (!this.userIdMap.containsKey(userId)) {
						this.userIdMap.put(userId, event.message().author()
								.username());
					}
					if (userId.equals(this.userId)) {
						return;
					}
					this.messageIdMap.put(messageId, userId);
					MessageReference messageReference = event.message()
							.reference();
					for (Attachment attachment : event.message().attachments()) {
						message = attachment.url() + " " + message;
					}
					if (!this.channelList.contains(channelId)) {
						return;
					}
					for (String channel : this.channelList) {
						String messageId3;
						String messageId2;
						Message msg;
						if (channelId.equals(channel))
							continue;
						if (!this.atMentionUsers
								|| this.dontAtMentionTheseUserIds
										.contains(event.message().author().id())) {
							switch (event.message().type()) {
							case 1:
							case 2:
								msg = Message.create(String.format(
										"%s <@%s> %s",
										new Object[] {
												this.userIdEmojiMap.get(userId),
												event.message().author().id(),
												message }));
								break;
							default:
								msg = Message.create(String.format(
										"%s <@%s>: %s",
										new Object[] {
												this.userIdEmojiMap.get(userId),
												event.message().author().id(),
												message }));
								break;
							}
							msg = msg.reference(messageId);
							if (messageReference != null) {
								messageId2 = this.messageIdMap2.get(channel)
										.get(messageReference.messageId());
								if (messageId2 == null) {
									for (String ch : this.channelList) {
										messageId3 = this.messageIdMap2.get(ch)
												.get(messageReference
														.messageId());
										if (messageId3 == null)
											continue;
										messageId2 = this.messageIdMap3.get(
												channel).get(messageId3);
									}
								}
								if (messageId2 != null) {
									msg.reference(messageId2);
								}
							}
							this.discordBot.send(channel, msg).then(
									message2 -> {
										this.messageIdMap2.get(channel).put(
												messageId, message2.id());
										this.messageIdMap2.get(channelId).put(
												message2.id(), messageId);
										this.messageIdMap3.get(channel).put(
												messageId, message2.id());
										this.messageIdMap.put(message2.id(),
												userId);
									});
							continue;
						}
						switch (event.message().type()) {
						case 1:
						case 2: {
							msg = Message.create((String) String.format(
									"%s <@%s> %s",
									this.userIdEmojiMap.get(userId), event
											.message().author().id(), message));
							break;
						}
						default: {
							msg = Message.create((String) String.format(
									"%s <@%s>: %s",
									this.userIdEmojiMap.get(userId), event
											.message().author().id(), message));
						}
						}
						msg = msg.reference(messageId);
						if (messageReference != null) {
							messageId2 = this.messageIdMap2.get(channel).get(
									messageReference.messageId());
							if (messageId2 == null) {
								for (String ch : this.channelList) {
									messageId3 = this.messageIdMap2.get(ch)
											.get(messageReference.messageId());
									if (messageId3 == null)
										continue;
									messageId2 = this.messageIdMap3
											.get(channel).get(messageId3);
								}
							}
							if (messageId2 != null) {
								msg.reference(messageId2);
							}
						}
						this.discordBot
								.send(channel, msg)
								.then(message2 -> {
									this.messageIdMap2.get(channel).put(
											messageId, message2.id());
									this.messageIdMap2.get(channelId).put(
											message2.id(), messageId);
									this.messageIdMap3.get(channel).put(
											messageId, message2.id());
									this.messageIdMap.put(message2.id(), userId);
								});
					}
				});
		this.messageUpdateListener = new Listener<MessageUpdateEvent>(
				event -> {
					StringBuilder sb;
					System.out.println(event);
					String userId = event.message().author().id();
					String channelId = event.message().channelId();
					String message = event.message().content();
					String messageId = event.message().id();
					if (message.equals("?list")) {
						sb = new StringBuilder();
						for (Map.Entry<String, String> entry : this.userIdMap
								.entrySet()) {
							sb.append(String.format("%s %s",
									this.userIdEmojiMap.get(entry.getKey()),
									entry.getValue()));
							sb.append("\n");
						}
						Message message3 = Message.create((String) sb
								.toString());
						this.discordBot
								.send(channelId, message3)
								.then(message2 -> {
									this.messageIdMap2.get(channelId).put(
											messageId, message2.id());
									this.messageIdMap2.get(channelId).put(
											message2.id(), messageId);
									this.messageIdMap.put(message2.id(), userId);
								});
						return;
					}
					if (message.equals("?help")) {
						sb = new StringBuilder();
						sb.append("?help");
						sb.append("\n");
						sb.append("?list");
						sb.append("\n");
						sb.append("?who");
						Message message4 = Message.create((String) sb
								.toString());
						this.discordBot
								.send(channelId, message4)
								.then(message2 -> {
									this.messageIdMap2.get(channelId).put(
											messageId, message2.id());
									this.messageIdMap2.get(channelId).put(
											message2.id(), messageId);
									this.messageIdMap.put(message2.id(), userId);
								});
						return;
					}
					if (message.equals("?who")) {
						if (event.message().reference() == null) {
							Message msg2 = Message
									.create((String) "You have to reply to a msg when using this cmd.");
							this.discordBot.send(channelId, msg2).then(
									message2 -> {
										this.messageIdMap2.get(channelId).put(
												messageId, message2.id());
										this.messageIdMap2.get(channelId).put(
												message2.id(), messageId);
										this.messageIdMap.put(message2.id(),
												userId);
									});
						} else {
							for (Map.Entry entry : this.userIdMap.entrySet()) {
								if (!((String) entry.getValue())
										.equals(this.userIdMap
												.get(this.messageIdMap
														.get(event.message()
																.reference()
																.messageId()))))
									continue;
								Message message5 = Message
										.create((String) ((String) entry
												.getValue()));
								this.discordBot.send(channelId, message5).then(
										message2 -> {
											this.messageIdMap2.get(channelId)
													.put(messageId,
															message2.id());
											this.messageIdMap2.get(channelId)
													.put(message2.id(),
															messageId);
											this.messageIdMap.put(
													message2.id(), userId);
										});
							}
						}
						return;
					}
					if (!this.userIdEmojiMap.containsKey(userId)) {
						for (String string : this.emojis) {
							boolean bl = false;
							for (Map.Entry<String, String> entry : this.userIdEmojiMap
									.entrySet()) {
								if (!entry.getValue().equals(string))
									continue;
								bl = true;
								break;
							}
							if (bl)
								continue;
							this.userIdEmojiMap.put(userId, string);
						}
					}
					if (!this.userIdMap.containsKey(userId)) {
						this.userIdMap.put(userId, event.message().author()
								.username());
					}
					if (userId.equals(this.userId)) {
						return;
					}
					this.messageIdMap.put(messageId, userId);
					MessageReference messageReference = event.message()
							.reference();
					for (Attachment attachment : event.message().attachments()) {
						message = attachment.url() + " " + message;
					}
					if (!this.channelList.contains(channelId)) {
						return;
					}
					for (String channel : this.channelList) {
						String messageId3;
						String messageId2;
						if (channelId.equals(channel))
							continue;
						if (!this.atMentionUsers
								|| this.dontAtMentionTheseUserIds
										.contains(event.message().author().id())) {
							Message msg4 = Message.create((String) String
									.format("%s %s has edited this message https://discord.com/channels/@me/%s/%s: %s",
											this.userIdEmojiMap.get(userId),
											event.message().author().username(),
											channelId, messageId, message));
							msg4 = msg4.reference(messageId);
							if (messageReference != null) {
								messageId2 = this.messageIdMap2.get(channel)
										.get(messageReference.messageId());
								if (messageId2 == null) {
									for (String ch : this.channelList) {
										messageId3 = this.messageIdMap2.get(ch)
												.get(messageReference
														.messageId());
										if (messageId3 == null)
											continue;
										messageId2 = this.messageIdMap3.get(
												channel).get(messageId3);
									}
								}
								if (messageId2 != null) {
									msg4.reference(messageId2);
								}
							}
							this.discordBot.send(channel, msg4).then(
									message2 -> {
										this.messageIdMap2.get(channel).put(
												messageId, message2.id());
										this.messageIdMap2.get(channelId).put(
												message2.id(), messageId);
										this.messageIdMap3.get(channel).put(
												messageId, message2.id());
										this.messageIdMap.put(message2.id(),
												userId);
									});
							continue;
						}
						Message msg = Message.create((String) String
								.format("%s <@%s> has edited this message https://discord.com/channels/@me/%s/%s: %s",
										this.userIdEmojiMap.get(userId), event
												.message().author().id(),
										channelId, messageId, message));
						msg = msg.reference(messageId);
						if (messageReference != null) {
							messageId2 = this.messageIdMap2.get(channel).get(
									messageReference.messageId());
							if (messageId2 == null) {
								for (String ch : this.channelList) {
									messageId3 = this.messageIdMap2.get(ch)
											.get(messageReference.messageId());
									if (messageId3 == null)
										continue;
									messageId2 = this.messageIdMap3
											.get(channel).get(messageId3);
								}
							}
							if (messageId2 != null) {
								msg.reference(messageId2);
							}
						}
						this.discordBot
								.send(channel, msg)
								.then(message2 -> {
									this.messageIdMap2.get(channel).put(
											messageId, message2.id());
									this.messageIdMap2.get(channelId).put(
											message2.id(), messageId);
									this.messageIdMap3.get(channel).put(
											messageId, message2.id());
									this.messageIdMap.put(message2.id(), userId);
								});
					}
				});
		this.messageDeleteListener = new Listener<MessageDeleteEvent>(
				event -> {
					System.out.println(event);
					String messageId = event.id();
					String userId = this.messageIdMap.get(messageId);
					if (userId == null) {
						return;
					}
					String channelId = event.channelId();
					if (!this.userIdEmojiMap.containsKey(userId)) {
						for (String emoji : this.emojis) {
							boolean hit = false;
							for (Map.Entry<String, String> entry : this.userIdEmojiMap
									.entrySet()) {
								if (!entry.getValue().equals(emoji))
									continue;
								hit = true;
								break;
							}
							if (hit)
								continue;
							this.userIdEmojiMap.put(userId, emoji);
						}
					}
					if (userId.equals(this.userId)) {
						return;
					}
					String message = String
							.format("has deleted this message https://discord.com/channels/@me/%s/%s",
									channelId, messageId);
					for (String channel : this.channelList) {
						String messageId3;
						String messageId2;
						Message msg;
						if (channelId.equals(channel))
							continue;
						if (!this.atMentionUsers
								|| this.dontAtMentionTheseUserIds
										.contains(userId)) {
							msg = Message.create((String) String.format(
									"%s %s %s",
									this.userIdEmojiMap.get(userId),
									this.userIdMap.get(userId), message));
							msg = msg.reference(messageId);
							messageId2 = this.messageIdMap2.get(channel).get(
									messageId);
							if (messageId2 == null) {
								for (String ch : this.channelList) {
									messageId3 = this.messageIdMap2.get(ch)
											.get(messageId);
									if (messageId3 == null)
										continue;
									messageId2 = this.messageIdMap3
											.get(channel).get(messageId3);
								}
							}
							if (messageId2 != null) {
								msg.reference(messageId2);
							}
							this.discordBot.send(channel, msg).then(
									message2 -> {
										this.messageIdMap2.get(channelId).put(
												message2.id(), messageId);
										this.messageIdMap.put(message2.id(),
												userId);
									});
							continue;
						}
						msg = Message.create((String) String.format(
								"%s <@%s> %s", this.userIdEmojiMap.get(userId),
								userId, message));
						msg = msg.reference(messageId);
						messageId2 = this.messageIdMap2.get(channel).get(
								messageId);
						if (messageId2 == null) {
							for (String ch : this.channelList) {
								messageId3 = this.messageIdMap2.get(ch).get(
										messageId);
								if (messageId3 == null)
									continue;
								messageId2 = this.messageIdMap3.get(channel)
										.get(messageId3);
							}
						}
						if (messageId2 != null) {
							msg.reference(messageId2);
						}
						this.discordBot
								.send(channel, msg)
								.then(message2 -> {
									this.messageIdMap2.get(channelId).put(
											message2.id(), messageId);
									this.messageIdMap.put(message2.id(), userId);
								});
					}
				});
		this.messageReactionAddListener = new Listener<MessageReactionAddEvent>(
				event -> {
					Reaction reaction = event.reaction();
					System.out.println(event);
					String userId = reaction.id();
					String messageId = reaction.messageId();
					String channelId = reaction.channelId();
					if (!this.userIdEmojiMap.containsKey(userId)) {
						for (String emoji : this.emojis) {
							boolean hit = false;
							for (Map.Entry<String, String> entry : this.userIdEmojiMap
									.entrySet()) {
								if (!entry.getValue().equals(emoji))
									continue;
								hit = true;
								break;
							}
							if (hit)
								continue;
							this.userIdEmojiMap.put(userId, emoji);
						}
					}
					if (userId.equals(this.userId)) {
						return;
					}
					String message = String.format("has reacted with %s",
							reaction.emoji().name());
					for (String channel : this.channelList) {
						String messageId3;
						String messageId2;
						Message msg;
						if (channelId.equals(channel))
							continue;
						if (!this.atMentionUsers
								|| this.dontAtMentionTheseUserIds
										.contains(userId)) {
							msg = Message.create((String) String.format(
									"%s %s %s",
									this.userIdEmojiMap.get(userId),
									this.userIdMap.get(userId), message));
							msg = msg.reference(messageId);
							messageId2 = this.messageIdMap2.get(channel).get(
									messageId);
							if (messageId2 == null) {
								for (String ch : this.channelList) {
									messageId3 = this.messageIdMap2.get(ch)
											.get(messageId);
									if (messageId3 == null)
										continue;
									messageId2 = this.messageIdMap3
											.get(channel).get(messageId3);
								}
							}
							if (messageId2 != null) {
								msg.reference(messageId2);
							}
							this.discordBot.send(channel, msg).then(
									message2 -> {
										this.messageIdMap2.get(channelId).put(
												message2.id(), messageId);
										this.messageIdMap.put(message2.id(),
												userId);
									});
							continue;
						}
						msg = Message.create((String) String.format(
								"%s <@%s> %s", this.userIdEmojiMap.get(userId),
								userId, message));
						msg = msg.reference(messageId);
						messageId2 = this.messageIdMap2.get(channel).get(
								messageId);
						if (messageId2 == null) {
							for (String ch : this.channelList) {
								messageId3 = this.messageIdMap2.get(ch).get(
										messageId);
								if (messageId3 == null)
									continue;
								messageId2 = this.messageIdMap3.get(channel)
										.get(messageId3);
							}
						}
						if (messageId2 != null) {
							msg.reference(messageId2);
						}
						this.discordBot
								.send(channel, msg)
								.then(message2 -> {
									this.messageIdMap2.get(channelId).put(
											message2.id(), messageId);
									this.messageIdMap.put(message2.id(), userId);
								});
					}
				});
		this.messageReactionRemoveListener = new Listener<MessageReactionRemoveEvent>(
				event -> {
					Reaction reaction = event.reaction();
					System.out.println(event);
					String userId = reaction.id();
					String messageId = reaction.messageId();
					String channelId = reaction.channelId();
					if (!this.userIdEmojiMap.containsKey(userId)) {
						for (String emoji : this.emojis) {
							boolean hit = false;
							for (Map.Entry<String, String> entry : this.userIdEmojiMap
									.entrySet()) {
								if (!entry.getValue().equals(emoji))
									continue;
								hit = true;
								break;
							}
							if (hit)
								continue;
							this.userIdEmojiMap.put(userId, emoji);
						}
					}
					if (userId.equals(this.userId)) {
						return;
					}
					String message = String.format("has unreacted with %s",
							reaction.emoji().name());
					for (String channel : this.channelList) {
						String messageId3;
						String messageId2;
						Message msg;
						if (channelId.equals(channel))
							continue;
						if (!this.atMentionUsers
								|| this.dontAtMentionTheseUserIds
										.contains(userId)) {
							msg = Message.create((String) String.format(
									"%s %s %s",
									this.userIdEmojiMap.get(userId),
									this.userIdMap.get(userId), message));
							msg = msg.reference(messageId);
							messageId2 = this.messageIdMap2.get(channel).get(
									messageId);
							if (messageId2 == null) {
								for (String ch : this.channelList) {
									messageId3 = this.messageIdMap2.get(ch)
											.get(messageId);
									if (messageId3 == null)
										continue;
									messageId2 = this.messageIdMap3
											.get(channel).get(messageId3);
								}
							}
							if (messageId2 != null) {
								msg.reference(messageId2);
							}
							this.discordBot.send(channel, msg).then(
									message2 -> {
										this.messageIdMap2.get(channelId).put(
												message2.id(), messageId);
										this.messageIdMap.put(message2.id(),
												userId);
									});
							continue;
						}
						msg = Message.create((String) String.format(
								"%s <@%s> %s", this.userIdEmojiMap.get(userId),
								userId, message));
						msg = msg.reference(messageId);
						messageId2 = this.messageIdMap2.get(channel).get(
								messageId);
						if (messageId2 == null) {
							for (String ch : this.channelList) {
								messageId3 = this.messageIdMap2.get(ch).get(
										messageId);
								if (messageId3 == null)
									continue;
								messageId2 = this.messageIdMap3.get(channel)
										.get(messageId3);
							}
						}
						if (messageId2 != null) {
							msg.reference(messageId2);
						}
						this.discordBot
								.send(channel, msg)
								.then(message2 -> {
									this.messageIdMap2.get(channelId).put(
											message2.id(), messageId);
									this.messageIdMap.put(message2.id(), userId);
								});
					}
				});
		this.discordBot.eventSystem().subscribeAll((Object) this);
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String formattedMessage = dateFormat.format(date);
		double endTime = System.currentTimeMillis() - startTime;
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		System.out.println("[" + formattedMessage
				+ "] [Server thread/INFO]: Done ("
				+ df.format(endTime / 1000.0) + "s)! For help, type \"help\"");
		kThread.sleep((long) 5000L);
		this.discordBot.presence(Availability.ONLINE, Status.NOTHING);
		kThread.sleep_till(() -> true);
	}

	public static void main(String[] args) {
		new Test();
	}
}
