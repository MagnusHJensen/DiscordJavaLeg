package dk.magnusjensen.discordjavaleg.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class RoleEntity implements ISnowflake {

	private long id;
	private String name;
	private int color;
	private boolean hoist;
	private int position;
	private String permissions;
	private boolean managed;
	private boolean mentionable;

	public RoleEntity(long id, String name, int color, boolean hoist, int position, String permissions, boolean managed, boolean mentionable) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.hoist = hoist;
		this.position = position;
		this.permissions = permissions;
		this.managed = managed;
		this.mentionable = mentionable;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return "#" + color;
	}

	public boolean isHoist() {
		return hoist;
	}

	public int getPosition() {
		return position;
	}

	public String getPermissions() {
		return permissions;
	}

	public boolean isManaged() {
		return managed;
	}

	public boolean isMentionable() {
		return mentionable;
	}

	protected static ArrayList<RoleEntity> parseArrayFromJson(ArrayNode node) {
		ArrayList<RoleEntity> roles = new ArrayList<>();
		if (node.size() == 0) {
			return roles;
		}
		node.forEach((roleData) -> {
			roles.add(parseRoleFromJson(roleData));
		});

		return roles;
	}

	public static RoleEntity parseRoleFromJson(JsonNode roleData) {
		long id = Long.parseLong(roleData.get("id").textValue());
		String name = roleData.get("name").textValue();
		int color = roleData.get("color").intValue();
		boolean hoist = roleData.get("hoist").booleanValue();
		int position = roleData.get("position").intValue();
		String permissions = roleData.get("permissions").textValue();
		boolean managed = roleData.get("managed").booleanValue();
		boolean mentionable = roleData.get("mentionable").booleanValue();
		return new RoleEntity(id, name, color, hoist, position, permissions, managed, mentionable);
	}
}
