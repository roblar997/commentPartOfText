package com.example.demo.DAO;

import com.example.demo.entities.Tidslinje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        String sql = "SELECT t.title FROM textToComment t ";

        TypedQuery<String> queryType = em.createQuery(sql, String.class);

        return queryType.getResultList();
    }

    public String getText(String title){

        return "...";
    }

}
