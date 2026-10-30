package TestCases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import Framework.TestBase;
import Framework.Utils.FileOperations;
import Framework.Utils.TakeScreenshot;
import Tasks.CadastroTask;
import Tasks.ExtratoTask;
import Tasks.LoginTask;
import Tasks.TransferenciaTask;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Teste Banco Digital")
@Feature("Realizar Transferência com Sucesso")
public class RealizarTransferenciaComSucessoTest extends TestBase {

    
    private static final String SENHA = FileOperations.getProperty("senha");
    private static final double SALDO_INICIAL = FileOperations.getPropertyDouble("saldoInicial");
    private static final double VALOR_TRANSFERENCIA = FileOperations.getPropertyDouble("valorTransferencia");
    private static final String DESCRICAO = FileOperations.getProperty("descricao");

    
    private static final long PAUSA = 1500;

    private WebDriver driver;
    private CadastroTask cadastroTask;
    private LoginTask loginTask;
    private TransferenciaTask transferenciaTask;
    private ExtratoTask extratoTask;

    @BeforeEach
    public void setUpTasks() {
        driver = getDriverManager();
        cadastroTask = new CadastroTask(driver);
        loginTask = new LoginTask(driver);
        transferenciaTask = new TransferenciaTask(driver);
        extratoTask = new ExtratoTask(driver);
    }

    private void pausar(long milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    
    @Test
    @Story("Usuário transfere dinheiro entre duas contas com saldo")
    @Description("Cadastra duas contas, transfere da origem para o destino e valida o extrato das duas")
    public void realizarTransferencia() {
        
        String nomeOrigem = "Adriane";
        String nomeDestino = "Fran";
        String emailOrigem = "adrianemelhoraluna@gmail.com";
        String emailDestino = "franmelhorprof@gmail.com";

        try {
            pausar(PAUSA);

            // 1 e 2) Cadastra as duas contas, ambas com saldo
            cadastroTask.cadastrarContaComSaldo(emailOrigem, nomeOrigem, SENHA);
            pausar(PAUSA);
            String[] contaDestino = cadastroTask.cadastrarContaComSaldo(emailDestino, nomeDestino, SENHA);
            pausar(PAUSA);

            // 3) Acessa a conta de origem e transfere
            loginTask.efetuarLogin(emailOrigem, SENHA, SALDO_INICIAL);
            pausar(PAUSA);
            transferenciaTask.realizarTransferencia(contaDestino, VALOR_TRANSFERENCIA, DESCRICAO);
            pausar(PAUSA);

            // 4) Valida o extrato da conta que enviou o valor
            extratoTask.validarExtrato(SALDO_INICIAL - VALOR_TRANSFERENCIA, DESCRICAO);
            pausar(PAUSA);

            // 5) Acessa a conta de destino e mostra o recibo recebido
            loginTask.efetuarLogin(emailDestino, SENHA, SALDO_INICIAL + VALOR_TRANSFERENCIA);
            pausar(PAUSA);
            extratoTask.validarExtrato(SALDO_INICIAL + VALOR_TRANSFERENCIA, DESCRICAO);

        } catch (Exception | AssertionError e) {
            // se algo falhar, anexa um print do erro ao relatório e repassa o erro
            TakeScreenshot.screenshotPage(driver, "ERRO");
            throw e;
        }
    }
}
