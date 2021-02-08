package dk.magnusjensen.discordjavaleg.events.message;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class MessageDeletedEvent extends Event {
	public MessageDeletedEvent(DiscordJavaLeg client) {
		super(client);
	}
}
