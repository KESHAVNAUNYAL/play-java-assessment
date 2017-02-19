package controllers;

import com.google.inject.Inject;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import play.libs.Json;
import play.libs.Json.*;

import play.*;
import play.mvc.*;
import play.data.*;
import play.db.jpa.Transactional;
import model.*;
import views.html.*;
import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class PlayUserController extends Controller {

	// static Form<PlayUser> play_userForm = Form.form(PlayUser.class);

	Form<PlayUser> play_userForm;

	@Inject
	public PlayUserController(FormFactory form) {
		this.play_userForm = form.form(PlayUser.class);
	}

	
	public Result jsonResult(Result httpResponse) {
		// response().setContentType("application/json; charset=utf-8");
		response().setContentType("text/html");
		return httpResponse;
	}

	
	public Result index() {
		return ok(index.render("API REST for JAVA Play Framework"));
	}

	public Result usercreate() {
		return ok(usercreate.render("API REST for JAVA Play Framework", play_userForm));
	}

	
	@Transactional(readOnly = true)
	public Result list(Integer page, Integer size) {
		// List models = PLAY_USERService.paginate(page-1, size);
		// Long count = PLAY_USERService.count();

		ObjectNode result = Json.newObject();
		/*
		 * result.put("data", Json.toJson(models)); result.put("total", count);
		 * if (page > 1) result.put("link-prev",
		 * routes.PLAY_USERController.list(page-1, size).toString()); if
		 * (page*size < count) result.put("link-next",
		 * routes.PLAY_USERController.list(page+1, size).toString());
		 * result.put("link-self", routes.PLAY_USERController.list(page,
		 * size).toString());
		 */

		return jsonResult(ok(result));
	}

	/**
	 * Get the play_users without pagination
	 *
	 * 
	 * @return Result
	 */
	@Transactional(readOnly = true)
	public Result all() {
		// List models = PLAY_USERService.all();
		ObjectNode result = Json.newObject();
		// response().setContentType("text/html");
		// return ok(Json.toJson(models));
		// return ok(result);
		PlayUser person = new PlayUser();
		person.setId(1);
		person.setFirstName("XXXX");
		person.setLastName("YYYY");
		// JsonNode personJson = Json.toJson(person);
		result.put("users", Json.toJson(person));

		return jsonResult(ok(result));
	}

	//@Transactional(readOnly = true)
	// public Result allrest() {

	// ObjectNode result = Json.newObject();
	// List<PlayUser []> models = PlayUserService.all();
	/*
	 * for (PlayUser[] result11 : models) { PlayUser play_user = new PlayUser();
	 * play_user.setId( (Integer)result11[0] );
	 * play_user.setFirstName((String)result11[1] ); String str = "User" +
	 * Integer.toString(play_user.getId());
	 * play_user.setLastName((String)result11[2]);
	 * play_user.setEmailAddress((String)result11[3]);
	 * play_user.setStreetName((String)result11[4]);
	 * play_user.setHouseNumber((String)result11[5]);
	 * play_user.setCity((String)result11[6]); Logger.info("BIRTHDATE is = " +
	 * (Date)(result11[7]) ); play_user.setBirthDate((Date)result11[7]);
	 * play_user.setUserName((String)result11[8]);
	 * play_user.setPassword((String)result11[9]);
	 * 
	 * result.put(str,Json.toJson(play_user));
	 * 
	 * }
	 */

	// return jsonResult(ok(result));
	// return ok(
	// views.html.index.render(models, play_userForm)

	// );

	// }
	@Transactional(readOnly = true)
	public Result allrestNew(Integer page, Integer size) {

		// List<Object []> models = PlayUserService.all();
		List<Object[]> models = PlayUserService.paginate(page - 1, size);
		Long count = PlayUserService.count();
		List<PlayUser> play_user_m = new ArrayList<PlayUser>();

		for (Object[] result11 : models) {
			PlayUser play_user = new PlayUser();
			play_user.setId((Integer) result11[0]);
			play_user.setFirstName((String) result11[1]);

			play_user.setLastName((String) result11[2]);
			play_user.setEmailAddress((String) result11[3]);
			play_user.setStreetName((String) result11[4]);
			play_user.setHouseNumber((String) result11[5]);
			play_user.setCity((String) result11[6]);

			play_user.setBirthDate((Date) result11[7]);
			play_user.setUserName((String) result11[8]);
			play_user.setPassword((String) result11[9]);
			play_user_m.add(play_user);

		}
		// if (page > 1) result.put("link-prev",
		// routes.PlayUserService.allrestNew(page-1, size).toString());
		// if (page*size < count) result.put("link-next",
		// routes.PlayUserService.allrestNew(page+1, size).toString());
		// result.put("link-self", routes.PlayUserService.allrestNew(page,
		// size).toString());

		return ok(views.html.userslist.render(count, play_user_m));

	}

	@Transactional(readOnly = true)
	public Result edit(Integer id) {
		PlayUser playuser = PlayUserService.find(id);
		if (playuser == null) {
			ObjectNode result = Json.newObject();
			result.put("error", "Not found " + id);
			return jsonResult(notFound(result));
		}

		Form play_userForm = Form.form(PlayUser.class).fill(playuser);
		return ok(views.html.useredit.render(id, play_userForm));
	}

	/**
	 * Get one play_user by id
	 *
	 * @param Integer
	 *            id
	 *
	 * @return Result
	 */
	@Transactional(readOnly = true)
	public Result get(Integer id) {
		PlayUser playuser = PlayUserService.find(id);
		if (playuser == null) {
			ObjectNode result = Json.newObject();
			result.put("error", "Not found " + id);
			return jsonResult(notFound(result));
		}

		Form play_userForm = Form.form(PlayUser.class).fill(playuser);
		return jsonResult(ok(Json.toJson(playuser)));
		// return ok(useredit.render("API REST for JAVA Play
		// Framework",play_user));

	}

	/**
	 * Create an play_user with the data of request
	 */
	/*
	 * @return Result
	 */
	@Transactional
	public Result create() {
		Logger.info("inside create in playcontroller." + play_userForm);
		Form<PlayUser> play_user = play_userForm.bindFromRequest();

		if (play_user.hasErrors()) {
			
//			flash("error", "Please correct errors above.");
			return badRequest(usercreate.render("API REST for JAVA Play Framework", play_user));
			
		}

		PlayUser play_user11 = play_userForm.bindFromRequest().get();
		play_user11.password = BCrypt.hashpw(play_user11.password, BCrypt.gensalt());
		PlayUser newPLAY_USER = PlayUserService.create(play_user11);
				
		flash("success", "User Id : " + newPLAY_USER.id + " has been created");
		return redirect(routes.PlayUserController.index());
	}

	/**
	 * Update an play_user with the data of request
	 *
	 * @return Result
	 */
	@Transactional
	public Result update(Integer i) {
		Logger.info("inside update in playcontroller." + play_userForm);
		Form<PlayUser> play_user = play_userForm.bindFromRequest();

		if (play_user.hasErrors()) {
			flash("error", "Please correct errors above.");
			return badRequest(views.html.useredit.render(i, play_user));
		}
		PlayUser play_user11 = play_userForm.bindFromRequest().get();
		play_user11.password = BCrypt.hashpw(play_user11.password, BCrypt.gensalt());
		PlayUser updatedPLAY_USER = PlayUserService.update(play_user11);

		flash("success", "User Id : " + updatedPLAY_USER.id + " has been updated");
		return redirect(routes.PlayUserController.allrestNew(1, 20));

		// return redirect(routes.PlayUserController.index());
	}

	/**
	 * Delete an play_user by id
	 *
	 * @param Integer
	 *            id
	 *
	 * @return Result
	 */
	@Transactional
	public Result delete(Integer id) {
		if (PlayUserService.delete(id)) {
			ObjectNode result = Json.newObject();
			result.put("msg", "Deleted " + id);
			return jsonResult(ok(result));
		}
		ObjectNode result = Json.newObject();
		result.put("error", "Not found " + id);
		return jsonResult(notFound(result));
	}
}
