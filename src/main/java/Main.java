import dk.magnusjensen.discordjavaleg.DiscordJavaLeg;
import dk.magnusjensen.discordjavaleg.InviteCreateListener;
import dk.magnusjensen.discordjavaleg.entities.Presence;

import java.io.IOException;

public class Main {


	public static void main(String[] args) throws IOException {
		DiscordJavaLeg javaLeg = new DiscordJavaLeg.Builder()
				.setToken("Nzg0OTM2ODcyMjk1NTMwNTI2.X8wjbQ.cZ2dy9jur-FmbLPW-9SmDhfGVmc")
				.setAfk(false)
				.setPresence(Presence.Status.IDLE, Presence.Type.GAME, "I love burritos!")
				//.disableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
				.addEventListener(new ReadyListener())
				.addEventListener(new PingListener())
				.addEventListener(new InviteCreateListener())
				.build();

		javaLeg.login();

	}


}
