package dk.magnusjensen.discordjavaleg.util;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtil {

	private static final long DISCORD_EPOCH = 1420070400000L;
	private static final long TIMESTAMP_OFFSET = 22;

	public static OffsetDateTime getTimeCreated(long id) {
		long timestamp = (id >> TIMESTAMP_OFFSET) + DISCORD_EPOCH;
		Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmt.setTimeInMillis(timestamp);
		return OffsetDateTime.ofInstant(gmt.toInstant(), gmt.getTimeZone().toZoneId());
	}
}
