
package AutomationTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;


public class Practice {
    private static WebDriver driver;

    public Practice() {
        System.setProperty("webdriver.chrome.driver", "C:/D/Luxoft/Driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-maximized");
        options.setHeadless(false);
        //options.setHeadless(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000L));

    }

    @Test
    public void shouldNavigateOnlineShop () throws MalformedURLException, InterruptedException {
        //GIVEN
        String testUrl = "https://pydocs.ru/";
        String expectedPageTitle = "Документация по языку программирования Python";
        Thread.sleep(3000L);
        //WHEN
        driver.navigate().to(new URL(testUrl));
        String actualPageTitle = driver.getTitle();
        //THEN
        Assertions.assertEquals(expectedPageTitle, actualPageTitle);
    }
    @Test
    public void shouldSearch_byId() {
        //GIVEN
        String expectedHeaderText = "Задача Python #1. Вычислите умножение и сумму двух чисел";
        //WHEN
        driver.get("https://pydocs.ru/python-zadachi-dlya-nachinayushhih/");
        WebElement headerElements = driver.findElement(By.id("umsum"));
        //THEN
        Assertions.assertEquals(expectedHeaderText, headerElements.getText());
    }
    @Test
    public void shouldSearch_byName() {
        //GIVEN
        //WHEN
        driver.get("https://go.skillbox.ru/auth/sign-in");
        WebElement nameInputElement = driver.findElement(By.name("username"));
        //THEN
        Assertions.assertTrue(nameInputElement.isEnabled());
    }
    @Test
    public void shouldSearch_byClassName() {
        //GIVEN
        //WHEN
        driver.get("https://pydocs.ru/python-zadachi-dlya-nachinayushhih/");
        WebElement blockElement = driver.findElement(By.className("wp-block-preformatted"));
        //THEN
        Assertions.assertTrue(blockElement.isEnabled());
    }
    @Test
    public void shouldSearch_byClassName_multipleElements() {
        //GIVEN
        //WHEN
        driver.get("https://pydocs.ru/python-zadachi-dlya-nachinayushhih/");
        List<WebElement> spoilerElements = driver.findElements(By.className("spoiler-wrap"));

        //THEN
        Assertions.assertEquals(18, spoilerElements.size());


    }
    @Test
    public void shouldSearch_byTagName() {
        //GIVEN
        int expectedUlsCount = 277;
        //WHEN
        driver.get("https://pydocs.ru/python-zadachi-dlya-nachinayushhih/");
        List<WebElement> headerElements = driver.findElements(By.tagName("li"));
        //THEN
        Assertions.assertEquals(expectedUlsCount, headerElements.size());
    }
    @Test
    public void shouldSearch_byLinkPart() throws InterruptedException {
        //GIVEN
        String expectedUrl = "https://vertex-academy.com/tutorials/ru/istoriya-sozdaniya-java-2/";
        //WHEN
        driver.get("https://vertex-academy.com/tutorials/ru/samouchitel-po-java-s-nulya/");
        WebElement linkElement = driver.findElement(By.partialLinkText("Урок 1:"));
        Thread.sleep(1000L);
        linkElement.click();
        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);

    }
    @Test
    public void shouldSearch_byCssSelectorType() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("https://go.skillbox.ru/auth/sign-in");
        WebElement inputElement =
                driver.findElement(By.cssSelector("input[type='text']"));
        inputElement.sendKeys("test@test.ua");
        //THEN
        Thread.sleep(1000L);

    }
    @Test
    public void shouldSearch_byCssSelectorAttribute() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("https://go.skillbox.ru/auth/sign-in");
        WebElement inputElement =
                driver.findElement(By.cssSelector("input[autocomplete='username']"));
        inputElement.sendKeys("test@test.ua");
        //THEN
        Thread.sleep(1000L);
    }
    @Test
    public void shouldSearch_byCssSelector_parentChild() {
        //GIVEN
        //WHEN
        driver.get("https://pydocs.ru/python-zadachi-dlya-nachinayushhih/");
        List<WebElement> elements =
                driver.findElements(By.cssSelector("ol > li"));
        //THEN
        Assertions.assertEquals(30, elements.size());

    }
    @Test
    public void shouldSearch_byCssSelector_complexStructure() {
        //GIVEN
        //WHEN
        driver.get("http://www.shoplist.com.ua/");
        List<WebElement> elements =
                driver.findElements(By.cssSelector(
                        "#menu> li:nth-child(2) > a"));
        //THEN
        Assertions.assertEquals(1, elements.size());
    }

              //Xpath,RelativeLocator,Locator
    @Test
    public void byCssSelectorHeaderElement2() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://www.shoplist.com.ua/");
        WebElement element = driver.findElement(By.cssSelector("#menu> li:nth-child(2) > a"));
        element.click();
        String currentUrl = driver.getCurrentUrl();
        Thread.sleep(2000L);
        //THEN
        org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("citys.php");

    }
    @Test
    public void byXpathHeaderElement2() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://www.shoplist.com.ua/");
        WebElement headerElement = driver.findElement(By.xpath("//ul[@id='menu']/li[2]//a"));
        headerElement.click();
        String currentUrl = driver.getCurrentUrl();
        Thread.sleep(2000L);
        //THEN
        org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("citys.php");
    }
    @Test
    public void byRelativeLocatorHeaderElement2() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://www.shoplist.com.ua/");
        By headerLocator = RelativeLocator.with(By.linkText("Магазины по городам"));
        WebElement element = driver.findElement(headerLocator);
        element.click();
        String currentUrl = driver.getCurrentUrl();
        //THEN
        Thread.sleep(4000l);
        org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("citys.php");
    }
    @Test
    public void byXpathButton() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://www.shoplist.com.ua/");
        WebElement inputElement = driver.findElement(By.xpath("//input[@id='search1']"));
        inputElement.sendKeys("одежда");
        WebElement button = driver.findElement(By.xpath(
                      "//button[@class='ie-fix-button']"));
        button.click();
        String currentUrl = driver.getCurrentUrl();
        Thread.sleep(4000l);
        //THEN
       org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("advanced_search_result.php?keywords=%EE%E4%E5%E6%E4%E0&scope=shops");
    }

    @Test
    public void byRelativeLocatorButton() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://www.shoplist.com.ua/");
        By searchInputElement = RelativeLocator.with(By.id("search1"));
        WebElement elementInput = driver.findElement(searchInputElement);
        elementInput.sendKeys("одежда");
        By searchButtonElement = RelativeLocator.with(By.xpath("//button[@class='ie-fix-button']"));
        WebElement elementButton = driver.findElement(searchButtonElement);
        elementButton.click();
        String currentUrl = driver.getCurrentUrl();
        Thread.sleep(4000l);
        //THEN
        org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("advanced_search_result.php?keywords=%EE%E4%E5%E6%E4%E0&scope=shops");
    }
    //Selected,Disabled,Checkbox,Radios
    @Test
    public void checkSelected() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://prom.ua/Internet-magazin-2.html");
        WebElement checkboxElementInputDelivery1 = driver.findElement(By.xpath("//span[contains(.,'Доставка')]//following::input[1]"));
        WebElement checkboxElementInputDelivery2 = driver.findElement(By.xpath("//span[contains(.,'Самовывоз')]//following::input[1]"));
        WebElement checkboxElementInputDelivery3 = driver.findElement(By.xpath("//span[contains(.,'Нова Пошта')]//following::input[1]"));
        WebElement checkboxElementInputDelivery4 = driver.findElement(By.xpath("//span[contains(.,'Укрпошта')]//following::input[1]"));

        //THEN
        Assertions.assertFalse(checkboxElementInputDelivery1.isSelected());
        Assertions.assertFalse(checkboxElementInputDelivery2.isSelected());
        Assertions.assertFalse(checkboxElementInputDelivery3.isSelected());
        Assertions.assertFalse(checkboxElementInputDelivery4.isSelected());

        // WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", checkboxElementInputDelivery1);
        Thread.sleep(3000L);
        String currentUrl = driver.getCurrentUrl();

        //THEN
        org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("?delivery=pickup");
        Assertions.assertTrue(checkboxElementInputDelivery1.isSelected());
        Assertions.assertFalse(checkboxElementInputDelivery2.isSelected());
        Assertions.assertFalse(checkboxElementInputDelivery3.isSelected());
        Assertions.assertFalse(checkboxElementInputDelivery4.isSelected());
    }

    @Test
    public void checkDisabled_And_Displayed() {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://mdbootstrap.com/docs/standard/forms/checkbox/");
        WebElement checkboxElementInputDisabled = driver.findElement(By.xpath("//label[contains(.,' Disabled checkbox ')]//preceding-sibling::input"));

        //THEN
        boolean isDisabledElement = checkboxElementInputDisabled.isEnabled();
        Assertions.assertFalse(isDisabledElement);
        boolean isDisplayedElement = checkboxElementInputDisabled.isDisplayed();
        Assertions.assertTrue(isDisplayedElement);

    }
    @Test
    public void checkRadios() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/#radios");
        WebElement radiosElementInput1 = driver.findElement(By.xpath("//input[@id='flexRadioDefault1']"));
        WebElement radiosElementInput2 = driver.findElement(By.xpath("//input[@id='flexRadioDefault2']"));

        //THEN
        Assertions.assertFalse(radiosElementInput1.isSelected());
        Assertions.assertTrue(radiosElementInput2.isSelected());

        // WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", radiosElementInput1);
        Thread.sleep(3000L);

        //THEN
        Assertions.assertTrue(radiosElementInput1.isSelected());
        Assertions.assertFalse(radiosElementInput2.isSelected());

    }
    @AfterEach
    public void cleanUp(){
        driver.close();
        driver.quit();
    }
}

