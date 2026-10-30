package Tasks;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Framework.Browser.Waits;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import Validacao.HomeValidacao;

public class LoginTask {

    private static final Logger log = LoggerFactory.getLogger(LoginTask.class);

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private HomeValidacao homeValidacao;
    private Waits waits;

    public LoginTask(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        homePage = new HomePage(this.driver);
        homeValidacao = new HomeValidacao(this.driver);
        waits = new Waits(this.driver);
    }

    public void efetuarLogin(String email, String senha, double saldoEsperado) {
        log.info("Efetuando login com o e-mail {}", email);
        digitar(loginPage.getEmailInput(), email);
        digitar(loginPage.getPasswordInput(), senha);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginPage.getLoginButton());
        waits.loadElement(homePage.getSaldoText());
        homeValidacao.validationSaldoHome(saldoEsperado);
    }

    // Limpa o campo antes de digitar, para não misturar com o login anterior
    private void digitar(WebElement campo, String texto) {
        campo.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        campo.sendKeys(texto);
    }
}