package dk.magnusjensen.discordjavaleg.events.voice;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class VoiceStateUpdateEvent extends Event {
	public VoiceStateUpdateEvent(DiscordJavaLeg client) {
		super(client);
	}
}
