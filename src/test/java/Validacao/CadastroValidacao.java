package Validacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import PageObjects.GenericPage;

public class CadastroValidacao {

    private WebDriver driver;
    private GenericPage genericPage;

    public CadastroValidacao(WebDriver driver) {
        this.driver = driver;
        genericPage = new GenericPage(this.driver);
    }

    public void validationContaCriada() {
        try {
            String texto = genericPage.getModalText().getText();
            Assertions.assertTrue(texto.contains("criada com sucesso"));
        } catch (Exception e) {
            Assertions.fail("Falha ao validar a criação da conta: " + e.getMessage());
        }
    }

    // Lê "A conta 123-4 foi criada com sucesso" e devolve {numero, digito}
    public String[] obterNumeroConta() {
        String texto = genericPage.getModalText().getText();
        Matcher matcher = Pattern.compile("(\\d+)-(\\d)").matcher(texto);
        Assertions.assertTrue(matcher.find(), "Número da conta não encontrado: " + texto);
        return new String[] { matcher.group(1), matcher.group(2) };
    }
}
