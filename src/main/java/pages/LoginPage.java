package pages;

import com.microsoft.playwright.ConsoleMessage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.BaseClass;
import utils.ExtentManager;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    private final Page page;

    // Locators
    private final String emailInput = "//*[@placeholder='Email']";
    private final String passwordInput = "//*[@placeholder='Password']";
    private final String loginButton = "//*button[@type='submit']";
    //private final String location = "//*button[@type='submit']";

    // Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLogin() throws Exception{
        try{
            page.navigate(BaseClass.baseURL);
            ExtentManager.getTest().pass("Navigated to Login Page");
        }
        catch (Exception e) {
            Thread.sleep(500);
            ExtentManager.getTest().fail("Step failed: " + e.toString(), ExtentManager.attachScreenshotAsBase64(page).build());
            throw new Exception();
        }
    }

    public void registerUser(String Username, String email, String password) throws Exception{
        try {
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register")).click();
            page.getByPlaceholder("Enter your name").click();
            page.getByPlaceholder("Enter your name").fill(Username);
            page.getByPlaceholder("Enter your email").click();
            page.getByPlaceholder("Enter your email").fill(email);
            page.getByPlaceholder("Enter password").click();
            page.getByPlaceholder("Enter password").fill(password);
            page.getByPlaceholder("Confirm password").click();
            page.getByPlaceholder("Confirm password").fill(password);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            assertThat(page.locator("h1")).containsText("Welcome Pradnya");
            ExtentManager.getTest().pass("User registered successfully.");
        }
        catch (Exception e) {
            Thread.sleep(500);
            ExtentManager.getTest().fail("Step failed: " + e.toString(), ExtentManager.attachScreenshotAsBase64(page).build());
            throw new Exception();
        }
    }

    public void login(String email, String password) throws Exception{
        try {
            System.out.println("Current URL: " + page.url());
            assertThat(page.getByRole(AriaRole.HEADING)).containsText("Login");
            page.getByPlaceholder("Enter your email").click();
            page.getByPlaceholder("Enter your email").fill(email);
            page.getByPlaceholder("Enter password").click();
            page.getByPlaceholder("Enter password").fill(password);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            assertThat(page.locator("h1")).containsText("Welcome Pradnya");
            page.getByText("Goals Dashboard").click();
            assertThat(page.getByRole(AriaRole.PARAGRAPH)).containsText("Goals Dashboard");
            ExtentManager.getTest().pass("Signed in to goal setter app successfully.");
        }
        catch (Exception e) {
            Thread.sleep(500);
            ExtentManager.getTest().fail("Step failed: " + e.toString(), ExtentManager.attachScreenshotAsBase64(page).build());
            throw new Exception();
        }
    }

    public void invalidlogin(String email, String password) throws Exception{
        try {
            System.out.println("Current URL: " + page.url());
            assertThat(page.getByRole(AriaRole.HEADING)).containsText("Login");
            page.getByPlaceholder("Enter your email").click();
            page.getByPlaceholder("Enter your email").fill(email);
            page.getByPlaceholder("Enter password").click();
            page.getByPlaceholder("Enter password").fill(password);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            assertThat(page.getByRole(AriaRole.ALERT)).containsText("Invalid credentials");
            ExtentManager.getTest().pass("Login failed due to invalid credentials");
        }
        catch (Exception e) {
            Thread.sleep(500);
            ExtentManager.getTest().fail("Step failed: " + e.toString(), ExtentManager.attachScreenshotAsBase64(page).build());
            throw new Exception();
        }
    }

    public void logout() throws Exception{
        try {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
            assertThat(page.getByRole(AriaRole.HEADING)).containsText("Login");
            ExtentManager.getTest().pass("Signed out from goal setter app successfully.");
        }
        catch (Exception e) {
            Thread.sleep(500);
            ExtentManager.getTest().fail("Step failed: " + e.toString(), ExtentManager.attachScreenshotAsBase64(page).build());
            throw new Exception();
        }
    }

    public void createGoal() throws Exception{
        try{
            page.getByLabel("Goal").fill("Get selected in REM Waste");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add Goal")).click();
            System.out.println("Goal created successfully.");
            assertThat(page.locator("h2")).containsText("Get selected in REM Waste");
            ExtentManager.getTest().pass("Goal created successfully.");
        }
        catch (Exception e) {
            Thread.sleep(500);
            ExtentManager.getTest().fail("Step failed: " + e.toString(), ExtentManager.attachScreenshotAsBase64(page).build());
            throw new Exception();
        }
    }

    public void deleteGoal() throws Exception{
        try{
            assertThat(page.locator("h2")).containsText("Get selected in REM Waste");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("X")).click();
            System.out.println("Goal deleted successfully.");
            ExtentManager.getTest().pass("Goal deleted successfully.");
        }
        catch (Exception e) {
            Thread.sleep(500);
            ExtentManager.getTest().fail("Step failed: " + e.toString(), ExtentManager.attachScreenshotAsBase64(page).build());
            throw new Exception();
        }
    }
}
