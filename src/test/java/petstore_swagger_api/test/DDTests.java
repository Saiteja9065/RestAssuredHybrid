package petstore_swagger_api.test;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import petstore_swagger_api.endpoints.UserEndPoints;
import petstore_swagger_api.payload.UserData;
import petstore_swagger_api.utils.DataProviders;

public class DDTests {

    public static final Logger log = LogManager.getLogger(DDTests.class);

    @Test(priority = 1, dataProvider = "userData", dataProviderClass = DataProviders.class)
    public void testCreateUser(String id, String username, String firstName, String lastName, String email, String password, String phone) {
        log.info("Creating user with username: {}", username);
        UserData userPayload = new UserData();
        userPayload.setId(Integer.parseInt(id));
        userPayload.setUsername(username);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
        log.info("User created successfully with username: {}", username);
    }

    @Test(priority = 2, dataProvider = "username", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String username) {
        log.info("Deleting user with username: {}", username);
        Response response = UserEndPoints.deleteUser(username);
        response.then().statusCode(200);
        Assert.assertEquals(response.statusCode(), 200);
        log.info("User deleted successfully with username: {}", username);
    }

}
