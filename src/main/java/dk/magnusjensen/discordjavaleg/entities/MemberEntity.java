package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;

import java.util.ArrayList;

public class MemberEntity {
	private UserEntity user = null;
	private String nick;
	private ArrayList<RoleEntity> roles;
	private String premiumSince = null;
	private boolean deaf;
	private boolean mute;
	private boolean pending = false;
	private String permissions = null;
	private GuildEntity guild;


	public MemberEntity(String nick, ArrayList<RoleEntity> roles, boolean deaf, boolean mute, GuildEntity guild) {
		this.nick = nick;
		this.roles = roles;
		this.deaf = deaf;
		this.mute = mute;
		this.guild = guild;
	}

	public long getId() {
		return this.user.getId();
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getNick() {
		return nick;
	}

	public String getPremiumSince() {
		return premiumSince;
	}
	public void setPremiumSince(String premiumSince) {
		this.premiumSince = premiumSince;
	}

	public boolean isDeaf() {
		return deaf;
	}

	public boolean isMute() {
		return mute;
	}

	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String perms) {
		this.permissions = perms;
	}

	protected static ArrayList<MemberEntity> parseArrayFromJson(ArrayNode node, GuildEntity guild, DiscordJavaLeg client) {
		ArrayList<MemberEntity> members = new ArrayList<>();

		node.forEach((memberData) -> members.add(parseMemberFromJson(memberData, guild, client)));

		return members;
	}

	public static MemberEntity parseMemberFromJson(JsonNode memberData, GuildEntity guild, DiscordJavaLeg client) {
		String nick = memberData.get("nick").textValue();
		ArrayList<RoleEntity> roles = new ArrayList<>();
		memberData.get("roles").forEach((roleId) -> {
			roles.add(guild.fetchRole(Long.parseLong(roleId.textValue())));
		});
		boolean deaf = memberData.get("deaf").booleanValue();
		boolean mute = memberData.get("mute").booleanValue();


		MemberEntity member = new MemberEntity(nick, roles, deaf, mute, guild);

		if (memberData.has("user")) {
			member.setUser(UserEntity.parseUserFromJsonNode(memberData.get("user"), client));

		}
		if (memberData.has("permissions")) {
			member.setPermissions(memberData.get("permissions").textValue());
		}
		if (memberData.has("premium_since")) {
			member.setPremiumSince(memberData.get("premium_since").textValue());
		}
		if (memberData.has("pending")) {
			member.setPending(memberData.get("pending").booleanValue());
		}
		return member;
	}
}
