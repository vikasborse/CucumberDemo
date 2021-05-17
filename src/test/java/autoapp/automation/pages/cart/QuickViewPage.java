    package autoapp.automation.pages.cart;

    import autoapp.automation.pages.BasePage;
    import autoapp.automation.utility.BrowserDriver;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebElement;
    import java.util.List;

    public class QuickViewPage extends BasePage {

        public QuickViewPage(BrowserDriver driver) {
            super(driver);
        }

        public static String iFrame_xpath= "//iframe[contains(@id, 'fancybox-frame')]";
        public static String selectedItemName_xpath =  "//*[@id=\"product\"]/div/div/div[2]/h1";
        public static String selectedItemQuantity_id = "quantity_wanted";
        public static String availableColors_xpath = ".//ul[@id='color_to_pick_list']/li/a";
        public static String addToCartButton_xpath= "//*[@id=\"add_to_cart\"]/button";

        /**
         * getShoppingItemName
         * returns shopping item name from quick view page
         * @return
         */
        public static String getShoppingItemName() {
            String itemName = "";

            WebElement iframe = findElement(driver, By.xpath(iFrame_xpath));
            waitForElementToBePresent(driver, iframe,10);
            driver = switchToIframe(driver, iframe);
            System.out.println("The iframe is: " + iframe);

            itemName = findElement(driver, By.xpath(selectedItemName_xpath)).getText();
            return  itemName;
        }

        /**
         * getSelectedItemQuantityValue
         * returns shopping item quantity from quick view page
         * @return
         */
        public static String getSelectedItemQuantityValue() {
            WebElement element = findElement(driver, By.id(selectedItemQuantity_id));
            return getElementAttribute(element, "value");
        }

        /**
         * getAvailableItemColors
         * returns shopping item's available color's from quick view page
         * @return
         */
        public static String getAvailableItemColors() {

            StringBuilder stringBuilder = new StringBuilder();
            List<WebElement> availableColors = findElements(driver, By.xpath(availableColors_xpath));

            for(WebElement color: availableColors){
                stringBuilder.append( getElementAttribute(color, "name") +",");
            }

            return stringBuilder.toString();
        }

        /**
         * setItemColor
         * setting items color in quick view
         * @param color
         */
        public static  void setItemColor(String color){
            WebElement selectItemColor = findElement(driver, By.xpath(".//ul[@id='color_to_pick_list']/li/a[@name='"+color+"']"));
            clickButton(selectItemColor);
        }

        /**
         * clickAddToCartButton
         * Adds item to cart
         */
        public static void clickAddToCartButton() {
            WebElement addToCartButton = findElement(driver, By.xpath(addToCartButton_xpath));
            clickButton(addToCartButton);
        }


    }
