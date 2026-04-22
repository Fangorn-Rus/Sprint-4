package Test;

import POM.FirstOrderPage;
import POM.HomePage;
import POM.SecondOrderPage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(Parameterized.class)
public class TestBottomOrderPageChrome {
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

    public TestBottomOrderPageChrome(String name, String surname, String address, String stationName, String phoneNumber,
                                  String orderDate, String rentalPeriodText, String color, String commentText) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationName = stationName;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.rentalPeriodText = rentalPeriodText;
        this.color = color;
        this.commentText = commentText;
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
                },
        };
    }

    @Test
    public void TopButtonTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(driver);
        FirstOrderPage objFirstOrderPage = new FirstOrderPage(driver);
        SecondOrderPage objSecondOrderPage = new SecondOrderPage(driver);

        objHomePage.removeCookieBanner();
        objHomePage.clickBottomButton();

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

    @After
    public void tearDown() {
        driver.quit();
    }
}
