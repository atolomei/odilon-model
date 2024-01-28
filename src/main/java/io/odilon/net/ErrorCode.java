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
package io.odilon.net;

import java.util.Optional;

public enum ErrorCode {
			
	NOT_MODIFIED (1000, "Not modified"), 
	ACCESS_DENIED (1010, "Access denied"), 
	TOO_MANY_REQUESTS(1020, "Too many requests"),
	INVALID_VERSION (1030, "Version in Request is older than the current Version"),  
	LOCKED(1040, "Locked"), 
	NO_DATA(1050, "No data"), 
	INVALID_APPLICATION (1060, "Invalid application"),

 	RESOURCE_NOT_FOUND(1260, "Resource not found"), 
 	OBJECT_NOT_FOUND (1210, "Object not found -> %1"),
 	OBJECT_ALREADY_EXIST (1410, "%1"),
 	RESOURCES_ERROR(1500, "Resources Error"),

 	
 	
	SERVER_UNREACHEABLE (5000, "Server unreacheable"),
 	API_NOT_ENABLED (7000, "Api not enabled"),
	MALFORMED_URL (8700, "Malformed URL"),
	
	BUCKET_NOT_EMPTY(8701, "%1"),
	BUCKET_NOT_EXISTS(8702, "%1"),
	
	DATA_STORAGE_MODE_OPERATION_NOT_ALLOWED(8800, "%1"),
	
	INTERNAL_ERROR (9000, "%1"),
	CLIENT_ERROR (9001, "%1");
	
	private int code;
	private String message;
			
	private ErrorCode(int code, String message) {
		this.message = message;
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public int value() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String toJSON() {
		return  "\"errorCode\":"+String.valueOf(code)+ ", \"message\":" + 
				(Optional.of(message).isPresent()? ("\""+message+"\""):"null");
	}
}
