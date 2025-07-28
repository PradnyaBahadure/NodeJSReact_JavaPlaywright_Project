package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotType;
import java.util.Base64;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setDocumentTitle("Playwright Automation Report");
            spark.config().setReportName("E2E Test Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }

    public static MediaEntityBuilder attachScreenshotAsBase64(Page page) {
        byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true)
                .setType(ScreenshotType.PNG));
        String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);
        return MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot);
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
