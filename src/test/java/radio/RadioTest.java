package radio;

import core.DataBaseTest;
import helpers.HelperDB;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MegaPage;
import pages.SevenPage;
import java.util.List;

public class RadioTest extends DataBaseTest {

    @Flaky
    @Epic("Песни")
    @Feature("Прослушивание песен")
    @Description("Прослушивание Радио 7")
    @Test
    public void playSevenTest() {
        String song = new SevenPage().playNow();
        Assertions.assertNotNull(song);
    }

    @Epic("Песни")
    @Feature("Прослушивание песен")
    @Description("Прослушивание радио Мегаполис")
    @Test
    public void playMegaTest() {
        String song = new MegaPage().nowSong();
        Assertions.assertNotNull(song);
    }

    @Epic("Песни")
    @Feature("Статистика песен")
    @Description("Статистика песен радио Мегаполис")
    @Test
    public void uiScannerMegaTest() {
        List<Song> songs = new MegaPage().lastThreeSongs();
        songs.forEach(x -> HelperDB.setRepeatedCount(x, statement));
        HelperDB.addListSong(songs, statement);
    }
}
