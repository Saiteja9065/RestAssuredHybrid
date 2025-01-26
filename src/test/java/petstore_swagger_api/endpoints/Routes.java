package petstore_swagger_api.endpoints;

public class Routes {
    public static String base_url = "https://petstore.swagger.io/v2";

    //User module
    //here we are defining the routes for the user module, {username} is a path parameter
    public static String createUserUrl = base_url+"/user";
    public static String getUserUrl = base_url+"/user/{username}";
    public static String updateUserUrl = base_url+"/user/{username}";
    public static String deleteUserUrl = base_url+"/user/{username}";
}
