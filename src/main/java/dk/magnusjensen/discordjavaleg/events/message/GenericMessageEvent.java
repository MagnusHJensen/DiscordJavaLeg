package dk.magnusjensen.discordjavaleg.events.message;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.MessageEntity;
import dk.magnusjensen.discordjavaleg.events.Event;

public class GenericMessageEvent extends Event {
	private MessageEntity message;
	public GenericMessageEvent(DiscordJavaLeg client) {
		super(client);
	}
}
