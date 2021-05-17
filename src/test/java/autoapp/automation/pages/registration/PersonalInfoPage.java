package autoapp.automation.pages.registration;

import autoapp.automation.pages.BasePage;
import autoapp.automation.utility.BrowserDriver;
import cucumber.api.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class PersonalInfoPage extends BasePage {

    public static String Mr_Id = "id_gender1";
    public static String Mrs_Id = "id_gender2";
    public static String firstname_Id = "customer_firstname";
    public static String lastname_Id = "customer_lastname";
    public static String password_Id = "passwd";
    public static String address_Id = "address1";
    public static String city_Id = "city";
    public static String state_Id = "id_state";
    public static String postcode_Id = "postcode";
    public static String mobile_Id = "phone_mobile";
    public static String register_xpath = "//span[contains(text(),'Register')]";

    public PersonalInfoPage(BrowserDriver driver){
        super(driver);
    }

    public static void enterPersonalInformation(DataTable dataTable){
        List<Map<String,String>> personalInfo = dataTable.asMaps(String.class,String.class);

        if(personalInfo.get(0).get("Gender") == "Mr"){
            driver.findElement(By.id(Mr_Id)).click();
        }else { driver.findElement(By.id(Mrs_Id)).click();}

        driver.findElement(By.id(firstname_Id)).sendKeys(personalInfo.get(0).get("FirstName"));
        driver.findElement(By.id(lastname_Id)).sendKeys(personalInfo.get(0).get("LastName"));
        driver.findElement(By.id(password_Id)).sendKeys(personalInfo.get(0).get("Password"));
        driver.findElement(By.id(address_Id)).sendKeys(personalInfo.get(0).get("Address"));
        driver.findElement(By.id(city_Id)).sendKeys(personalInfo.get(0).get("City"));
        Select state = new Select(driver.findElement(By.id(state_Id)));
        state.selectByVisibleText(personalInfo.get(0).get("State"));
        driver.findElement(By.id(postcode_Id)).sendKeys(personalInfo.get(0).get("Zip"));
        driver.findElement(By.id(mobile_Id)).sendKeys(personalInfo.get(0).get("Mobile"));

        driver.findElement(By.xpath(register_xpath)).click();
    }
}
