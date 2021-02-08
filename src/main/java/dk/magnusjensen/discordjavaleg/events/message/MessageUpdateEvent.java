package dk.magnusjensen.discordjavaleg.events.message;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class MessageUpdateEvent extends Event {
	public MessageUpdateEvent(DiscordJavaLeg client) {
		super(client);
	}
}
