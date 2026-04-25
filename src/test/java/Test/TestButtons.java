package Test;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pom.FirstOrderPage;
import pom.HomePage;
import pom.SecondOrderPage;

@RunWith(Parameterized.class)
public class TestButtons{
    public static final String url = "https://qa-scooter.praktikum-services.ru/";
    public static final String firefoxBrowser = "firefox";
    public static final String chromeBrowser = "chrome";
    public WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String stationName;
    private final String phoneNumber;
    private final String orderDate;
    private final String rentalPeriodText;
    private final String color;
    private final String commentText;
    private final String browser;

    public TestButtons(String name, String surname, String address, String stationName, String phoneNumber,
                                      String orderDate, String rentalPeriodText, String color, String commentText,
                                        String browser) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationName = stationName;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.rentalPeriodText = rentalPeriodText;
        this.color = color;
        this.commentText = commentText;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                {         "Александр"
                        , "Пушкин"
                        , "Никольская ул., 81с1"
                        , "Площадь революции"
                        , "+79991112233"
                        , "15.04.2026"
                        , "двое суток"
                        , "black"
                        , "Привезите быстрее!"
                        , firefoxBrowser
                },
                {         "Лев"
                        , "Толстой"
                        , "Большая Черкизовская улица, 125с1"
                        , "Черкизовская"
                        , "+78884445566"
                        , "11.03.2026"
                        , "сутки"
                        , "grey"
                        , "Я подожду."
                        , firefoxBrowser
                },
                {         "Александр"
                        , "Пушкин"
                        , "Никольская ул., 81с1"
                        , "Площадь революции"
                        , "+79991112233"
                        , "15.04.2026"
                        , "двое суток"
                        , "black"
                        , "Привезите быстрее!"
                        , chromeBrowser
                },
                {         "Лев"
                        , "Толстой"
                        , "Большая Черкизовская улица, 125с1"
                        , "Черкизовская"
                        , "+78884445566"
                        , "11.03.2026"
                        , "сутки"
                        , "grey"
                        , "Я подожду."
                        , chromeBrowser
                },
        };
    }

    @Test
    public void TopButtonTest(){
        driver = new SetUpBrowsers().getDriver(browser);
        driver.get(url);
        HomePage objHomePage = new HomePage(driver);
        FirstOrderPage objFirstOrderPage = new FirstOrderPage(driver);
        SecondOrderPage objSecondOrderPage = new SecondOrderPage(driver);

        objHomePage.removeCookieBanner();
        objHomePage.clickTopButton();

        objFirstOrderPage.setName(name);
        objFirstOrderPage.setSurname(surname);
        objFirstOrderPage.setAddress(address);
        objFirstOrderPage.setMetroStation(stationName);
        objFirstOrderPage.setPhoneNumber(phoneNumber);
        objFirstOrderPage.clickButtonNext();

        objSecondOrderPage.setOrderDate(orderDate);
        objSecondOrderPage.setRentalPeriod(rentalPeriodText);
        objSecondOrderPage.setColor(color);
        objSecondOrderPage.setComment(commentText);
        objSecondOrderPage.clickButtonOrder();
        objSecondOrderPage.checkOrderStatus();
    }

    @Test
    public void BottomButtonTest(){
        driver = new SetUpBrowsers().getDriver(browser);
        driver.get(url);
        HomePage objHomePage = new HomePage(driver);

        objHomePage.removeCookieBanner();
        objHomePage.clickBottomButton();
    }

    @After
    public void tearDown() {        driver.quit();    }
}
