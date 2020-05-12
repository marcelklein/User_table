package web_project.requests.controller;

import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import web_project.requests.controller.JsonConverter;
import db_connection.DbInterfaceUserAdmin;
import db_connection.model.UserStruct;

public class SelectController {

	static int userId = -1;
	static UserStruct newUser = null;
	static Vector<UserStruct> users = new Vector<UserStruct>();
	static UserStruct userStruct = new UserStruct();
	static DbInterfaceUserAdmin dbInterface = new DbInterfaceUserAdmin();

	public static void selectUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		userId = JsonConverter.getUserId(request);
		userStruct = dbInterface.getUserByUserId(userId);

		JSONObject jsonUser = JsonConverter.getJsonUser(userStruct);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonUser.toString());

	}

	public static void selectAllUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		users = dbInterface.getAllUser();

		JSONObject allJsonUser = JsonConverter.getJsonUser(users);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(allJsonUser.toString());
	}

	public static void createUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		newUser = JsonConverter.getJsonUserData(request);
		System.out.println(newUser);
		dbInterface.saveUserToDb(newUser);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"Status\": \"Ok\"}");
	}
	
	public static void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		userId = JsonConverter.getUserId(request);
		dbInterface.deleteUserFromDb(userId);
		
//		newUser = JsonConverter.getJsonUserData(request);
//		System.out.println(newUser);
//		dbInterface.saveUserToDb(newUser);
//
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write("{\"Status\": \"Ok\"}");
	}
}
