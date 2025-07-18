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
package io.odilon.errors;

/**
 * <p>
 * Main Odilon unchecked Exception
 * </p>
 * 
 * <p>
 * Unchecked exceptions do not need to be declared in a method or constructor's
 * throws clause. They are thrown if something that has gone wrong with the
 * program and is unrecoverable.
 * </p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 */
public class InternalCriticalException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    Exception rootException;

    public InternalCriticalException(Exception rootException) {
        super(rootException);
        this.rootException = rootException;
    }

    public InternalCriticalException(Exception rootException, String message) {
        super(message, rootException);
        this.rootException = rootException;
    }

    public InternalCriticalException(String message) {
        super(message);
    }

    public Exception getRootException() {
        return rootException;
    }

    public String getMessage() {
        String m = super.getMessage();
        if (rootException != null) {
            return "rootException -> " + rootException.getClass().getName()
                    + ((rootException.getMessage() != null) ? (" " + rootException.getMessage()) : "") + " | " + m;
        }
        return m;
    }

}
