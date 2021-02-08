package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class GuildCreateEvent extends Event {
	public GuildCreateEvent(DiscordJavaLeg client) {
		super(client);
	}
}
