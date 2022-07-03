package com.example.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Stateless
public class newTextDAO {
    @PersistenceContext
    @Autowired
    private EntityManager em;

    public String addText(String title, String text){

        return "OK";
    }
    public List<String> getTitles(){
        return  null;
    }

    public String getText(String title){

        return "...";
    }

}
