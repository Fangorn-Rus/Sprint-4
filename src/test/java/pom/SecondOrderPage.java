package pom;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SecondOrderPage {
    private final WebDriver driver;
    //Локатор поля Когда привезти самокат
    private final By orderDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Локатор срока аренды, выпадающий список
    private final By fieldRentalPeriod = By.cssSelector(".Dropdown-arrow");
    //Цвет черный
    private final By colorBlack = By.id("black");
    //Цвет серый
    private final By colorGrey =  By.id("grey");
    //Локатор комментария
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Локатор кнопки Заказать
    private final By buttonOrder = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Локатор кнопки Да во всплывающем окне
    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Локатор окна статуса заказа
    private final By windowStatus = By.xpath("//div[contains(text(), 'Заказ оформлен')]");
    //Локатор текста статуса заказа
    private final By orderStatus = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");

    public SecondOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setOrderDate (String Date){
        driver.findElement(orderDate).sendKeys(Date);
        driver.findElement(By.tagName("body")).click(); // Закрыть календарь (кликнуть вне его)
    }
    public void setRentalPeriod(String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(fieldRentalPeriod).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Dropdown-menu")));
        String xpath = String.format("//div[@class='Dropdown-option' and text()='%s']", text);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }
    public void setColor (String color){
        if(color.equals("black")){
            driver.findElement(colorBlack).click();
        }
        if(color.equals("grey")){
            driver.findElement(colorGrey).click();
        }
    }
    public void setComment(String text){
        driver.findElement(comment).sendKeys(text);
    }

    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonYes));
        driver.findElement(buttonYes).click();
    }
    public void checkOrderStatus(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(windowStatus));
        Assert.assertTrue(
                driver.findElement(orderStatus).getText().startsWith("Заказ оформлен")
        );
    }



}
