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
 *  <p>Metadata info for an Odilon Bucket, used both by the Odilon Server and Odilon Java SDK clients</p>
 *  <p>See {@link Bucket} JSON representation of a {@link Bucket}, used both by the server and SDK client</p> 
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei) 
 */
public class BucketMetadata extends OdilonModelObject implements Serializable {
			
	private static final long serialVersionUID = 1L;

	@JsonProperty("bucketName")  
	public String bucketName;
	
	@JsonProperty("id")  
	public Long id;
	  
	@JsonProperty("creationDate")
	public  OffsetDateTime creationDate;
	
	@JsonProperty("status")
	public BucketStatus status;

	@JsonProperty("appVersion")
	public String appVersion;

	@JsonProperty("lastModified")
	public OffsetDateTime lastModified;

	
	public void setLastModified(OffsetDateTime now) {
	        this.lastModified=now;
    }
	
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
		if (id!=null)
			clone.id=Long.valueOf(id.longValue());
		return clone;
	}
	
	
	public String getBucketName() {
		return this.bucketName;
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

		  if (this.id!=null) {
			  if (that.id!=null)
				  return this.id.longValue()==that.id.longValue();
			  else
				  return false;
		  }
		  else {
			  if (that.id!=null)
				  return false;
		  }
	    
	    if (this.bucketName.equals(that.bucketName)) {
	      return true;
	    }

	    return false;
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
	   *<p>Returns bucket name</p>
	   */
	  public String bucketName() {
	    return this.bucketName;
	  }


	  /**
	   * <p>Returns created time</p>
	   */
	  public OffsetDateTime creationDate() {
	    return this.creationDate;
	  }

	  public BucketStatus getStatus() {
		    return this.status;
	  }
	  
	  public Long getId() {
		  return this.id;
	  }


	  public void setCreationDate(OffsetDateTime now) {
	      this.creationDate=now;
	  }


    public void setBucketName(String bucketName) {
        this.bucketName=bucketName;
    }


    public void setStatus(BucketStatus status) {
        this.status=status;
    }


    public void setAppVersion(String version) {
            this.appVersion=version;
    }


    public void setId(Long id) {
            this.id=id;
    }

    
}
