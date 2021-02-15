package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class GuildEntity implements ISnowflake {

	private long id;
	private String name;
	@Nullable
	private String icon = null;
	@Nullable
	private String splash = null;
	@Nullable
	private String discoverySplash = null;
	private long ownerId;
	private String region;
	@Nullable
	private Long afkChannelId = null;
	private int afkTimeout;
	private int verificationLevel;
	private int defaultMessageNotifactions;
	private int contentFilter;
	private Map<Long, RoleEntity> roles = new HashMap<>();
	private Map<Long, EmojiEntity> emojis = new HashMap<>();
	private EnumSet<GuildFeature> features = EnumSet.noneOf(GuildFeature.class);
	private int mfaLevel;
	@Nullable
	private Long applicationId = null;
	@Nullable
	private Long systemChannelId = null;
	private int systemChannelFlags;
	@Nullable
	private Long rulesChannelId = null;
	private boolean large = false;
	private boolean unavailable = false;
	private int memberCount;
	private Map<Long, MemberEntity> members = new HashMap<>();
	private Map<Long, ChannelEntity> channels = new HashMap<>();
	private Map<String, InviteEntity> invites = new HashMap<>();


	public GuildEntity(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Nullable
	public String getIcon() {
		return icon;
	}

	public void setIcon(@Nullable String icon) {
		this.icon = icon;
	}

	@Nullable
	public String getSplash() {
		return splash;
	}

	public void setSplash(@Nullable String splash) {
		this.splash = splash;
	}

	@Nullable
	public String getDiscoverySplash() {
		return discoverySplash;
	}

	public void setDiscoverySplash(@Nullable String discoverySplash) {
		this.discoverySplash = discoverySplash;
	}


	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Nullable
	public Long getAfkChannelId() {
		return afkChannelId;
	}

	public void setAfkChannelId(@Nullable Long afkChannelId) {
		this.afkChannelId = afkChannelId;
	}

	public int getAfkTimeout() {
		return afkTimeout;
	}

	public void setAfkTimeout(int afkTimeout) {
		this.afkTimeout = afkTimeout;
	}

	public int getVerificationLevel() {
		return verificationLevel;
	}

	public void setVerificationLevel(int verificationLevel) {
		this.verificationLevel = verificationLevel;
	}

	public int getDefaultMessageNotifactions() {
		return defaultMessageNotifactions;
	}

	public void setDefaultMessageNotifactions(int defaultMessageNotifactions) {
		this.defaultMessageNotifactions = defaultMessageNotifactions;
	}

	public int getContentFilter() {
		return contentFilter;
	}

	public void setContentFilter(int contentFilter) {
		this.contentFilter = contentFilter;
	}

	public Collection<RoleEntity> getRoles() {
		return roles.values();
	}

	public void addRole(RoleEntity role) {
		this.roles.put(role.getId(), role);
	}

	public void removeRole(RoleEntity role) {
		this.roles.remove(role.getId());
	}

	public RoleEntity fetchRole(long id) {
		return this.roles.get(id);
	}

	public Collection<EmojiEntity> getEmojis() {
		return emojis.values();
	}

	private void addEmoji(EmojiEntity emoji) {
		this.emojis.put(emoji.getId(), emoji);
	}

	public EmojiEntity fetchEmoji(long id) {
		return this.emojis.get(id);
	}

	public EnumSet<GuildFeature> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<GuildFeature> features) {
		this.features.addAll(features);
	}

	public int getMfaLevel() {
		return mfaLevel;
	}

	public void setMfaLevel(int mfaLevel) {
		this.mfaLevel = mfaLevel;
	}

	@Nullable
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(@Nullable Long applicationId) {
		this.applicationId = applicationId;
	}

	@Nullable
	public Long getSystemChannelId() {
		return systemChannelId;
	}

	public void setSystemChannelId(@Nullable Long systemChannelId) {
		this.systemChannelId = systemChannelId;
	}

	public int getSystemChannelFlags() {
		return systemChannelFlags;
	}

	public void setSystemChannelFlags(int systemChannelFlags) {
		this.systemChannelFlags = systemChannelFlags;
	}

	@Nullable
	public Long getRulesChannelId() {
		return rulesChannelId;
	}

	public void setRulesChannelId(@Nullable Long rulesChannelId) {
		this.rulesChannelId = rulesChannelId;
	}

	public boolean isLarge() {
		return large;
	}

	public void setLarge(boolean large) {
		this.large = large;
	}

	public boolean isUnavailable() {
		return unavailable;
	}

	public void setUnavailable(boolean unavailable) {
		this.unavailable = unavailable;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public Collection<MemberEntity> getMembers() {
		return members.values();
	}

	public void addMember(MemberEntity member) {
		this.members.put(member.getId(), member);
	}

	public void removeMember(long memberId) {
		this.members.remove(memberId);
	}

	public MemberEntity fetchMember(long id) {
		return this.members.get(id);
	}

	public Map<Long, ChannelEntity> getChannels() {
		return channels;
	}

	public ChannelEntity getChannel(long channelId) {
		return this.channels.get(channelId);
	}


	// TODO: Figure out limited acces to my code only, not all users.
	public void addChannel(ChannelEntity channel) {
		this.channels.put(channel.getId(), channel);
	}

	// TODO: ^^^^
	public void removeChannel(ChannelEntity channel) {
		this.channels.remove(channel.getId());
	}

	public ArrayList<InviteEntity> fetchInvites() {
		return new ArrayList<>(this.invites.values());
	}

	@Nullable
	public InviteEntity getInvite(String code) {
		return this.invites.getOrDefault(code, null);
	}

	public void addInvite(InviteEntity invite) {
		this.invites.put(invite.getCode(), invite);
	}

	public void removeInvite(String code) {
		this.invites.remove(code);
	}

	@Override
	public long getId() {
		return this.id;
	}

	public static GuildEntity parseGuildFromJson(JsonNode data, boolean create, DiscordJavaLeg client) {
		GuildEntity guild = new GuildEntity(Long.parseLong(data.get("id").textValue()), data.get("name").textValue());
		guild.setIcon(data.get("icon").asText());
		guild.setSplash(data.get("splash").asText());
		guild.setOwnerId(Long.parseLong(data.get("owner_id").textValue()));

		guild.setAfkTimeout(data.get("afk_timeout").intValue());
		guild.setVerificationLevel(data.get("verification_level").intValue());
		guild.setDefaultMessageNotifactions(data.get("default_message_notifications").intValue());
		guild.setContentFilter(data.get("explicit_content_filter").intValue());
		RoleEntity.parseArrayFromJson((ArrayNode) data.get("roles")).forEach(guild::addRole);
		EmojiEntity.parseArrayFromJson((ArrayNode) data.get("emojis"), guild, client).forEach(guild::addEmoji);
		guild.setFeatures(GuildFeature.parseFeaturesFromArray((ArrayNode) data.get("features")));
		guild.setMfaLevel(data.get("mfa_level").intValue());
		guild.setSystemChannelFlags(data.get("system_channel_flags").intValue());



		if (create) {
			// set fields, only sent from GUILD_CREATE
			guild.setLarge(data.get("large").booleanValue());
			guild.setUnavailable(data.get("unavailable").booleanValue());
			guild.setMemberCount(data.get("member_count").intValue());
			MemberEntity.parseArrayFromJson((ArrayNode) data.get("members"), guild).forEach(guild::addMember);
			ChannelEntity.parseArrayFromJson((ArrayNode) data.get("channels"), client).forEach(guild::addChannel);
		}

		if (data.hasNonNull("afk_channel_id")) {
			guild.setAfkChannelId(Long.parseLong(data.get("afk_channel_id").textValue()));
		}

		if (data.hasNonNull("application_id")) {
			guild.setApplicationId(Long.parseLong(data.get("application_id").textValue()));
		}
		if (data.hasNonNull("system_channel_id")) {
			guild.setSystemChannelId(Long.parseLong(data.get("system_channel_id").textValue()));
		}
		if (data.hasNonNull("rules_channel_id")) {
			guild.setRulesChannelId(Long.parseLong(data.get("rules_channel_id").textValue()));
		}

		ArrayList<InviteEntity> invites = client.getInvitesForGuild(guild.id);

		invites.forEach(guild::addInvite);

		return guild;
	}


}
