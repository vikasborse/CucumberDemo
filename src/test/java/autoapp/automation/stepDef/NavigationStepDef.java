    package autoapp.automation.stepDef;

    import autoapp.automation.pages.navigation.NavigationPage;
    import cucumber.api.DataTable;
    import cucumber.api.java.en.Then;
    import org.testng.Assert;
    import org.testng.asserts.SoftAssert;

    import java.util.List;
    import java.util.Map;

    public class NavigationStepDef {


        @Then("^I verify the mega menu is working$")
        public void iVerifyTheMegaMenuIsWorking(DataTable dataTable) {
            List<Map<String,String>> megaMenus = dataTable.asMaps(String.class,String.class);
            // Add all provided products to the cart from Quick View
            SoftAssert softAssert = new SoftAssert();
            for(Map menu : megaMenus) {
               boolean menuOpens = NavigationPage.verifyMegaMenuIsWorking(menu);
                softAssert.assertTrue(menuOpens, "Menu "+ menu.get("Menu") + " is not working!");
            }

            softAssert.assertAll();
        }



    }
