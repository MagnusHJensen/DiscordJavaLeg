package dk.magnusjensen.discordjavaleg.events.user;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class UserTypingEvent extends Event {
	public UserTypingEvent(DiscordJavaLeg client) {
		super(client);
	}
}
