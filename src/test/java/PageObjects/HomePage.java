package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Framework.Browser.Waits;

public class HomePage {

    private WebDriver driver;
    private Waits waits;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(this.driver);
    }

    private WebElement esperar(By by) {
        return waits.visibilityOfElement(by);
    }

    public WebElement getSaldoText() {
        return esperar(By.id("textBalance"));
    }

    public WebElement getTransferenciaButton() {
        return esperar(By.id("btn-TRANSFERÊNCIA"));
    }

    public WebElement getExtratoButton() {
        return esperar(By.id("btn-EXTRATO"));
    }
}
