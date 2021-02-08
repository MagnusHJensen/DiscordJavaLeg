package dk.magnusjensen.discordjavaleg.events.webhooks;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class WebhooksUpdateEvent extends Event {
	public WebhooksUpdateEvent(DiscordJavaLeg client) {
		super(client);
	}
}
