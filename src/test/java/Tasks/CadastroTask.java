package Tasks;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CadastroPage;
import PageObjects.GenericPage;
import PageObjects.LoginPage;
import Validacao.CadastroValidacao;
import Validacao.GenericValidacao;

public class CadastroTask {

    private WebDriver driver;
    private LoginPage loginPage;
    private CadastroPage cadastroPage;
    private GenericPage genericPage;
    private GenericValidacao genericValidacao;
    private CadastroValidacao cadastroValidacao;

    public CadastroTask(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        cadastroPage = new CadastroPage(this.driver);
        genericPage = new GenericPage(this.driver);
        genericValidacao = new GenericValidacao(this.driver);
        cadastroValidacao = new CadastroValidacao(this.driver);
    }

    // Cadastra uma conta COM saldo e devolve {numeroConta, digito}
    public String[] cadastrarContaComSaldo(String email, String nome, String senha) {
        // Recarrega a página para o formulário voltar limpo (toggle desligado, campos vazios)
        driver.navigate().refresh();

        clicarPorJs(loginPage.getRegistrarButton());

        digitar(cadastroPage.getEmailInput(), email);
        digitar(cadastroPage.getNomeInput(), nome);
        digitar(cadastroPage.getPasswordInput(), senha);
        digitar(cadastroPage.getPasswordConfirmationInput(), senha);

        clicarPorJs(cadastroPage.getSaldoToggle());
        clicarPorJs(cadastroPage.getCadastrarButton());

        cadastroValidacao.validationContaCriada();
        String[] conta = cadastroValidacao.obterNumeroConta();

        genericPage.getModalCloseButton().click();
        genericValidacao.aguardarModalFechar();
        voltarParaLogin();
        return conta;
    }

    // Clique por JavaScript: não é barrado por outro elemento na frente (animação dos cards)
    private void clicarPorJs(WebElement elemento) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
    }

    // Limpa o campo antes de digitar, sem clicar nele (o sendKeys já foca o campo)
    private void digitar(WebElement campo, String texto) {
        campo.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        campo.sendKeys(texto);
    }

    // Volta para o login e espera a tela de login aparecer antes do próximo passo
    private void voltarParaLogin() {
        try {
            WebElement voltar = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//a[normalize-space()='Voltar ao login']")));
            clicarPorJs(voltar);
        } catch (TimeoutException e) {
            // o link não apareceu, então a tela já está no login
        }
        loginPage.getLoginButton();
    }
}