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
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * <p>Odilon monitoring</p>
 * 
 *@see Dropwizard metrics {@link https://metrics.dropwizard.io/4.2.0/index.html}
 *
 * @author atolomei@novamens.com (Alejandro Tolomei)
 *
 */
public class MetricsValues extends ODModelObject implements Serializable {

	private static final long serialVersionUID = 1L;

	static NumberFormat nf_dec = NumberFormat.getInstance(Locale.ENGLISH);

	static {
		nf_dec.setMinimumFractionDigits(2);
	    nf_dec.setMaximumFractionDigits(2);
	    nf_dec.setRoundingMode(RoundingMode.HALF_UP);
	}

	static	NumberFormat nf_int = NumberFormat.getInstance(Locale.ENGLISH);
    static  
    {
    	nf_int.setMinimumFractionDigits(0);
    	nf_int.setMaximumFractionDigits(0);
    	nf_int.setRoundingMode(RoundingMode.HALF_UP);
    }
    
    								
    static	NumberFormat nf_rat = NumberFormat.getInstance(Locale.ENGLISH);
    static  
    {
    	nf_rat.setMinimumFractionDigits(2);
    	nf_rat.setMaximumFractionDigits(2);
    	nf_rat.setRoundingMode(RoundingMode.HALF_UP);
    }
    
    
	public MetricsValues() {}

	// ----------------------------
	// PUT/GET OBJECT

	@JsonProperty("getObjectMeter")
	public double getObjectMeter[] = new double[3];
	
	@JsonProperty("putObjectMeter")
	public double putObjectMeter[]  = new double[3];

	
	//public long cacheSize = 0;
	
	
	// ----------------------------
	// OBJECT CRUD

	@JsonProperty("createObjectCounter")
	public long createObjectCounter = 0;
	
	@JsonProperty("updateObjectCounter")
	public long updateObjectCounter = 0;
	
	@JsonProperty("deleteObjectCounter")
	public long deleteObjectCounter = 0;
	

	
	@JsonProperty("deleteObjectVersionCounter")
	public long deleteObjectVersionCounter = 0;

	

	@JsonProperty("objectDeleteAllVersionsCounter")
	public long objectDeleteAllVersionsCounter = 0;

	@JsonProperty("objectRestorePreviousVersionCounter")
	public long objectRestorePreviousVersionCounter = 0;
	
	
	
	
	// ----------------------------
	// OBJECTMETADATA CACHE
	
	@JsonProperty("cacheObjectHitCounter")
	public long cacheObjectHitCounter = 0;

	@JsonProperty("cacheObjectMissCounter")
	public long cacheObjectMissCounter = 0;
	
	@JsonProperty("cacheObjectSize")
	public long cacheObjectSize = 0;

	

	// ----------------------------
	// FILE CACHE

	@JsonProperty("cacheFileHitCounter")
	public long cacheFileHitCounter = 0;

	@JsonProperty("cacheFileMissCounter")
	public long cacheFileMissCounter = 0;
	
	@JsonProperty("cacheFileSize")
	public long cacheFileSize = 0;

	@JsonProperty("cacheFileHardDiskUsage")
	public long cacheFileHardDiskUsage = 0;
	
	// ----------------------------
	// REPLICATION

	@JsonProperty("replicaObjectCreate")
	public long  replicaObjectCreate = 0;
	
	@JsonProperty("replicaObjectUpdate")
	public long  replicaObjectUpdate = 0;
	
	@JsonProperty("replicaObjectDelete")
	public long  replicaObjectDelete = 0;

	
	// REPLICATION VERSION CONTROL
	
	@JsonProperty("replicaRestoreObjectPreivousVersionCounter")
	public long  replicaRestoreObjectPreviousVersionCounter = 0;

	
	@JsonProperty("replicaDeleteObjectAllVersionsCounter")
	public long  replicaDeleteObjectAllVersionsCounter = 0;

	

	// ----------------------------
	// ENCRYPTION
	
	@JsonProperty("encrpytFileMeter")
	public double encrpytFileMeter[]  = new double[3];
	
	@JsonProperty("decryptFileMeter")
	public double decryptFileMeter[]  = new double[3];
	
	@JsonProperty("encryptVaultMeter")
	public double encryptVaultMeter[]  = new double[3];
	
	@JsonProperty("decryptVaultMeter")
	public double decryptVaultMeter[]  = new double[3];


	// ----------------------------
	// API CALLS
	
	@JsonProperty("getAPIMeter")
	public double getAPIMeter[]  = new double[3];
	
	@JsonProperty("putAPIMeter")
	public double putAPIMeter[]  = new double[3];

	@Override
	public String toJSON() {
		  try {
				return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			} catch (JsonProcessingException e) {
						return "\"error\":\"" + e.getClass().getName()+ " | " + e.getMessage()+"\""; 
			}
		}

	private String str( double[] arr) {
		return String.valueOf( nf_dec.format(arr[0]) + " " + nf_dec.format(arr[1]) + " " + nf_dec.format(arr[2]));
	}
	
	@JsonIgnore
	public Map<String, String> getColloquial() {

		Map<String, String> map = new TreeMap<String, String>();

		

		// ----------------------------
		// OBJECT CRUD
		//
		
		map.put("objectGetMeter", str(getObjectMeter));
		map.put("objectPutMeter", str(putObjectMeter));
		
		// ----------------------------
		
		map.put("objectCreateCounter", nf_int.format(createObjectCounter));
		map.put("objectUpdateCounter", nf_int.format(updateObjectCounter));
		map.put("objectDeleteCounter", nf_int.format(deleteObjectCounter));
		map.put("objectDeleteVersionCounter", nf_int.format(deleteObjectVersionCounter));

		map.put("objectRestorePreviousVersionCounter", nf_int.format(objectRestorePreviousVersionCounter));
		map.put("objectDeleteAllVersionsCounter", nf_int.format(objectDeleteAllVersionsCounter));

		
		
		// ----------------------------
		// OBJECT CACHE
		//
		
		map.put("cacheObjectHitCounter", nf_int.format(cacheObjectHitCounter));
		map.put("cacheObjectMissCounter", nf_int.format(cacheObjectMissCounter));
		map.put("cacheObjectSize", nf_int.format(cacheObjectSize));
		
		// ----------------------------
		// FILE CACHE
		//
		map.put("cacheFileHitCounter", nf_int.format(cacheFileHitCounter));
		map.put("cacheFileMissCounter", nf_int.format(cacheFileMissCounter));
		map.put("cacheFileSize", nf_int.format(cacheFileSize));
		map.put("cacheFileHardDiskUsage", nf_rat.format(Double.valueOf(cacheFileHardDiskUsage).doubleValue() / SharedConstant.d_gigabyte)+" GB");
	
		
		// ----------------------------
		// REPLICATION
		//
		
		map.put("replicaObjectCreate", nf_int.format(replicaObjectCreate));
		map.put("replicaObjectUpdate", nf_int.format(replicaObjectUpdate));
		map.put("replicaObjectDelete", nf_int.format(replicaObjectDelete));
		
		map.put("replicaRestoreObjectPreivousVersionCounter", nf_int.format(replicaRestoreObjectPreviousVersionCounter));
		map.put("replicaDeleteObjectAllVersionsCounter", nf_int.format(replicaDeleteObjectAllVersionsCounter));
		
		
		// ----------------------------
		// ENCRYPTION
		// 
		
		map.put("encrpytFileMeter", str(encrpytFileMeter));
		map.put("decryptFileMeter", str(decryptFileMeter));
		
		
		// ----------------------------
		// VAULT
		// 
		
		map.put("vaultEncryptMeter", str(encryptVaultMeter));
		map.put("vaultDecryptMeter", str(decryptVaultMeter));
		
		return map;
	}
		
	

	
}
