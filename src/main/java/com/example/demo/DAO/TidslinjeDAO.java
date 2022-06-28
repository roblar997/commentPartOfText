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
        //String sql = "SELECT t FROM Tidslinje t";
        //TypedQuery<Tidslinje> query = em.createQuery(sql, Tidslinje.class);
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
         String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\" WHERE \"isdeleted\" IS False";
         List<Tidslinje> tidslinjer = db.query(sql, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;
        //return query.getResultList();
    }
    @Transactional
    public String changeTidsline(Tidslinje tidslinje, Integer id){

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


        String sql =  "UPDATE \"schemaTest\".\"Tidslinje\" SET \"user\"=?, \"timestampcreated\"=?, \"timestampchanged\"=?, \"start\"=?, \"end\"=?, \"text\"=?, \"like\"=?, \"dislike\"=?, \"isdeleted\"=? WHERE \"id\"=?";
        db.update(sql,tidslinje.getUser(),tidslinje.getTimestampCreated(),tidslinje.getTimestampChanged(),tidslinje.getStart(),tidslinje.getEnd(),tidslinje.getText(),tidslinje.getLike(),tidslinje.getDislike(),tidslinje.getIsdeleted(),id);

        return "OK";

    }
    @Transactional
    public String removeTidsline(Integer id, Long timestampchanged){

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


        String sql  =  "UPDATE \"schemaTest\".\"Tidslinje\" SET \"isdeleted\"=?, \"timestampchanged\"=? WHERE \"id\"=?";
       db.update(sql,true, timestampchanged,id);
       return "OK";

    }

    @Transactional
    public String reverseDelete(Integer id, Long timestampchanged){
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

        String sql  =  "UPDATE \"schemaTest\".\"Tidslinje\" SET \"isdeleted\"=?, \"timestampchanged\"=? WHERE \"id\"=?";
        db.update(sql,false, timestampchanged,id);
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

        String sql  =  "DELETE FROM \"schemaTest\".\"Tidslinje\" WHERE \"isdeleted\"=?";
        db.update(sql,true);
        return "OK";

    }
    @Transactional
    public Integer addTidslinje(Tidslinje tidslinje){

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


        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO \"schemaTest\".\"Tidslinje\" (\"user\",\"timestampcreated\",\"timestampchanged\",\"start\",\"end\",\"text\",\"like\",\"dislike\",\"isdeleted\") VALUES(?,?,?,?,?,?,?,?,?)";

        db.update(con -> {
            PreparedStatement query = con.prepareStatement(sql, new String[]{"id"});
            query.setString(1, tidslinje.getUser() );
            query.setObject(2, tidslinje.getTimestampCreated(), Types.BIGINT);
            query. setObject(3, tidslinje.getTimestampChanged(), Types.BIGINT);
            query.setInt(4, tidslinje.getStart());
            query.setInt(5,tidslinje.getEnd());
            query.setString(6,tidslinje.getText());
            query.setBoolean(7,tidslinje.getLike());
            query.setBoolean(8 ,tidslinje.getDislike());
            query.setBoolean(9 ,tidslinje.getIsdeleted());
            return query;
        },keyHolder);


        Integer id = keyHolder.getKey().intValue();
        return id;
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
        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\" WHERE \"timestampchanged\" >= ? ";
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


        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\" WHERE \"timestampchanged\" <> \"timestampcreated\" AND \"timestampchanged\" > ? ";
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


        String sql =  "SELECT * FROM \"schemaTest\".\"Tidslinje\" WHERE \"timestampcreated\" > ? ";
        List<Tidslinje> tidslinjer = db.query(sql,new Long[]{timestamp}, new BeanPropertyRowMapper(Tidslinje.class));
        return tidslinjer;

    }



}
