package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.GuildEntity;
import dk.magnusjensen.discordjavaleg.events.Event;

public class GenericGuildEvent extends Event {

	private GuildEntity guild;

	public GenericGuildEvent(DiscordJavaLeg client, GuildEntity guild) {
		super(client);
		this.guild = guild;
	}

	public GuildEntity getGuild() {
		return this.guild;
	}
}
