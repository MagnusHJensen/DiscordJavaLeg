package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

public class UserEntity implements ISnowflake {
	private long id;
	private String username;
	private String discriminator;
	@Nullable
	private String avatar = null;
	private boolean bot = false;
	private boolean system = false;
	private boolean mfaEnabled = false;
	private String locale = "enus";
	private boolean verified = false;
	@Nullable
	private String email = null;
	private int flags = 0;
	private int premiumType = 0;
	private int publicFlags = 0;

	public UserEntity(long id, String username, String discriminator) {
		this.id = id;
		this.username = username;
		this.discriminator = discriminator;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getUsername() {
		return username;
	}

	public String getDiscriminator() {
		return discriminator;
	}

	@Nullable
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}

	public boolean isSystem() {
		return system;
	}
	public void setSystem(boolean system) {
		this.system = system;
	}

	public boolean isMfaEnabled() {
		return mfaEnabled;
	}
	public void setMfaEnabled(boolean mfaEnabled) {
		this.mfaEnabled = mfaEnabled;
	}

	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@Nullable
	public String getEmail() {
		return email;
	}

	public int getFlags() {
		return flags;
	}

	public int getPremiumType() {
		return premiumType;
	}

	public int getPublicFlags() {
		return publicFlags;
	}

	public static UserEntity parseUserFromJsonNode(JsonNode node) {
		return new UserEntity(Long.parseLong(node.get("id").textValue()), node.get("username").asText(), node.get("discriminator").asText());
	}

	@Override
	public String toString() {
		return this.username + "#" + this.discriminator;
	}
}
