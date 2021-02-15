package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;

import java.util.ArrayList;

public class ChannelEntity implements ISnowflake {

	private DiscordJavaLeg client;
	private long id;
	private int type;
	private GuildEntity guild = null;
	private Integer position = null;
	private String name = null;
	private String topic = null;
	private Boolean nsfw = null;
	private MessageEntity lastMessage = null;
	private Integer bitrate = null;
	private Integer userLimit = null;
	private Integer rateLimitPerUser = null;
	private String icon = null;
	private Long ownerId = null;
	private Long applicationId = null;
	private Long parentId = null;

	public ChannelEntity(DiscordJavaLeg client, long id, int type) {
		this.client = client;
		this.id = id;
		this.type = type;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public int getType() {
		return type;
	}


	public GuildEntity getGuild() {
		return guild;
	}

	private void setGuild(GuildEntity guild) {
		this.guild = guild;
	}

	public Integer getPosition() {
		return position;
	}

	private void setPosition(Integer position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getTopic() {
		return topic;
	}

	private void setTopic(String topic) {
		this.topic = topic;
	}

	public Boolean getNsfw() {
		return nsfw;
	}

	private void setNsfw(Boolean nsfw) {
		this.nsfw = nsfw;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	private void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}

	public Integer getUserLimit() {
		return userLimit;
	}

	private void setUserLimit(Integer userLimit) {
		this.userLimit = userLimit;
	}

	public Integer getRateLimitPerUser() {
		return rateLimitPerUser;
	}

	private void setRateLimitPerUser(Integer rateLimitPerUser) {
		this.rateLimitPerUser = rateLimitPerUser;
	}

	public String getIcon() {
		return icon;
	}

	private void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	private void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	private void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getParentId() {
		return parentId;
	}

	private void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public MessageEntity getLastMessage() {
		return this.lastMessage;
	}

	private void setLastMessage(MessageEntity message) {
		if (message != null) {
			this.lastMessage = message;
		}
	}

	public MessageEntity send(String msg) {
		String messageJson = "{\"content\": \"" + msg + "\", \"tts\": false}";
		return this.client.sendMessage(messageJson, this.getId());
	}

	//public void send(EmbedEntity embed) {

	//}

	static ArrayList<ChannelEntity> parseArrayFromJson(ArrayNode node, DiscordJavaLeg client) {
		ArrayList<ChannelEntity> channels = new ArrayList<>();

		node.forEach((channelData) -> channels.add(parseChannelFromJson(channelData, client)));

		return channels;
	}

	public static ChannelEntity parseChannelFromJson(JsonNode channelData, DiscordJavaLeg client) {
		long id = Long.parseLong(channelData.get("id").textValue());
		int type = channelData.get("type").intValue();

		ChannelEntity channel = new ChannelEntity(client, id, type);

		if (channelData.has("guild_id")) {

			channel.setGuild(client.getGuild(Long.parseLong(channelData.get("guild_id").textValue())));
		}
		if (channelData.has("position")) {
			channel.setPosition(channelData.get("position").intValue());
		}
		if (channelData.has("name")) {
			channel.setName(channelData.get("name").textValue());
		}
		if (channelData.has("topic")) {
			channel.setTopic(channelData.get("topic").textValue());
		}
		if (channelData.has("nsfw")) {
			channel.setNsfw(channelData.get("nsfw").booleanValue());
		}
		if (channelData.hasNonNull("last_message_id")) {
			channel.setLastMessage(client.fetchMessage(id, Long.parseLong(channelData.get("last_message_id").textValue())));
		}
		if (channelData.has("bitrate")) {
			channel.setBitrate(channelData.get("bitrate").intValue());
		}
		if (channelData.has("user_limit")) {
			channel.setUserLimit(channelData.get("user_limit").intValue());
		}
		if (channelData.has("rate_limit_per_user")) {
			channel.setRateLimitPerUser(channelData.get("rate_limit_per_user").intValue());
		}
		if (channelData.has("icon")) {
			channel.setIcon(channelData.get("icon").textValue());
		}
		if (channelData.has("owner_id")) {
			channel.setOwnerId(Long.parseLong(channelData.get("owner_id").textValue()));
		}
		if (channelData.has("application_id")) {
			channel.setApplicationId(Long.parseLong(channelData.get("application_id").textValue()));
		}
		if (channelData.has("parent_id")) {
			channel.setParentId(Long.parseLong(channelData.get("parent_id").textValue()));
		}

		if (client.getChannelById(channel.getId()) == null) {
			client.cacheChannel(channel);
		}

		return channel;
	}
}
