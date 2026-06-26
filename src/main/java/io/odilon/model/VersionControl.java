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
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.odilon.log.Logger;
import io.odilon.util.Check;


/**

<p>
Version Control

Disabled
Standard
Protected Versioning

Where:

Setting	New Versions	Delete Previous Versions
Disabled	No	N/A
Standard	Yes	Yes
Protected	Yes	No
  
  
  
 * </p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
public enum VersionControl {

	DISABLED("disabled", 0), STANDARD("standard", 1), PROTECTED("protected", 2);

	/** Write Once Read Many */

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(VersionControl.class.getName());

	private static List<VersionControl> ds;
	private static List<String> names;

	private String name;
	private int code;

	public String getDescription() {
		return getDescription(Locale.getDefault());
	}

	public String getDescription(Locale locale) {
		return this.getName();
	}

	public String toJSON() {
		StringBuilder str = new StringBuilder();
		str.append("\"name\":\"" + name + "\"");
		str.append(", \"code\":" + code);
		str.append(", \"description\": \"" + getDescription() + "\"");
		return str.toString();
	}

	@JsonValue
	public String toJson() {
		return this.name;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.getClass().getSimpleName());
		str.append(toJSON());
		return str.toString();
	}

	public String getName() {
		return this.name;
	}

	public int getCode() {
		return this.code;
	}

	private VersionControl(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public static List<String> getNames() {

		if (names != null)
			return names;

		synchronized (VersionControl.class) {
			names = new ArrayList<String>();
			names.add(DISABLED.getName());
			names.add(STANDARD.getName());
			names.add(PROTECTED.getName());
		}
		return names;
	}

	public static List<VersionControl> getValues() {

		if (ds != null)
			return ds;

		synchronized (VersionControl.class) {
			ds = new ArrayList<VersionControl>();
			ds.add(DISABLED);
			ds.add(STANDARD);
			ds.add(PROTECTED);
		}
		return ds;
	}

	public static VersionControl fromId(String id) {

		Check.requireNonNullArgument(id, "id is null");

		try {
			int value = Integer.valueOf(id).intValue();
			return fromCode(value);

		} catch (IllegalArgumentException e) {
			throw (e);
		} catch (Exception e) {
			throw new IllegalArgumentException("id not integer -> " + id);
		}
	}

	@JsonCreator
	public static VersionControl fromValue(String value) {

		if (value == null) {
			return null;
		}

		String normalized = value.trim();

		/*
		 * Legacy boolean compatibility:
		 * Old servers stored versionControl as a JSON boolean (true/false).
		 * true  -> STANDARD  (version control was enabled)
		 * false -> DISABLED  (version control was disabled)
		 */
		if ("true".equalsIgnoreCase(normalized))
			return STANDARD;
		if ("false".equalsIgnoreCase(normalized))
			return DISABLED;

		/*
		 * Legacy toString() compatibility
		 */
		if (normalized.startsWith("VersionControl")) {

			int idx = normalized.indexOf("\"name\":");

			if (idx >= 0) {

				int firstQuote = normalized.indexOf('"', idx + 7);

				int secondQuote = normalized.indexOf('"', firstQuote + 1);

				if (firstQuote >= 0 && secondQuote > firstQuote) {

					normalized = normalized.substring(firstQuote + 1, secondQuote);
				}
			}
		}

		for (VersionControl ds : values()) {

			if (ds.name.equalsIgnoreCase(normalized) || ds.name().equalsIgnoreCase(normalized)) {

				return ds;
			}
		}

		throw new IllegalArgumentException("unsupported name -> " + value);
	}

	public static VersionControl fromCode(int code) {

		if (code == PROTECTED.getCode())
			return PROTECTED;
		if (code == DISABLED.getCode())
			return DISABLED;
		if (code == STANDARD.getCode())
			return STANDARD;

		throw new IllegalArgumentException("unsuported code -> " + String.valueOf(code));
	}

}
