    package autoapp.automation.stepDef;

    import autoapp.automation.pages.products.ProductsPage;
    import cucumber.api.DataTable;
    import cucumber.api.java.en.Then;
    import java.util.List;
    import java.util.Map;

    public class ProductStepDef {


        @Then("^I add product to cart from Quick View$")
        public void iSelectADressInQuickView(DataTable dataTable) {

            List<Map<String,String>> products = dataTable.asMaps(String.class,String.class);
            // Add all provided products to the cart from Quick View
            for(Map product : products) {
                ProductsPage.addProductToCartFromQuickView(product);
            }
        }

    }
