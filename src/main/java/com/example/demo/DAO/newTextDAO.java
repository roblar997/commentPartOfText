package com.example.demo.DAO;

import com.example.demo.entities.Tidslinje;
import com.example.demo.entities.textToComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public String addText(String title, String text){
        textToComment textToAdd = new textToComment(text,title);
        em.merge(textToAdd);

        return "OK";
    }
    @Transactional
    public List<String> getTitles(){
        String sql = "SELECT t.title FROM textToComment t ";

        TypedQuery<String> queryType = em.createQuery(sql, String.class);

        return queryType.getResultList();
    }
    @Transactional
    public textToComment getText(String title){

        String sql = "SELECT t FROM textToComment t WHERE t.title=:title";

        TypedQuery<textToComment> queryType = em.createQuery(sql, textToComment.class);
        queryType.setParameter("title",title);
        return queryType.getSingleResult();
    }

}
