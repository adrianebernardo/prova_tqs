package Validacao;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import Framework.Utils.TakeScreenshot;
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
            TakeScreenshot.screenshotPage(driver, "Login");
        } catch (AssertionError e) {
            TakeScreenshot.screenshotPage(driver, "Login - ERRO");
            throw e;
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver, "Login - ERRO");
            Assertions.fail("Botão de login não encontrado: " + e.getMessage());
        }
    }
}
