package com.example.demo.wrapper;

import java.util.Objects;

public class methodOnlyWrapper {
    private String remoteMethod;

    public String getRemoteMethod() {
        return remoteMethod;
    }

    public methodOnlyWrapper(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    @Override
    public String toString() {
        return "methodOnlyWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof methodOnlyWrapper)) return false;
        methodOnlyWrapper that = (methodOnlyWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod);
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }
}
