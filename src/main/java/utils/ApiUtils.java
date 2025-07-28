package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ApiUtils {
    public static String token = null;

    public static Response sendPostRequest(String endpoint, String body, boolean authRequired, String message) {
        try {
            var request = given()
                    .contentType("application/json")
                    .body(body);

            if (authRequired && token != null) {
                request.header("Authorization", "Bearer " + token);
            }
            ExtentManager.getTest().pass("POST method to " + message + "sent successfully");
            return request.when().post(endpoint);
        } catch (Exception e) {
            ExtentManager.getTest().fail("API Step failed: " + e.getMessage());
            throw e;
        }
    }

    public static Response sendGetRequest(String endpoint, boolean authRequired, String message) {
        try {
            var request = given()
                    .contentType("application/json");

            if (authRequired && token != null) {
                request.header("Authorization", "Bearer " + token);
            }
            ExtentManager.getTest().pass("GET method to " + message + "sent successfully");
            return request.when().get(endpoint);
        } catch (Exception e) {
            ExtentManager.getTest().fail("API Step failed: " + e.getMessage());
            throw e;
        }
    }

    public static Response sendPutRequest(String endpoint, String body, boolean authRequired, String message) {
        try {
            //System.out.println("endpoint : "+endpoint);
            var request = given()
                    .contentType("application/json")
                    .body(body);

            if (authRequired && token != null) {
                request.header("Authorization", "Bearer " + token);
            }
            ExtentManager.getTest().pass("PUT method to " + message + "sent successfully");
            return request.when().put(endpoint);
        } catch (Exception e) {
            ExtentManager.getTest().fail("API Step failed: " + e.getMessage());
            throw e;
        }
    }

    public static Response sendDeleteRequest(String endpoint, boolean authRequired, String message) {
        try {
            var request = given()
                    .contentType("application/json");

            if (authRequired && token != null) {
                request.header("Authorization", "Bearer " + token);
            }
            ExtentManager.getTest().pass("DELETE method to " + message + "sent successfully");
            return request.when().delete(endpoint);
        } catch (Exception e) {
            ExtentManager.getTest().fail("API Step failed: " + e.getMessage());
            throw e;
        }
    }

    public static Response loginAndStoreToken(String email, String password) {
        try {
            String loginBody = """
                {
                    "email": "%s",
                    "password": "%s"
                }
                """.formatted(email, password);
            //System.out.println(baseURI);
            Response response = sendPostRequest("/api/users/login", loginBody, false, "login to application");
            if (response.statusCode() == 200) {
                token = response.jsonPath().getString("token");
            } else {
                System.out.println("Login failed: " + response.body().asString());
            }
            ExtentManager.getTest().pass("Signed in successfully to the application");
            return response;
        }  catch (Exception e) {
            ExtentManager.getTest().fail("API Step failed: " + e.getMessage());
            throw e;
        }
    }
}
