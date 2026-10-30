package Validacao;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import Framework.Utils.TakeScreenshot;
import PageObjects.HomePage;

public class HomeValidacao {

    private WebDriver driver;
    private HomePage homePage;
    private GenericValidacao genericValidacao;

    public HomeValidacao(WebDriver driver) {
        this.driver = driver;
        homePage = new HomePage(this.driver);
        genericValidacao = new GenericValidacao(this.driver);
    }

    public void validationSaldoHome(double saldoEsperado) {
        try {
            double saldo = genericValidacao.converterValor(homePage.getSaldoText().getText());
            Assertions.assertEquals(saldoEsperado, saldo, 0.001);
            TakeScreenshot.screenshotPage(driver, "Saldo da home");
        } catch (AssertionError e) {
            TakeScreenshot.screenshotPage(driver, "Saldo da home - ERRO");
            throw e;
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver, "Saldo da home - ERRO");
            Assertions.fail("Falha ao validar o saldo da home: " + e.getMessage());
        }
    }
}
