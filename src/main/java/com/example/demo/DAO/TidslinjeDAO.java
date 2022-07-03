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
import java.util.ArrayList;
import java.util.List;


@Component
@Stateless
public class TidslinjeDAO {


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

        String sql = "DELETE FROM Tidslinje t WHERE t.isdeleted=true";

        TypedQuery<Tidslinje> queryType = em.createQuery(sql, Tidslinje.class);
        queryType.executeUpdate();
        return "OK";

    }
    @Transactional
    public void addTidslinje(Tidslinje tidslinje){
            em.merge(tidslinje);
            return;
    }
    @Transactional
    public List<Tidslinje> getLatestChangedOrAdded(Long timestamp){

        if(timestamp == null) return new ArrayList<Tidslinje>();

        String sql = "SELECT t FROM Tidslinje t WHERE t.timestampChanged >= :timestamp";

        TypedQuery<Tidslinje> queryType = em.createQuery(sql, Tidslinje.class);
        queryType.setParameter("timestamp",timestamp);

        return queryType.getResultList();

    }
    @Transactional
    public List<Tidslinje> getLatestChanged(Long timestamp){

        String sql = "SELECT t FROM Tidslinje t WHERE t.timestampChanged <> t.timestampCreated AND t.timestampChanged >= :timestamp";

        TypedQuery<Tidslinje> queryType = em.createQuery(sql, Tidslinje.class);
        queryType.setParameter("timestamp",timestamp);

        return queryType.getResultList();


    }
    @Transactional
    public List<Tidslinje> getLatestAdded(Long timestamp){

        String sql = "SELECT t FROM Tidslinje t WHERE  t.timestampCreated >= :timestamp";

        TypedQuery<Tidslinje> queryType = em.createQuery(sql, Tidslinje.class);
        queryType.setParameter("timestamp",timestamp);

        return queryType.getResultList();


    }



}
