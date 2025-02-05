package petstore_swagger_api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import petstore_swagger_api.payload.UserData;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPointsTwo {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }
    public static Response createUser(UserData userPayload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userPayload)
                .when()
                .post(getURL().getString("post_url"));
        return response;
    }

    public static Response getUser(String username){
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(getURL().getString("get_url"));
        return response;
    }

    public static Response updateUser(String username, UserData userPayload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(userPayload)
                .when()
                .put(getURL().getString("put_url"));
        return response;
    }

    public static Response deleteUser(String username){
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(getURL().getString("delete_url"));
        return response;
    }
}
