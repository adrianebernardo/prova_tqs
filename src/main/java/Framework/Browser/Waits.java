package Framework.Browser;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    private static final Duration TEMPO_PADRAO = Duration.ofSeconds(10);

    private WebDriver driver;

    public Waits(WebDriver driver) {
        this.driver = driver;
    }

    
    public WebElement visibilityOfElement(By by) {
        return new WebDriverWait(driver, TEMPO_PADRAO)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    
    public boolean invisibilityOfElement(By by) {
        return new WebDriverWait(driver, TEMPO_PADRAO)
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
