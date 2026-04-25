package Test;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pom.HomePage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestDropdownText {
    public static final String url = "https://qa-scooter.praktikum-services.ru/";
    public static final String firefoxBrowser = "firefox";
    public static final String chromeBrowser = "chrome";
    public WebDriver driver;

    private final int index;
    private final String question;
    private final String answer;
    private final boolean result;
    private final String browser;

    public TestDropdownText(int index, String question, String answer, boolean result, String browser) {
        this.index = index;
        this.question = question;
        this.answer = answer;
        this.result = result;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                { 0
                        , "Сколько это стоит? И как оплатить?"
                        , "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                        , true
                        , firefoxBrowser},
                { 1
                        , "Хочу сразу несколько самокатов! Так можно?"
                        , "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
                        , true
                        , firefoxBrowser},
                { 2
                        , "Как рассчитывается время аренды?"
                        , "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."
                        , true
                        , firefoxBrowser},
                { 3
                        , "Можно ли заказать самокат прямо на сегодня?"
                        , "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
                        , true
                        , firefoxBrowser},
                { 4
                        , "Можно ли продлить заказ или вернуть самокат раньше?"
                        , "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."
                        , true
                        , firefoxBrowser},
                { 5
                        , "Вы привозите зарядку вместе с самокатом?"
                        , "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."
                        , true
                        , firefoxBrowser},
                { 6
                        , "Можно ли отменить заказ?"
                        , "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."
                        , true
                        , firefoxBrowser},
                { 7
                        , "Я жизу за МКАДом, привезёте?"
                        , "Да, обязательно. Всем самокатов! И Москве, и Московской области."
                        , true
                        , firefoxBrowser},
                { 0
                        , "Сколько это стоит? И как оплатить?"
                        , "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                        , true
                        , chromeBrowser},
                { 1
                        , "Хочу сразу несколько самокатов! Так можно?"
                        , "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
                        , true
                        , chromeBrowser},
                { 2
                        , "Как рассчитывается время аренды?"
                        , "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."
                        , true
                        , chromeBrowser},
                { 3
                        , "Можно ли заказать самокат прямо на сегодня?"
                        , "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
                        , true
                        , chromeBrowser},
                { 4
                        , "Можно ли продлить заказ или вернуть самокат раньше?"
                        , "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."
                        , true
                        , chromeBrowser},
                { 5
                        , "Вы привозите зарядку вместе с самокатом?"
                        , "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."
                        , true
                        , chromeBrowser},
                { 6
                        , "Можно ли отменить заказ?"
                        , "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."
                        , true
                        , chromeBrowser},
                { 7
                        , "Я жизу за МКАДом, привезёте?"
                        , "Да, обязательно. Всем самокатов! И Москве, и Московской области."
                        , true
                        , chromeBrowser},
        };
    }

    @Test
    public void DropdownListTest(){
        driver = new SetUpBrowsers().getDriver(browser);
        driver.get(url);
        HomePage objHomePage = new HomePage(driver);

        objHomePage.removeCookieBanner();
        assertEquals(result, objHomePage.checkDropdownListAnswer(index, question, answer)); //проверяем, верный ли текст в ответе
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
