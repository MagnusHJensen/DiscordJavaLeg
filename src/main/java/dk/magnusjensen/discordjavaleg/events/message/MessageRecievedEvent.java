package dk.magnusjensen.discordjavaleg.events.message;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.MessageEntity;
import dk.magnusjensen.discordjavaleg.events.Event;

public class MessageRecievedEvent extends Event {
	private MessageEntity message;
	public MessageRecievedEvent(DiscordJavaLeg client, MessageEntity message) {
		super(client);
		this.message = message;
	}

	public MessageEntity getMessage() {
		return this.message;
	}
}
