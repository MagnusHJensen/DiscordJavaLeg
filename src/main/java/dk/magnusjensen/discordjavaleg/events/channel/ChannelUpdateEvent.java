package dk.magnusjensen.discordjavaleg.events.channel;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.ChannelEntity;

public class ChannelUpdateEvent extends GenericChannelEvent {

	public ChannelUpdateEvent(DiscordJavaLeg client, ChannelEntity channel) {
		super(client, channel);
	}
}
