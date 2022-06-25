package com.example.demo.wrapper;

import java.util.Objects;

public class methodIdChangeDateWrapper {
    private String remoteMethod;
    private Integer id;
    private Long timestampChanged;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof methodIdChangeDateWrapper)) return false;
        methodIdChangeDateWrapper that = (methodIdChangeDateWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(id, that.id) && Objects.equals(timestampChanged, that.timestampChanged);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, id, timestampChanged);
    }

    @Override
    public String toString() {
        return "methodIdWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                ", id=" + id +
                ", timestampChanged=" + timestampChanged +
                '}';
    }

    public String getRemoteMethod() {
        return remoteMethod;
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTimestampChanged() {
        return timestampChanged;
    }

    public void setTimestampChanged(Long timestampChanged) {
        this.timestampChanged = timestampChanged;
    }

    public methodIdChangeDateWrapper(String remoteMethod, Integer id, Long timestampChanged) {
        this.remoteMethod = remoteMethod;
        this.id = id;
        this.timestampChanged = timestampChanged;
    }


}
