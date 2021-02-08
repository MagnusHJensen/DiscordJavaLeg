package dk.magnusjensen.discordjavaleg.events.channel;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.ChannelEntity;
import dk.magnusjensen.discordjavaleg.events.Event;

public class GenericChannelEvent extends Event {

	private ChannelEntity channel;

	public GenericChannelEvent(DiscordJavaLeg client, ChannelEntity channel) {
		super(client);
		this.channel = channel;
	}

	public ChannelEntity getChannel() {
		return this.channel;
	}
}
