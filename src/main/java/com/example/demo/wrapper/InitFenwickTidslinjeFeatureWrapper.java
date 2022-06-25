package com.example.demo.wrapper;

import com.example.demo.entities.Feature;
import com.example.demo.entities.InitFenwick;
import com.example.demo.entities.Tidslinje;

import java.util.List;
import java.util.Objects;

public class InitFenwickTidslinjeFeatureWrapper {

    private InitFenwick initFenwick;

    public InitFenwickTidslinjeFeatureWrapper(InitFenwick initFenwick, List<Tidslinje> tidslinjer, List<Feature> featureList) {
        this.initFenwick = initFenwick;
        this.tidslinjer = tidslinjer;
        this.featureList = featureList;
    }

    private List<Tidslinje> tidslinjer;
    private List<Feature> featureList;

 

    public InitFenwick getInitFenwick() {
        return initFenwick;
    }

    public void setInitFenwick(InitFenwick initFenwick) {
        this.initFenwick = initFenwick;
    }

    public List<Tidslinje> getTidslinjer() {
        return tidslinjer;
    }

    public void setTidslinjer(List<Tidslinje> tidslinjer) {
        this.tidslinjer = tidslinjer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InitFenwickTidslinjeFeatureWrapper)) return false;
        InitFenwickTidslinjeFeatureWrapper that = (InitFenwickTidslinjeFeatureWrapper) o;
        return Objects.equals(initFenwick, that.initFenwick) && Objects.equals(tidslinjer, that.tidslinjer) && Objects.equals(featureList, that.featureList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initFenwick, tidslinjer, featureList);
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }
}
