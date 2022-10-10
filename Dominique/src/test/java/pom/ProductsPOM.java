package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;
import java.util.List;

public class ProductsPOM {

    // Selenium Page Model and Page Factory can be used
    public String  shopMenu = "menu-category--706522961-heading";
    public String  onlineExclusiveDeals = "menu-column--2599970378-item-2-link";
    public String  searchBoxInput = "(//*[@id='search-box-input'])[1]";
    public String searchResults = "(//*[@id='product-list-container']//ul/li[contains(@class,'SearchBoxAutoSuggest_menu-item__H5XGf')])[1]";
    public String cookies = "//*[@id='onetrust-accept-btn-handler'][contains(.,'Accept All Cookies')]";
    public String seeDetails = "(//*[@id='product-list-container']//div[contains(@class,'col-xl-4 col-lg-4 col-md-4 col-sm-6')])[1]";
    public String seeDetailsScreen = "//*[@id='__next']/div[3]/div[2]/div[2]/div[1]/div[2]/div/div[1]/div[2][contains(@class,'DependencyHelper__CustomContainer-ws8fko-0 gaukmq ml-m flex-fill')]";
    public String productPrice = "//*[@id='__next']/div[3]/div[2]/div[2]/div[2]/div/div[1]/div[3]/div/div[contains(@class,'DependencyHelper__CustomContainer-ws8fko-0 gaukmq DealSpecifications_contract-box--price__2y4HR')]";
    public String contract = "//*[@id='__next']/div[3]/div[2]/div[2]/div[2]/div/div[1]/div[3]/div[contains(@class,'DependencyHelper__CustomContainer-ws8fko-0 gaukmq DealSpecifications_contract-box__2IfFl DealSpecifications_contract-box--active__rILKX col-xs-6 col-md-3')]";
    public String onlineExclusive = "//*[@id='tag_Online Exclusive']";
    public String getThisDealBtn = "//*[@id='__next']/div[3]/div[2]/div[2]/div[2]/div/div[1]/div[6]/div[contains(@class,'DealSpecifications_sticky_on_mobile__Du_PI')]";
    public String orderSummary = "/html/body/div[1]/div[1]/div/div/div[2]/div[1]/div/div/div[contains(@class,'middle_section')]";



}
