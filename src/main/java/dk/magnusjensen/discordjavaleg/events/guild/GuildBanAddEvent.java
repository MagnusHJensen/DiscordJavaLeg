package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.GuildEntity;
import dk.magnusjensen.discordjavaleg.entities.UserEntity;

public class GuildBanAddEvent extends GenericGuildEvent {

	private UserEntity user;

	public GuildBanAddEvent(DiscordJavaLeg client, GuildEntity guild, UserEntity user) {
		super(client, guild);
		this.user = user;
	}

	public UserEntity getUser() {
		return this.user;
	}
}
