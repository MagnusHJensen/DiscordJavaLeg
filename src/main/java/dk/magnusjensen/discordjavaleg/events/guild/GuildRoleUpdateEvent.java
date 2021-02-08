package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.GuildEntity;
import dk.magnusjensen.discordjavaleg.entities.RoleEntity;
import dk.magnusjensen.discordjavaleg.events.Event;

public class GuildRoleUpdateEvent extends GenericGuildEvent {

	private RoleEntity oldRole;
	private RoleEntity newRole;

	public GuildRoleUpdateEvent(DiscordJavaLeg client, GuildEntity guild, RoleEntity newRole, RoleEntity oldRole) {
		super(client, guild);
		this.oldRole = oldRole;
		this.newRole = newRole;
	}

	public RoleEntity getNewRole() {
		return this.newRole;
	}


	/**
	* @return RoleEntity containing the old role. The old role, does not exists in the guild any longer.
    */
	public RoleEntity getOldRole() {
		return this.oldRole;
	}
}
