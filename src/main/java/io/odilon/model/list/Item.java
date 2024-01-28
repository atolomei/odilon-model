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
package io.odilon.model.list;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.odilon.model.ODModelObject;
		
/**
 * 
 * <p>Wrapper for Lists and other Iterable structures where some elements may not be a T but an error</p>
 *
 * @param <T>
 */
public class Item<T extends Serializable> extends ODModelObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("object")
	private T object;
	
	@JsonProperty("error")
	private String error;
	
	public Item() {
	}
	
	public T getObject() {
		return object;
	}
	
	public Item(Exception e) {
		this.error=e.getClass().getName()+ " | " + e.getMessage();
	}
	
	public Item(T object) {
		this.object=object;
	}
	
	public void setError(String error) {
		this.error=error;
	}
	
	public boolean isOk() {
		return object!=null;
	}

	public String getErrorString() {
		return error;
	}
	

}
