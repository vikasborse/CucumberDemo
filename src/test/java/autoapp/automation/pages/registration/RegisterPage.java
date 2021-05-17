    package autoapp.automation.pages.registration;

    import autoapp.automation.pages.BasePage;
import autoapp.automation.utility.BrowserDriver;
import org.openqa.selenium.By;


    public class RegisterPage extends BasePage {

        public static String signIn_xpath = "//a[@title='Log in to your customer account']";
        public static String email_id = "email_create";
        public static String createAccount_xpath = "//form[@id='create-account_form']//span[1]";
        public static String create_account_error_id = "create_account_error";

        public RegisterPage(BrowserDriver driver) {
            super(driver);
        }


        public static void openApplicaiton() {
            String url = getParamFromProperties("app.url");
            System.out.println("URL:"+url);

            driver.navigate().to(url);
            driver.manage().window().maximize();
        }



        public static void clickSignIn() {
            driver.findElement(By.xpath(signIn_xpath)).click();
        }

        public static void createAccount(String emailId) {
            emailId = emailId.replaceAll("Random", Integer.toString(generateRandomIntIntRange(00000, 99999)));
            driver.findElement(By.id(email_id)).sendKeys(emailId);
            driver.findElement(By.xpath(createAccount_xpath)).click();
        }

        public static String verifyCreateAccountErrors(String emailId) {

            driver.findElement(By.id(email_id)).clear();
            driver.findElement(By.id(email_id)).sendKeys(emailId);
            driver.findElement(By.xpath(createAccount_xpath)).click();

            waitForElementToBeVisible(driver, By.id(create_account_error_id), TIME_OUT_IN_SECONDS);

            return driver.findElement(By.id(create_account_error_id)).getText();
        }




    }
