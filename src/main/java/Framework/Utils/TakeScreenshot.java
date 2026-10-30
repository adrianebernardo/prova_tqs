package Framework.Utils;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

public class TakeScreenshot {

    // Tira um print da tela e anexa ao relatório do Allure
    public static void screenshotPage(WebDriver webDriver, String name) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshotBytes), "png");
        } catch (Exception e) {
            // se não der para tirar o print, não esconde o erro real do teste
            System.out.println("Nao foi possivel tirar o print: " + name);
        }
    }
}
