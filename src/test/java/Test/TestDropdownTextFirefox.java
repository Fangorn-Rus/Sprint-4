package Test;

import POM.HomePage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestDropdownTextFirefox {
    public WebDriver driver;

    private final int index;
    private final String question;
    private final String answer;
    private final boolean result;

    public TestDropdownTextFirefox(int index, String question, String answer, boolean result) {
        this.index = index;
        this.question = question;
        this.answer = answer;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                { 0
                        , "Сколько это стоит? И как оплатить?"
                        , "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                        , true},
                { 3
                        , "Можно ли заказать самокат прямо на сегодня?"
                        , "Случайный текст"
                        , false},
        };
    }

    @Test
    public void DropdownListTest(){
        FirefoxOptions options  = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new FirefoxDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(driver);

        assertEquals(result, objHomePage.checkDropdownListAnswer(index, question, answer)); //проверяем, верный ли текст в ответе
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
