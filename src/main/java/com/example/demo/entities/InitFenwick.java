package com.example.demo.entities;

import java.util.Objects;

public class InitFenwick {

    public Integer getNmbFeatures() {
        return nmbFeatures;
    }

    public void setNmbFeatures(Integer nmbFeatures) {
        this.nmbFeatures = nmbFeatures;
    }

    public Integer getSize() {
        return size;
    }

    public InitFenwick(Integer nmbFeatures, Integer size) {
        this.nmbFeatures = nmbFeatures;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InitFenwick)) return false;
        InitFenwick that = (InitFenwick) o;
        return Objects.equals(nmbFeatures, that.nmbFeatures) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nmbFeatures, size);
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    private Integer nmbFeatures;

    private Integer size;

}
