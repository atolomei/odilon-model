package io.odilon.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Locale;


public class FileNameNormalizer {

	 public static String normalize(String rawName) {

	        // 1️⃣ Decode URL (tolerante)
	        String decoded = decodeLenient(rawName);

	        // 2️⃣ Normalizar Unicode (ñ → n + ~)
	        String normalized = Normalizer.normalize(decoded, Normalizer.Form.NFD);

	        // 3️⃣ Eliminar diacríticos
	        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

	        // 4️⃣ Reemplazar caracteres no ASCII seguros
	        normalized = normalized
	                .replaceAll("[^a-zA-Z0-9._-]", "_")
	                .replaceAll("_+", "_");

	        // 5️⃣ Lowercase opcional
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
