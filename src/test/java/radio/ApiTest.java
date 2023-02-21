package radio;

import core.DataBaseTest;
import helpers.HelperDB;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.SevenPage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest extends DataBaseTest {

    @Epic("Радио")
    @Story("Логотип радио")
    @Description("Проверка отображения логотипа Радио 7")
    @Test
    public void logoTest() throws IOException {
        File file = new File("src//test//resources//downloads//actualLogo.jpg");
        if (!file.exists()) {
            byte[] actualLogotype = given()
                    .when()
                    .get("https://radio7.ru/media/default/images/logo.svg?2")
                    .asByteArray();
            byte[] expectedLogotype = Files.readAllBytes(Paths.get("src//test//resources//downloads//expectedLogo.svg"));
            Assertions.assertArrayEquals(actualLogotype, expectedLogotype);

            try (FileOutputStream outputStream = new FileOutputStream(file)){
                outputStream.write(actualLogotype);
            }
        }
    }

    @Epic("Радио")
    @Feature("Статистика песен")
    @Description("Статистика песен Радио 7")
    @Test
    public void radioSevenSongScanner() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://radio7.ru/on_air/onair.json.js?_=1676961938786")
                .then().log().all()
                .extract().response();

        SevenPage.jsonTracks(response.asString());
        JsonPath jsonPath = response.jsonPath();
        List<String> artists = jsonPath.getList("playlist.artist.name");
        List<String> songs = jsonPath.getList("playlist.song.name");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date = simpleDateFormat.format(new Date());

        List<Song> tracks = new ArrayList<>();

        for (int i = 0; i < artists.size(); i++) {
            tracks.add(new Song.SongBuilder()
                    .setSong(songs.get(i))
                    .setArtist(artists.get(i))
                    .setDate(date)
                    .setRadio(Radio.RADIO_7)
                    .build());
        }

        tracks.forEach(x -> HelperDB.setRepeatedCount(x, statement));
        HelperDB.addListSong(tracks, statement);
    }
}
