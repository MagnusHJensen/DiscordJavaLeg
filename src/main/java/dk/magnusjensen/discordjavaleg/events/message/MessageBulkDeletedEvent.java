package dk.magnusjensen.discordjavaleg.events.message;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class MessageBulkDeletedEvent extends Event {
	public MessageBulkDeletedEvent(DiscordJavaLeg client) {
		super(client);
	}
}
