package helpers;

import io.qameta.allure.Step;
import radio.Song;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class HelperDB {

    @Step("Добавление списка песен в БД")
    public static void addListSong(List<Song> songs, Statement statement) {
        songs.forEach(x -> {
            try {
                String query = String.format("INSERT INTO %s VALUES ('%s', '%s', '%s', %d);",
                        x.getRadio(), x.getSong(), x.getArtist(), x.getDate(), x.getRepeated());
                statement.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
                throw new RuntimeException(e);
            }
        });
    }

    @Step("Добавление песни в БД")
    public static void addSong(Song song, Statement statement) {
        try {
            statement.executeQuery(String.format("INSERT INTO %s VALUES ('%s', '%s', '%s', %d);",
                    song.getRadio(), song.getSong(), song.getArtist(), song.getDate(), song.getRepeated()));
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @Step("Установка количества проигрываний песни на радио")
    public static void setRepeatedCount(Song song, Statement statement) {
        try {
            String repeated = null;
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE song = '%s' AND artist = '%s';",
                    song.getRadio(), song.getSong(), song.getArtist()));
            if (resultSet.next())
                repeated = resultSet.getString("repeated");
            if (repeated == null) {
                song.setRepeated(1);
                return;
            }
            statement.executeUpdate(String.format("DELETE FROM %s WHERE song = '%s' and artist = '%s';",
                    song.getRadio(), song.getSong(), song.getArtist()));
            song.setRepeated(Integer.parseInt(repeated) + 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

