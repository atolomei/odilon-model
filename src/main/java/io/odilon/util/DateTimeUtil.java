/*
 * Odilon Object Storage
 * (c) kbee 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.odilon.util;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class DateTimeUtil {

	/**
	 * <p>
	 * Returns the current instant truncated to millisecond precision.
	 * </p>
	 *
	 * <p>
	 * <b>Why this exists</b>: {@code OffsetDateTime.now()} carries whatever
	 * resolution the underlying JVM clock provides — on most modern JVMs this is
	 * microsecond or even nanosecond precision. Every timestamp field stamped onto
	 * a newly created {@code ObjectMetadata} ({@code creationDate},
	 * {@code versioncreationDate}, {@code lastModified}, {@code integrityCheck},
	 * {@code dateSynced}) is included in the {@code metaChecksum} hash computed by
	 * {@code ObjectMetadataChecksum} in {@code odilon-server}. Truncating to
	 * milliseconds here — <em>before</em> the value is ever stamped onto the
	 * object and hashed — makes the stored value canonical and independent of
	 * clock resolution or any particular Jackson date/time serializer
	 * configuration, so the checksum stays stable across save/read cycles and
	 * across drives with (theoretically) different JVM clock resolutions.
	 * </p>
	 *
	 * <p>
	 * Callers that create or update {@link io.odilon.model.ObjectMetadata}
	 * timestamp fields should use this method instead of calling
	 * {@code OffsetDateTime.now()} directly.
	 * </p>
	 */
	public static OffsetDateTime now() {
		return OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS);
	}

	public static String timeElapsed(OffsetDateTime from, OffsetDateTime to) {

		Check.requireNonNullArgument(from, "from is null");
		Check.requireNonNullArgument(to, "to is null");

		if (from.plusSeconds(1).isAfter(to))
			return String.valueOf(dateTimeDifference(from, to, ChronoUnit.MILLIS)) + " millis";

		long diff = dateTimeDifference(from, to, ChronoUnit.MILLIS);

		/** less than 5 min, display in seconds */
		if (from.plusMinutes(5).isAfter(to)) {
			double diffSecs = Double.valueOf(diff) / 1000.0;
			return String.format("%8.2f secs", diffSecs).trim();
		}

		/** less than 1 hr, display in minutes */
		if (from.plusHours(1).isAfter(to)) {
			double diffMins = Double.valueOf(diff) / (1000.0 * 60.0);
			return String.format("%8.2f min", diffMins).trim();
		}

		/** more than 1 hr, display in hours */
		double diffHours = Double.valueOf(diff) / (1000.0 * 60.0 * 60.0);
		return String.format("%8.2f hr", diffHours).trim();
	}

	public static long dateTimeDifference(Temporal d1, Temporal d2, ChronoUnit unit) {
		return unit.between(d1, d2);
	}
}
