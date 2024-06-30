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

package io.odilon.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Odilon stores objects using a flat structure of containers called Buckets.</p>
 * <p>A bucket is like a folder, it just contains binary objects, potentially a very large number. 
 *  Every object contained by a bucket has a unique ObjectName in that bucket; therefore, 
 *  the pair <b>BucketName</b> + <b>ObjectName</b> is a Unique ID for each object in Odilon.
 * </p>
 * 
 * <p>This class is a JSON representation of a Bucket used both by the 
 * Odilon server and Odilon client SDK. These JSON objects are stored 
 * in disk on the server and also sent to clients through the network 
 * or Internet.</p>
 *  
 * @author atolomei@novamens.com (Alejandro Tolomei)
 * 
 */
public class Bucket extends OdilonModelObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("creationDate")
	private OffsetDateTime creationDate;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("status")
	private BucketStatus status;
	
	@JsonProperty("lastModified")
	public OffsetDateTime lastModified;
	
	public Bucket() {
	}
	
	public Bucket(String name, Long id, OffsetDateTime creationDate, OffsetDateTime lastModified, BucketStatus status) {
	  this.name=name;
	  this.id=id;
	  this.creationDate=creationDate;
	  this.lastModified=lastModified;
	  this.status=status;
	}

	public BucketStatus getStatus() {
	   return status;
	}

	public String getName() {
	   return name;
	}
	
	public Long getId() {
	   return id;
	}
	
	public OffsetDateTime getCreationDate() {
		 return creationDate;
	}
	 

	  
}
