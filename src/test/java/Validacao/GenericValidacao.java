package Validacao;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Framework.Browser.Waits;
import Framework.Utils.TakeScreenshot;
import PageObjects.GenericPage;

public class GenericValidacao {

    private WebDriver driver;
    private GenericPage genericPage;
    private Waits waits;

    public GenericValidacao(WebDriver driver) {
        this.driver = driver;
        genericPage = new GenericPage(this.driver);
        waits = new Waits(this.driver);
    }

    public void validacaoModal(String textoEsperado) {
        try {
            String texto = genericPage.getModalText().getText();
            Assertions.assertTrue(texto.contains(textoEsperado),
                    "Mensagem do modal inesperada: " + texto);
            TakeScreenshot.screenshotPage(driver, "Modal");
        } catch (AssertionError e) {
            TakeScreenshot.screenshotPage(driver, "Modal - ERRO");
            throw e;
        }
    }

    public void aguardarModalFechar() {
        waits.invisibilityOfElement(By.id("btnCloseModal"));
    }

    public void validarTexto(WebElement elemento, String textoEsperado) {
        try {
            Assertions.assertEquals(textoEsperado, elemento.getText());
            TakeScreenshot.screenshotPage(driver, "Texto validado");
        } catch (AssertionError e) {
            TakeScreenshot.screenshotPage(driver, "Texto validado - ERRO");
            throw e;
        }
    }

    // Converte textos como "R$ 1.000,00" em 1000.00
    public double converterValor(String texto) {
        String numero = texto.replaceAll("[^0-9,]", "").replace(",", ".");
        return Double.parseDouble(numero);
    }
}
