package Validacao;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import PageObjects.LoginPage;

public class LoginValidacao {

    private WebDriver driver;
    private LoginPage loginPage;

    public LoginValidacao(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(this.driver);
    }

    public void loginValidation() {
        try {
            Assertions.assertTrue(loginPage.getLoginButton().isDisplayed());
        } catch (Exception e) {
            Assertions.fail("Botão de login não encontrado: " + e.getMessage());
        }
    }
}
