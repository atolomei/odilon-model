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

/**
 * <p>
 * Fixed Reed–Solomon configurations supported by Odilon ErasureCoding.
 * The data/parity split is <strong>internal</strong> — it is derived
 * automatically from the number of drives per volume declared in
 * {@code odilon.properties}. Administrators never need to configure
 * {@code raid6.dataDrives} or {@code raid6.parityDrives} explicitly.
 * </p>
 *
 * <pre>
 *  drives/volume | data shards | parity shards | max drive failures
 *  --------------|-------------|---------------|-------------------
 *       3        |      2      |       1       |         1
 *       6        |      4      |       2       |         2
 *      12        |      8      |       4       |         4
 *      24        |     16      |       8       |         8
 *      48        |     32      |      16       |        16
 * </pre>
 *
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
public enum ECConfig {

    RS_3 ( 3,  2,  1),
    RS_6 ( 6,  4,  2),
    RS_12(12,  8,  4),
    RS_24(24, 16,  8),
    RS_48(48, 32, 16);

    /** Total drives per volume (data + parity). */
    public final int totalDrives;

    /** Reed–Solomon data shards. */
    public final int dataDrives;

    /** Reed–Solomon parity shards. */
    public final int parityDrives;

    ECConfig(int totalDrives, int dataDrives, int parityDrives) {
        this.totalDrives  = totalDrives;
        this.dataDrives   = dataDrives;
        this.parityDrives = parityDrives;
    }

    /**
     * Returns the configuration for the given per-volume drive count.
     *
     * @throws IllegalArgumentException if {@code drivesPerVolume} is not one of
     *         the supported values (3, 6, 12, 24, 48).
     */
    public static ECConfig fromDriveCount(int drivesPerVolume) {
        for (ECConfig cfg : values()) {
            if (cfg.totalDrives == drivesPerVolume)
                return cfg;
        }
        throw new IllegalArgumentException(
                "Unsupported ErasureCoding drive count per volume: " + drivesPerVolume
                + ". Supported values: 3, 6, 12, 24, 48.");
    }

    /** Comma-separated list of all supported drive counts, for error messages. */
    public static String supportedCounts() {
        StringBuilder sb = new StringBuilder();
        for (ECConfig cfg : values()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(cfg.totalDrives);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return name() + "[total=" + totalDrives
                + ", data=" + dataDrives
                + ", parity=" + parityDrives + "]";
    }
}
