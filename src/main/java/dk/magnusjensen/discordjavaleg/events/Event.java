package dk.magnusjensen.discordjavaleg.events;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;

public class Event {

	private DiscordJavaLeg client;

	public Event(DiscordJavaLeg client) {
		this.client = client;
	}

	public DiscordJavaLeg getClient() {
		return client;
	}
}
