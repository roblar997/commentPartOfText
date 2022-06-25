package com.example.demo.wrapper;

import com.example.demo.entities.Tidslinje;

import java.util.Objects;

public class tidslinjeMethodIdWrapper {


    private String remoteMethod;
    private Tidslinje timeline;
    private Integer id;

    public tidslinjeMethodIdWrapper(String remoteMethod, Tidslinje timeline, Integer id) {
        this.remoteMethod = remoteMethod;
        this.timeline = timeline;
        this.id = id;
    }

    @Override
    public String toString() {
        return "tidslinjeMethodIdWrapper{" +
                "remoteMethod='" + remoteMethod + '\'' +
                ", timeline=" + timeline +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof tidslinjeMethodIdWrapper)) return false;
        tidslinjeMethodIdWrapper that = (tidslinjeMethodIdWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(timeline, that.timeline) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, timeline, id);
    }

    public String getRemoteMethod() {
        return remoteMethod;
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    public Tidslinje getTimeline() {
        return timeline;
    }

    public void setTimeline(Tidslinje timeline) {
        this.timeline = timeline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
