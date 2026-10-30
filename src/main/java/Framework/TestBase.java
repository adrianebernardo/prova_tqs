package Framework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import Framework.Browser.DriverManager;
import Framework.Browser.TypeBrowser;
import Framework.Utils.FileOperations;

public class TestBase extends DriverManager {

    private static WebDriver driver;
    private static String URL = FileOperations.getProperty("url");

    
    private static TypeBrowser getTypeBrowser() {
        String nome = System.getProperty("browser", FileOperations.getProperty("browser"));
        return TypeBrowser.valueOf(nome.trim().toUpperCase());
    }

    public static WebDriver getDriverManager() {
        if (driver == null) {
            driver = getManagerDrive(getTypeBrowser());
        }
        return driver;
    }

    @BeforeEach
    public void setUp() {
        getDriverManager().get(URL);
    }

    @AfterEach
    public void finish() {
        fecharDriver();
        driver = null;
    }
}