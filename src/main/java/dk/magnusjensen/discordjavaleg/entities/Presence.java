package dk.magnusjensen.discordjavaleg.entities;

public class Presence {
	private Type type;
	private Status status;
	private String description;

	public Presence(Status status, Type type, String description) {
		this.status = status;
		this.type = type;
		this.description = description;
	}

	public Status getStatus() {
		return this.status;
	}

	public Type getType() {
		return this.type;
	}

	public String getDescription() {
		return this.description;
	}


	public enum Type {
		GAME(0),
		STREAMING(1),
		LISTENING(2),
		CUSTOM(4),
		COMPETING(5);

		private int type;
		Type(int type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return Integer.toString(this.type);
		}
	}

	public enum Status {

		ONLINE("online"),
		DND("dnd"),
		IDLE("idle"),
		INVISIBLE("invisible"),
		OFFLINE("offline");

		private String status;
		Status(String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return this.status;
		}
	}
}
