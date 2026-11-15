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

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import tools.jackson.core.JsonProcessingException;

/**
 * <p>
 * Values for Server monitoring
 * </p>
 * Rates (per second / minute) are computed by Prometheus with rate(); the
 * deprecated *Meter arrays are kept only for backward compatibility.
 *
 * @author atolomei@novamens.com (Alejandro Tolomei)
 *
 */
@JsonInclude(Include.NON_NULL)
public class MetricsValues extends OdilonModelObject implements Serializable {

	private static final long serialVersionUID = 1L;

	static NumberFormat nf_dec = NumberFormat.getInstance(Locale.ENGLISH);

	static {
		nf_dec.setMinimumFractionDigits(2);
		nf_dec.setMaximumFractionDigits(2);
		nf_dec.setRoundingMode(RoundingMode.HALF_UP);
	}

	static NumberFormat nf_int = NumberFormat.getInstance(Locale.ENGLISH);
	static {
		nf_int.setMinimumFractionDigits(0);
		nf_int.setMaximumFractionDigits(0);
		nf_int.setRoundingMode(RoundingMode.HALF_UP);
	}

	static NumberFormat nf_rat = NumberFormat.getInstance(Locale.ENGLISH);
	static {
		nf_rat.setMinimumFractionDigits(2);
		nf_rat.setMaximumFractionDigits(2);
		nf_rat.setRoundingMode(RoundingMode.HALF_UP);
	}

	public MetricsValues() {
	}

	
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
	public long replicaObjectCreate = 0;

	@JsonProperty("replicaObjectUpdate")
	public long replicaObjectUpdate = 0;

	@JsonProperty("replicaObjectDelete")
	public long replicaObjectDelete = 0;

	// REPLICATION VERSION CONTROL

	@JsonProperty("replicaRestoreObjectPreivousVersionCounter")
	public long replicaRestoreObjectPreviousVersionCounter = 0;

	@JsonProperty("replicaDeleteObjectAllVersionsCounter")
	public long replicaDeleteObjectAllVersionsCounter = 0;

	// ----------------------------
	// ENCRYPTION

	/*
	 * Deprecated rate meters, kept only so the JSON stays backward compatible with
	 * older clients. Rates are not computed in-process anymore (use Prometheus
	 * rate()): index 0 holds the total count, indexes 1 and 2 are always 0. Use the
	 * *Counter fields below instead.
	 */
	@Deprecated
	@JsonProperty("encrpytFileMeter")
	public double encrpytFileMeter[] = new double[3];

	@Deprecated
	@JsonProperty("decryptFileMeter")
	public double decryptFileMeter[] = new double[3];

	@Deprecated
	@JsonProperty("encryptVaultMeter")
	public double encryptVaultMeter[] = new double[3];

	@Deprecated
	@JsonProperty("decryptVaultMeter")
	public double decryptVaultMeter[] = new double[3];

	@Deprecated
	@JsonProperty("getAPIMeter")
	public double getAPIMeter[] = new double[3];

	@Deprecated
	@JsonProperty("putAPIMeter")
	public double putAPIMeter[] = new double[3];

	@Deprecated
	@JsonProperty("getObjectMeter")
	public double getObjectMeter[] = new double[3];

	@Deprecated
	@JsonProperty("putObjectMeter")
	public double putObjectMeter[] = new double[3];

	// ----------------------------
	// TOTALS (replace the deprecated meters)

	@JsonProperty("getObjectCounter")
	public long getObjectCounter = 0;

	@JsonProperty("putObjectCounter")
	public long putObjectCounter = 0;

	@JsonProperty("apiCallCounter")
	public long apiCallCounter = 0;

	@JsonProperty("encryptFileCounter")
	public long encryptFileCounter = 0;

	@JsonProperty("decryptFileCounter")
	public long decryptFileCounter = 0;

	@JsonProperty("encryptVaultCounter")
	public long encryptVaultCounter = 0;

	@JsonProperty("decryptVaultCounter")
	public long decryptVaultCounter = 0;

	@Override
	public String toJSON() {
		try {
			return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (Exception e) {
			return "\"error\":\"" + e.getClass().getName() + " | " + e.getMessage() + "\"";
		}
	}

	@JsonIgnore
	public Map<String, String> getColloquial() {

		Map<String, String> map = new TreeMap<String, String>();

		// ----------------------------
		// OBJECT CRUD
		//
		map.put("objectGetCounter", nf_int.format(getObjectCounter));
		map.put("objectPutCounter", nf_int.format(putObjectCounter));

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
		map.put("cacheFileHardDiskUsage", nf_rat.format(Double.valueOf(cacheFileHardDiskUsage).doubleValue() / SharedConstant.d_gigabyte) + " GB");

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
		map.put("encryptFileCounter", nf_int.format(encryptFileCounter));
		map.put("decryptFileCounter", nf_int.format(decryptFileCounter));

		// ----------------------------
		// VAULT
		//
		map.put("vaultEncryptCounter", nf_int.format(encryptVaultCounter));
		map.put("vaultDecryptCounter", nf_int.format(decryptVaultCounter));

		return map;
	}

}
