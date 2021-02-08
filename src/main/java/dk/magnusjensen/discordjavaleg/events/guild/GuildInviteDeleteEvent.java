package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.events.Event;

public class GuildInviteDeleteEvent extends Event {
	private String code;
	public GuildInviteDeleteEvent(DiscordJavaLeg client, String code) {
		super(client);
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
