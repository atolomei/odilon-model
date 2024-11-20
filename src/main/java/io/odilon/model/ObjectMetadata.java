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
 * <p>Object's metadata (excluding the binary file) used both by the Odilon Server and Odilon Java SDK client.</p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
public class ObjectMetadata extends OdilonModelObject implements Serializable {
			
	@SuppressWarnings("unused")
	static private Logger logger =	Logger.getLogger(ObjectMetadata.class.getName());
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("bucketName")  
	public String bucketName;
	  
	@JsonProperty("bucketId")  
	public Long bucketId;
	
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
	
	/** Date it was synced to the standby server */
	@JsonProperty("dateSynced")
	public OffsetDateTime dateSynced;
	
	@JsonProperty("integrityCheck")
	public OffsetDateTime integrityCheck;

	@JsonProperty("systemTags")
	public String systemTags;

	@JsonProperty("customTags")
	public List<String> customTags;

	
	public ObjectMetadata() {
	}
	
	public ObjectMetadata(Long bucketId, String objectName) {
		  
		this.bucketId = bucketId;
		this.objectName = objectName;
		this.creationDate = OffsetDateTime.now();
		this.length = 0;
		this.version = 0;
		this.etag = "";
		this.contentType = "";
		this.bucketName="";
	  }

	public Long getBucketId() {
		return this.bucketId;
	}
	
	
	public void setSha256Blocks(List<String> list) {
		sha256Blocks = list;
	}
	
	public List<String> getSha256Blocks() {
		return sha256Blocks;
	}
	
	
	public void setCustomTags(List<String> list) {
		customTags = list;
	}
	
	public List<String> getCustomTags() {
		return customTags;
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
	    
	    if (bucketId!=null && that.bucketId==null)
	    	return false;
	    
	    if (bucketId==null && that.bucketId!=null)
	    	return false;
	    
	    if (bucketId.longValue()!=that.bucketId.longValue()) {
	      return false;
	    }
	    
	    if (objectName!=null && that.objectName==null)
	    	return false;
	    
	    if (objectName==null && that.objectName!=null)
	    	return false;
	    
	    if (!objectName.equals(that.objectName)) {
	      return false;
	    }
	    
	    if (creationDate!=null && that.creationDate!=null) {
		    if (!creationDate.equals(that.creationDate)) {
		      return false;
		    }
	    }
	    
	    if (etag!=null && that.etag!=null) {
		    if (!etag.equals(that.etag)) {
		      return false;
		    }
	    }
	    
	    if (fileName!=null && that.fileName==null)
	    	return false;
	    
	    if (fileName==null && that.fileName!=null)
	    	return false;
	    
    	if (!fileName.equals(that.fileName))
    		return false;

	    
	    if (contentType!=null && that.contentType==null)
	    	return false;
	    
	    if (contentType==null && that.contentType!=null)
	    	return false;
	    
    	return contentType.equals(that.contentType);
	    
	    
	  }

	  /**
	   * 
	   */
	  @Override
	  public int hashCode() {
	    
		  int result = 0;
				  
		  if (bucketId!=null)
				result = bucketId.hashCode();

		  if (objectName!=null)
			  result = 31 * result + objectName.hashCode();
	    
	    result = 31 * result + (int) (length ^ (length >>> 32));

	    /** we can not use creation date because it may differ from drive to drive */
	    
	    if (etag!=null)
	    	result = 31 * result + etag.hashCode();
	    
	    if (fileName!=null)
	    	result = 31 * result + fileName.hashCode();
	    
	    if (contentType!=null)
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
	    return "bucketName";
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

	public String getBucketName() {
		return this.bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getDrive() {
		return drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(OffsetDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public OffsetDateTime getVersioncreationDate() {
		return versioncreationDate;
	}

	public void setVersioncreationDate(OffsetDateTime versioncreationDate) {
		this.versioncreationDate = versioncreationDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public boolean isEncrypt() {
		return this.encrypt;
	}

	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	public boolean isVault() {
		return vault;
	}

	public void setVault(boolean vault) {
		this.vault = vault;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ObjectStatus getStatus() {
		return status;
	}

	public void setStatus(ObjectStatus status) {
		this.status = status;
	}

	public String getRaid() {
		return raid;
	}

	public void setRaid(String raid) {
		this.raid = raid;
	}

	public String getSha256() {
		return sha256;
	}

	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}

	public int getTotalBlocks() {
		return totalBlocks;
	}

	public void setTotalBlocks(int totalBlocks) {
		this.totalBlocks = totalBlocks;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public OffsetDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(OffsetDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public OffsetDateTime getDateSynced() {
		return dateSynced;
	}

	public void setDateSynced(OffsetDateTime dateSynced) {
		this.dateSynced = dateSynced;
	}

	public OffsetDateTime getIntegrityCheck() {
		return integrityCheck;
	}

	public void setIntegrityCheck(OffsetDateTime integrityCheck) {
		this.integrityCheck = integrityCheck;
	}
}
