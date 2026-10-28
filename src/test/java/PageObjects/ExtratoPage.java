package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Framework.Browser.Waits;

public class ExtratoPage {

    private WebDriver driver;
    private Waits waits;

    public ExtratoPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(this.driver);
    }

    private WebElement esperar(By by) {
        return waits.visibilityOfElement(by);
    }

    public WebElement getSaldoDisponivelText() {
        return esperar(By.id("textBalanceAvailable"));
    }
}
