package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirstOrderPage {
    private final WebDriver driver;
    //Локатор поля Имя
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    //Локатор поля Фамилия
    private final By surName = By.xpath(".//input[@placeholder='* Фамилия']");
    //Локатор поля Адрес, куда привезти
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Локатор поля станции метро
    private final By metroStationList = By.className("select-search__input");
    //Локатор выбора первой станции метро из списка
    private final By metroStation = By.className("select-search__select");
    //Локатор телефона
    private final By phoneNumber = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор кнопки Далее
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public FirstOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName (String userName) {
        driver.findElement(name).sendKeys(userName);
    }
    public void setSurname (String userSurname) {
        driver.findElement(surName).sendKeys(userSurname);
    }
    public void setAddress(String userAddress){
        driver.findElement(address).sendKeys(userAddress);
    }
    public void setMetroStation(String station){
        driver.findElement(metroStationList).click();
        driver.findElement(metroStationList).sendKeys(station);
        driver.findElement(metroStation).click();
    }
    public void setPhoneNumber(String phoneNumber){
        driver.findElement(this.phoneNumber).sendKeys(phoneNumber);
    }
    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }


}
