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
package io.odilon.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import io.odilon.log.Logger;

public class ODFileUtils  {
																						
	private static Logger logger = Logger.getLogger(ODFileUtils.class.getName());

	static final int BUFFER_SIZE = 16384;
	
	
	public static void forceMkdir(File directory) throws IOException {
		 FileUtils.forceMkdir(directory);
	}
	public static long sizeOf(File file) {
		return FileUtils.sizeOf(file);
	}
	
	public static void moveDirectory(File srcDir, File destDir)  throws IOException {
		FileUtils.moveDirectory(srcDir, destDir);
	}
	
	public static boolean deleteQuietly(File file) {
		return FileUtils.deleteQuietly(file);
	}
	
	public static void forceDelete(final File file) throws IOException {
		FileUtils.forceDelete(file);
	}

	
	public static String calculateSHA256String(final String string) throws IOException, NoSuchAlgorithmException {
	
		MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
			throw e;
		}
		
		try {
			byte bytes[] = string.getBytes();
		    digest.update(bytes, 0, bytes.length);
		    return Base64.getEncoder().encodeToString(digest.digest());
		    
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	
	
	
	
	
	public static String calculateSHA256String(final File file) throws IOException, NoSuchAlgorithmException {

		byte[] buffer= new byte[BUFFER_SIZE];
		int count = 0;
	
		MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
			throw e;
		}
		    
		BufferedInputStream bis = null;
			
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			while ((count = bis.read(buffer)) > 0)
		        digest.update(buffer, 0, count);
		    String sha256 = Base64.getEncoder().encodeToString(digest.digest());
		    return sha256;
		    
		} catch (FileNotFoundException e) {
				logger.error(e);
				throw e;
				
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
			finally {
				if (bis!=null) {
					try {
						bis.close();
						
					} catch (IOException e) {
						logger.error(e);
					}
				}
		}
	}
	
}
