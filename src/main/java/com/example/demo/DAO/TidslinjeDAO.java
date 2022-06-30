package com.example.demo.DAO;

import com.example.demo.entities.Tidslinje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;


@Component
@Stateless
public class TidslinjeDAO {


    @Autowired
    private JdbcTemplate db;

    @PersistenceContext
    @Autowired
    private EntityManager em;

    public TidslinjeDAO() {

    }


    @Transactional
    public List<Tidslinje> getTidslinjer(){

       String sql = "SELECT t FROM Tidslinje t WHERE t.isdeleted=False";
       TypedQuery<Tidslinje> query = em.createQuery(sql, Tidslinje.class);

      return query.getResultList();
    }
    @Transactional
    public String changeTidsline(Tidslinje tidslinje, Integer id){
        String sql = "SELECT t FROM Tidslinje t WHERE t.id=:id";

        TypedQuery<Tidslinje> queryType = em.createQuery(sql, Tidslinje.class);
        queryType.setParameter("id",id);

        Tidslinje tidslinjen = queryType.getSingleResult();

        if(tidslinjen != null){
            tidslinjen.setIsdeleted(tidslinje.getIsdeleted());
            tidslinjen.setTimestampChanged(tidslinje.getTimestampChanged());
            tidslinjen.setTimestampCreated(tidslinje.getTimestampCreated());
            tidslinjen.setStart(tidslinje.getStart());
            tidslinjen.setEnd(tidslinje.getEnd());
            tidslinjen.setLike(tidslinje.getLike());
            tidslinjen.setDislike(tidslinje.getDislike());
            tidslinjen.setText(tidslinje.getText());
            tidslinjen.setUser(tidslinje.getUser());
            em.merge(tidslinjen);
        }

        return "OK";

    }
    @Transactional
    public String removeTidsline(Integer id, Long timestampchanged){
        String sql = "SELECT t FROM Tidslinje t WHERE t.id=:id";

        TypedQuery<Tidslinje> queryType = em.createQuery(sql, Tidslinje.class);
        queryType.setParameter("id",id);

        Tidslinje tidslinjen = queryType.getSingleResult();

        if(tidslinjen != null){
            tidslinjen.setIsdeleted(true);
            tidslinjen.setTimestampChanged(timestampchanged);

            em.merge(tidslinjen);
        }

       return "OK";

    }

    @Transactional
    public String reverseDelete(Integer id, Long timestampchanged){
        String sql = "SELECT t FROM Tidslinje t WHERE t.id=:id";

        TypedQuery<Tidslinje> queryType = em.createQuery(sql, Tidslinje.class);
        queryType.setParameter("id",id);

        Tidslinje tidslinjen = queryType.getSingleResult();

        if(tidslinjen != null){
            tidslinjen.setIsdeleted(false);
            tidslinjen.setTimestampChanged(timestampchanged);

            em.merge(tidslinjen);
        }
        return "OK";

    }
    @Transactional
    public String eraseDeleted(){
          /*  EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try {
            tx.begin();
            //...
            tx.commit();
        } catch (Throwable e) {

            tx.rollback();
        } finally {
            em.close();
        }*/

        String sql  =  "DELETE FROM \"schematest\".\"tidslinje\" WHERE \"isdeleted\"=?";
        db.update(sql,true);
        return "OK";

    }
    @Transactional
    public Integer addTidslinje(Tidslinje tidslinje){

            em.merge(tidslinje);
            return tidslinje.getId();
    }
    @Transactional
    public List<Tidslinje> getLatestChangedOrAdded(Long timestamp){

        /*  EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try {
            tx.begin();
            //...
            tx.commit();
        } catch (Throwable e) {

            tx.rollback();
        } finally {
            em.close();
        }*/


        //Get newest changes
        String sql =  "SELECT * FROM \"schematest\".\"tidslinje\" WHERE \"timestampchanged\" >= ? ";
        List<Tidslinje> tidslinjer = db.query(sql,new Long[]{timestamp}, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;

    }
    @Transactional
    public List<Tidslinje> getLatestChanged(Long timestamp){

       /*  EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try {
            tx.begin();
            //...
            tx.commit();
        } catch (Throwable e) {

            tx.rollback();
        } finally {
            em.close();
        }*/


        String sql =  "SELECT * FROM \"schematest\".\"tidslinje\" WHERE \"timestampchanged\" <> \"timestampcreated\" AND \"timestampchanged\" > ? ";
        List<Tidslinje> tidslinjer = db.query(sql,new Long[]{timestamp}, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;
    }
    @Transactional
    public List<Tidslinje> getLatestAdded(Long timestamp){

          /*  EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try {
            tx.begin();
            //...
            tx.commit();
        } catch (Throwable e) {

            tx.rollback();
        } finally {
            em.close();
        }*/


        String sql =  "SELECT * FROM \"schematest\".\"tidslinje\" WHERE \"timestampcreated\" > ? ";
        List<Tidslinje> tidslinjer = db.query(sql,new Long[]{timestamp}, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;

    }



}
