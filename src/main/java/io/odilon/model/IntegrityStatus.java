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
package io.odilon.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * Persistent integrity repair status of an object, written by the
 * background data scrubber ({@code DataIntegrityChecker}).
 * </p>
 *
 * <p>
 * The value is serialized to JSON as an integer code so that the on-disk
 * representation is compact and stable across renames.
 * {@link #fromCode} returns {@link #OK} for {@code null} or any unknown code,
 * which guarantees backward compatibility with objects written before this
 * field existed (their stored JSON will not contain {@code integrityStatus},
 * so Jackson leaves the field at the initialized default {@link #OK}).
 * </p>
 *
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
public enum IntegrityStatus {

    /**
     * Object passed its last scrub pass cleanly, or was successfully
     * repaired by the scrubber. This is also the implicit default for
     * objects written before this field was introduced.
     */
    OK(1),

    /**
     * Object has more corrupt shards than the Reed-Solomon parity capacity
     * can recover. Manual intervention or restoration from a standby replica
     * or backup is required.
     */
    IRRECOVERABLE(99);

    private final int code;

    IntegrityStatus(int code) {
        this.code = code;
    }

    /**
     * Returns the integer code persisted to JSON.
     * Jackson uses this as the serialized form via {@code @JsonValue}.
     */
    @JsonValue
    public int getCode() {
        return code;
    }

    /**
     * Deserializes from the integer code stored in JSON.
     *
     * <p>
     * Returns {@link #OK} for:
     * <ul>
     *   <li>{@code null} — field absent in older JSON (backward compatibility)</li>
     *   <li>any unrecognized code — forward compatibility with future values</li>
     * </ul>
     * </p>
     */
    @JsonCreator
    public static IntegrityStatus fromCode(Integer code) {
        if (code == null)
            return OK;
        for (IntegrityStatus s : values()) {
            if (s.code == code)
                return s;
        }
        return OK; // safe default — never throw on unknown codes
    }

    /** Returns the lowercase enum name, e.g. {@code "ok"}, {@code "irrecoverable"}. */
    public String getName() {
        return name().toLowerCase();
    }
}
