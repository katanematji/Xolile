import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/features",
        tags = "@searchProduct,@addToACart,@shopOnlineExclusiveDeals",
        glue = "stepDefinitions",
        plugin = {"pretty",
                "json:target/report/json/cucumber.json",
                "junit:target/report/runners.Runner.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class ProductsRunner {


}
