package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Framework.Browser.Waits;

public class TransferenciaPage {

    private WebDriver driver;
    private Waits waits;

    public TransferenciaPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(this.driver);
    }

    private WebElement esperar(By by) {
        return waits.visibilityOfElement(by);
    }

    public WebElement getContaInput() {
        return esperar(By.name("accountNumber"));
    }

    public WebElement getDigitoInput() {
        return esperar(By.name("digit"));
    }

    public WebElement getValorInput() {
        return esperar(By.name("transferValue"));
    }

    public WebElement getDescricaoInput() {
        return esperar(By.name("description"));
    }

    public WebElement getTransferirButton() {
        return esperar(By.xpath("//button[normalize-space()='Transferir agora']"));
    }
}
