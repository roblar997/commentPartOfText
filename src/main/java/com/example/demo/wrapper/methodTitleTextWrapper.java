package com.example.demo.wrapper;

import java.util.Objects;

public class methodTitleTextWrapper {

    public String getRemoteMethod() {
        return remoteMethod;
    }

    @Override
    public String toString() {
        return "methodTitleTextWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public methodTitleTextWrapper(String remoteMethod, String title, String text) {
        this.remoteMethod = remoteMethod;
        this.title = title;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof methodTitleTextWrapper)) return false;
        methodTitleTextWrapper that = (methodTitleTextWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(title, that.title) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, title, text);
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String remoteMethod;
    private String title;
    private String text;



}
