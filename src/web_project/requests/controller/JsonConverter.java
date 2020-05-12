package web_project.requests.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.Result;

import db_connection.model.UserStruct;

public class JsonConverter {

	static String getJsonStringFromServletRequest(HttpServletRequest request) {
		
		String jsonString = "";
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		jsonString = sb.toString();
		System.out.println(jsonString);
		
		return jsonString;
	}
	
	static int getUserId(HttpServletRequest request) {

		int userId = -1;
		String jsonString = getJsonStringFromServletRequest(request);
		
		try {
			JSONObject jsonInput = new JSONObject(jsonString);
			
			userId = jsonInput.getInt("newUser");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userId;
	}

	static JSONObject getJsonUser(UserStruct user) {
		
		JSONObject jsonUser = new JSONObject();
		
		try {
			jsonUser.put("userId", user.userId);
			jsonUser.put("surname", user.surname);
			jsonUser.put("firstname", user.firstname);
			jsonUser.put("streetAddress", user.streetAddress);
			jsonUser.put("city", user.city);
			jsonUser.put("zipCode", user.zipCode);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		System.out.println(jsonUser.toString());
		return jsonUser;
		
	}
	
	static JSONObject getJsonUser (Vector<UserStruct> allUser) {
		
		JSONObject allJsonUser = new JSONObject();
		
		
		try {
			for(int i = 0; i < allUser.size(); i++) {
				
				JSONObject jsonUser = new JSONObject();
				jsonUser.put("userId", allUser.get(i).userId);
				jsonUser.put("surname", allUser.get(i).surname);
				jsonUser.put("firstname", allUser.get(i).firstname);
				jsonUser.put("streetAddress", allUser.get(i).streetAddress);
				jsonUser.put("city", allUser.get(i).city);
				jsonUser.put("zipCode", allUser.get(i).zipCode);
				
				allJsonUser.put ("user_" + allUser.get(i).userId, jsonUser);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(allJsonUser.toString());
		
		
		return allJsonUser;
	}
	
	static UserStruct getJsonUserData (HttpServletRequest request) {
		
		String jsonString = getJsonStringFromServletRequest(request);
		
//		String surname = null;
//		String firstname = null;
//		String address = null;
//		String city = null;
//		int zipCode = -1;
		
		UserStruct newUser = new UserStruct();	
		
		try {
			JSONObject jo = new JSONObject(jsonString);
			newUser.surname = jo.getJSONObject("newUser").getString("surname");
			newUser.firstname = jo.getJSONObject("newUser").getString("firstname");
			newUser.streetAddress = jo.getJSONObject("newUser").getString("address");
			newUser.city = jo.getJSONObject("newUser").getString("city");
			newUser.zipCode = jo.getJSONObject("newUser").getInt("zipCode");
			


		} catch (JSONException e) 
		{

			e.printStackTrace();
		}
		System.out.println(newUser);
		return newUser;

	}
}
