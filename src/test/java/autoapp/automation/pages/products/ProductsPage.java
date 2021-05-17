    package autoapp.automation.pages.products;

    import autoapp.automation.pages.BasePage;
    import autoapp.automation.pages.cart.QuickViewPage;
    import autoapp.automation.utility.BrowserDriver;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.interactions.Actions;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;
    import java.util.List;
    import java.util.Map;
    import java.util.concurrent.TimeUnit;

    public class ProductsPage extends BasePage {

        public ProductsPage(BrowserDriver driver) {
            super(driver);
        }

        public static String productList_xpath = "//*[@id=\"center_column\"]/ul/li";
        public static String productQuickView_xpath =  ".//div/div[1]/div/a[1]/img";
        public static String availableColors_xpath =  ".//../parent::div/parent::div/following-sibling::div/div[3]//a";
        public static String close_xpath =  ".//a[@title='Close']";

        /**
         * addProductToCartFromQuickView
         * Adds all the provided products to the cart from quick view
         * @param product
         */
        public static void addProductToCartFromQuickView(Map<String,String> product){

            driver.manage().timeouts().implicitlyWait(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);

            List<WebElement> elements = driver.findElements(By.xpath(productList_xpath));
            int totalProducts = elements.size();
            System.out.println("Total products found: "+totalProducts);

            for(int productNumber= 1;productNumber< totalProducts+1; productNumber++){
                WebElement element = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li["+productNumber+"]/div/div[1]/div/a[1]/img"));
                String productName = element.getAttribute("title");
                String availableColors = getAvailableItemColors(element);
                System.out.println("Product "+ productNumber+": " );
                System.out.println("Product Name: "+ productNumber+": " + productName);
                System.out.println("Available color's: "+ availableColors);

                if(productName.equalsIgnoreCase(product.get("Name")) &&
                        availableColors.toLowerCase().contains(product.get("Color").toLowerCase())
                ){
                    System.out.println("Opening product [ "+productName+" ] with available color [ "+ product.get("Color") + " ] in Quick View.");
                    WebDriverWait wait = new WebDriverWait(driver, 5);
                    wait.until(ExpectedConditions.elementToBeClickable(element));
                    if (element.isEnabled()) {
                        Actions actions = new Actions(driver);
                        actions.moveToElement(element).click().build().perform();
                    } else {
                        System.out.print("Product's quick view is not clickable");
                    }


                    System.out.println("Selected item name:"+ QuickViewPage.getShoppingItemName());
                    System.out.println("Selected item quantity:"+QuickViewPage.getSelectedItemQuantityValue());
                    String colours = QuickViewPage.getAvailableItemColors();
                    System.out.println("Selected item's available colors:"+ colours);

                    if(colours.contains(product.get("Color"))){
                        //select the color
                        System.out.println("Setting item's color:"+ product.get("Color"));
                        QuickViewPage.setItemColor(product.get("Color"));
                    }else{
                        System.err.println("Color "+ product.get("Color") +" not found");
                    }

                    QuickViewPage.clickAddToCartButton();
                    break;

                }

            }

        }

        /**
         * getAvailableItemColors
         * @param element
         * @return available colors for product on Product list page
         */
        public static String getAvailableItemColors(WebElement element) {

            StringBuilder stringBuilder = new StringBuilder();
            List<WebElement> availableColors = element.findElements(By.xpath(availableColors_xpath));
            for(WebElement color: availableColors){
                stringBuilder.append( getElementAttribute(color, "href").split("color-")[1] +",");
            }
            return stringBuilder.toString();
        }


        /**
         * getProductInfoFromCart
         * @param product
         * @return List of product attributes such as name , quantity, color , size from Cart page.
         */
        public static String getProductInfoFromCart(Map<String,String> product){

            List<WebElement> productInfo = driver.findElements(By.xpath("//img[@title='"+product.get("Name")+"']/parent::div/following-sibling::div//span"));

            StringBuilder productInfoStringBuilder = new StringBuilder();
            for(WebElement webElement: productInfo){

                productInfoStringBuilder.append(webElement.getText() + ";");
            }
            System.out.println( "ProductInfo: "+ productInfoStringBuilder.toString() );

            return productInfoStringBuilder.toString();

        }



    }
