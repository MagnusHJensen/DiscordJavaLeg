package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.GuildEntity;
import dk.magnusjensen.discordjavaleg.entities.RoleEntity;

public class GuildRoleCreateEvent extends GenericGuildEvent {
	private RoleEntity role;


	public GuildRoleCreateEvent(DiscordJavaLeg client, GuildEntity guild, RoleEntity role) {
		super(client, guild);
		this.role = role;
	}

	public RoleEntity getRole() {
		return this.role;
	}
}
