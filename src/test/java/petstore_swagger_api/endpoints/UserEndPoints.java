package petstore_swagger_api.endpoints;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import petstore_swagger_api.payload.UserData;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    public static Response createUser(UserData payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.createUserUrl);
        return response;
    }

    public static Response getUser(String username){
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(Routes.getUserUrl);
        return response;
    }

    public static Response updateUser(String username, UserData payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(Routes.updateUserUrl);
        return response;
    }

    public static Response deleteUser(String username){
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(Routes.deleteUserUrl);
        return response;
    }
}
