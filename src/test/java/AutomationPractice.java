import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutomationPractice {
   
    static WebDriver driver;

    @BeforeAll
    static void prepareBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
    }

    @AfterAll
    static void closeBrowser(){
        driver.quit();
   }

    @Test
    void shouldNotBeAbleToLogInIfEmailNotProvided()  {
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();
        WebElement passwordInput = driver.findElement(By.id("passwd"));
        WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
        passwordInput.sendKeys("1qaz!QAZ");
        submitButton.click();
        WebElement authenticationError =
                driver.findElement(By.className("page-heading"));
        Assertions.assertTrue(authenticationError.isDisplayed());
    }

    @Test
    void shouldNotBeAbleToLogInWithNoPasswordProvided() {
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();
        WebElement loginInput = driver.findElement(By.id("email"));
        WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
        loginInput.sendKeys("test@tester.pl");
        submitButton.click();
        WebElement authenticationError =
                driver.findElement(By.className("page-heading"));
        Assertions.assertTrue(authenticationError.isDisplayed());
    }

    @Test
    void shouldVerifyMovingFromLoginPageToHomePage(){
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();
        WebElement logInPage = driver.findElement(By.className("page-heading"));
        WebElement homePageButton = driver.findElement(By.className("home"));
        homePageButton.click();
        Assertions.assertEquals
                ("http://automationpractice.com/index.php", driver.getCurrentUrl());
    }

    @Test
    void shouldVerifyMovingFromHomePageToContactPage(){
        WebElement contactPage = driver.findElement(By.id("contact-link"));
        contactPage.click();
        WebElement contactUsElement = driver.findElement(By.className("page-heading"));
        Assertions.assertEquals("CUSTOMER SERVICE - CONTACT US", contactUsElement.getText(),
                "Contact Page Not Loaded");
    }

    @Test
    void shouldVerifyIfHomePageContainsLogoAndSearchBar(){
        WebElement mainLogo = driver.findElement(By.className("logo"));
        Assertions.assertTrue(mainLogo.isDisplayed());
        WebElement searchBar = driver.findElement(By.className("search_query"));
        Assertions.assertTrue(searchBar.isDisplayed());
    }

    @Test
    void shouldVerifyIfLoginPageContainsLogoAndSearchBar() {
        WebElement signInButton = driver.findElement(By.className("login"));
        signInButton.click();
        //Wait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-heading")));
        //WebElement loginPage = driver.findElement(By.className("page-heading"));
        //Assertions.assertEquals("AUTHENTICATION", loginPage.getText());
        WebElement mainLogo = driver.findElement(By.className("logo"));
        Assertions.assertTrue(mainLogo.isDisplayed());
        WebElement searchBar = driver.findElement(By.className("search_query"));
        Assertions.assertTrue(searchBar.isDisplayed());
    }

   @Test
    void shouldVerifyProductAddedToCart() {
       WebElement productTShirt = driver.findElement(
               By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]"));
       productTShirt.click();
       WebElement addTShirtToCart = driver.findElement(By.name("Submit"));
       addTShirtToCart.click();
       WebElement proceedToCheckout = driver.findElement(By.className("button-medium"));
       Wait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("button-medium")));
       proceedToCheckout.click();
       WebElement shoppingCartCounter = driver.findElement(By.className("heading-counter"));
       Assertions.assertEquals("Your shopping cart contains: 1 Product", shoppingCartCounter.getText());
   }

    @Test
    void shouldVerifyProductRemovedFromCart() {
        WebElement productTShirt = driver.findElement(
                By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]"));
        productTShirt.click();
        WebElement addTShirtToCart = driver.findElement(By.name("Submit"));
        addTShirtToCart.click();
        WebElement proceedToCheckout = driver.findElement(By.className("button-medium"));
        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("button-medium")));
        proceedToCheckout.click();
        WebElement removeTShirtFromCart = driver.findElement(By.className("icon-trash"));
        removeTShirtFromCart.click();
        WebElement emptyCartWarning = driver.findElement(By.className("alert"));
        Assertions.assertTrue(emptyCartWarning.isEnabled());
    }

}
