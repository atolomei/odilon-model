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
package io.odilon.model.list;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Optional;

import io.odilon.errors.InternalCriticalException;
import io.odilon.log.Logger;

/**
 * 
 * <p>
 * A ResultSet is the set of results returned by a query. ResultSets are
 * {@link Iterable} and every result in the set must be {@link Serializable}
 * </p>
 * 
 * @param <T>
 * 
 *            Example list all bucket's objects (from project
 *            {@code odilon-client}):
 * 
 *            <pre> {@code 
 * try {
 *	ResultSet<Item <ObjectMetadata>> resultSet = client.listObjects(bucket.getName());
 *		while (resultSet.hasNext()) {
 *			Item item = resultSet.next();
 *			if (item.isOk())
 *				System.out.println("ObjectName:" +  item.getObject().objectName + " | file: " + item.getObject().fileName);
 *			else
 *				System.out.println(item.getErrorString());
 *            }
 *            } catch (ODClientException e) {
 *            System.out.println(String.valueOf(e.getHttpStatus()) + " " + e.getMessage() + " " + String.valueOf(e.getErrorCode()));
 *            }
 * }
 * </pre>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 * 
 */
public class ResultSet<T extends Serializable> implements Iterator<T> {

    @SuppressWarnings("unused")
    static private Logger logger = Logger.getLogger(ResultSet.class.getName());

    private int relativeIndex = 0; // next item to return -> 0 to (list.size() - 1)
    private int relativeSize = 0; // size of the list just fetched
    private long cumulativeIndex = 0; // total items returned so far

    private DataList<T> dataList;
    private DataProvider<T> dataProvider;

    /**
     * @param pageRequest
     */
    public ResultSet(DataProvider<T> pageRequest) {
        this.dataProvider = pageRequest;
    }

    @Override
    public synchronized boolean hasNext() {

        if (dataList == null)
            return fetch();

        /** if the buffer still has items */
        if (relativeIndex < relativeSize)
            return true;

        /** buffer has no items */
        return fetch();

    }

    /**
     * 
     * @return next element in the set. If there are no elements throws
     *         {@link IndexOutOfBoundsException}
     */
    @Override
    public synchronized T next() {

        /** if the buffer still has items to return */
        if (this.relativeIndex < this.relativeSize) {
            T object = this.dataList.get(relativeIndex);
            this.relativeIndex++;
            this.cumulativeIndex++;
            return object;
        }

        /** fill in the buffer */
        boolean hasItems = fetch();

        if (!hasItems)
            throw new IndexOutOfBoundsException(
                    "No more items available. Normally the caller should check hasNext() before calling this method [returned so far -> "
                            + String.valueOf(cumulativeIndex) + ")]");

        T object = this.dataList.get(relativeIndex);

        this.relativeIndex++;
        this.cumulativeIndex++;

        return object;
    }

    public synchronized long getPageSize() {
        return (this.dataList != null ? this.dataList.getPageSize() : 0);
    }

    /**
     * <p>
     * The Optional is because in some use cases the total size of the
     * {@link ResultSet} may not be known
     * </p>
     */
    public synchronized Optional<Long> getSize() throws IOException {

        if (this.dataList == null)
            fetch();

        if (this.dataList == null)
            return Optional.ofNullable(null);

        return this.dataList.getSize();
    }

    /*
     * cumulativeIndex is the total items returned so far -> 0 .. cumulativeIndex-1
     * when we fetch from the server, the next item required is #cumulativeIndex
     */
    private boolean fetch() {

        if (this.dataList != null && this.dataList.isEOD()) {
            this.relativeIndex = 0;
            this.relativeSize = 0;
            this.dataList = null;
            return false;
        }

        try {
            this.dataList = this.dataProvider.fetch(cumulativeIndex);
        } catch (IOException e) {
            throw new InternalCriticalException(e, "error fechting data from dataProvider -> "
                    + (Optional.ofNullable(this.dataProvider).isPresent() ? this.dataProvider.toString() : "null"));
        }

        if (this.dataList == null)
            return false;

        if (this.dataList.getList() == null)
            return false;

        this.relativeIndex = 0;
        this.relativeSize = this.dataList.getList().size();
        return this.relativeSize > 0;
    }

}
