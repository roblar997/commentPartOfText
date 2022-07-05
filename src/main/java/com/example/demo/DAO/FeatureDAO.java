package com.example.demo.DAO;

import com.example.demo.entities.Feature;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Component
@Stateless
public class FeatureDAO {

    private List<Feature> features;

    public FeatureDAO(){
        this.features = new LinkedList<Feature>();
        Feature feature1 = new Feature(1,2,1);
        Feature feature2 = new Feature(3,4,1);
        Feature feature3 = new Feature(4,5,1);
        features.add(feature1);
        features.add(feature2);
        features.add(feature3);
    }
    public List<Feature> getFeatures(Integer texttocommentid) {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
    public void addFeature(Feature feature){
        this.features.add(feature);
    }
    public void removeFeature(Feature feature){

    }
}
