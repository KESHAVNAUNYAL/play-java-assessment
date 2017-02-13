package model;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import java.util.List;

public class PLAY_USERService {
    /**
     * Create a play_user
     *
     * @param PLAY_USER data
     *
     * @return PLAY_USER
     */
    public static PLAY_USER create(PLAY_USER data) {
    	Logger.info("inside create in playservice.");
        return PLAY_USERDAO.create(data);
    }

    /**
     * Update a play_user
     *
     * @param PLAY_USER data
     *
     * @return PLAY_USER
     */
    public static PLAY_USER update(PLAY_USER data) {
        return PLAY_USERDAO.update(data);
    }

    /**
     * Find a play_user by id
     *
     * @param Integer id
     *
     * @return PLAY_USER
     */
    public static PLAY_USER find(Integer id) {
        return PLAY_USERDAO.find(id);
    }

    /**
     * Delete a play_user by id
     *
     * @param Integer id
     */
    public static Boolean delete(Integer id) {
        PLAY_USER play_user = PLAY_USERDAO.find(id);
        if (play_user != null) {
            PLAY_USERDAO.delete(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get all play_users
     *
     * @return List<PLAY_USER>
     */
    public static List<Object []> all() {
        return PLAY_USERDAO.all();
    }

    /**
     * Get the page of play_users
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<PLAY_USER>
     */
    public static List<PLAY_USER> paginate(Integer page, Integer size) {
        return PLAY_USERDAO.paginate(page, size);
    }

    /**
     * Get the number of total of play_users
     *
     * @return Long
     */
    public static Long count() {
        return PLAY_USERDAO.count();
    }
}