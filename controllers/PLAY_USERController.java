package controllers;

import java.util.Date;
import java.util.List;


import com.google.inject.Inject;
import play.*;
import play.mvc.*;
import play.libs.Json;
import play.libs.Json.*;
import play.data.Form;
import play.data.FormFactory; 
import play.db.jpa.*;
import play.data.*;
import play.Logger;
import play.i18n.Messages;
import java.io.IOException; 

import model.PLAY_USER;
import model.PLAY_USERDAO;
import model.PLAY_USERService;

import views.html.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class PLAY_USERController extends Controller {
	
	
	Form<PLAY_USER> play_userForm ;
	@Inject
	public PLAY_USERController ( FormFactory form)
	{
		this.play_userForm = form.form(PLAY_USER.class);
	}
	
	//Form<PLAY_USER> play_userForm = form.form(PLAY_USER.class);
    /**
     * Add the content-type json to response
     *
     * @param Result httpResponse
     *
     * @return Result
     */
    public Result jsonResult(Result httpResponse) {
        //response().setContentType("application/json; charset=utf-8");
    	response().setContentType("text/html");
        return httpResponse;
    }

    /**
     * Get the index page
     *
     * @return Result
     */
    public Result index() {
        return ok(index.render("API REST for JAVA Play Framework"));
    }

    /**
     * Get the play_users with pagination
     *
     * @param Integer page
     * @param Integer size
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result list(Integer page, Integer size) {
        //List models = PLAY_USERService.paginate(page-1, size);
        //Long count = PLAY_USERService.count();

        ObjectNode result = Json.newObject();
        /*result.put("data", Json.toJson(models));
        result.put("total", count);
        if (page > 1) result.put("link-prev", routes.PLAY_USERController.list(page-1, size).toString());
        if (page*size < count) result.put("link-next", routes.PLAY_USERController.list(page+1, size).toString());
        result.put("link-self", routes.PLAY_USERController.list(page, size).toString());*/

        return jsonResult(ok(result));
    }
    
    /**
     * Get the play_users without pagination
     *
    
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result all() {
        //List models = PLAY_USERService.all();
        ObjectNode result = Json.newObject();
        //response().setContentType("text/html");
        //return ok(Json.toJson(models));
        //return ok(result);
    	PLAY_USER person = new PLAY_USER();
    	person.setId(1);
    	person.setFirstName("XXXX");
    	person.setLastName("YYYY");
    	//JsonNode personJson = Json.toJson(person);
    	result.put("users",Json.toJson(person));
    	
    	return jsonResult(ok(result));
    }
    
    @Transactional(readOnly = true)
    public Result allrest() {
    	
    		ObjectNode result = Json.newObject();
        List<Object[]> models = PLAY_USERService.all();
        for (Object[] result11 : models)
        {
            PLAY_USER play_user = new PLAY_USER();
            play_user.setId(    (Integer)result11[0] );
            play_user.setFirstName((String)result11[1] );
            String str = "User" + Integer.toString(play_user.getId());
            play_user.setLastName((String)result11[2]);
            play_user.setEmailAddress((String)result11[3]);
            play_user.setStreetName((String)result11[4]);
            play_user.setHouseNumber((String)result11[5]);
            play_user.setCity((String)result11[6]);
            play_user.setBirthDate((Date)result11[7]);
            play_user.setUserName((String)result11[8]);
            play_user.setPassword((String)result11[9]);
      
            result.put(str,Json.toJson(play_user));         
        }  
           
    	return jsonResult(ok(result));
    }

    /**
     * Get one play_user by id
     *
     * @param Integer id
     *
     * @return Result
     */
    @Transactional(readOnly = true)
    public Result get(Integer id) {
        PLAY_USER play_user = PLAY_USERService.find(id);
        if (play_user == null ) {
            ObjectNode result = Json.newObject();
            result.put("error", "Not found " + id);
            return jsonResult(notFound(result));
        }
        return jsonResult(ok(Json.toJson(play_user)));
    }

    /**
     * Create an play_user with the data of request
     */
     /* @return Result
     */
    @Transactional
    public Result create() {
    	Logger.info("inside create in playcontroller." + play_userForm);
        Form<PLAY_USER> play_user = play_userForm.bindFromRequest();
        Logger.info("inside create in playcontroller." + play_user);
        PLAY_USER play_user11 = play_userForm.bindFromRequest().get();
        Logger.info("inside create in playcontroller. 2" + play_user11);
        if (play_user.hasErrors()) {
        	Logger.info("inside create in playcontroller. 3");
            return jsonResult(badRequest(play_user.errorsAsJson()));
        }
        ObjectNode result = Json.newObject();
        PLAY_USER newPLAY_USER = PLAY_USERService.create(play_user.get());
        result.put("users",Json.toJson(newPLAY_USER));
        return jsonResult(ok(result));
    }

    /**
     * Update an play_user with the data of request
     *
     * @return Result
     */
    @Transactional
    public Result update() {
    	Logger.info("inside create in playcontroller." + play_userForm);
        Form<PLAY_USER> play_user = play_userForm.bindFromRequest();
        Logger.info("inside create in playcontroller." + play_user);
        PLAY_USER play_user11 = play_userForm.bindFromRequest().get();
        Logger.info("inside create in playcontroller. 2" + play_user11);
        if (play_user.hasErrors()) {
        	Logger.info("inside create in playcontroller. 3");
            return jsonResult(badRequest(play_user.errorsAsJson()));
        }
        ObjectNode result = Json.newObject();
        PLAY_USER updatedPLAY_USER = PLAY_USERService.update(play_user.get());
        Logger.info("inside create in playcontroller." + updatedPLAY_USER);
        return jsonResult(ok(Json.toJson(updatedPLAY_USER)));
    }

    /**
     * Delete an play_user by id
     *
     * @param Integer id
     *
     * @return Result
     */
    @Transactional
    public Result delete(Integer id) {
        if (PLAY_USERService.delete(id)) {
            ObjectNode result = Json.newObject();
            result.put("msg", "Deleted " + id);
            return jsonResult(ok(result));
        }
        ObjectNode result = Json.newObject();
        result.put("error", "Not found " + id);
        return jsonResult(notFound(result));
    }
}
