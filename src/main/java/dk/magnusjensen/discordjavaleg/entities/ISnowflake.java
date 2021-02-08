package dk.magnusjensen.discordjavaleg.entities;

import dk.magnusjensen.discordjavaleg.util.TimeUtil;

import java.time.OffsetDateTime;

public interface ISnowflake {
	long getId();

	default OffsetDateTime getCreatedAt() {
		return TimeUtil.getTimeCreated(getId());
	}
}
