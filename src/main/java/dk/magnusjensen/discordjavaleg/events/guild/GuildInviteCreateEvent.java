package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.InviteEntity;
import dk.magnusjensen.discordjavaleg.events.Event;

public class GuildInviteCreateEvent extends Event {
	private InviteEntity invite;
	public GuildInviteCreateEvent(DiscordJavaLeg client, InviteEntity invite) {
		super(client);
		this.invite = invite;
	}

	public InviteEntity getInvite() {
		return this.invite;
	}
}
