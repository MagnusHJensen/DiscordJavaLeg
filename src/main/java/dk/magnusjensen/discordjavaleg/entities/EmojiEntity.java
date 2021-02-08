package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class EmojiEntity implements ISnowflake {

	private long id;
	private String name;
	private ArrayList<RoleEntity> roles;
	private UserEntity user;
	private boolean requireColons;
	private boolean managed;
	private boolean animated;
	private boolean available;

	public EmojiEntity(long id, String name, ArrayList<RoleEntity> roles, UserEntity user, boolean requireColons, boolean managed, boolean animated, boolean available) {
		this.id = id;
		this.name = name;
		this.roles = roles;
		this.user = user;
		this.requireColons = requireColons;
		this.managed = managed;
		this.animated = animated;
		this.available = available;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<RoleEntity> getRoles() {
		return roles;
	}

	public UserEntity getUser() {
		return user;
	}

	public boolean isRequireColons() {
		return requireColons;
	}

	public boolean isManaged() {
		return managed;
	}

	public boolean isAnimated() {
		return animated;
	}

	public boolean isAvailable() {
		return available;
	}

	protected static ArrayList<EmojiEntity> parseArrayFromJson(ArrayNode node) {
		ArrayList<EmojiEntity> emojis = new ArrayList<>();

		node.forEach((emojiData) -> {
			long id = Long.parseLong(emojiData.get("id").textValue());
			String name = emojiData.get("name").textValue();
			ArrayList<RoleEntity> roles = RoleEntity.parseArrayFromJson((ArrayNode) emojiData.get("roles"));
			UserEntity user = UserEntity.parseUserFromJsonNode(emojiData.get("user"));
			boolean requireColons = emojiData.get("require_colons").booleanValue();
			boolean managed = emojiData.get("managed").booleanValue();
			boolean animated = emojiData.get("animated").booleanValue();
			boolean available = emojiData.get("available").booleanValue();

			emojis.add(new EmojiEntity(id, name, roles, user, requireColons, managed, animated, available));
		});

		return emojis;
	}
}
