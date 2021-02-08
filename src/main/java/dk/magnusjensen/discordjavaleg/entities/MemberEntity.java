package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class MemberEntity {
	private UserEntity user;
	private String nickname = null;
	private ArrayList<Long> roleIds;
	private String premiumSince = null;
	private boolean deaf;
	private boolean mute;
	private boolean pending = false;
	private String permission = null;
	private GuildEntity guild;


	public MemberEntity(UserEntity user, ArrayList<Long> roleIds, boolean deaf, boolean mute, GuildEntity guild) {
		this.user = user;
		this.roleIds = roleIds;
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

	public String getNickname() {
		return nickname;
	}
	public void setNickName(String nickname) {
		this.nickname = nickname;
	}

	public ArrayList<Long> getRoleIds() {
		return roleIds;
	}

	public RoleEntity getRoleById(long id) {
		return this.guild.fetchRole(id);
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

	public String getPermission() {
		return permission;
	}
	public void setPermissions(String perms) {
		this.permission = perms;
	}

	protected static ArrayList<MemberEntity> parseArrayFromJson(ArrayNode node, GuildEntity guild) {
		ArrayList<MemberEntity> members = new ArrayList<>();

		node.forEach((memberData) -> members.add(parseMemberFromJson(memberData, guild)));

		return members;
	}

	public static MemberEntity parseMemberFromJson(JsonNode memberData, GuildEntity guild) {
		UserEntity user = UserEntity.parseUserFromJsonNode(memberData.get("user"));
		ArrayList<Long> roleIds = new ArrayList<>();
		memberData.get("roles").forEach((roleId) -> {
			roleIds.add(Long.parseLong(roleId.textValue()));
		});

		boolean deaf = memberData.get("deaf").booleanValue();
		boolean mute = memberData.get("mute").booleanValue();


		MemberEntity member = new MemberEntity(user, roleIds, deaf, mute, guild);

		if (memberData.has("nick")) {
			member.setNickName(memberData.get("nick").textValue());
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
