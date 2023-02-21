package radio;

import core.BaseSelenideTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MegaPage;
import pages.SevenPage;

import java.io.File;

public class RadioTest extends BaseSelenideTest {

    @Test
    public void playSevenTest() {
        String song = new SevenPage().playNow();
        Assertions.assertNotNull(song);
    }

    @Test
    public void playMegaTest() {
        String song = new MegaPage().nowSong();
        Assertions.assertNotNull(song);
    }
}
