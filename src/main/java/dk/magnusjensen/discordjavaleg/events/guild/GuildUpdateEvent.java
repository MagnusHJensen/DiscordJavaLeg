package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.GuildEntity;

public class GuildUpdateEvent extends GenericGuildEvent {

	public GuildUpdateEvent(DiscordJavaLeg client, GuildEntity guild) {
		super(client, guild);
	}
}
