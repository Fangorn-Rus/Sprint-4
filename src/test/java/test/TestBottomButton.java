package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pom.HomePage;
import pom.SecondOrderPage;

import static org.junit.Assert.assertTrue;

public class TestBottomButton {
    public static final String url = "https://qa-scooter.praktikum-services.ru/";

    public WebDriver driver;

    @Before
    public void setUp() {
        driver = new SetUpBrowsers().getDriver();
        driver.get(url);
    }

    @Test
    public void BottomButtonTest(){
        HomePage objHomePage = new HomePage(driver);
        SecondOrderPage objSecondOrderPage = new SecondOrderPage(driver);

        objHomePage.removeCookieBanner();
        objHomePage.clickBottomButton();

        assertTrue(objSecondOrderPage.checkOpenOrderWindow());
    }

    @After
    public void tearDown() {        driver.quit();    }
}
