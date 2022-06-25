package com.example.demo.entities;

import java.util.Objects;

public class Feature {

    private Integer timeslot;
    private Integer featureNmb;
    private Integer val;

    public Feature(Integer timeslot, Integer featureNmb, Integer val) {
        this.timeslot = timeslot;
        this.featureNmb = featureNmb;
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feature)) return false;
        Feature feature = (Feature) o;
        return Objects.equals(timeslot, feature.timeslot) && Objects.equals(featureNmb, feature.featureNmb) && Objects.equals(val, feature.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeslot, featureNmb, val);
    }

    public Integer getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Integer timeslot) {
        this.timeslot = timeslot;
    }

    public Integer getFeatureNmb() {
        return featureNmb;
    }

    public void setFeatureNmb(Integer featureNmb) {
        this.featureNmb = featureNmb;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
