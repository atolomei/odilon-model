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
package io.odilon.errors;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.odilon.model.BaseObject;

/**
 * <p>
 * JSON version of Odilon errors
 * </p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
public class OdilonErrorProxy extends BaseObject {

    @JsonProperty("httpStatus")
    private int httpStatus;

    @JsonProperty("odilonErrorCode")
    private int odilonErrorCode;

    @JsonProperty("odilonErrorMessage")
    private String odilonErrorMessage;

    @JsonProperty("context")
    private Map<String, String> context = new HashMap<String, String>();

    public OdilonErrorProxy() {
    }

    public OdilonErrorProxy(int httpStatus, int odilonErrorCode, String odilonErrorMessage) {

        this.httpStatus = httpStatus;
        this.odilonErrorCode = odilonErrorCode;
        this.odilonErrorMessage = odilonErrorMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return odilonErrorCode;
    }

    public void setErrorCode(int odilonErrorCode) {
        this.odilonErrorCode = odilonErrorCode;
    }

    public String getMessage() {
        return odilonErrorMessage;
    }

    public void setErrorMessage(String odilonErrorMessage) {
        this.odilonErrorMessage = odilonErrorMessage;
    }

    public Map<String, String> getContext() {
        return context;
    }

}
