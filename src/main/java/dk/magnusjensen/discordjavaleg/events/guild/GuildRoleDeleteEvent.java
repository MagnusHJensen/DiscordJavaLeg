package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.GuildEntity;
import dk.magnusjensen.discordjavaleg.entities.RoleEntity;
import dk.magnusjensen.discordjavaleg.events.Event;

public class GuildRoleDeleteEvent extends GenericGuildEvent {
	private RoleEntity role;

	public GuildRoleDeleteEvent(DiscordJavaLeg client, GuildEntity guild, RoleEntity role) {
		super(client, guild);
		this.role = role;
	}

	public RoleEntity getRole() {
		return role;
	}
}
