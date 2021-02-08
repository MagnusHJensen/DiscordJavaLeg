import dk.magnusjensen.discordjavaleg.entities.MessageEntity;
import dk.magnusjensen.discordjavaleg.events.EventListener;
import dk.magnusjensen.discordjavaleg.events.message.MessageRecievedEvent;

public class PingListener implements EventListener<MessageRecievedEvent> {

	@Override
	public void onEvent(MessageRecievedEvent event) {
		MessageEntity message = event.getMessage();

		if (message.getContent().equals("ping")) {
			MessageEntity msg = message.getChannel().send("Pong!");
			msg.edit("Pinog!");
			message.getChannel().send(Integer.toString(msg.getGuild().getMemberCount()));
		}
	}
}
