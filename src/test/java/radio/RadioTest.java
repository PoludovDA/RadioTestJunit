package radio;

import core.BaseSelenideTest;
import core.DataBaseTest;
import helpers.HelperDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MegaPage;
import pages.SevenPage;

import java.io.File;
import java.util.List;

public class RadioTest extends DataBaseTest {

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

    @Test
    public void uiScannerMegaTest() {
        List<Song> songs = new MegaPage().lastThreeSongs();
        songs.forEach(x -> HelperDB.setRepeatedCount(x, statement));
        HelperDB.addListSong(songs, statement);
    }
}
