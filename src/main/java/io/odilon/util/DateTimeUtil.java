package io.odilon.util;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class DateTimeUtil {

	
public static String timeElapsed(OffsetDateTime  from, OffsetDateTime to) {
		
		Check.requireNonNullArgument(from, "from is null");		
		Check.requireNonNullArgument(to, "to is null");
		
		if (from.plusSeconds(1).isAfter(to))
			return String.valueOf(dateTimeDifference(from, to, ChronoUnit.MILLIS))+" millis";
		
		long diff=dateTimeDifference(from, to, ChronoUnit.MILLIS);
		
		/** less than 5 min, display in seconds */
		if (from.plusMinutes(5).isAfter(to)) {
			double diffSecs = Double.valueOf(diff) / 1000.0;
			return String.format("%8.2f secs", diffSecs).trim();
		}
		 
		/** less than 1 hr, display in minutes */
		if (from.plusHours(1).isAfter(to)) {
			double diffMins = Double.valueOf(diff) / (1000.0*60.0);
			return String.format("%8.2f min", diffMins).trim();
		}
		
		/** more than 1 hr, display in hours */
		double diffHours = Double.valueOf(diff) / (1000.0*60.0*60.0);
		return String.format("%8.2f hr", diffHours).trim();
	}
	
public static long dateTimeDifference(Temporal d1, Temporal d2, ChronoUnit unit) {
        return unit.between(d1, d2);
    }
}
