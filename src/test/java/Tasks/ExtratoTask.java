package Tasks;

import org.openqa.selenium.WebDriver;

import PageObjects.GenericPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Framework.Browser.Waits;
import Validacao.ExtratoValidacao;
import Validacao.LoginValidacao;

public class ExtratoTask {

    // Tempo que o extrato fica aberto na tela, para mostrar o recibo (em milissegundos)
    private static final long TEMPO_MOSTRAR_EXTRATO = 4000;

    private WebDriver driver;
    private HomePage homePage;
    private GenericPage genericPage;
    private ExtratoValidacao extratoValidacao;
    private LoginValidacao loginValidacao;
    private LoginPage loginPage;
    private Waits waits;

    public ExtratoTask(WebDriver driver) {
        this.driver = driver;
        homePage = new HomePage(this.driver);
        genericPage = new GenericPage(this.driver);
        extratoValidacao = new ExtratoValidacao(this.driver);
        loginValidacao = new LoginValidacao(this.driver);
        loginPage = new LoginPage(this.driver);
        waits = new Waits(this.driver);
    }

    // Abre o extrato, valida saldo e a transferência, mostra o recibo e faz logout
    public void validarExtrato(double saldoEsperado, String descricao) {
        waits.loadElement(homePage.getExtratoButton());
        homePage.getExtratoButton().click();
        extratoValidacao.validationSaldoExtrato(saldoEsperado);
        extratoValidacao.validationTransferenciaNoExtrato(descricao);

        pausar(TEMPO_MOSTRAR_EXTRATO);

        waits.loadElement(genericPage.getBackButton());
        genericPage.getBackButton().click();
        waits.loadElement(genericPage.getExitButton());
        genericPage.getExitButton().click();
        waits.loadElement(loginPage.getLoginButton());
        loginValidacao.loginValidation();
    }

    private void pausar(long milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}