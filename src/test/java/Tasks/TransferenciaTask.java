package Tasks;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.Browser.Waits;
import PageObjects.GenericPage;
import PageObjects.HomePage;
import PageObjects.TransferenciaPage;
import Validacao.GenericValidacao;
import Validacao.TransferenciaValidacao;

public class TransferenciaTask {

    private WebDriver driver;
    private HomePage homePage;
    private TransferenciaPage transferenciaPage;
    private GenericPage genericPage;
    private GenericValidacao genericValidacao;
    private TransferenciaValidacao transferenciaValidacao;
    private Waits waits;

    public TransferenciaTask(WebDriver driver) {
        this.driver = driver;
        homePage = new HomePage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
        genericPage = new GenericPage(this.driver);
        genericValidacao = new GenericValidacao(this.driver);
        transferenciaValidacao = new TransferenciaValidacao(this.driver);
        waits = new Waits(this.driver);
    }

    public void realizarTransferencia(String[] contaDestino, double valor, String descricao) {
        waits.loadElement(homePage.getTransferenciaButton());
        homePage.getTransferenciaButton().click();

        transferenciaPage.getContaInput().sendKeys(contaDestino[0]);
        transferenciaPage.getDigitoInput().sendKeys(contaDestino[1]);
        transferenciaPage.getValorInput().sendKeys(String.valueOf(valor));
        transferenciaPage.getDescricaoInput().sendKeys(descricao);
        transferenciaPage.getTransferirButton().click();

        transferenciaValidacao.validationSucessoTransferencia();
        fecharModal();
        genericValidacao.aguardarModalFechar();
        waits.loadElement(genericPage.getBackButton());
        genericPage.getBackButton().click();
    }

    // Se o botão for recriado pelo site no meio do clique, tenta de novo
    private void fecharModal() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class, ElementClickInterceptedException.class)
                .until(d -> {
                    d.findElement(By.id("btnCloseModal")).click();
                    return true;
                });
    }
}