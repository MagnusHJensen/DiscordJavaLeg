package dk.magnusjensen.discordjavaleg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.neovisionaries.ws.client.*;
import com.neovisionaries.ws.client.WebSocket;
import dk.magnusjensen.discordjavaleg.entities.*;
import dk.magnusjensen.discordjavaleg.events.Event;
import dk.magnusjensen.discordjavaleg.events.EventListener;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class DiscordJavaLeg {

	public static String LIB_NAME = "DiscordJavaLeg";
	private String API_BASE_URL = "https://discord.com/api/v8";
	private OkHttpClient API_CLIENT = new OkHttpClient();
	private WebSocket ws;
	private ObjectMapper mapper = new ObjectMapper();
	private EventHandler eventHandler = new EventHandler(this);
	private boolean waitingForACK;
	private Timer HB_TIMER = new Timer();
	private Builder builder;
	private int lastSequence = -1;
	private String sessionId;
	private UserEntity selfUser;
	private Map<Long, GuildEntity> guilds = new HashMap<>();
	private ArrayList<Long> unavailableGuilds = new ArrayList<>();

	public DiscordJavaLeg(Builder builder){
		this.builder = builder;
	}

	public UserEntity getSelfUser() {
		return this.selfUser;
	}


	protected OkHttpClient getApiClient() {
		return this.API_CLIENT;
	}

	protected WebSocket getWS() {
		return this.ws;
	}

	protected ObjectMapper getMapper() {
		return this.mapper;
	}

	protected Builder getBuilder() {
		return this.builder;
	}

	public void login() throws IOException {

		Request request = new Request.Builder()
				.url(API_BASE_URL + "/gateway/bot")
				.addHeader("Authorization", "Bot " + builder.getToken())
				.get().build();

		Call call = API_CLIENT.newCall(request);

		Response response = call.execute();

		assert(response.code() == 200);

		String body = response.body().string();

		JsonNode node = mapper.readValue(body, JsonNode.class);

		String wsUrl = node.get("url").asText();

		ws = new WebSocketFactory().createSocket(wsUrl + "/?v=8&encoding=json");


		ws.addListener(new OPCodeParser(this));

		ws.connectAsynchronously();
	}

	protected boolean isWaitingForACK() {
		return this.waitingForACK;
	}

	protected void recievedHeartbeatACK() {
		this.waitingForACK = false;
	}

	protected void setupHeartBeat(int heartbeatInterval) {
		HB_TIMER.schedule(new TimerTask() {
			@Override
			public void run() {
				sendHeartBeat();
			}
		}, 0, heartbeatInterval);
	}

	protected void sendHeartBeat() {
		if (ws != null) {
			if (isWaitingForACK()) {
				// Terminate with a non-1000 closing code, reconnect and attempt to resume.
			}
			if (lastSequence == -1) {
				ws.sendText("{\"op\": 1, \"d\": null}");
			} else {
				ws.sendText("{\"op\": 1, \"d\": " + lastSequence + "}");
			}
			this.waitingForACK = true;
		}
	}

	protected void setLastSequence(int sequence) {
		if (sequence != 0) {
			lastSequence = sequence;
		}
	}

	protected void identifySelf() {
		StringBuilder identifyString = new StringBuilder();
		identifyString.append("{");
		identifyString.append("\"op\": 2,");

		identifyString.append("\"d\": {");
		identifyString.append("\"token\": \"").append(this.getBuilder().getToken()).append("\",");
		identifyString.append("\"intents\": " + this.calculateIntents() + ",");

		identifyString.append("\"properties\": {");
		identifyString.append("\"os\": \"windows\",");
		identifyString.append("\"browser\": \"" + DiscordJavaLeg.LIB_NAME + "\",");
		identifyString.append("\"device\": \"" + DiscordJavaLeg.LIB_NAME + "\"");
		identifyString.append("},");

		identifyString.append("\"presence\": {");
		identifyString.append("\"status\": \"" + this.getBuilder().getPresence().getStatus() + "\",");
		identifyString.append("\"afk\": " + this.getBuilder().isAfk() +",");
		identifyString.append("\"activities\": [{");
		identifyString.append("\"name\": \"" + this.getBuilder().getPresence().getDescription() + "\",");
		identifyString.append("\"type\": " + this.getBuilder().getPresence().getType());
		identifyString.append("}]");
		identifyString.append("}");

		identifyString.append("}");

		identifyString.append("}");
		System.out.println(identifyString.toString());

		ws.sendText(identifyString.toString());
	}

	protected void handleEvent(JsonNode node) throws JsonProcessingException {
		String eventName = node.get("t").asText();
		JsonNode dataNode = node.get("d");

		this.eventHandler.handleEvent(eventName, dataNode);

	}

	protected void addGuild(GuildEntity guild) {
		if (this.unavailableGuilds.contains(guild.getId())) {
			this.unavailableGuilds.remove(guild.getId());
		}
		this.guilds.put(guild.getId(), guild);
	}

	public int getAvailableGuildsCount() {
		return this.guilds.size();
	}

	protected void addUnavailableGuild(long id) {
		this.unavailableGuilds.add(id);
	}

	public int getUnavailableGuildsCount() {
		return this.unavailableGuilds.size();
	}

	protected void setSelfUser(UserEntity selfUser) {
		this.selfUser = selfUser;
	}

	private int calculateIntents() {
		int totalIntent = 0;
		for (GatewayIntent intent : this.builder.intents) {
			int lsv = intent.getLeftShiftValue();
			if (lsv == 0) {
				totalIntent++;
			} else {
				totalIntent += 1 << lsv;
			}
		}
		return totalIntent;
	}

	public MessageEntity sendMessage(String msg, long id) {
		RequestBody body = RequestBody.Companion.create(msg, MediaType.parse("application/json"));
		Request messageRequest = new Request.Builder()
				.url(API_BASE_URL + "/channels/" + id + "/messages")
				.addHeader("Authorization", "Bot " + this.builder.getToken())
				.post(body)
				.build();

		Call call = API_CLIENT.newCall(messageRequest);
		try {
			Response response = call.execute();
			return MessageEntity.parseMessageFromJson(mapper.readValue(response.body().string(), JsonNode.class), this);
		} catch (IOException exception) {
			exception.printStackTrace();
			return null;
		}

	}

	public MessageEntity editMessage(String contentJson, long channelId, long id) {
		RequestBody body = RequestBody.Companion.create(contentJson, MediaType.parse("application/json"));
		Request messageRequest = new Request.Builder()
				.url(API_BASE_URL + "/channels/" + channelId + "/messages/" + id)
				.addHeader("Authorization", "Bot " + this.builder.getToken())
				.patch(body)
				.build();

		Call call = API_CLIENT.newCall(messageRequest);
		try {
			Response response = call.execute();
			return MessageEntity.parseMessageFromJson(mapper.readValue(response.body().string(), JsonNode.class), this);
		} catch (IOException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	public ArrayList<InviteEntity> getInvitesForGuild(long guildId) {
		Request request = new Request.Builder()
				.url(this.API_BASE_URL + "/guilds/" + guildId + "/invites")
				.addHeader("Authorization", "Bot " + this.builder.getToken())
				.get()
				.build();

		Call call = this.API_CLIENT.newCall(request);
		try {
			Response response = call.execute();
			ArrayNode inviteData = this.mapper.readValue(response.body().string(), ArrayNode.class);
			return InviteEntity.parseInvitesFromJson(inviteData);
		} catch (IOException ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}
	}

	public GuildEntity getGuild(long guildId) {
		return this.guilds.get(guildId);
	}

	public static class Builder {
		private String token;
		private Presence presence;
		private boolean afk;
		private Map<String, ArrayList<EventListener<? extends Event>>> listeners = new HashMap<>();
		private EnumSet<GatewayIntent> intents = EnumSet.allOf(GatewayIntent.class);

		public Builder() {
		}

		public Builder setToken(String token) {
			this.token = token;
			return this;
		}

		public String getToken() {
			return token;
		}

		public Builder setPresence(Presence.Status status, Presence.Type type, String description) {
			this.presence = new Presence(status, type, description);
			return this;
		}

		public Presence getPresence() {
			return this.presence;
		}

		public Builder setAfk(boolean afk) {
			this.afk = afk;
			return this;
		}

		public boolean isAfk() {
			return afk;
		}

		public Builder addEventListener(EventListener<? extends Event> listener) {
			String eventName = ((ParameterizedType) listener.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0].getTypeName();

			if (!listeners.containsKey(eventName)){
				listeners.put(eventName, new ArrayList<>());
			}
			listeners.get(eventName).add(listener);
			return this;
		}

		public ArrayList<EventListener<? extends Event>> getListenersForEvent(String eventName) {
			if (listeners.containsKey(eventName)) {
				return listeners.get(eventName);
			}

			return new ArrayList<>();
		}

		public Builder disableIntents(GatewayIntent... intents) {
			for (GatewayIntent intent : intents) {
				this.intents.remove(intent);
			}
			return this;
		}

		public DiscordJavaLeg build() {
			return new DiscordJavaLeg(this);
		}

	}
}
