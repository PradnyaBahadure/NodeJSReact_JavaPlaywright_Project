package apitests;

import org.junit.jupiter.api.*;
import utils.ApiUtils;
import utils.BaseClass;
import utils.ExtentManager;

import java.util.List;
import java.util.UUID;

@Tag("api")
public class LoginAPITest extends BaseClass {

    private static String username = "pradnyabahadure01@gmail.com";
    private static String password = "123456abc";

    @Test
    @DisplayName("Register New User")
    public void registerNewUser() throws Exception {
        String requestBody = """
        {
            "name": "Pradnya Test User",
            "email": "pradnyabahadure01@gmail.com",
            "password": "123456abc"
        }
        """;

        var response = ApiUtils.sendPostRequest("/api/users", requestBody, false, "register new user");

        // Validate response
        Assertions.assertEquals(201, response.statusCode(), "Expected status code 201");
        String token = response.jsonPath().getString("token");
        Assertions.assertNotNull(token, "Token should not be null");
        ExtentManager.getTest().pass("User created successfully. Token: " + token);
    }

    @Test
    @DisplayName("Validate Login API")
    public void validateLoginSuccess() throws Exception {
        var response = ApiUtils.loginAndStoreToken(username, password);

        Assertions.assertEquals(200, response.statusCode(), "Expected 200 status code");
        Assertions.assertNotNull(response.jsonPath().getString("token"), "Token should not be null");

        ExtentManager.getTest().pass("Login validated successfully.");
    }

    @Test
    @DisplayName("Validate Login API - Invalid User")
    public void invalidLoginShouldFail() throws Exception {
        // Use fake/incorrect credentials
        String fakeUsername = "nonexistinguser_" + UUID.randomUUID() + "@test.com";
        String fakePassword = "wrongPassword123";

        var response = ApiUtils.loginAndStoreToken(fakeUsername, fakePassword);

        Assertions.assertEquals(400, response.statusCode(), "Expected HTTP 400 for invalid login");

        String actualMessage = response.jsonPath().getString("message");
        Assertions.assertEquals("Invalid credentials", actualMessage, "Expected 'Invalid credentials' message");

        ExtentManager.getTest().pass("Login API correctly returned 400 with invalid credentials.");
    }

    @Test
    @DisplayName("Create new goal")
    public void createNewGoal() throws Exception {
        var loginResponse = ApiUtils.loginAndStoreToken(username, password);
        Assertions.assertEquals(200, loginResponse.statusCode(), "Login failed. Cannot proceed to create goal.");

        String body = """
        {
            "text": "Sample Task from Automation"
        }
        """;

        var createResponse = ApiUtils.sendPostRequest("/api/goals", body, true, "create new goal");
        Assertions.assertEquals(200, createResponse.statusCode(), "Expected status code 200 for goal creation.");

        String goalText = createResponse.jsonPath().getString("text");
        Assertions.assertEquals("Sample Task from Automation", goalText, "Goal text mismatch.");

        ExtentManager.getTest().pass("New goal created successfully.");
    }

    @Test
    @DisplayName("Get Existing Goals")
    public void getExistingGoals() throws Exception {
        var loginResponse = ApiUtils.loginAndStoreToken(username, password);
        Assertions.assertEquals(200, loginResponse.statusCode(), "Login failed. Cannot fetch goals.");

        var goalsResponse = ApiUtils.sendGetRequest("/api/goals", true, "get details of existing goal");
        Assertions.assertEquals(200, goalsResponse.statusCode(), "Failed to retrieve goals.");

        List<String> allGoals = goalsResponse.jsonPath().getList("text");
        Assertions.assertFalse(allGoals.isEmpty(), "No goals found.");
        Assertions.assertTrue(allGoals.contains("Sample Task from Automation"),
                "Expected goal not found in list: " + allGoals);

        ExtentManager.getTest().pass("Got the info of created goal successfully.");
    }

    @Test
    @DisplayName("Update existing goal")
    public void updateExistingGoal() throws Exception {
        var response = ApiUtils.loginAndStoreToken(username, password);
        Assertions.assertEquals(200, response.statusCode());

        response = ApiUtils.sendGetRequest("/api/goals", true, "get details of existing goal");
        Assertions.assertEquals(200, response.statusCode());

        String actualMessage = response.jsonPath().getString("[0].text");
        Assertions.assertEquals("Sample Task from Automation", actualMessage);

        String id = response.jsonPath().getString("[0]._id").trim();
        System.out.println("Goal id : " + id);

        String body = """
        {
            "text": "Sample Task from Automation Script"
        }
        """;

        var newresponse = ApiUtils.sendPutRequest("/api/goals/" + id, body, true, "update goal name");
        Assertions.assertEquals(200, newresponse.statusCode());

        String goalName = newresponse.jsonPath().getString("text");
        Assertions.assertEquals("Sample Task from Automation Script", goalName);

        ExtentManager.getTest().pass("Updated and verified goal successfully.");
    }

    @Test
    @DisplayName("Delete existing goal")
    public void deleteExistingGoal() throws Exception {
        var response = ApiUtils.loginAndStoreToken(username, password);
        Assertions.assertEquals(200, response.statusCode());

        response = ApiUtils.sendGetRequest("/api/goals", true, "get details of existing goal");
        Assertions.assertEquals(200, response.statusCode());
        String goalText = response.jsonPath().getString("[0].text");
        Assertions.assertEquals("Sample Task from Automation Script", goalText);

        String id = response.jsonPath().getString("[0]._id").trim();
        System.out.println("Goal ID: " + id);

        var deleteResponse = ApiUtils.sendDeleteRequest("/api/goals/" + id, true, "delete existing goal");
        Assertions.assertEquals(200, deleteResponse.statusCode());

        String deletedId = deleteResponse.jsonPath().getString("id").trim();
        System.out.println("Deleted Goal ID: " + deletedId);
        Assertions.assertEquals(id, deletedId);

        ExtentManager.getTest().pass("Goal deleted successfully.");
    }
}
