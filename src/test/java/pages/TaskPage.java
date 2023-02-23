package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoAlertPresentException;

import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {
    private final static SelenideElement dynamicButton = $x("//button[contains(text(),'Button')]");
    private final static SelenideElement blueButton = $x("//button[contains(@class,'primary')]");
    private final static SelenideElement twiceButton = $x("//button[text()='Button']");

    @Step("Клик по динамической кнопке")
    public void checkDynamicButton() {
        dynamicButton.shouldBe(Condition.enabled);
    }

    @Step("Обработка аллерта")
    public void clickBlueAndWindow() {
        int k = 0;
        String alertMessage = null;
        while (k < 3) {
            try {
                blueButton.shouldBe(Condition.enabled).click();
                alertMessage = WebDriverRunner.getWebDriver().switchTo().alert().getText();
                k++;
            } catch (NoAlertPresentException exception) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Аллерт не появился, жмем еще раз");
            }
        }

        Assertions.assertEquals("Primary button pressed", alertMessage);
    }

    @Step("Проверка двойного клика")
    public void checkTwiceButton() {
        twiceButton.click();
        twiceButton.shouldBe(Condition.enabled);
    }
}
