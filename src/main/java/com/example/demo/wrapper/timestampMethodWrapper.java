package com.example.demo.wrapper;

import java.util.Objects;

public class timestampMethodWrapper {


    private String remoteMethod;
    private Long timestamp;

    public String getRemoteMethod() {
        return remoteMethod;
    }

    @Override
    public String toString() {
        return "timestampMethodWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof timestampMethodWrapper)) return false;
        timestampMethodWrapper that = (timestampMethodWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, timestamp);
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
