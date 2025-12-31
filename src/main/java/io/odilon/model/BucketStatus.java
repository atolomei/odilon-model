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

import io.odilon.util.Check;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * <p>
 * Status of a Bucket
 * </p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
public enum BucketStatus {

    ENABLED("enabled", 1, "enabled"), ARCHIVED("archived", 2, "archived"), DELETED("deleted", 3, "deleted");

    private String name;
    private int code;
    private String description;
    
    static List<BucketStatus> ops;

    // A static map to quickly look up enum constants by name
    private static final Map<String, BucketStatus> FORMAT_MAP = 
        Arrays.stream(BucketStatus.values())
              .collect(Collectors.toMap(s -> s.name, Function.identity()));

    /**
     * Factory method for deserialization using the 'name' property from the JSON object.
     * Jackson uses this method when it encounters a JSON object instead of a simple string.
     */
    @JsonCreator
    public static BucketStatus fromJson(@JsonProperty("name") String name) {
    	return Optional.ofNullable(FORMAT_MAP.get(name.toLowerCase()))
                       .orElseThrow(() -> new IllegalArgumentException("Unknown name: " + name));
    }
    
    
    
    
    public boolean isAccesible() {
        return this == ENABLED || this == ARCHIVED;
    }

    private BucketStatus(String name, int code, String description) {
        this.name = name;
        this.code = code;
        this.description=description;
    }

    public String getDescription() {
        //return getDescription(Locale.getDefault());
    	return this.description;
    }

    public String getDescription(Locale locale) {
        // ResourceBundle res =
        // ResourceBundle.getBundle(BucketStatus.this.getClass().getName(), locale);
        // return res.getString(this.getName());
        return this.description;
    }

    /**
    public String toJSON() {
        StringBuilder str = new StringBuilder();
        str.append("\"name\": \"" + name + "\"");
        str.append(", \"code\": " + String.valueOf(code));
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.getClass().getSimpleName() + "{");
        str.append(toJSON());
        str.append("}");
        return str.toString();
    }
*/
    
    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static BucketStatus fromId(String id) {

        Check.requireNonNullStringArgument(id, "id is null or empty");

        try {
            int value = Integer.valueOf(id).intValue();
            return get(value);

        } catch (IllegalArgumentException e) {
            throw (e);
        } catch (Exception e) {
            throw new IllegalArgumentException("id can not be converted int Integer -> " + id);
        }

    }

    public static List<BucketStatus> getValues() {

        if (ops != null)
            return ops;

        ops = new ArrayList<BucketStatus>();

        ops.add(ENABLED);
        ops.add(ARCHIVED);
        ops.add(DELETED);

        return ops;
    }

    public static BucketStatus get(int code) {

        if (code == ENABLED.getCode())
            return ENABLED;
        if (code == ARCHIVED.getCode())
            return ARCHIVED;
        if (code == DELETED.getCode())
            return DELETED;

        throw new IllegalArgumentException("unsuported code -> " + String.valueOf(code));

    }

}
