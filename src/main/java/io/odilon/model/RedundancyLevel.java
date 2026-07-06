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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.odilon.util.Check;

/**
 * <p>
 * The standard RAID levels comprise a basic set of RAID ("redundant array of
 * independent disks" or "redundant array of inexpensive disks") configurations
 * that employ the techniques of striping, mirroring, or parity to create large
 * reliable data stores from multiple general-purpose computer hard disk drives
 * (HDDs).
 * </p>
 * 
 * <p>
 * Supported types<br/>
 * <br/>
 * <b>RAID 0 (striping)</b> <br/>
 * Two or more disks are combined to form a volume, which appears as a single
 * virtual drive. It is not a configuration with data replication, its function
 * is to provide greater storage and performance by allowing access to the disks
 * in parallel. <br/>
 * <br/>
 * <br/>
 * <b>RAID 1 (mirroring)</b> <br/>
 * For each object, 1 or more exact copies (or mirrors) are created on two or
 * more disks. This provides redundancy in case of disk failure. At least 2
 * disks are required, Odilon also supports 3 or more for greater redundancy.
 * <br/>
 * <br/>
 * <br/>
 * <b>RAID 6 / Erasure Coding</b> <br/>
 * It is a method of encoding data into blocks that can be distributed across
 * multiple disks or nodes and then reconstructed from a subset of those blocks.
 * It has great flexibility since you can adjust the number and size of the
 * blocks and the minimum required for recovery. It uses less disk space than
 * RAID 1 and can withstand multiple full disk failures. Odilon implements this
 * architecture using Reed Solomon error-correction codes. Odilon supports these
 * configurations: <br/>
 * </p>
 * <ul>
 * <li>3 Disks -> data: 2, parity: 1</li>
 * <li>6 Disks -> data: 4, parity: 2</li>
 * <li>12 Disks -> data: 8, parity: 4</li>
 * <li>24 Disks -> data: 16, parity: 8</li>
 * <li>48 Disks -> data: 32, parity: 16</li>
 * </ul>
 * 
 * 
 */
public enum RedundancyLevel {

	RAID_0("RAID 0", 0), // (striping)
	RAID_1("RAID 1", 1), // (mirroring)
	/**
	 * Erasure Coding via Reed-Solomon.
	 * The canonical property-file value is now {@code ErasureCoding}.
	 * The legacy value {@code RAID 6} is still accepted for backward compatibility.
	 */
	ERASURE_CODING("ErasureCoding", 6); // (Erasure Codes Reed Solomon -> 2+1, 4+2, 8+4, 16+8, 24+12, 32+16)

	private String name;
	private int code;
	private String nameCompatible;

	static List<RedundancyLevel> list;

	/**
	 * Resolution map: canonical names + all legacy aliases, all lower-cased.
	 * "erasurecoding" → RAID_6  (canonical new value)
	 * "raid 6"        → RAID_6  (legacy property value)
	 * "raid_6"        → RAID_6  (legacy underscore form)
	 * "raid 0"        → RAID_0
	 * "raid_0"        → RAID_0
	 * "raid 1"        → RAID_1
	 * "raid_1"        → RAID_1
	 */
	private static final Map<String, RedundancyLevel> FORMAT_MAP;
	static {
		FORMAT_MAP = Arrays.stream(RedundancyLevel.values())
				.collect(Collectors.toMap(s -> s.nameCompatible.toLowerCase(), Function.identity()));
		// Legacy aliases kept so existing odilon.properties files keep working
		FORMAT_MAP.put("raid 6",        ERASURE_CODING);
		FORMAT_MAP.put("raid_6",        ERASURE_CODING);
		FORMAT_MAP.put("erasure_coding", ERASURE_CODING); // backward compat: old Java enum name serialized by Jackson
		FORMAT_MAP.put("raid 0",        RAID_0);
		FORMAT_MAP.put("raid_0",        RAID_0);
		FORMAT_MAP.put("raid 1",        RAID_1);
		FORMAT_MAP.put("raid_1",        RAID_1);
	}

	/**
	 * Factory method for deserialization.
	 * Accepts both the canonical name ({@code ErasureCoding}) and all legacy aliases.
	 */
	@JsonCreator
	public static RedundancyLevel fromJson(@JsonProperty("name") String name) {
		String key = name.trim().toLowerCase();
		return Optional.ofNullable(FORMAT_MAP.get(key))
				.orElseThrow(() -> new IllegalArgumentException("Unknown redundancyLevel: " + name));
	}

	// ...existing code...

	public static List<RedundancyLevel> getValues() {

		if (list != null)
			return list;

		list = new ArrayList<RedundancyLevel>();

		list.add(RAID_0);
		list.add(RAID_1);
		list.add(ERASURE_CODING);

		return list;
	}

	/**
	 * Resolves a property-file or JSON string to a {@link RedundancyLevel}.
	 * Accepts both the canonical values and all legacy spellings:
	 * <ul>
	 *   <li>{@code ErasureCoding} → {@link #ERASURE_CODING}  (canonical)</li>
	 *   <li>{@code RAID 6}        → {@link #ERASURE_CODING}  (legacy)</li>
	 *   <li>{@code RAID 0}        → {@link #RAID_0}</li>
	 *   <li>{@code RAID 1}        → {@link #RAID_1}</li>
	 * </ul>
	 * Returns {@code null} for unrecognized values.
	 */
	public static RedundancyLevel get(String name) {
		Check.requireNonNullArgument(name, "name is null");
		return FORMAT_MAP.get(name.trim().toLowerCase());
	}

	public static RedundancyLevel get(int code) {

		if (code == RAID_0.code)
			return RAID_0;
		if (code == RAID_1.code)
			return RAID_1;
		if (code == ERASURE_CODING.code)
			return ERASURE_CODING;

		throw new IllegalArgumentException("unsupported code -> " + String.valueOf(code));

	}

	private RedundancyLevel(String name, int code) {
		this.name = name;
		this.code = code;
		this.nameCompatible = name.replace(" ", "_");

	}

	public String getDescription() {
		return getDescription(Locale.getDefault());
	}

	public String getDescription(Locale locale) {
		 
		return getName();

	}

	 

	@JsonValue
	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}
}
