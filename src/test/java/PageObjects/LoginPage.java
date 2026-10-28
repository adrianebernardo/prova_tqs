package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Framework.Browser.Waits;

public class LoginPage {

    private WebDriver driver;
    private Waits waits;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(this.driver);
    }

    private WebElement esperar(By by) {
        return waits.visibilityOfElement(by);
    }

    public WebElement getEmailInput() {
        return esperar(By.cssSelector(".card__login input[name='email']"));
    }

    public WebElement getPasswordInput() {
        return esperar(By.cssSelector(".card__login input[name='password']"));
    }

    public WebElement getLoginButton() {
        return esperar(By.xpath("//button[normalize-space()='Acessar']"));
    }

    public WebElement getRegistrarButton() {
        return esperar(By.xpath("//button[normalize-space()='Registrar']"));
    }
}
