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
 * 
 * 
 *  
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei) 
 */
public class BucketMetadata extends ODModelObject implements Serializable {
			
	private static final long serialVersionUID = 1L;

	@JsonProperty("bucketName")  
	public String bucketName;
	  
	@JsonProperty("creationDate")
	public  OffsetDateTime creationDate;
	
	@JsonProperty("status")
	public BucketStatus status;

	@JsonProperty("appVersion")
	public String appVersion;

	@JsonProperty("lastModified")
	public OffsetDateTime lastModified;
	
	public BucketMetadata() {
	}
	
	public BucketMetadata(String bucketName) {
		this.bucketName = bucketName;
	  }

	public BucketMetadata clone() {
		BucketMetadata clone = new BucketMetadata();
		clone.bucketName = bucketName;
		clone.creationDate = creationDate;
		clone.status=status;
		clone.appVersion=appVersion;
		clone.lastModified=lastModified;
		return clone;
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

	    BucketMetadata that = (BucketMetadata) o;

	    if (!bucketName.equals(that.bucketName)) {
	      return false;
	    }

	    return true;
	  }

	  /**
	   * Returns hash of this ObjectStat.
	   */
	  @Override
	  public int hashCode() {
	    int result = bucketName.hashCode();
	    result = 31 * result + creationDate.hashCode();
	    return result;
	  }

	  /**
	   * Returns bucket name.
	   */
	  public String bucketName() {
	    return bucketName;
	  }


	  /**
	   * Returns created time.
	   */
	  public OffsetDateTime creationDate() {
	    return creationDate;
	  }

	  public BucketStatus getStatus() {
		    return status;
		  }


	  
	

}
