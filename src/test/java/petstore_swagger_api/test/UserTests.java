package petstore_swagger_api.test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import petstore_swagger_api.endpoints.UserEndPoints;
import petstore_swagger_api.payload.UserData;

public class UserTests {

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
        //create user
        Response response = UserEndPoints.createUser(userPayload);
        response.then().statusCode(200);
        response.then().log().all();
        response.prettyPrint();

    }
}
