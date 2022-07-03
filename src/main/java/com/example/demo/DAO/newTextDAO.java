package com.example.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Stateless
public class newTextDAO {
    @PersistenceContext
    @Autowired
    private EntityManager em;

}
