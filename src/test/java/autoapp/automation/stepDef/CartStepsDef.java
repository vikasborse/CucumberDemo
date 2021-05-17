package autoapp.automation.stepDef;

import autoapp.automation.pages.products.ProductsPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.Map;

public class CartStepsDef {

    public WebDriver driver;

    @Then("^I verify the product is added in cart$")
    public void iVerifyTheProductIsAddedInCart(DataTable dataTable) {

        List<Map<String,String>> products = dataTable.asMaps(String.class,String.class);

        //Verify all products are added to the cart
        for(Map product : products) {

            String productInfoString = ProductsPage.getProductInfoFromCart(product);
            System.out.println("Found product in cart:"+ productInfoString);
            System.out.println("Is color same as provided:"+ productInfoString.split(";")[1].contains(product.get("Color").toString()));
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(productInfoString.split(";")[0].equalsIgnoreCase(product.get("Name").toString())
            ,product.get("Name").toString()+ " does not equal " + productInfoString.split(";")[0] );
            softAssert.assertTrue(productInfoString.split(";")[1].contains(product.get("Color").toString())
            ,product.get("Color").toString()+ " does not equal " + productInfoString.split(";")[1]);
            softAssert.assertTrue(productInfoString.split(";")[2].equalsIgnoreCase(product.get("Quantity").toString())
            ,product.get("Quantity").toString()+ " does not equal " + productInfoString.split(";")[2]);
            softAssert.assertAll();

        }
    }
}
