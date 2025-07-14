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

/**
 * <p>
 * Constants used both by Odilon Server and Odilon Java SDK client
 * </p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 * 
 */
public class SharedConstant {

	static final public int DEFAULT_PAGE_SIZE = 60;
	static final public int DEFAULT_EXPIRY_TIME = 14 * 24 * 3600; // 14 days

	static final public int BUFFER_SIZE = 8192;

	static final public int MAX_BUCKET_CHARS = 2048;
	static final public int MAX_OBJECT_CHARS = 2048;

	static final public String valid_endpoint_regex = "^[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?$";

	static final public String object_valid_regex = "^[^*\\?<>|/]+$";
	static final public String bucket_valid_regex = "[A-Za-z0-9\\-_]+";

	static final public long kilobyte = 1024;
	static final public long megabyte = (kilobyte * 1024);
	static final public long gigabyte = (megabyte * 1024);
	static final public long terabyte = (gigabyte * 1024);
	static final public long petabyte = (terabyte * 1024);
	static final public long exabyte = (petabyte * 1024);
	static final public long zettabyte = (exabyte * 1024);
	static final public long yottabyte = (zettabyte * 1024);

	static final public double d_kilobyte = 1024.0;
	static final public double d_megabyte = (d_kilobyte * 1024);
	static final public double d_gigabyte = (d_megabyte * 1024);
	static final public double d_terabyte = (d_gigabyte * 1024);
	static final public double d_petabyte = (d_terabyte * 1024);
	static final public double d_exabyte = (d_petabyte * 1024);
	static final public double d_zettabyte = (d_exabyte * 1024);
	static final public double d_yottabyte = (d_zettabyte * 1024);

	static final public String NOT_THROWN = "---- not thrown ----";
}
