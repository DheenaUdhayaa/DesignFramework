package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	UserPojo payload;
	public Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		faker = new Faker();
		payload = new UserPojo();
				
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setPassword(faker.internet().password(6, 8, true,true));
		payload.setPhone(faker.phoneNumber().phoneNumber());
		
		logger = LogManager.getLogger(this.getClass());
		
		
	}
	
	@Test (priority=1)
	public void testpostUser()
	{
		logger.info("..........Creating the user.......");
		Response response = UserEndPoints.createUser(payload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("..........User created.......");
	}
	
	@Test (priority=2)
	public void testUserDetails()
	{
		logger.info("..........Fetching the userDetails.......");
		
		String userNamee=this.payload.getUsername();
		Response response = UserEndPoints.readUser(userNamee);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getHeader("Content-Type"),"application/json");
		logger.info("..........Validating Schema.......");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserDetails.json"));
		logger.info("..........Schema Validated.......");
		logger.info("..........Details fetched.......");
		
	}
	 
	@Test(priority=3)
	public void testUpdate()
	{
		logger.info("..........Updating the user.......");
		
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setPhone(faker.phoneNumber().phoneNumber());
		
		
		Response response = UserEndPoints.updateUser(payload,this.payload.getUsername());
		response.then().log().all();
		
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("..........User updated.......");
		
		
		Response responsee = UserEndPoints.readUser(this.payload.getUsername());
		responsee.then().log().body();
		Assert.assertEquals(responsee.getStatusCode(),200);
		
		logger.info("..........Updated User Details.......");
		
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info(".........deleting the user.......");
		
		Response response = UserEndPoints.deleteUser(this.payload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("..........User Deleted.......");
		
	}
}