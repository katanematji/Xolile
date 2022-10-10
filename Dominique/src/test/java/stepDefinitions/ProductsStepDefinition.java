package stepDefinitions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pom.ProductsPOM;
import utils.Utilities;


import static freemarker.template.utility.SecurityUtilities.getSystemProperty;


public class ProductsStepDefinition {
    Utilities utilities = new Utilities();
    WebDriver webDriver;
    static ExtentTest extentTest;
    static ExtentReports extentReport;
    ProductsPOM productsPOM = new ProductsPOM();

    @Given("^Land on the site$")
    public void landOnTheSite() throws Exception {

        webDriver = utilities.initializeWebDriver();
        if (extentReport == null) {
            extentReport = new ExtentReports("target/report/extent/productWebReport.html");
        }
        extentTest = extentReport.startTest("Product dashboard");
        //Url can be stored in a config file, in this case we will define it here...
        webDriver.get("https://vodacom.co.za");
        Assert.assertEquals("Cellphone Deals | Vodacom", webDriver.getTitle());

        extentTest.log(LogStatus.PASS, "Landed Successfully");
    }


    @When("I can search product item {string}")
    public void iCanSearchProductItem(String productItem) throws Exception {

        extentTest = extentReport.startTest("Search Items");
        webDriver.findElement(By.id(productsPOM.shopMenu)).isDisplayed();
        webDriver.findElement(By.id(productsPOM.shopMenu)).click();

        webDriver.findElement(By.id(productsPOM.onlineExclusiveDeals)).isDisplayed();
        webDriver.findElement(By.id(productsPOM.onlineExclusiveDeals)).click();

        Thread.sleep(5000);
        webDriver.findElement(By.xpath(productsPOM.cookies)).isDisplayed();
        webDriver.findElement(By.xpath(productsPOM.cookies)).click();

        webDriver.findElement(By.xpath(productsPOM.searchBoxInput)).isDisplayed();

        webDriver.findElement(By.xpath(productsPOM.searchBoxInput)).sendKeys(productItem);
        Thread.sleep(3000);
        webDriver.findElement(By.xpath(productsPOM.searchResults)).isDisplayed();
        webDriver.findElement(By.xpath(productsPOM.searchResults)).click();

        webDriver.navigate().refresh();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath(productsPOM.seeDetails)).isDisplayed();
        webDriver.findElement(By.xpath(productsPOM.seeDetails)).click();
        Thread.sleep(5000);

        extentTest.log(LogStatus.PASS, "Searches Item Successfully");
    }

    @Then("I can assert the search results {string}")
    public void iCanAssertTheSearchResults(String productItem)  {
        extentTest = extentReport.startTest("See details");

        webDriver.findElement(By.xpath(productsPOM.seeDetailsScreen)).isDisplayed();
        assertContains("Search Product Results",productItem,webDriver.findElement(By.xpath(productsPOM.seeDetailsScreen)).getText());

        extentTest.log(LogStatus.PASS, "Searches the deal Successfully");
    }

    @And("I can validate the deal {string} {string} {string}")
    public void iCanValidateTheDeal(String price, String contract, String availableOnline) {

        extentTest = extentReport.startTest("Contract Details");

        assertContains("Validate Product Price",price,webDriver.findElement(By.xpath(productsPOM.productPrice)).getText());
        assertContains("Validate Product Contract",contract,webDriver.findElement(By.xpath(productsPOM.contract)).getText());
        assertContains("Validate Product Online",availableOnline,webDriver.findElement(By.xpath(productsPOM.onlineExclusive)).getText());

        extentTest.log(LogStatus.PASS, "Validated the deal Successfully");
    }

    @Then("I can get the deal and validate order summary {string} {string} {string}")
    public void iCanGetTheDealAndValidateOrderSummary(String device, String plan, String contract) throws InterruptedException {

        extentTest = extentReport.startTest("Order Summary");

        webDriver.findElement(By.xpath(productsPOM.getThisDealBtn)).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath(productsPOM.orderSummary)).isDisplayed();
        // You validate the order summary elements the same way you validated on See Details Screen

        extentTest.log(LogStatus.PASS, "Validated Order Summary Successfully");

    }



    @After
    public void afterTest(Scenario scenario) {
        if (webDriver != null) {
            try {
                //Screenshot at the end of the test and when there is a failure
                JavascriptExecutor jse = (JavascriptExecutor) webDriver;
                jse.executeScript("document.body.style.zoom = '50%';");
                Utilities webDriverUtil = new Utilities();
                String fileName = webDriverUtil.takeScreenshot(webDriver);
                if (scenario.isFailed()) {
                    extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(fileName));
                    if (getSystemProperty("Exception") != null) {
                        extentTest.log(LogStatus.ERROR, getSystemProperty("Exception"));
                    }
                } else {
                    extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(fileName));
                }
                webDriver.close();
                if ("Mac OS X".equals(System.getProperty("os.name"))) {

                    Runtime.getRuntime().exec("killall chromedriver");
                } else {
                    Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
                }
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            } finally {
                extentReport.endTest(extentTest);
                extentReport.flush();
            }
        }
    }

    // We can always create helper methods class and have all these types of functions there...
    public void assertContains(String fieldName, String expected, String actual) {
        if (!actual.contains(expected)) {
            System.setProperty("Exception","Expected " + fieldName + " was " + expected + ": actual result is: " + actual);
            Assert.fail("Expected " + fieldName + " was " + expected + ": actual result is: " + actual);
        }
    }



}
