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
package io.odilon.json;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * <p>
 * Jackson 3 ObjectMapper wrapper.
 * </p>
 *
 * <p>
 * <b>Why {@code ADJUST_DATES_TO_CONTEXT_TIME_ZONE} is disabled</b>: Jackson 3
 * enables this {@link DateTimeFeature} by default, which means every
 * {@link java.time.OffsetDateTime} is silently rewritten to the
 * deserialization context's default time zone (the JVM default, effectively
 * UTC unless configured otherwise) when it is read back from JSON — e.g. a
 * value serialized as {@code "2026-07-04T19:06:32.552465-03:00"} is
 * deserialized as the equivalent instant {@code "2026-07-04T22:06:32.552465Z"}.
 * The two values represent the exact same instant, but they are NOT the same
 * JSON string.
 * </p>
 *
 * <p>
 * {@code ObjectMetadataChecksum} (in {@code odilon-server}) hashes the raw
 * JSON serialization of {@code ObjectMetadata} to detect drive-level
 * corruption. Because {@code ObjectMetadata} carries several
 * {@code OffsetDateTime} fields ({@code creationDate}, {@code lastModified},
 * {@code versioncreationDate}, {@code integrityCheck}) that are normally
 * created with a non-UTC offset (the server's local zone), the checksum
 * computed right before the very first write never matched the checksum
 * recomputed the moment the record was read back — a false-positive "corrupt
 * metadata" warning on <em>every</em> object, on <em>every</em> read,
 * immediately after it was created. Disabling this feature makes
 * deserialization preserve the literal offset that was serialized, so the
 * JSON — and therefore the checksum — is stable across a save/read cycle.
 * </p>
 */
public class OdilonObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public OdilonObjectMapper() {
		super(defaultBuilder());
	}

	private static JsonMapper.Builder defaultBuilder() {
		return JsonMapper.builder().disable(DateTimeFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
	}
}