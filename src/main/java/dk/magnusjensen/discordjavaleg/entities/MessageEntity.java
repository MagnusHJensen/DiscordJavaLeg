package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;

import java.util.ArrayList;

public class MessageEntity implements ISnowflake {

	private DiscordJavaLeg client;
	private long id;
	private long channelId;
	private long guildId;
	private UserEntity author;
	private String content;
	private String timestamp;
	private boolean tts;
	private boolean mentionEveryone;
	private ArrayList<UserEntity> mentions;
	private ArrayList<RoleEntity> rolesMentions;
	private boolean pinned;
	private Long webhookId = null;
	private int type;

	public MessageEntity(DiscordJavaLeg client, long id, long channel_id, String conent, String timestamp, boolean tts, boolean mentionEveryone, ArrayList<UserEntity> mentions, ArrayList<RoleEntity> rolesMentions, boolean pinned, int type) {
		this.client = client;
		this.id = id;
		this.channelId = channel_id;
		this.content = conent;
		this.timestamp = timestamp;
		this.tts = tts;
		this.mentionEveryone = mentionEveryone;
		this.mentions = mentions;
		this.rolesMentions = rolesMentions;
		this.pinned = pinned;
		this.type = type;
	}


	public MessageEntity edit(String content) {
		String contentJson = "{\"content\": \"" + content + "\"}";
		return this.client.editMessage(contentJson, this.channelId, this.id);
	}

	@Override
	public long getId() {
		return this.id;
	}

	public ChannelEntity getChannel() {
		return this.getGuild().getChannel(this.channelId);
	}

	public GuildEntity getGuild() {
		return client.getGuild(this.guildId);
	}
	public void setGuildId(long guildId) {
		this.guildId = guildId;
	}

	public UserEntity getAuthor() {
		return author;
	}
	public void setAuthor(UserEntity author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public boolean isTts() {
		return tts;
	}

	public boolean isMentionEveryone() {
		return mentionEveryone;
	}

	public ArrayList<UserEntity> getMentions() {
		return mentions;
	}

	public ArrayList<RoleEntity> getRolesMentions() {
		return rolesMentions;
	}

	public boolean isPinned() {
		return pinned;
	}

	public Long getWebhookId() {
		return webhookId;
	}
	private void setWebhookId(long id) {
		this.webhookId = id;
	}

	public int getType() {
		return type;
	}

	public static MessageEntity parseMessageFromJson(JsonNode data, DiscordJavaLeg client) {
		long id = Long.parseLong(data.get("id").textValue());
		long channelId = Long.parseLong(data.get("channel_id").textValue());
		String content = data.get("content").textValue();
		String timestamp = data.get("timestamp").textValue();
		boolean tts = data.get("tts").booleanValue();
		boolean mentionEveryone = data.get("mention_everyone").booleanValue();
		ArrayList<UserEntity> mentions = new ArrayList<>();
		(data.get("mentions")).forEach((user) -> {
			mentions.add(UserEntity.parseUserFromJsonNode(user, client));
		});
		ArrayList<RoleEntity> rolesMentions = RoleEntity.parseArrayFromJson((ArrayNode) data.get("mention_roles"));
		boolean pinned = data.get("pinned").booleanValue();
		int type = data.get("type").intValue();

		MessageEntity msg = new MessageEntity(client, id, channelId, content, timestamp, tts, mentionEveryone, mentions, rolesMentions, pinned, type);

		if (data.has("webhook_id")) {
			msg.setWebhookId(Long.parseLong(data.get("webhook_id").textValue()));
		}
		if (data.has("guild_id")) {
			msg.setGuildId(Long.parseLong(data.get("guild_id").textValue()));
		}
		if (data.has("author")) {
			msg.setAuthor(UserEntity.parseUserFromJsonNode(data.get("author"), client));
		}

		return msg;
	}
}
