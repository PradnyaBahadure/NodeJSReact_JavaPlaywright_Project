package utils;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class BaseClass {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;
    public static String baseURL = "";

    @BeforeEach
    public void setup(TestInfo testInfo) {
        boolean isUITest = testInfo.getTags().contains("ui");
        if (isUITest) {
            if (playwright == null) {
                playwright = Playwright.create();
                browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false)
                );
            }
            baseURL = "http://localhost:5000/login";
            context = browser.newContext();
            page = context.newPage();
        }
        else{
            RestAssured.baseURI = "http://localhost:5000";
        }

        ExtentTest test = ExtentManager.getExtentReports().createTest(testInfo.getDisplayName());
        ExtentManager.setTest(test);
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
        if (testInfo.getTags().contains("ui") && context != null) {
            context.close();
        }
        ExtentManager.flushReport();
    }

    @AfterAll
    public static void tearDownAll() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
