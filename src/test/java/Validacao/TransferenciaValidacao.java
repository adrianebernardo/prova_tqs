package Validacao;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import Framework.Utils.TakeScreenshot;
import PageObjects.GenericPage;

public class TransferenciaValidacao {

    private WebDriver driver;
    private GenericPage genericPage;

    public TransferenciaValidacao(WebDriver driver) {
        this.driver = driver;
        genericPage = new GenericPage(this.driver);
    }

    public void validationSucessoTransferencia() {
        try {
            String texto = genericPage.getModalText().getText();
            Assertions.assertTrue(texto.contains("Transferencia realizada com sucesso"),
                    "Mensagem inesperada: " + texto);
            TakeScreenshot.screenshotPage(driver, "Transferencia realizada");
        } catch (AssertionError e) {
            TakeScreenshot.screenshotPage(driver, "Transferencia realizada - ERRO");
            throw e;
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver, "Transferencia realizada - ERRO");
            Assertions.fail("Falha ao validar a transferência: " + e.getMessage());
        }
    }
}
