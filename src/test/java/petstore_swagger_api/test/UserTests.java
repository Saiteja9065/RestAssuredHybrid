package petstore_swagger_api.test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import petstore_swagger_api.endpoints.UserEndPoints;
import petstore_swagger_api.payload.UserData;


public class UserTests {

    public static Logger log = LogManager.getLogger("UserTests.class");

    Faker faker;
    UserData userPayload;

    @BeforeClass
    public void setUpData(){
        faker = new Faker();
        userPayload = new UserData();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testCreateUser(){
        log.info("Creating user with username: {}", userPayload.getUsername());
        //create user
        Response response = UserEndPoints.createUser(userPayload);
        response.then().statusCode(200);
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        log.info("User created successfully with username: {}", userPayload.getUsername());

    }

    @Test(priority = 2)
    public void testGetUserbyUsername(){
        log.info("Getting user with username: {}", userPayload.getUsername());
        //get user by username
        Response response = UserEndPoints.getUser(userPayload.getUsername());
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(),200);
        log.info("User retrieved successfully with username: {}", userPayload.getUsername());
    }

    @Test(priority = 3)
    public void testUpdateUser(){
        log.info("Updating user with username: {}", userPayload.getUsername());
        //update user
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);

        Assert.assertEquals(response.statusCode(),200);
        log.info("User updated successfully with username: {}", userPayload.getUsername());
    }

    @Test(priority = 4)
        public void testDeleteUser(){
            //delete user
            log.info("Deleting user with username: {}", userPayload.getUsername());
            Response response = UserEndPoints.deleteUser(userPayload.getUsername());
            Assert.assertEquals(response.statusCode(),200);
            log.info("User deleted successfully with username: {}", userPayload.getUsername());
        }
    }

