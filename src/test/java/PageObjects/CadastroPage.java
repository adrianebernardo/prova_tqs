package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Framework.Browser.Waits;

public class CadastroPage {

    private WebDriver driver;
    private Waits waits;

    public CadastroPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(this.driver);
    }

    private WebElement esperar(By by) {
        return waits.visibilityOfElement(by);
    }

    public WebElement getEmailInput() {
        return esperar(By.cssSelector(".card__register input[name='email']"));
    }

    public WebElement getNomeInput() {
        return esperar(By.cssSelector(".card__register input[name='name']"));
    }

    public WebElement getPasswordInput() {
        return esperar(By.cssSelector(".card__register input[name='password']"));
    }

    public WebElement getPasswordConfirmationInput() {
        return esperar(By.cssSelector(".card__register input[name='passwordConfirmation']"));
    }

    // O input do toggle fica escondido atrás do label, por isso não usa espera de visibilidade
    public WebElement getSaldoToggle() {
        return driver.findElement(By.id("toggleAddBalance"));
    }

    public WebElement getCadastrarButton() {
        return esperar(By.xpath("//button[normalize-space()='Cadastrar']"));
    }

    public WebElement getVoltarLoginLink() {
        return esperar(By.xpath("//a[normalize-space()='Voltar ao login']"));
    }
}
