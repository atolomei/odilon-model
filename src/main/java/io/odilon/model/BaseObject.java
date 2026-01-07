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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.odilon.json.OdilonObjectMapper;
import io.odilon.log.Logger;
import io.odilon.util.RandomIDGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
 

@JsonInclude(Include.NON_NULL)
public abstract class BaseObject implements JSONObject {

	static private Logger logger = Logger.getLogger(BaseObject.class.getName());

	static final private ObjectMapper mapper =  new OdilonObjectMapper();

	@JsonIgnore
	static final private RandomIDGenerator idGenerator = new RandomIDGenerator();
	
	@JsonIgnore
	public ObjectMapper getObjectMapper() {
		return mapper;
	}

	public String toJSON() {
		try {
			return getObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			logger.error(e, SharedConstant.NOT_THROWN);
			return "\"error\":\"" + e.getClass().getName() + " | " + e.getMessage() + "\"";
		}
	}
	
	protected String randomString(final int size) {
		return idGenerator.randomString(size);
	}

}
