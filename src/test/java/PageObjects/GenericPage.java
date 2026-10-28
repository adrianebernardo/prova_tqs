package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Framework.Browser.Waits;

public class GenericPage {

    private WebDriver driver;
    private Waits waits;

    public GenericPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(this.driver);
    }

    private WebElement esperar(By by) {
        return waits.visibilityOfElement(by);
    }

    public WebElement getModalText() {
        return esperar(By.id("modalText"));
    }

    public WebElement getModalCloseButton() {
        return esperar(By.id("btnCloseModal"));
    }

    public WebElement getBackButton() {
        return esperar(By.id("btnBack"));
    }

    public WebElement getExitButton() {
        return esperar(By.id("btnExit"));
    }

    public WebElement getBody() {
        return driver.findElement(By.tagName("body"));
    }
}
