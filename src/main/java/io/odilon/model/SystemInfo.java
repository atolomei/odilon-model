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

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.odilon.log.Logger;

/**
 * 
 * <p>
 * Odilon configuration info and some fixed server values
 * </p>
 * <p>
 * For dynamic info on the status of the system we use {@link MetricsValues}
 * </p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
@JsonInclude(Include.NON_NULL)
public class SystemInfo extends BaseObject {

    @SuppressWarnings("unused")
    static private Logger logger = Logger.getLogger(SystemInfo.class.getName());

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

    public Integer availableProcessors;

    public OffsetDateTime started;

    public String isEncryptEnabled;
    public String isEncryptionInitialized;

    public String isVaultEnabled;
    public String vaultUrl;

    public String isHttps;

    public Long maxMemory;
    public Long totalMemory;

    public String osArch;
    public String osName;
    public String osVersion;

    public String userHome;
    public String userName;
    public String userProfile;
    public String userDir;

    public String javaHome;
    public String javaVersion;
    public String javaVendor;
    public String appVersion;

    public String serverHost;

    public Long freeMemory;
    public Double cpuLoadAverage;

    public String serverMode;
    public String serverDataStorageMode;

    
    
    
    public String isStandby;
    public String standbyUrl;
    public String standbyPort;

    public String isVersionControl;

    /**
     * total disk available to store data. This depends on the redundancy level used
     */
    public Long availableDisk;

    public Map<String, Long> serverStorage;

    public RedundancyLevel redundancyLevel;
    public String redundancyLevelDetail;

    @JsonIgnore
    public Map<String, Long> totalStorage;

    @JsonIgnore
    public Map<String, Long> getTotalStorage() {
        return totalStorage;
    }

    public void setTotalStorage(Map<String, Long> totalStorage) {
        this.totalStorage = totalStorage;
    }

    /** ----------------------------------------- */

    @JsonIgnore
    public Map<String, String> getColloquial() {

        Map<String, String> map = new TreeMap<String, String>();

        map.put("userDir", Optional.ofNullable(userDir).isPresent() ? userDir : "null");
        map.put("userHome", Optional.ofNullable(userHome).isPresent() ? userHome : "null");
        map.put("userName", Optional.ofNullable(userName).isPresent() ? userName : "null");
        map.put("userProfile", Optional.ofNullable(userProfile).isPresent() ? userProfile : "null");

        map.put("started", Optional.ofNullable(started).isPresent() ? started.toString() : "null");
        map.put("osName", Optional.ofNullable(osName).isPresent() ? osName : "");
        map.put("osArch", Optional.ofNullable(osArch).isPresent() ? osArch : "");
        map.put("osVersion", Optional.ofNullable(osVersion).isPresent() ? osVersion : "");
        map.put("userName", Optional.ofNullable(userName).isPresent() ? userName : "");
        map.put("userProfile", Optional.ofNullable(userProfile).isPresent() ? userProfile : "");
        map.put("javaVersion", Optional.ofNullable(javaVersion).isPresent() ? javaVersion : "");

        map.put("javaHome", Optional.ofNullable(javaHome).isPresent() ? javaHome : "");
        map.put("javaVendor", Optional.ofNullable(javaVendor).isPresent() ? javaVendor : "");

        map.put("appVersion", Optional.ofNullable(appVersion).isPresent() ? appVersion : "");
        map.put("serverHost", Optional.ofNullable(serverHost).isPresent() ? serverHost : "");
        map.put("serverMode", Optional.ofNullable(serverMode).isPresent() ? serverMode : "");
        map.put("dataStorageMode", serverDataStorageMode);

        map.put("redundancyLevel", Optional.ofNullable(redundancyLevel).isPresent() ? redundancyLevel.getName() : "null");

        if (redundancyLevelDetail != null)
            map.put("redundancyLevel.detail", redundancyLevelDetail);

        map.put("standby.enabled", Optional.ofNullable(isStandby).isPresent() ? isStandby : "");

        map.put("encryption.enabled", Optional.ofNullable(isEncryptEnabled).isPresent() ? isEncryptEnabled : "");
        map.put("encryption.initialized", Optional.ofNullable(isEncryptionInitialized).isPresent() ? isEncryptionInitialized : "");
        map.put("versionControl.enabled", isVersionControl);
        map.put("vault.enabled", isVaultEnabled);

        map.put("https", this.isHttps);

        if ((vaultUrl != null) && vaultUrl.length() > 0)
            map.put("vault.url", vaultUrl);

        if ((isStandby != null) && isStandby.equals("true")) {
            map.put("standby.url", Optional.ofNullable(standbyUrl).isPresent() ? standbyUrl : "");
            map.put("standby.port", Optional.ofNullable(standbyPort).isPresent() ? standbyPort : "");
        }

        map.put("availableProcessors",
                Optional.ofNullable(availableProcessors).isPresent() ? availableProcessors.toString() : "null");

        if (cpuLoadAverage != null)
            map.put("cpuLoadAverage", Optional.ofNullable(cpuLoadAverage).isPresent() ? nf_dec.format(cpuLoadAverage) : "null");

        map.put("maxMemory", Optional.ofNullable(maxMemory).isPresent() ? formatFileSize(maxMemory) : "");
        map.put("totalMemory", Optional.ofNullable(maxMemory).isPresent() ? formatFileSize(totalMemory) : "");
        map.put("freeMemory", Optional.ofNullable(freeMemory).isPresent() ? formatFileSize(freeMemory) : "");
        map.put("availableDisk", Optional.ofNullable(availableDisk).isPresent() ? formatFileSize(availableDisk) : "");

        if (!serverStorage.isEmpty()) {
            map.put("serverStorage",
                    serverStorage.entrySet().stream()
                            .map(e -> e.getKey() + " = "
                                    + (Optional.ofNullable(e.getValue()).isPresent() ? formatFileSize(e.getValue()) : "null"))
                            .collect(Collectors.joining(", ")));
        }

        return map;
    }

    public boolean isVersionControl() {
        return isVersionControl != null && isVersionControl.equals("true");
    }

    @JsonIgnore
    private String formatFileSize(long size) {

        try {
            if (size == 0)
                return nf_int.format(size).trim() + " KB";

            if (size < SharedConstant.kilobyte)
                return nf_int.format(size).trim() + " bytes";

            if (size < SharedConstant.megabyte)
                return nf_dec.format((double) size / SharedConstant.d_kilobyte).trim() + " KB";

            if (size < SharedConstant.gigabyte)
                return nf_dec.format((double) size / (double) SharedConstant.d_megabyte).trim() + " MB";

            if (size < SharedConstant.terabyte)
                return nf_dec.format((double) size / (double) SharedConstant.d_gigabyte).trim() + " GB";

            return nf_dec.format((double) size / (double) SharedConstant.d_terabyte).trim() + " TB";

        } catch (Exception e) {
            return e.getClass().getName() + e.getMessage();
        }
    }

}
