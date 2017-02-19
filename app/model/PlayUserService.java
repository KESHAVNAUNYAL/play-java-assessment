package model;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import java.util.List;

public class PlayUserService {
    /**
     * Create a PlayUser
     *
     * @param PlayUser data
     *
     * @return PlayUser
     */
    public static PlayUser create(PlayUser data) {
    	Logger.info("inside create in playservice.");
        return PlayUserDao.create(data);
    }

    /**
     * Update a PlayUser
     *
     * @param PlayUser data
     *
     * @return PlayUser
     */
    public static PlayUser update(PlayUser data) {
        return PlayUserDao.update(data);
    }

    /**
     * Find a play_user by id
     *
     * @param Integer id
     *
     * @return PLAY_USER
     */
    public static PlayUser find(Integer id) {
        return PlayUserDao.find(id);
    }

    /**
     * Delete a play_user by id
     *
     * @param Integer id
     */
    public static Boolean delete(Integer id) {
    	PlayUser play_user = PlayUserDao.find(id);
        if (play_user != null) {
        	PlayUserDao.delete(id);
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
        return PlayUserDao.all();
    }

    /**
     * Get the page of play_users
     *
     * @param Integer page
     * @param Integer size
     *
     * @return List<PLAY_USER>
     */
    public static List<Object[]> paginate(Integer page, Integer size) {
        return PlayUserDao.paginate(page, size);
    }

    /**
     * Get the number of total of play_users
     *
     * @return Long
     */
    public static Long count() {
        return PlayUserDao.count();
    }
}