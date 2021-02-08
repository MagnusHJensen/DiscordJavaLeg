package dk.magnusjensen.discordjavaleg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dk.magnusjensen.discordjavaleg.entities.*;
import dk.magnusjensen.discordjavaleg.events.Event;
import dk.magnusjensen.discordjavaleg.events.EventListener;
import dk.magnusjensen.discordjavaleg.events.ReadyEvent;
import dk.magnusjensen.discordjavaleg.events.channel.ChannelCreateEvent;
import dk.magnusjensen.discordjavaleg.events.channel.ChannelDeleteEvent;
import dk.magnusjensen.discordjavaleg.events.channel.ChannelUpdateEvent;
import dk.magnusjensen.discordjavaleg.events.guild.*;
import dk.magnusjensen.discordjavaleg.events.message.MessageReactionAddEvent;
import dk.magnusjensen.discordjavaleg.events.message.MessageRecievedEvent;

import java.util.ArrayList;

public class EventHandler {

	private DiscordJavaLeg client;

	public EventHandler(DiscordJavaLeg client) {
		this.client = client;
	}

	public void handleEvent(String eventName, JsonNode data) throws JsonProcessingException {

		/*
		Missing cases:
		Guild Emojis Update
		Guild Delete
		Guild Integrations Update


		 */

		switch (eventName) {
			case "READY": {
				this.client.setSelfUser(UserEntity.parseUserFromJsonNode(data.get("user")));
				ArrayNode guilds = (ArrayNode) data.get("guilds");
				guilds.forEach((guild) -> {
					client.addUnavailableGuild(guild.get("id").longValue());
				});
				ReadyEvent readyEvent = new ReadyEvent(this.client);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(ReadyEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<ReadyEvent>) listener).onEvent(readyEvent));
				break;
			}
			case "GUILD_CREATE": {
				this.client.addGuild(GuildEntity.parseGuildFromJson(data, true, this.client));
				GuildCreateEvent guildCreateEvent = new GuildCreateEvent(this.client);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildCreateEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildCreateEvent>) listener).onEvent(guildCreateEvent));
				break;
			}
			case "MESSAGE_CREATE": {
				MessageEntity message = MessageEntity.parseMessageFromJson(data, this.client);
				MessageRecievedEvent event = new MessageRecievedEvent(this.client, message);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(MessageRecievedEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<MessageRecievedEvent>) listener).onEvent(event));
				break;
			}
			case "CHANNEL_CREATE": {
				ChannelEntity channel = ChannelEntity.parseChannelFromJson(data, this.client);
				if (channel.getGuildId() != null) {
					this.client.getGuild(channel.getGuildId()).addChannel(channel);
				}
				ChannelCreateEvent event = new ChannelCreateEvent(this.client, channel);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(ChannelCreateEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<ChannelCreateEvent>) listener).onEvent(event));
				break;
			}
			case "CHANNEL_UPDATE": {
				ChannelEntity channel = ChannelEntity.parseChannelFromJson(data, this.client);
				if (channel.getGuildId() != null) {
					this.client.getGuild(channel.getGuildId()).addChannel(channel);
				}
				ChannelUpdateEvent event = new ChannelUpdateEvent(this.client, channel);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(ChannelUpdateEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<ChannelUpdateEvent>) listener).onEvent(event));
				break;
			}
			case "CHANNEL_DELETE": {
				ChannelEntity channel = ChannelEntity.parseChannelFromJson(data, this.client);
				if (channel.getGuildId() != null) {
					this.client.getGuild(channel.getGuildId()).removeChannel(channel);
				}
				ChannelDeleteEvent event = new ChannelDeleteEvent(this.client, channel);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(ChannelDeleteEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<ChannelDeleteEvent>) listener).onEvent(event));
				break;
			}
			case "GUILD_UPDATE": {
				GuildEntity guild = GuildEntity.parseGuildFromJson(data, false, this.client);
				this.client.addGuild(guild);
				GuildUpdateEvent event = new GuildUpdateEvent(this.client, guild);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildUpdateEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildUpdateEvent>) listener).onEvent(event));
				break;
			}
			case "GUILD_BAN_ADD": {
				long guildID = Long.parseLong(data.get("guild_id").textValue());
				GuildEntity guild = this.client.getGuild(guildID);
				UserEntity user = UserEntity.parseUserFromJsonNode(data.get("user"));
				GuildBanAddEvent event = new GuildBanAddEvent(this.client, guild, user);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildBanAddEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildBanAddEvent>) listener).onEvent(event));
				break;
			}
			case "GUILD_BAN_REMOVE": {
				long guildID = Long.parseLong(data.get("guild_id").textValue());
				GuildEntity guild = this.client.getGuild(guildID);
				UserEntity user = UserEntity.parseUserFromJsonNode(data.get("user"));
				GuildBanRemoveEvent event = new GuildBanRemoveEvent(this.client, guild, user);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildBanRemoveEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildBanRemoveEvent>) listener).onEvent(event));
				break;
			}
			case "GUILD_MEMBER_ADD": {
				long guildID = Long.parseLong(data.get("guild_id").textValue());
				GuildEntity guild = this.client.getGuild(guildID);
				MemberEntity member = MemberEntity.parseMemberFromJson(data, guild);
				guild.addMember(member);
				GuildMemberAddEvent event = new GuildMemberAddEvent(this.client, guild, member);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildMemberAddEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildMemberAddEvent>) listener).onEvent(event));
				break;
			}
			case "GUILD_MEMBER_REMOVE": {
				long guildID = Long.parseLong(data.get("guild_id").textValue());
				GuildEntity guild = this.client.getGuild(guildID);
				UserEntity user = UserEntity.parseUserFromJsonNode(data);
				guild.removeMember(user.getId());
				GuildMemberRemoveEvent event = new GuildMemberRemoveEvent(this.client, guild, user);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildMemberRemoveEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildMemberRemoveEvent>) listener).onEvent(event));
				break;
			}
			case "GUILD_ROLE_CREATE": {
				long guildID = Long.parseLong(data.get("guild_id").textValue());
				GuildEntity guild = this.client.getGuild(guildID);
				RoleEntity role = RoleEntity.parseRoleFromJson(data.get("role"));
				guild.addRole(role);
				GuildRoleCreateEvent event = new GuildRoleCreateEvent(this.client, guild, role);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildRoleCreateEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildRoleCreateEvent>) listener).onEvent(event));
				break;
			}
			case "GUILD_ROLE_UPDATE": {
				long guildID = Long.parseLong(data.get("guild_id").textValue());
				GuildEntity guild = this.client.getGuild(guildID);
				RoleEntity newRole = RoleEntity.parseRoleFromJson(data.get("role"));
				RoleEntity oldRole = guild.fetchRole(newRole.getId());
				guild.addRole(newRole);
				GuildRoleUpdateEvent event = new GuildRoleUpdateEvent(this.client, guild, newRole, oldRole);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildRoleUpdateEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildRoleUpdateEvent>) listener).onEvent(event));
				break;
			}
			case "GUILD_ROLE_DELETE": {
				long guildID = Long.parseLong(data.get("guild_id").textValue());
				GuildEntity guild = this.client.getGuild(guildID);
				long roleId = Long.parseLong(data.get("role_id").textValue());
				RoleEntity role = guild.fetchRole(roleId);
				guild.removeRole(role);
				GuildRoleDeleteEvent event = new GuildRoleDeleteEvent(this.client, guild, role);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildRoleDeleteEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildRoleDeleteEvent>) listener).onEvent(event));
				break;
			}
			case "INVITE_CREATE": {
				InviteEntity invite = InviteEntity.parseInviteFromJson(data);
				if (invite.getGuildId() != null) {
					GuildEntity guild = this.client.getGuild(invite.getGuildId());
					guild.addInvite(invite);
				}
				GuildInviteCreateEvent event = new GuildInviteCreateEvent(this.client, invite);
				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildInviteCreateEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildInviteCreateEvent>) listener).onEvent(event));
				break;
			}
			case "INVITE_DELETE": {
				String code = data.get("code").textValue();

				GuildInviteDeleteEvent event = new GuildInviteDeleteEvent(this.client, code);

				if (data.hasNonNull("guild_id")) {
					GuildEntity guild = this.client.getGuild(Long.parseLong(data.get("guild_id").textValue()));
					InviteEntity invite = guild.getInvite(code);
					if (invite != null) {
						event.setInvite(invite);
					}
					guild.removeInvite(code);
				}

				ArrayList<EventListener<? extends Event>> listeners = this.client.getBuilder().getListenersForEvent(GuildInviteDeleteEvent.class.getTypeName());
				listeners.forEach((listener) -> ((EventListener<GuildInviteDeleteEvent>) listener).onEvent(event));
				break;
			}


		}
	}
}
