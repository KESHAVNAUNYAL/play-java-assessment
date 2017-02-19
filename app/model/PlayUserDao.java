package model;

import play.*;
import org.hibernate.transform.*;
import play.mvc.*;
import play.db.jpa.*;
import play.Logger;
import java.util.List;


import javax.persistence.*;

public class PlayUserDao {
    /**
     * Create a PlayUser
     *
     * @param PlayUser model
     *
     * @return PlayUser
     */
    public static PlayUser create (PlayUser model) {
    	Logger.info("inside create in playuserdao. 1");
        //model.emptyToNull();
        JPA.em().persist(model);
        Logger.info("inside create in playuserdao. 2");
        // Flush and refresh for check
       // JPA.em().flush();
        //JPA.em().refresh(model);
        return model;
    }

    /**
     * Find a PlayUser by id
     *
     * @param Integer id
     *
     * @return PlayUser
     */
    public static PlayUser find(Integer id) {
        return JPA.em().find(PlayUser.class, id);
    }

    /**
     * Update an PLAYUSER
     *
     * @param PLAYUSER model
     *
     * @return PLAYUSER
     */
    public static PlayUser update(PlayUser model) {
    	Logger.info("Inside update DAO " + model.id);
        return JPA.em().merge(model);
    }

    /**
     * Delete a PLAYUSER by id
     *
     * @param Integer id
     */
    public static void delete(Integer id) {
    	PlayUser model = JPA.em().getReference(PlayUser.class, id);
        JPA.em().remove(model);
    }

    /**
     * Get all PLAYUSERs
     *
     * @return List<PlayUser>
     */
   public static List<Object[]> all() {
        
	   return (List<Object[]>) JPA.em().createQuery("SELECT id,firstName,lastName,emailAddress,streetName,houseNumber,city,birthDate,userName,password FROM PlayUser ORDER BY id").getResultList();
	//   return (List<PlayUser>) JPA.em().createQuery("SELECT id,firstName,lastName,emailAddress,streetName,houseNumber,city,birthDate,userName,password FROM PLAYUSER ORDER BY id").setResultTransformer(Transformers.aliasToBean(PlayUserDao.class)).list();
   }

    /**
     * Get the page of PlayUser
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<PlayUser>
     */
    public static List<Object[]> paginate(Integer page, Integer size) {
        return (List<Object[]>) JPA.em().createQuery("SELECT id,firstName,lastName,emailAddress,streetName,houseNumber,city,birthDate,userName,password FROM PlayUser ORDER BY id desc").setFirstResult(page*size).setMaxResults(size).getResultList();
    }

    /**
     * Get the number of total row
     *
     * @return Long
     */
    public static Long count() {
        return (Long) JPA.em().createQuery("SELECT count(*) FROM PlayUser ").getSingleResult();
    }
}