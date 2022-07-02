import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AutomationPractice {
   
    static WebDriver driver2;

    @BeforeAll
    static void prepareBrowser(){
        driver2 = new ChromeDriver();
        driver2.manage().window().maximize();
        driver2.get("http://automationpractice.com/index.php");
    }

    @AfterAll
    static void closeBrowser(){
        driver2.quit();
   }

    //Zad 1.
    @Test
    void shouldVerifyLoginWithNoEmailEntered() throws InterruptedException {
        WebElement signInButton = driver2.findElement(By.className("login"));
        Thread.sleep(2000);
        signInButton.click();
        Thread.sleep(2000);
        WebElement loginPage = driver2.findElement(By.className("page-heading"));
        Assertions.assertEquals("AUTHENTICATION", loginPage.getText());

        WebElement loginInput = driver2.findElement(By.id("email"));
        WebElement passwordInput = driver2.findElement(By.id("passwd"));
        WebElement submitButton = driver2.findElement(By.id("SubmitLogin"));

        loginInput.sendKeys("");
        passwordInput.sendKeys("1qaz!QAZ");
        submitButton.click();

        WebElement authenticationError =
                driver2.findElement(By.className("page-heading"));
        Assertions.assertTrue(authenticationError.isDisplayed());
    }

    //Zad 2.
    @Test
    void shouldVerifyLoginWithNoPasswordEntered() throws InterruptedException {
        WebElement signInButton = driver2.findElement(By.className("login"));
        Thread.sleep(2000);
        signInButton.click();
        Thread.sleep(2000);
        WebElement loginPage = driver2.findElement(By.className("page-heading"));
        Assertions.assertEquals("AUTHENTICATION", loginPage.getText());

        WebElement loginInput = driver2.findElement(By.id("email"));
        WebElement passwordInput = driver2.findElement(By.id("passwd"));
        WebElement submitButton = driver2.findElement(By.id("SubmitLogin"));

        loginInput.sendKeys("test@softie.pl");
        passwordInput.sendKeys("");
        submitButton.click();

        WebElement authenticationError =
                driver2.findElement(By.className("page-heading"));
        Assertions.assertTrue(authenticationError.isDisplayed());
    }

    //Zad 5.
    @Test
    void shouldVerifyLoginPageToHomePage() throws InterruptedException{
        WebElement signInButton = driver2.findElement(By.className("login"));
        Thread.sleep(2000);
        signInButton.click();
        Thread.sleep(2000);
        WebElement loginPage = driver2.findElement(By.className("page-heading"));
        Assertions.assertEquals("AUTHENTICATION", loginPage.getText());

        WebElement homePageButton = driver2.findElement(By.className("home"));
        homePageButton.click();
        Assertions.assertTrue(homePageButton.isDisplayed());
    }

    //Zad 4.
    @Test
    void shouldVerifyHomePageToContactPage() throws InterruptedException{
        WebElement contactPage = driver2.findElement(By.id("contact-link"));
        Thread.sleep(2000);
        contactPage.click();
        Thread.sleep(2000);
        WebElement contactUsElement = driver2.findElement(By.className("page-heading"));
        Assertions.assertEquals("CUSTOMER SERVICE - CONTACT US", contactUsElement.getText());
    }

    //Zad 3.
    @Test
    void shouldVerifyHomePageContainsLogoAndSearch(){
        List<WebElement> elements = driver2.findElements(By.id("header_logo"));
        //System.out.println(elements.size());
        Assertions.assertEquals(1, elements.size());
        //WebElement logo = driver2.findElement(By.id("header_logo"));
        //WebElement searchInput = driver2.findElement(By.className("search_query"));
        List<WebElement> elements2 = driver2.findElements(By.className("search_query"));
        //System.out.println(elements2.size());
        Assertions.assertEquals(1,elements2.size());
    }

    @Test
    void shouldVerifyIfLoginPageContainsLogoAndSearch() throws InterruptedException {
        WebElement signInButton = driver2.findElement(By.className("login"));
        Thread.sleep(2000);
        signInButton.click();
        Thread.sleep(2000);
        Wait wait = new WebDriverWait(driver2, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-heading")));
        WebElement loginPage = driver2.findElement(By.className("page-heading"));
        Assertions.assertEquals("AUTHENTICATION", loginPage.getText());
        List<WebElement> elements = driver2.findElements(By.id("header_logo"));
        Assertions.assertEquals(1, elements.size());
        List<WebElement> elements2 = driver2.findElements(By.className("search_query"));
        Assertions.assertEquals(1,elements2.size());
    }

    //Zad 6. & 7.
   @Test
    void shouldVerifyProductAddedToCart() throws InterruptedException{
        WebElement product = driver2.findElement
                (By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img"));
        Thread.sleep(2000);
        product.click();
        Thread.sleep(2000);
        WebElement productPage = driver2.findElement(By.className("breadcrumb"));
        Assertions.assertTrue(productPage.isDisplayed());
        WebElement addToCart = driver2.findElement(By.id("add_to_cart"));
        addToCart.click();
        Thread.sleep(5000);
        WebElement productAddedConfirmation =
                driver2.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2"));
        Assertions.assertEquals("Product successfully added to your shopping cart",
                productAddedConfirmation.getText());

      //Kontynuacja do Zad 7.

        WebElement checkoutButton = driver2.findElement(By.className("button-medium"));
        checkoutButton.click();
        WebElement cartSummary = driver2.findElement(By.className("navigation_page"));
        Assertions.assertEquals("Your shopping cart", cartSummary.getText());
        WebElement removeFromCart = driver2.findElement(By.className("icon-trash"));
        removeFromCart.click();
        WebElement warningEmptyCart = driver2.findElement(By.className("alert"));
        WebElement warningEmptyCart1 = driver2.findElement(By.className("alert"));
        Thread.sleep(5000);
        Assertions.assertEquals("Your shopping cart is empty.", warningEmptyCart1.getText());
   }
}