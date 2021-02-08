package dk.magnusjensen.discordjavaleg.events.user;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class UserInteractionEvent extends Event {
	public UserInteractionEvent(DiscordJavaLeg client) {
		super(client);
	}
}
