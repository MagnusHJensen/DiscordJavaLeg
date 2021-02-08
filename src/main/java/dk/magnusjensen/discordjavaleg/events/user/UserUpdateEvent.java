package dk.magnusjensen.discordjavaleg.events.user;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class UserUpdateEvent extends Event {
	public UserUpdateEvent(DiscordJavaLeg client) {
		super(client);
	}
}
