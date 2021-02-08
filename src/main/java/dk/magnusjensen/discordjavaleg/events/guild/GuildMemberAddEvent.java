package dk.magnusjensen.discordjavaleg.events.guild;

import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.entities.GuildEntity;
import dk.magnusjensen.discordjavaleg.entities.MemberEntity;

public class GuildMemberAddEvent extends GenericGuildEvent {
	private MemberEntity member;


	public GuildMemberAddEvent(DiscordJavaLeg client, GuildEntity guild, MemberEntity member) {
		super(client, guild);
		this.member = member;
	}

	public MemberEntity getMember() {
		return this.member;
	}
}
