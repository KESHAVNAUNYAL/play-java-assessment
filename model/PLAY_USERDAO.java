package model;

import play.*;
import org.hibernate.transform.*;
import play.mvc.*;
import play.db.jpa.*;
import play.Logger;
import java.util.List;
import java.util.Date;

import javax.persistence.*;

public class PLAY_USERDAO {
    /**
     * Create a play_user
     *
     * @param PLAY_USER model
     *
     * @return PLAY_USER
     */
    public static PLAY_USER create (PLAY_USER model) {
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
     * Find a play_user by id
     *
     * @param Integer id
     *
     * @return PLAY_USER
     */
    public static PLAY_USER find(Integer id) {
        return JPA.em().find(PLAY_USER.class, id);
    }

    /**
     * Update an play_user
     *
     * @param PLAY_USER model
     *
     * @return PLAY_USER
     */
    public static PLAY_USER update(PLAY_USER model) {
        return JPA.em().merge(model);
    }

    /**
     * Delete an play_user by id
     *
     * @param Integer id
     */
    public static void delete(Integer id) {
    	PLAY_USER model = JPA.em().getReference(PLAY_USER.class, id);
        JPA.em().remove(model);
    }

    /**
     * Get all play_users
     *
     * @return List<PLAY_USER>
     */
   public static List<Object[]> all() {
        
	   return (List<Object[]>) JPA.em().createQuery("SELECT id,firstName,lastName,emailAddress,streetName,houseNumber,city,birthDate,userName,password FROM PLAY_USER ORDER BY id").getResultList();
	//   return (List<PLAY_USER>) JPA.em().createQuery("SELECT id,firstName,lastName,emailAddress,streetName,houseNumber,city,birthDate,userName,password FROM PLAY_USER ORDER BY id").setResultTransformer(Transformers.aliasToBean(PLAY_USERDAO.class)).list();
   }

    /**
     * Get the page of play_user
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<PLAY_USER>
     */
    public static List<PLAY_USER> paginate(Integer page, Integer size) {
        return (List<PLAY_USER>) JPA.em().createQuery("SELECT id,firstName,lastName,emailAddress,streetName,houseNumber,city,birthDate,userName,password FROM PLAY_USER ORDER BY id").setFirstResult(page*size).setMaxResults(size).getResultList();
    }

    /**
     * Get the number of total row
     *
     * @return Long
     */
    public static Long count() {
        return (Long) JPA.em().createQuery("SELECT count(*) FROM PLAY_USER ").getSingleResult();
    }
}