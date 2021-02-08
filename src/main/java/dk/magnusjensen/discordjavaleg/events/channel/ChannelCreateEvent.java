package dk.magnusjensen.discordjavaleg.events.channel;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.ChannelEntity;

public class ChannelCreateEvent extends GenericChannelEvent {
	public ChannelCreateEvent(DiscordJavaLeg client, ChannelEntity channel) {
		super(client, channel);
	}
}
