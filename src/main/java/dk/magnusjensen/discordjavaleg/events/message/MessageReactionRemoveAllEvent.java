package dk.magnusjensen.discordjavaleg.events.message;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class MessageReactionRemoveAllEvent extends Event {
	public MessageReactionRemoveAllEvent(DiscordJavaLeg client) {
		super(client);
	}
}
