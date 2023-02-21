package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import radio.Radio;
import radio.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MegaPage {

    private final SelenideElement nowPlay = $x("//span[@id='cur_song_main']");
    private final ElementsCollection songs = $$x("//h3[text()='Только что звучали']/following-sibling::ul/li");

    public MegaPage() {
        Selenide.open("https://www.radio-megapolis.ru/");
    }

    public String nowSong() {
        return nowPlay.shouldBe(visible).text().split("\n")[1];
    }

    public List<Song> lastThreeSongs() {
        List<Song> res = new ArrayList<>();
        songs.get(0).shouldBe(visible);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date = simpleDateFormat.format(new Date());
        for (int i = 0; i < 3; i++) {
            res.add(new Song.SongBuilder()
                    .setSong(songs.get(i).text().split(" — ")[1])
                    .setArtist(songs.get(i).text().split(" — ")[0])
                    .setDate(date)
                    .setRadio(Radio.MEGAPOLIS)
                    .build());
        }
        return res;
    }
}
