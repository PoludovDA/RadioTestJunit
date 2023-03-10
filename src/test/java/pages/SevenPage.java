package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class SevenPage {
    private final ElementsCollection trackNow = $$x("//li[12]//span[@class='holder']/span");

    public SevenPage() {
        open("https://radio7.ru/");
        $(byText("Нет, спасибо")).click();
        $(byText("Да, верно")).click();
    }

    @Step("Получение играющей сейчас песни")
    public String playNow() {
        imageLogo("actualLogo.jpg");
        return trackNow.get(0).shouldBe(visible).text() + " - " + trackNow.get(1).text();
    }

    @Attachment
    public static byte[] imageLogo(String fileName) {

        try {
            return Files.readAllBytes(Paths.get("src/test/resources/downloads", fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Attachment(value = "Вложение", type = "application/json")
    public static byte[] jsonTracks(String name) {
        File file = new File("src//test//resources//downloads//trackResponse.json");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(name.getBytes());
            return Files.readAllBytes(Paths.get("src//test//resources//downloads//trackResponse.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
