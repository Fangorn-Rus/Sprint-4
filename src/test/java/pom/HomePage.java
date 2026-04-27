package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    //Все кнопки Заказать
    private final By orderButtons = By.xpath("//button[contains(text(), 'Заказать')]");

    public HomePage(WebDriver driver) {       this.driver = driver;    }

     public String[] checkDropdownText (int index){
        String[] questionAndAnswer = new String[2];
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.id("accordion__heading-" + index)
         ));
         WebElement realQuestion = driver.findElement(By.id("accordion__heading-" + index));
         questionAndAnswer[0] = realQuestion.getText();
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", realQuestion);
         wait.until(ExpectedConditions.elementToBeClickable(realQuestion)).click();
         wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.id("accordion__panel-" + index)
         ));
         WebElement realAnswer = driver.findElement(By.id("accordion__panel-" + index));
         questionAndAnswer[1] = realAnswer.getText();
         return questionAndAnswer;
     }

    public void clickTopButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> buttons = driver.findElements(orderButtons);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttons.get(0));
        wait.until(ExpectedConditions.elementToBeClickable(buttons.get(0))).click();
    }

    public void clickBottomButton (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> buttons = driver.findElements(orderButtons);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttons.get(1));
        wait.until(ExpectedConditions.elementToBeClickable(buttons.get(1))).click();
    }

    public void removeCookieBanner() {
            String script = "var banner = document.querySelector('.App_CookieConsent__1yUIN'); if(banner) banner.remove();";
            ((JavascriptExecutor) driver).executeScript(script);
    }


}
