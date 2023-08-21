package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTestForUser {
	public Logger logger;
	
	@BeforeClass
	public void log()
	{
		logger=LogManager.getLogger(this.getClass());
		
	}
	@Test(priority=1,dataProvider ="Data",dataProviderClass = DataProviders.class)
	public void createUsertest(String UserId,String Usernaem,String firstName,String lastName,String email,String pwd,String phone)
	{
		logger.info("..........Creating the user.......");
		
		UserPojo pojo = new UserPojo();
		
		pojo.setEmail(email);
		pojo.setFirstName(firstName);
		pojo.setId(Integer.parseInt(UserId));
		pojo.setLastName(lastName);
		pojo.setPassword(pwd);
		pojo.setPhone(phone);
		pojo.setUsername(Usernaem);
		
		Response response = UserEndPoints.createUser(pojo);
		response.then().log().all();
		response.then().statusCode(200);
		
		Response res = UserEndPoints.readUser(pojo.getUsername());
		res.then().log().body();
		res.then().statusCode(200);		
		logger.info("..........User created.......");
		
	}
	
	@Test(priority=2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void deleteUserTest(String name)
	{	
		logger.info("..........Deleting the user.......");
	
		Response response = UserEndPoints.deleteUser(name);
		response.then().log().body();
		response.then().statusCode(200);
		logger.info("..........User deleted.......");
		
	}

}
