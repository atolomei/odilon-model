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

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>A {@link DataProvider} that keeps track of the 
 * Server Agent that is processing its' page requests. </p>
 * 
 * <p>	It is used by the client to iterate through a bucket's Objects.
 * The cacheKey serves as a reference for the server, who uses
 * it to optimize the iteration (for example prevents the client
 * from fetching more results when there are no more items)</p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 * 
 * @param <T> must be Serializable
 */
public abstract class CachedDataProvider<T extends Serializable> implements DataProvider<T> {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("cacheKey")
	private String cacheKey;

	@JsonIgnore
	private boolean done = false;

	public CachedDataProvider() {
	}
	
	public void setCacheKey(String key) {
		this.cacheKey=key;
	}
	
	public String getCacheKey() {
		return this.cacheKey;
	}
	
	
	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	@Override
	public DataList<T> fetch() throws IOException {
		return fetch(Optional.empty(), Optional.ofNullable(cacheKey));
	}
	
	@Override
	public DataList<T> fetch(long offset) throws IOException {
		return fetch(Optional.of(Long.valueOf(offset)), Optional.ofNullable(cacheKey));
	}

	protected abstract DataList<T> fetch(Optional<Long> offset, Optional<String> cacheKey) throws IOException;	

}
