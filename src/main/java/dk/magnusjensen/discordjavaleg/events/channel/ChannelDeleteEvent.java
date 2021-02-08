package dk.magnusjensen.discordjavaleg.events.channel;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.ChannelEntity;

public class ChannelDeleteEvent extends GenericChannelEvent {
	public ChannelDeleteEvent(DiscordJavaLeg client, ChannelEntity channel) {
		super(client, channel);
	}
}
