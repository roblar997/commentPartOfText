package com.example.demo.wrapper;

import java.util.Objects;

public class timestampMethodtexttocommentidWrapper {


    private String remoteMethod;

    public String getRemoteMethod() {
        return remoteMethod;
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    public timestampMethodtexttocommentidWrapper(String remoteMethod, Long timestamp, Integer texttocommentid) {
        this.remoteMethod = remoteMethod;
        this.timestamp = timestamp;
        this.texttocommentid = texttocommentid;
    }

    @Override
    public String toString() {
        return "timestampMethodtexttocommentidWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                ", timestamp=" + timestamp +
                ", texttocommentid=" + texttocommentid +
                '}';
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTexttocommentid() {
        return texttocommentid;
    }

    public void setTexttocommentid(Integer texttocommentid) {
        this.texttocommentid = texttocommentid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof timestampMethodtexttocommentidWrapper)) return false;
        timestampMethodtexttocommentidWrapper that = (timestampMethodtexttocommentidWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(timestamp, that.timestamp) && Objects.equals(texttocommentid, that.texttocommentid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, timestamp, texttocommentid);
    }

    private Long timestamp;
    private Integer texttocommentid;
}
