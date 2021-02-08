import dk.magnusjensen.discordjavaleg.events.EventListener;
import dk.magnusjensen.discordjavaleg.events.ReadyEvent;

public class ReadyListener implements EventListener<ReadyEvent> {
	@Override
	public void onEvent(ReadyEvent event) {
		System.out.println(event.getClient().getSelfUser());
	}
}
