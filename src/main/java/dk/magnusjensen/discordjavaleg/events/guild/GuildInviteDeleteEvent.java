package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.InviteEntity;
import dk.magnusjensen.discordjavaleg.events.Event;

public class GuildInviteDeleteEvent extends Event {
	private String code;
	private InviteEntity invite;
	public GuildInviteDeleteEvent(DiscordJavaLeg client, String code) {
		super(client);
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public InviteEntity getInvite() {
		return this.invite;
	}

	public void setInvite(InviteEntity invite) {
		this.invite = invite;
	}
}
