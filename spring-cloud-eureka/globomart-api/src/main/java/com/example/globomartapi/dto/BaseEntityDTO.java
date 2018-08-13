package com.example.globomartapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
abstract public class BaseEntityDTO<ID extends Serializable> implements Serializable {

    abstract protected ID getId();

    protected Boolean deleted;

    protected Date createdDate;

    protected UserDTO createdBy;

    protected Date lastModifiedDate;

    protected UserDTO lastModifiedBy;

    @JsonIgnore
    protected Long version;

    @JsonProperty("version")
    public Long getVersion() {
        return version;
    }

    @JsonIgnore
    public void setVersion(Long version) {
        this.version = version;
    }

    @JsonIgnore
    public Boolean getDeleted() {
        return deleted;
    }
}
