    package autoapp.automation.pages.navigation;

    import autoapp.automation.pages.BasePage;
    import autoapp.automation.utility.BrowserDriver;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.interactions.Actions;

    import java.util.List;
    import java.util.Map;


    public class NavigationPage extends BasePage {

        public NavigationPage(BrowserDriver driver) {
            super(driver);
        }

        /**
         * verifyMegaMenuIsWorking
         * Verifies the mega menus are working
         * @param megaMenu
         *  @return true |false
         */
        public static boolean verifyMegaMenuIsWorking(Map<String, String> megaMenu) {

            boolean menuOpens = false;
            //Maximise browser window
            driver.manage().window().maximize();

            String[] menus = megaMenu.get("Menu").split("/");
            if(menus.length>1) {
                System.out.println(menus[0] + " / " + menus[1]);
                WebElement div = findElement(driver, By.id("block_top_menu"));

                WebElement menuItem = null;
                List<WebElement> elements = div.findElements(By.xpath(".//ul//li"));
                for (WebElement webElement1 : elements) {

                    if (webElement1.getText().equalsIgnoreCase(menus[0])) {
                        System.out.println("Clicking menu [ "+ menus[0] +" ]");
                        //Instantiate Action Class
                        Actions actions = new Actions(driver);
                        actions.moveToElement(webElement1).perform();
                        menuItem = webElement1;
                        break;
                    }

                }

                System.out.println("Clicking menu [ "+ menus[1].toUpperCase() +" ]");
                waitForElementToBeVisible(driver,  By.linkText(menus[1]) , 5000);
                WebElement link = findElement(driver, By.linkText(menus[1]));
                link.click();

                //verify page header
                waitForElementToBeVisible(driver,  By.tagName("h1"), 5000);
                WebElement header = findElement(driver, By.tagName("h1"));

                try{
                    System.out.println("Header:"+header.getText() + " found!");
                    System.out.println("["+header.getText() +"]["+ menus[1]+"]");
                    menuOpens = header.getText().trim().contains(menus[1]);
                }catch (Exception ex){}


            }
            return menuOpens;
        }




    }
