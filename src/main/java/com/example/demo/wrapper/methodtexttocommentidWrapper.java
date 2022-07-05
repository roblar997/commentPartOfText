package com.example.demo.wrapper;

import java.util.Objects;

public class methodtexttocommentidWrapper {
    private String remoteMethod;
    private Integer texttocommentid;

    public String getRemoteMethod() {
        return remoteMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof methodtexttocommentidWrapper)) return false;
        methodtexttocommentidWrapper that = (methodtexttocommentidWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(texttocommentid, that.texttocommentid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, texttocommentid);
    }

    @Override
    public String toString() {
        return "methodtexttocommentidWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                ", texttocommentid=" + texttocommentid +
                '}';
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    public Integer getTexttocommentid() {
        return texttocommentid;
    }

    public void setTexttocommentid(Integer texttocommentid) {
        this.texttocommentid = texttocommentid;
    }

    public methodtexttocommentidWrapper(String remoteMethod, Integer texttocommentid) {
        this.remoteMethod = remoteMethod;
        this.texttocommentid = texttocommentid;
    }
}
