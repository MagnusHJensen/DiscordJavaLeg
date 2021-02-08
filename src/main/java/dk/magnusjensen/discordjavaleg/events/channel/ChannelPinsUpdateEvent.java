package dk.magnusjensen.discordjavaleg.events.channel;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class ChannelPinsUpdateEvent extends Event {
	public ChannelPinsUpdateEvent(DiscordJavaLeg client) {
		super(client);
	}
}
