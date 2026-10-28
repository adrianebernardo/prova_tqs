package TestCases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import Framework.TestBase;
import Framework.Utils.FileOperations;
import Tasks.CadastroTask;
import Tasks.ExtratoTask;
import Tasks.LoginTask;
import Tasks.TransferenciaTask;

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
    public void realizarTransferencia() {
        
        String nomeOrigem = "Adriane";
        String nomeDestino = "Fran";
        String emailOrigem = "adrianemelhoraluna@gmail.com";
        String emailDestino = "franmelhorprof@gmail.com";

        pausar(PAUSA);

        
        cadastroTask.cadastrarContaComSaldo(emailOrigem, nomeOrigem, SENHA);
        pausar(PAUSA);
        String[] contaDestino = cadastroTask.cadastrarContaComSaldo(emailDestino, nomeDestino, SENHA);
        pausar(PAUSA);

        
        loginTask.efetuarLogin(emailOrigem, SENHA, SALDO_INICIAL);
        pausar(PAUSA);
        transferenciaTask.realizarTransferencia(contaDestino, VALOR_TRANSFERENCIA, DESCRICAO);
        pausar(PAUSA);

        
        extratoTask.validarExtrato(SALDO_INICIAL - VALOR_TRANSFERENCIA, DESCRICAO);
        pausar(PAUSA);

       
        loginTask.efetuarLogin(emailDestino, SENHA, SALDO_INICIAL + VALOR_TRANSFERENCIA);
        pausar(PAUSA);
        extratoTask.validarExtrato(SALDO_INICIAL + VALOR_TRANSFERENCIA, DESCRICAO);
    }
}