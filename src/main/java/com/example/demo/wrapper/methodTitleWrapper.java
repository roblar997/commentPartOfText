package com.example.demo.wrapper;

import java.util.Objects;

public class methodTitleWrapper {
    private String remoteMethod;

    public String getRemoteMethod() {
        return remoteMethod;
    }

    public methodTitleWrapper(String remoteMethod, String title) {
        this.remoteMethod = remoteMethod;
        this.title = title;
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    @Override
    public String toString() {
        return "methodTitleWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof methodTitleWrapper)) return false;
        methodTitleWrapper that = (methodTitleWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

}
