    package autoapp.automation.stepDef;

    import autoapp.automation.pages.navigation.NavigationPage;
    import autoapp.automation.pages.registration.PersonalInfoPage;
    import autoapp.automation.pages.products.ProductsPage;
    import autoapp.automation.pages.registration.RegisterPage;
    import cucumber.api.DataTable;
    import cucumber.api.java.en.And;
    import cucumber.api.java.en.Given;
    import cucumber.api.java.en.Then;
    import cucumber.api.java.en.When;
    import org.testng.asserts.SoftAssert;
    import java.util.List;
    import java.util.Map;

    public class RegisterStepDef {

        @Given("^Automation practice application is opened$")
        public void automation_practice_application_is_opened() throws Throwable {
            RegisterPage.openApplicaiton();
        }

        @When("^I click sign in$")
        public void i_click_sign_in() throws Throwable {
            RegisterPage.clickSignIn();
        }

        @And("^I create account with emailid \"([^\"]*)\"$")
        public void i_create_account_with_emailid(String emailId) throws Throwable {
            RegisterPage.createAccount(emailId);
            Thread.sleep(5000);
        }


        @Then("^I should be able to register with my below details$")
        public void i_should_be_able_to_register_with_my_below_details(DataTable dataTable) throws Throwable {
            PersonalInfoPage.enterPersonalInformation(dataTable);
            Thread.sleep(5000);
        }


        @Then("^I verify the invalid email address shows expected errors$")
        public void iVerifyTheInvalidEmailAddressShowsExpectedErrors(DataTable dataTable) {

            SoftAssert softAssert =  new SoftAssert();

            List<Map<String,String>> emailAddresses = dataTable.asMaps(String.class,String.class);
            for(Map emailAddressMap : emailAddresses){
                //Verify each email address with expected errors
                String emailAddress = emailAddressMap.get("EmailAddress").toString();
                String emailAddressErrors = emailAddressMap.get("ExpectedErrors").toString();
                String errorsText = RegisterPage.verifyCreateAccountErrors(emailAddress);
                softAssert.assertTrue(errorsText.contains(emailAddressErrors),
                        "Email address "+ emailAddress+ " should show errors as :"+emailAddressErrors +", but actual errors are :"+errorsText);
            }

            //Assert all validations
            softAssert.assertAll();
        }




    }
