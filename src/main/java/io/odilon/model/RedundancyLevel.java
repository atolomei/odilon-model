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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.odilon.util.Check;

/**
 * <p>The standard RAID levels comprise a basic set of RAID 
 * ("redundant array of independent disks" or "redundant array of inexpensive disks") 
 * configurations that employ the techniques of striping, mirroring, 
 * or parity to create large reliable data stores from multiple general-purpose 
 * computer hard disk drives (HDDs).</p> 
 * 
 * <p>Supported types<br/><br/> 
 * <b>RAID 0 (striping)</b> <br/>
 * Two or more disks are combined to form a volume, which appears as a single virtual drive. It is not a configuration with data replication, its function is to provide greater storage and performance by allowing access to the disks in parallel.
 * <br/>
 * <br/>
 * <br/>
 * <b>RAID 1 (mirroring)</b> <br/> 
 * For each object, 1 or more exact copies (or mirrors) are created on two or more disks. This provides redundancy in case of disk failure. At least 2 disks are required, Odilon also supports 3 or more for greater redundancy.
 * <br/>
 * <br/>
 * <br/>
 * <b>RAID 6 / Erasure Coding</b> <br/>
 * It is a method of encoding data into blocks that can be distributed across multiple disks or nodes and then reconstructed from a subset of those blocks. It has great flexibility since you can adjust the number and size of the blocks and the minimum required for recovery. It uses less disk space than RAID 1 and can withstand multiple full disk failures. Odilon implements this architecture using Reed Solomon error-correction codes.
 * Odilon supports these configurations: <br/>
 * </p>
 *  <ul>
 *  <li>  3 Disks  -> data: 2, parity: 1</li>
 *  <li>  6 Disks  -> data: 4, parity: 2</li>
 *  <li> 12 Disks  -> data: 8, parity: 4</li>
 *  <li> 24 Disks  -> data: 16, parity: 8</li>
 *  </ul>
 * 
 * 
 */
public enum RedundancyLevel {
			
	RAID_0 	("RAID 0", 0), //(striping)
	RAID_1 	("RAID 1", 1), // (mirroring) 
	RAID_6 	("RAID 6", 6); // (Erasure Codes Reed Solomon -> 2+1, 4+2, 8+4, 16+8)
		
	private String name;
	private int code;
	
	static List<RedundancyLevel> list;
	
	public static List<RedundancyLevel> getValues() {
		
		if (list!=null)
			return list;
		
		list = new ArrayList<RedundancyLevel>();
		
		list.add(RAID_0);
		list.add(RAID_1);
		list.add(RAID_6);
		
		return list;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static RedundancyLevel get(String name) {
		
		Check.requireNonNullArgument(name, "name is null");
		
		String normalized = name.toUpperCase().trim();
		
		if (normalized.equals(RAID_0.getName())) return RAID_0;
		if (normalized.equals(RAID_1.getName())) return RAID_1;
		if (normalized.equals(RAID_6.getName())) return RAID_6;
		
		return null;
	}
	
	public static RedundancyLevel get(int code) {
		
		if (code==RAID_0.code) return RAID_0;
		if (code==RAID_1.code) return RAID_1;
		if (code==RAID_6.code) return RAID_6;

		throw new IllegalArgumentException ("unsupported code -> " + String.valueOf(code));
	
	}
	
	private RedundancyLevel(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getDescription() {
		return getDescription(Locale.getDefault());
	}
	
	public String getDescription(Locale locale) {
		//ResourceBundle res = ResourceBundle.getBundle(RedundancyLevel.this.getClass().getName(), locale);
		//return res.getString(this.getName());
		return getName();
		
	}
	
	public String toJSON() {
		StringBuilder str = new StringBuilder();
		str.append("\"name\":\"" + name + "\"");
		str.append("\"description\":\"" + getDescription() + "\"");
		return str.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.getClass().getSimpleName() +"{");
		str.append(toJSON());
		str.append("}");
		return str.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public int getCode() {
		return code;
	}
}
