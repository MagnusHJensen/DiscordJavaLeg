package dk.magnusjensen.discordjavaleg.events.voice;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class VoiceChannelUpdate extends Event {
	public VoiceChannelUpdate(DiscordJavaLeg client) {
		super(client);
	}
}
