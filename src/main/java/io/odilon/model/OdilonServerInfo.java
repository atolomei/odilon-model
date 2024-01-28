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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OdilonServerInfo extends ODModelObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("creationDate")
	private OffsetDateTime creationDate;
	
	@JsonProperty("standByStartDate")
	private OffsetDateTime standByStartDate;
	
	@JsonProperty("standBySyncedDate")
	private OffsetDateTime standBySyncedDate;
	
	@JsonProperty("serverMode")
	private String serverMode;
	
	@JsonProperty("standbyUrl")
	private String standbyUrl;
	
	@JsonProperty("standbyPort")
	private int standbyPort;
	
	@JsonProperty("isStandBy")
	private boolean isStandBy;
	
	@JsonProperty("isVersionControl")
	private boolean isVersionControl;

	@JsonProperty("versionControlDate")
	private OffsetDateTime versionControlDate;
	

	@JsonProperty("encryptionIntialized")
	private boolean encryptionIntialized;
	
	@JsonProperty("encryptionInitializedDate")
	private OffsetDateTime encryptionInitializedDate;


	
	public void setEncryptionIntialized( boolean b) {
		encryptionIntialized=b;
	}

	public boolean isEncryptionIntialized() {
		return encryptionIntialized;
	}
	
	public OffsetDateTime getEncryptionIntializedDate() {
		return encryptionInitializedDate;
	}

	public void setEncryptionIntializedDate(OffsetDateTime date) {
		encryptionInitializedDate=date;
	}

	
	
	/**
	 * 
	 */
	public OdilonServerInfo() {
	}

	//public List<String> getEncryptorKeys() {
	//	return encryptorKeys;
	//}
					
	//public void setEncryptorKeys(List<String> list) {
	//	this.encryptorKeys = list;
	//}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(OffsetDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public OffsetDateTime getStandByStartDate() {
		return standByStartDate;
	}

	public void setStandByStartDate(OffsetDateTime standByStartDate) {
		this.standByStartDate = standByStartDate;
	}
	
	public OffsetDateTime getStandBySyncedDate() {
		return standBySyncedDate;
	}

	public void setStandBySyncedDate(OffsetDateTime standBySyncedDate) {
		this.standBySyncedDate = standBySyncedDate;
	}
	
	public String getServerMode() {
		return serverMode;
	}

	public void setServerMode(String serverMode) {
		this.serverMode = serverMode;
	}
	
	public String getStandbyUrl() {
		return standbyUrl;
	}

	public void setStandbyUrl(String standbyUrl) {
		this.standbyUrl = standbyUrl;
	}
			
	public int getStandbyPort() {
		return standbyPort;
	}

	public void setStandbyPort(int standbyPort) {
		this.standbyPort = standbyPort;
	}

	@JsonIgnore
	public boolean isStandByEnabled() {
		return isStandBy;
	}

	public void setStandByEnabled(boolean isStandByEnabled) {
		this.isStandBy = isStandByEnabled;
	}
	
	public boolean isVersionControl() {
		return isVersionControl;
	}

	public void setVersionControl(boolean isVersionControl) {
		this.isVersionControl = isVersionControl;
	}

	public OffsetDateTime getVersionControlDate() {
		return versionControlDate;
	}

	public void setVersionControlDate(OffsetDateTime date) {
		versionControlDate = date;
	}



	

	
}
