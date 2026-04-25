package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HomePage {
    private final WebDriver driver;
    //Все кнопки Заказать
    private final By orderButtons = By.xpath("//button[contains(text(), 'Заказать')]");

    public HomePage(WebDriver driver) {       this.driver = driver;    }

     public boolean checkDropdownListAnswer (int index, String expectedQuestion, String expectedAnswer){
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

         wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.id("accordion__heading-" + index)
         ));
         WebElement question = driver.findElement(By.id("accordion__heading-" + index)); //находим вопрос по индексу
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);// Скроллим до элемента
         wait.until(ExpectedConditions.elementToBeClickable(question)).click();
         assertEquals(expectedQuestion, question.getText());

         wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.id("accordion__panel-" + index)
         ));
         WebElement realAnswer = driver.findElement(By.id("accordion__panel-" + index)); //находим текст ответа
         return expectedAnswer.equals(realAnswer.getText()); //проверяем, верный ли текст в ответе
     }

    public void clickTopButton(){
        List<WebElement> buttons = driver.findElements(orderButtons);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttons.get(0));
        buttons.get(0).click();
    }

    public void clickBottomButton (){
        List<WebElement> buttons = driver.findElements(orderButtons);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttons.get(1));
        buttons.get(1).click();
    }

    public void removeCookieBanner() {
            String script = "var banner = document.querySelector('.App_CookieConsent__1yUIN'); if(banner) banner.remove();";
            ((JavascriptExecutor) driver).executeScript(script);
    }


}
