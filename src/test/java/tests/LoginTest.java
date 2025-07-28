package tests;
import org.junit.jupiter.api.Order;
import utils.BaseClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import java.util.UUID;

@Tag("ui")
public class LoginTest extends BaseClass {
    private static String username = "pradnyabahadure02@gmail.com";
    private static String password = "123456abc";

    @Test
    @Order(1)
    @DisplayName("Register New User Test")
    void RegisterUser() throws Exception{
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLogin();
        loginPage.registerUser("Pradnya", username, password);
        loginPage.logout();
    }

    @Test @Tag("smoke")
    @DisplayName("Validate Login Test")
    void ValidateLogin() throws Exception{
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLogin();
        loginPage.login(username, password);
        loginPage.logout();
    }

    @Test @Tag("smoke")
    @DisplayName("Validate Invalid Login Test")
    void ValidateInValidLogin() throws Exception{
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLogin();

        // Use fake/incorrect credentials
        String fakeUsername = "nonexistinguser_" + UUID.randomUUID() + "@test.com";
        String fakePassword = "wrongPassword123";

        loginPage.invalidlogin(fakeUsername, fakePassword);
    }

    @Test @Tag("smoke")
    @DisplayName("Create Goal Test")
    void CreateGoal() throws Exception{
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLogin();
        loginPage.login(username, password);
        loginPage.createGoal();
        loginPage.logout();
    }

    @Test
    @DisplayName("Delete Goal Test")
    void DeleteGoal() throws Exception{
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLogin();
        loginPage.login(username, password);
        loginPage.deleteGoal();
        loginPage.logout();
    }
}
