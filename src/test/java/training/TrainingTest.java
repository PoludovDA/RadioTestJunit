package training;

import core.BaseSelenideTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainTrainingPage;


public class TrainingTest extends BaseSelenideTest {

    @Epic("Проблемный фронт")
    @Description("Тестирование проблемной кнопки")
    @Test
    public void buttonDynamicTest() {
        new MainTrainingPage().chooseTask("Dynamic ID").checkDynamicButton();
    }

    @Epic("Проблемный фронт")
    @Description("Тестирование аллерта")
    @RepeatedTest(1)
    public void alertTest() {
        new MainTrainingPage().chooseTask("Class Attribute").clickBlueAndWindow();
    }

    @Epic("Проблемный фронт")
    @Description("Тестирование проблемного клика")
    @Test
    public void doubleTest() {
        new MainTrainingPage().chooseTask("Hidden Layers").checkTwiceButton();
    }

    @Epic("Проблемный фронт")
    @ValueSource(strings = {"great", "big", "Denis"})
    @Description("Проверка длины строки")
    @ParameterizedTest
    public void strParamTest(String word) {
        Assertions.assertEquals(5, word.length());
    }
}