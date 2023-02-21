package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MegaPage {

    private final SelenideElement nowPlay = $x("//span[@id='cur_song_main']");

    public MegaPage() {
        Selenide.open("https://www.radio-megapolis.ru/");
    }

    public String nowSong() {
        return nowPlay.shouldBe(visible).text().split("\n")[1];
    }
}
