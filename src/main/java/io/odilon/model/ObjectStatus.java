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

/**
 * <p>
 * Odilon Object's Status
 * </p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
public enum ObjectStatus {

    DRAFT("draft", 0), ENABLED("enabled", 1), ARCHIVED("archived", 2), DELETED("deleted", 3);

    private String name;
    private int code;

    static List<ObjectStatus> ops;

    private ObjectStatus(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getDescription() {
        return getDescription(Locale.getDefault());
    }

    public String getDescription(Locale locale) {
        // ResourceBundle res =
        // ResourceBundle.getBundle(ObjectStatus.this.getClass().getName(), locale);
        // return res.getString(this.getName());
        return this.getName();
    }

    public String toJSON() {
        StringBuilder str = new StringBuilder();
        str.append("\"name\": \"" + name + "\"");
        str.append(", \"code\": " + String.valueOf(code));
        str.append(", \"description\": \"" + getDescription() + "\"");
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

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static ObjectStatus fromId(String id) {

        if (id == null)
            throw new IllegalArgumentException("id is null");

        try {
            int value = Integer.valueOf(id).intValue();
            return get(value);

        } catch (IllegalArgumentException e) {
            throw (e);
        } catch (Exception e) {
            throw new IllegalArgumentException("id not integer -> " + id);
        }

    }

    public static List<ObjectStatus> getValues() {

        if (ops != null)
            return ops;

        ops = new ArrayList<ObjectStatus>();

        ops.add(DRAFT);
        ops.add(ENABLED);
        ops.add(ARCHIVED);
        ops.add(DELETED);

        return ops;
    }

    public static ObjectStatus get(int code) {

        if (code == DRAFT.getCode())
            return DRAFT;
        if (code == ENABLED.getCode())
            return ENABLED;
        if (code == ARCHIVED.getCode())
            return ARCHIVED;
        if (code == DELETED.getCode())
            return DELETED;

        throw new IllegalArgumentException("unsuported code -> " + String.valueOf(code));

    }
}
