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
package io.odilon.log;

import org.apache.logging.log4j.LogManager;

/**
 * <p>Simple Wrapper for {@linkplain org.apache.logging.log4j.Logger} loggers.<br/>
 * The addition is that it formats output.</p>
 */
public class Logger {

	private org.apache.logging.log4j.Logger logger;

	public static Logger getLogger(String name) {
		return new Logger(name);
	}

	public Logger(String name) {
		this.logger = LogManager.getLogger(name);
	}

	public Logger(org.apache.logging.log4j.Logger logger) {
		this.logger = logger;
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public void error(Throwable e, String ... strings) {
		StringBuilder str = new StringBuilder();
		for (String s: strings)
			str.append((str.length()>0?" | ":"")+s);

		String ca =  (e.getCause()!=null? ( " | " + e.getCause().getMessage() + " | ") :"");

		logger.error(e.getClass().getName() + " | " +  Thread.currentThread().getStackTrace()[2].getMethodName() + " | " + e.getMessage() +   ca    + (str.length()>0?" | " + str.toString():""));

		if (logger.isDebugEnabled()) {
			logger.error(e.getClass().getName(), e);
		}
	}

	public void error(String ... strings) {
		StringBuilder str = new StringBuilder();
		for (String s: strings)
			str.append((str.length()>0?" | ":"")+s);
		logger.error(Thread.currentThread().getStackTrace()[2].getMethodName() + (str.length()>0?" | " + str.toString():""));
	}

	public void error(Throwable e) {
		logger.error(e.getClass().getName() + " | " +  Thread.currentThread().getStackTrace()[2].getMethodName() + " | " + e.getMessage());
		if (logger.isDebugEnabled()) {
			logger.error(e.getClass().getName(), e);
		}
	}

	public void warn(Throwable e) {
		logger.warn(e.getClass().getName() + " | " +  Thread.currentThread().getStackTrace()[2].getMethodName() + " | " + e.getMessage());
	}
	
	public void warn(Throwable e, String string) {
		logger.warn(e.getClass().getName() + " | " +  Thread.currentThread().getStackTrace()[2].getMethodName() + " | " + e.getMessage() + (string != null ? (" | " + string) : "")	);
	}

	public void warn(String ... strings) {
		StringBuilder str = new StringBuilder();
		for (String s: strings)
			str.append((str.length()>0?" | ":"")+s);
		logger.warn(Thread.currentThread().getStackTrace()[2].getMethodName() + (str.length()>0?" | " + str.toString():""));
	}

				
	public void debug(String ... strings) {
		StringBuilder str = new StringBuilder();
		for (String s: strings)
			str.append((str.length()>0?" | ":"")+s);
		logger.debug(Thread.currentThread().getStackTrace()[2].getMethodName() + (str.length()>0?" | " + str.toString():""));
	}

	
	public void debug(Throwable e, String ... strings) {
		StringBuilder str = new StringBuilder();
		for (String s: strings)
			str.append((str.length()>0?" | ":"")+s);

		String ca =  (e.getCause()!=null? ( " | " + e.getCause().getMessage() + " | ") :"");
		logger.debug(e.getClass().getName() + " | " +  Thread.currentThread().getStackTrace()[2].getMethodName() + " | " + e.getMessage() +   ca    + (str.length()>0?" | " + str.toString():""));
	}

	public void debug(String message) {
		logger.debug(message);
	}

	public void debug(Object message) {
		logger.debug(message);
	}

	public void info(String message) {
		logger.info(message);
	}

	public void info(Object message) {
		logger.info(message);
	}

	public void fatal(String message) {
		logger.fatal(message);
	}

	public void fatal(Object message) {
		logger.fatal(message);
	}

	public void trace(String message) {
		logger.trace(message);
	}

	public void trace(Object message) {
		logger.trace(message);
	}
}

