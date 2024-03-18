/*
 * Odilon Object Storage
 * (C) Novamens 
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


/**
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
public class Check {

	public static <T> T requireNonNullArgument(T obj, String message) {
        if (obj == null)
            throw new IllegalArgumentException(message);
        return obj;
    }
	
	public static String requireNonNullStringArgument(String obj, String message) {

		if (obj == null)
            throw new IllegalArgumentException(message);
        
        if ("".equals((String) obj)) 
            throw new IllegalArgumentException(message);
        
        return obj;
    }
						
	public static void requireTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}
	
	public static void checkTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}
	public static <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }

}
