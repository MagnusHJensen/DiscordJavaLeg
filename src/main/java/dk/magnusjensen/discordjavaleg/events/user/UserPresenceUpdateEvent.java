package dk.magnusjensen.discordjavaleg.events.user;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class UserPresenceUpdateEvent extends Event {
	public UserPresenceUpdateEvent(DiscordJavaLeg client) {
		super(client);
	}
}
