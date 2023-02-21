package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.BaseSelenideTest;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SevenPage {
    private final SelenideElement playButton = $$x("//*[@class='mp-bg']").get(0);
    private final ElementsCollection trackNow = $$x("//li[12]//span[@class='holder']/span");
    private final SelenideElement logotype = $x("//header//img");

    public SevenPage() {
        open("https://radio7.ru/");
        $(byText("Нет, спасибо")).click();
        $(byText("Да, верно")).click();
    }

    public String playNow() {
        return trackNow.get(0).shouldBe(visible).text() + " - " + trackNow.get(1).text();
    }
}
