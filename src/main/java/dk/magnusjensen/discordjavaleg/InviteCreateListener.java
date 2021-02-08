package dk.magnusjensen.discordjavaleg;

import dk.magnusjensen.discordjavaleg.events.EventListener;
import dk.magnusjensen.discordjavaleg.events.guild.GuildInviteCreateEvent;

public class InviteCreateListener implements EventListener<GuildInviteCreateEvent> {
	@Override
	public void onEvent(GuildInviteCreateEvent event) {
		System.out.println(event.getInvite());
	}
}
