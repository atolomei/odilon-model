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

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * Used to store a page of Objects or Buckets
 * </p>
 * 
 * @author atolomei@novamens.com (Alejandro Tolomei)
 * 
 * @param <T> must be Serializable
 */
public class DataList<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("serverAgentId")
    private String serverAgentId;

    @JsonProperty("size")
    private Long size;

    @JsonProperty("offset")
    private long offset = 0;

    @JsonProperty("pagesize")
    private long pageSize;

    @JsonProperty("list")
    private List<T> list;

    @JsonProperty("eod")
    private boolean eod = false;

    public DataList() {
    }

    public DataList(List<T> list, Optional<String> agentId) {
        this.list = list;
        agentId.ifPresent(x -> serverAgentId = x);
    }

    public DataList(List<T> list) {
        this.list = list;
    }

    public T get(int index) {
        return list.get(index);
    }

    public Optional<Long> getSize() {
        return Optional.ofNullable(size);
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getAgentId() {
        return serverAgentId;
    }

    public void setAgentId(String serverAgentId) {
        this.serverAgentId = serverAgentId;
    }

    /**
     * <p>
     * flag that informs whether this list is the end of the data stream (End Of
     * Data) on the side of the server. It is used to prevent the last pull from the
     * server to get an empty resultSet.
     * </p>
     * 
     * @return true if there is no more data to fetch.
     */
    public boolean isEOD() {
        return eod;
    }

    public void setEOD(boolean eod) {
        this.eod = eod;
    }

}
