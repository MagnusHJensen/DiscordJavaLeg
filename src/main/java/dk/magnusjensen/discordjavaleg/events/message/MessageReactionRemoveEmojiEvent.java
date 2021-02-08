package dk.magnusjensen.discordjavaleg.events.message;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class MessageReactionRemoveEmojiEvent extends Event {
	public MessageReactionRemoveEmojiEvent(DiscordJavaLeg client) {
		super(client);
	}
}
