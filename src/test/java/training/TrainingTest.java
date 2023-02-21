package training;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import core.BaseSelenideTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import pages.MainTrainingPage;


public class TrainingTest extends BaseSelenideTest {

    @Test
    public void buttonDynamicTest() {
        new MainTrainingPage().chooseTask("Dynamic ID").checkDynamicButton();
    }

    @Test
    @RepeatedTest(1)
    public void alertTest() {
        new MainTrainingPage().chooseTask("Class Attribute").clickBlueAndWindow();
    }

    @Test
    public void doubleTest() {
        new MainTrainingPage().chooseTask("Hidden Layers").checkTwiceButton();
    }
}