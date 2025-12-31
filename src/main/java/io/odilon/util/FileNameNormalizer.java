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

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Locale;

public class FileNameNormalizer {

	public static String normalize(String rawName) {

		// Decode URL (tolerante)
		String decoded = decodeLenient(rawName);

		// Normalizar Unicode (ñ → n + ~)
		String normalized = Normalizer.normalize(decoded, Normalizer.Form.NFD);

		// Eliminar diacríticos
		normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

		// Reemplazar caracteres no ASCII seguros
		normalized = normalized.replaceAll("[^a-zA-Z0-9._-]", "_").replaceAll("_+", "_");

		// Lowercase opcional
		return normalized.toLowerCase(Locale.ROOT);
	}

	private static String decodeLenient(String input) {
		try {
			return URLDecoder.decode(input, StandardCharsets.UTF_8);
		} catch (IllegalArgumentException e) {
			return input.replace("%20", " ");
		}
	}
}
