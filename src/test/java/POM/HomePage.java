package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HomePage {
    private final WebDriver driver;
    //Все кнопки Заказать
    private final By orderButtons = By.xpath("//button[contains(text(), 'Заказать')]");

    public HomePage(WebDriver driver) {       this.driver = driver;    }

     public boolean checkDropdownListAnswer (int index, String expectedQuestion, String expectedAnswer){
         WebElement question = driver.findElement(By.id("accordion__heading-" + index)); //находим вопрос по индексу
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);// Скроллим до элемента
         question.click();
         assertEquals(expectedQuestion, question.getText());
         WebElement realAnswer = driver.findElement(By.id("accordion__panel-" + index)); //находим текст ответа
         return expectedAnswer.equals(realAnswer.getText()); //проверяем, верный ли текст в ответе
     }

    public void clickTopButton(){
        List<WebElement> buttons = driver.findElements(orderButtons);
        //System.out.println("Нашли кнопок: " + buttons.size());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttons.get(0));
        buttons.get(0).click();
    }

    public void clickBottomButton (){
        List<WebElement> buttons = driver.findElements(orderButtons);
        //System.out.println("Нашли кнопок: " + buttons.size());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", buttons.get(1));
        buttons.get(1).click();
    }

    public void removeCookieBanner() {
        try {
            String script = "var banner = document.querySelector('.App_CookieConsent__1yUIN'); if(banner) banner.remove();";
            ((JavascriptExecutor) driver).executeScript(script);
            //System.out.println("Баннер куки удалён из DOM");
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Не удалось удалить баннер: " + e.getMessage());
        }
    }


}
