package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmojiEntity implements ISnowflake {

	private long id;
	private String name;
	private Map<Long, RoleEntity> roles;
	private GuildEntity guild;
	private UserEntity user;
	private boolean requireColons;
	private boolean managed;
	private boolean animated;
	private boolean available;

	public EmojiEntity(long id, String name, Map<Long, RoleEntity> roles, GuildEntity guild) {
		this.id = id;
		this.name = name;
		this.roles = roles;
		this.guild = guild;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public Collection<RoleEntity> getRoles() {
		return roles.values();
	}

	public GuildEntity getGuild() {
		return this.guild;
	}

	public UserEntity getUser() {
		return user;
	}

	private void setUser(UserEntity user) {
		this.user = user;
	}

	public boolean isRequireColons() {
		return requireColons;
	}

	private void setRequireColons(boolean requireColons) {
		this.requireColons = requireColons;
	}

	public boolean isManaged() {
		return managed;
	}

	private void setManaged(boolean managed) {
		this.managed = managed;
	}

	public boolean isAnimated() {
		return animated;
	}

	private void setAnimated(boolean animated) {
		this.animated = animated;
	}

	public boolean isAvailable() {
		return available;
	}

	private void setAvailable(boolean available) {
		this.available = available;
	}

	protected static ArrayList<EmojiEntity> parseArrayFromJson(ArrayNode node, GuildEntity guild, DiscordJavaLeg client) {
		ArrayList<EmojiEntity> emojis = new ArrayList<>();

		node.forEach((emojiData) -> {
			emojis.add(parseEmojiFromJson(emojiData, guild, client));
		});

		return emojis;
	}

	public static EmojiEntity parseEmojiFromJson(JsonNode data, GuildEntity guild, DiscordJavaLeg client) {
		long id = Long.parseLong(data.get("id").textValue());
		String name = data.get("name").textValue();
		Map<Long, RoleEntity> roles = new HashMap<>();

		if (data.has("roles")) {
			 data.get("roles").forEach((roleId) -> {
				RoleEntity role = guild.fetchRole((Long.parseLong(roleId.textValue())));
				roles.put(role.getId(), role);
			});
		}
		EmojiEntity emoji = new EmojiEntity(id, name, roles, guild);

		if (data.has("user")) {
			emoji.setUser(UserEntity.parseUserFromJsonNode(data.get("user"), client));
		}
		if (data.has("require_colons")) {
			emoji.setRequireColons(data.get("require_colons").booleanValue());
		}
		if (data.has("managed")) {
			emoji.setManaged(data.get("managed").booleanValue());
		}
		if (data.has("animated")) {
			emoji.setAnimated(data.get("animated").booleanValue());
		}
		if (data.has("available")) {
			emoji.setAvailable(data.get("available").booleanValue());
		}

		return emoji;
	}

}
