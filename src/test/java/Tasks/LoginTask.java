package Tasks;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import Validacao.HomeValidacao;

public class LoginTask {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private HomeValidacao homeValidacao;

    public LoginTask(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        homePage = new HomePage(this.driver);
        homeValidacao = new HomeValidacao(this.driver);
    }

    public void efetuarLogin(String email, String senha, double saldoEsperado) {
        digitar(loginPage.getEmailInput(), email);
        digitar(loginPage.getPasswordInput(), senha);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginPage.getLoginButton());
        homePage.getSaldoText();
        homeValidacao.validationSaldoHome(saldoEsperado);
    }

    // Limpa o campo antes de digitar, para não misturar com o login anterior
    private void digitar(WebElement campo, String texto) {
        campo.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        campo.sendKeys(texto);
    }
}