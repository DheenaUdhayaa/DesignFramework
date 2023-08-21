package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints {
	
	static ResourceBundle resource1()
	{
		ResourceBundle resource = ResourceBundle.getBundle("bundle");
		return resource;
	}
	
	public static Response createUser(UserPojo payload)
	{
		String post_url = resource1().getString("post_url");
		System.out.println(post_url);
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url)
			//.post(Routes.post_url);
		;
		return response;
	}
	
	public static Response readUser(String userName)
	{
		Response response = given()
				.pathParam("username",userName)
			.when()
				.get(Routes.get_url);
		return response;
	}
	
	public static Response updateUser(UserPojo payload,String userName)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			.pathParam("username",userName)
		.when()
			.put(Routes.update_url);
			
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
			.pathParam("username", userName)
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}	
}