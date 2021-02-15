package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;

import java.util.ArrayList;

public class InviteEntity {
	private ChannelEntity channel;
	private String code;
	private String createdAt;
	private GuildEntity guild = null;
	private UserEntity inviter = null;
	private int maxAge;
	private int maxUses;
	private boolean temporary;
	private int uses;

	public InviteEntity(ChannelEntity channel, String code, String createdAt, int maxAge, int maxUses, boolean temporary, int uses) {
		this.channel = channel;
		this.code = code;
		this.createdAt = createdAt;
		this.maxAge = maxAge;
		this.maxUses = maxUses;
		this.temporary = temporary;
		this.uses = uses;
	}

	public ChannelEntity getChannel() {
		return channel;
	}

	public String getCode() {
		return code;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public GuildEntity getGuild() {
		return guild;
	}

	private void setGuild(GuildEntity guild) {
		this.guild = guild;
	}

	public UserEntity getInviter() {
		return inviter;
	}

	private void setInviter(UserEntity inviter) {
		this.inviter = inviter;
	}

	public int getMaxAge() {
		return maxAge;
	}


	public int getMaxUses() {
		return maxUses;
	}


	public boolean isTemporary() {
		return temporary;
	}


	public int getUses() {
		return uses;
	}

	public static InviteEntity parseInviteFromJson(JsonNode data, DiscordJavaLeg client) {
		long channelId = Long.parseLong(data.get("channel_id").textValue());
		String code = data.get("code").textValue();
		String createdAt = data.get("created_at").textValue();
		int maxAge = data.get("max_age").intValue();
		int maxUses = data.get("max_uses").intValue();
		boolean temporary = data.get("temporary").booleanValue();
		int uses = data.get("uses").intValue();


		InviteEntity invite = new InviteEntity(client.getChannelById(channelId), code, createdAt, maxAge, maxUses, temporary, uses);

		if (data.has("guild_id")) {
			invite.setGuild(client.getGuild(Long.parseLong(data.get("guild_id").textValue())));
		}
		if (data.has("inviter")) {
			invite.setInviter(UserEntity.parseUserFromJsonNode(data.get("inviter"), client));
		}

		return invite;
	}

	public static ArrayList<InviteEntity> parseInvitesFromJson(ArrayNode data, DiscordJavaLeg client) {
		ArrayList<InviteEntity> invites = new ArrayList<>();

		data.forEach((inviteData) -> {
			invites.add(parseInviteFromJson(inviteData, client));
		});

		return invites;
	}
}
