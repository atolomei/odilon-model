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
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.odilon.log.Logger;

/**
 * <p>Object's metadata (not the binary file)
 * It is returned by some of the API calls</p> 
 * 
 */
public class ObjectMetadata extends ODModelObject implements Serializable {
			
	@SuppressWarnings("unused")
	static private Logger logger =	Logger.getLogger(ObjectMetadata.class.getName());
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("bucketName")  
	public String bucketName;
	  
	@JsonProperty("objectName")
	public String objectName;
	
	@JsonProperty("drive")
	public String drive;

	@JsonProperty("creationDate")
	public  OffsetDateTime creationDate;
	
	@JsonProperty("versioncreationDate")
	public  OffsetDateTime versioncreationDate;

	@JsonProperty("version")
	public int version;
	
	@JsonProperty("length")
	public long length;
	
	@JsonProperty("encrypt")
	public boolean encrypt = true;
	
	@JsonProperty("vault")
	public boolean vault = true;
	
	@JsonProperty("etag")
	public String etag;
	
	@JsonProperty("contentType")
	public String contentType;
	
	@JsonProperty("fileName")
	public String fileName;
	
	@JsonProperty("status")
	public ObjectStatus status;

	@JsonProperty("raid")
	public String raid;

	@JsonProperty("sha256")
	public String sha256;

	@JsonProperty("sha256Blocks")
	public List<String> sha256Blocks;
	
	@JsonProperty("totalBlocks")
	public int totalBlocks;
	
	@JsonProperty("appVersion")
	public String appVersion;
	
	@JsonProperty("lastModified")
	public OffsetDateTime lastModified;
	
	@JsonProperty("dateSynced")
	public OffsetDateTime dateSynced;
	
	@JsonProperty("integrityCheck")
	public OffsetDateTime integrityCheck;

	@JsonProperty("systemTags")
	public String systemTags;


	//@JsonProperty("blocks")
	//public int blocks;

	
	
	public ObjectMetadata() {
	}
	
	public ObjectMetadata(String bucketName, String objectName) {
		  
		this.bucketName = bucketName;
		this.objectName = objectName;
		this.creationDate = OffsetDateTime.now();
		this.length = 0;
		this.version = 0;
		this.etag = "";
		this.contentType = "";
	  }

	public void setSha256Blocks(List<String> list) {
		sha256Blocks = list;
	}
	
	public List<String> getSha256Blocks() {
		return sha256Blocks;
	}
	
	public boolean isAccesible() {
		if (status==null)
			return false;
		return status==ObjectStatus.ENABLED || status==ObjectStatus.ARCHIVED;
		
	}
	  /**
	   * Checks whether given object is same as this ObjectStat.
	   */
	  @Override
	  public boolean equals(Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }

	    ObjectMetadata that = (ObjectMetadata) o;

	    if (length != that.length) {
	      return false;
	    }
	    if (!bucketName.equals(that.bucketName)) {
	      return false;
	    }
	    if (!objectName.equals(that.objectName)) {
	      return false;
	    }
	    if (!creationDate.equals(that.creationDate)) {
	      return false;
	    }
	    if (!etag.equals(that.etag)) {
	      return false;
	    }
	    return contentType.equals(that.contentType);
	  }

	  /**
	   * 
	   */
	  @Override
	  public int hashCode() {
	    int result = bucketName.hashCode();
	    result = 31 * result + objectName.hashCode();
	    result = 31 * result + creationDate.hashCode();
	    result = 31 * result + (int) (length ^ (length >>> 32));
	    result = 31 * result + etag.hashCode();
	    result = 31 * result + contentType.hashCode();
	    result = 31 * result + (int) (version ^ (version >>> 32));
	    return result;
	  }

	  public void setSystemTags(String systemTags) {
			this.systemTags=systemTags;
	  }

	  public String getSystemTags() {
			return this.systemTags;
	  }
	  
	  /**
	   * Returns bucket name.
	   */
	  public String bucketName() {
	    return bucketName;
	  }

	  /**
	   * Returns object name.
	   */
	  public String name() {
	    return objectName;
	  }


	  /**
	   * Returns created time.
	   */
	  public OffsetDateTime creationDate() {
	    return creationDate;
	  }

	  /**
	   * Returns object length.
	   */
	  public long length() {
	    return length;
	  }

	  /**
	   * Returns ETag.
	   */
	  public String etag() {
	    return etag;
	  }

	  /**
	   * Returns content type of object.
	   */
	  public String contentType() {
	    return contentType;
	  }

	public void addSystemTag(String stag) {

		if (stag==null)
			return;
		
		if (this.systemTags==null)
			this.systemTags = stag.replace("|", "#");
		else
			this.systemTags = this.systemTags + "|" + stag.replace("|", "#");
	}
}
