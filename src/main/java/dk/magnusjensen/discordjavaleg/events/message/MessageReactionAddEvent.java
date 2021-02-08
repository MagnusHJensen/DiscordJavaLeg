package dk.magnusjensen.discordjavaleg.events.message;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class MessageReactionAddEvent extends Event {
	public MessageReactionAddEvent(DiscordJavaLeg client) {
		super(client);
	}
}
