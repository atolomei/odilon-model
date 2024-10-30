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



import io.odilon.log.Logger;

/**
 * <p>Abstract class for Odilon Information Model</p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei) 
 */
public abstract class OdilonModelObject extends BaseObject {
										
	@SuppressWarnings("unused")
	static private Logger logger = Logger.getLogger(BucketMetadata.class.getName());

	public OdilonModelObject() {
	}
	
	@Override
	public String toString() {
			StringBuilder str = new StringBuilder();
			str.append(this.getClass().getSimpleName());
			str.append(toJSON());
			return str.toString();
	}
		
	
	
	


	 
}
