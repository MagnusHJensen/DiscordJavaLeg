# DiscordJavaLeg

## DiscordJavaLeg is a public Discord API wrapper, written in Java.

This is a learning project, so it can be unstable, not optimized until i get around to it.
It also has missing docs, I'm gonna come around to that in time.


#### Quick example
```java
import dk.magnusjensen.discordjavaleg.events.EventListener;
import dk.magnusjensen.discordjavaleg.events.ReadyEvent;

public class Main implements EventListener<ReadyEvent> {

    public static void main(String[] args) {
        DiscordJavaLeg bot = new DiscordJavaLeg.Builder()
            .setToken("your bot token")
            .setAfk(false)
            .setPresence(Presence.Status.ONLINE, Presence.Type.GAME, "with DiscordJavaLeg")
            .disableIntents(GatewayIntent.GUILD_MEMBERS, (GatewayIntent).GUILD_PRESENCES)
            .addEventListener(this)
            .build();
    
        bot.login();
    }


    public void onEvent(ReadyEvent event) {
        System.out.println("Connected as " + event.getClient().getSelfUser());
    }

}
```

#### Ping Example

```java
import dk.magnusjensen.discordjavaleg.events.EventListener;import dk.magnusjensen.discordjavaleg.events.message.MessageRecievedEvent;

public class PingListener implements EventListener<MessageRecievedEvent> {
    
public void onEvent(MessageRecievedEvent event) {

        if (event.getMessage().getContent().equals("ping")) {
        	MessageEntity msg = event.getMessage().getChannel().send("Pong!");
        }

    }

}
```
