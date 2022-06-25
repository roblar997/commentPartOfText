package com.example.demo.wrapper;

import com.example.demo.entities.Tidslinje;

import java.util.Objects;

public class tidslinjeMethodWrapper {



    public String getRemoteMethod() {
        return remoteMethod;
    }

    public void setRemoteMethod(String remoteMethod) {
        this.remoteMethod = remoteMethod;
    }

    public tidslinjeMethodWrapper() {
    }

    public tidslinjeMethodWrapper(String remoteMethod, Tidslinje timeline) {
        this.remoteMethod = remoteMethod;
        this.timeline = timeline;
    }

    public Tidslinje getTimeline() {
        return timeline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof tidslinjeMethodWrapper)) return false;
        tidslinjeMethodWrapper that = (tidslinjeMethodWrapper) o;
        return Objects.equals(remoteMethod, that.remoteMethod) && Objects.equals(timeline, that.timeline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteMethod, timeline);
    }

    public void setTimeline(Tidslinje timeline) {
        this.timeline = timeline;
    }

    private String remoteMethod;
    private Tidslinje timeline;

}
