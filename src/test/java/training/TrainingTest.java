package training;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import core.BaseSelenideTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import pages.MainTrainingPage;


public class TrainingTest extends BaseSelenideTest {

    @Epic("Проблемный фронт")
    @Description("Тестирование проблемной кнопки")
    @Test
    public void buttonDynamicTest() {
        new MainTrainingPage().chooseTask("Dynamic ID").checkDynamicButton();
    }

    @Epic("Проблемный фронт")
    @RepeatedTest(1)
    @Description("Тестирование аллерта")
    @Test
    public void alertTest() {
        new MainTrainingPage().chooseTask("Class Attribute").clickBlueAndWindow();
    }

    @Epic("Проблемный фронт")
    @Description("Тестирование проблемного клика")
    @Test
    public void doubleTest() {
        new MainTrainingPage().chooseTask("Hidden Layers").checkTwiceButton();
    }
}