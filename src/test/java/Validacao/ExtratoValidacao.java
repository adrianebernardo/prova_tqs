package Validacao;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import Framework.Utils.TakeScreenshot;
import PageObjects.ExtratoPage;
import PageObjects.GenericPage;

public class ExtratoValidacao {

    private WebDriver driver;
    private ExtratoPage extratoPage;
    private GenericPage genericPage;
    private GenericValidacao genericValidacao;

    public ExtratoValidacao(WebDriver driver) {
        this.driver = driver;
        extratoPage = new ExtratoPage(this.driver);
        genericPage = new GenericPage(this.driver);
        genericValidacao = new GenericValidacao(this.driver);
    }

    public void validationSaldoExtrato(double saldoEsperado) {
        try {
            double saldo = genericValidacao.converterValor(extratoPage.getSaldoDisponivelText().getText());
            Assertions.assertEquals(saldoEsperado, saldo, 0.001);
            TakeScreenshot.screenshotPage(driver, "Saldo do extrato");
        } catch (AssertionError e) {
            TakeScreenshot.screenshotPage(driver, "Saldo do extrato - ERRO");
            throw e;
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver, "Saldo do extrato - ERRO");
            Assertions.fail("Falha ao validar o saldo do extrato: " + e.getMessage());
        }
    }

    public void validationTransferenciaNoExtrato(String descricao) {
        try {
            String texto = genericPage.getBody().getText();
            Assertions.assertTrue(texto.contains(descricao),
                    "A transferência '" + descricao + "' não aparece no extrato");
            TakeScreenshot.screenshotPage(driver, "Recibo no extrato");
        } catch (AssertionError e) {
            TakeScreenshot.screenshotPage(driver, "Recibo no extrato - ERRO");
            throw e;
        }
    }
}
