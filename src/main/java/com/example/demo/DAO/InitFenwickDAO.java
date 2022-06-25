package com.example.demo.DAO;

import com.example.demo.entities.InitFenwick;
import org.springframework.stereotype.Component;

import javax.ejb.Stateless;

@Component
@Stateless
public class InitFenwickDAO {

    private InitFenwick fenwick;



    public InitFenwickDAO(){
        Integer size = 100;
        Integer nmbFeatures = 100;
        fenwick = new InitFenwick(nmbFeatures,size);
    }

    public InitFenwick getFenwick(){
        return this.fenwick;
    }

}
