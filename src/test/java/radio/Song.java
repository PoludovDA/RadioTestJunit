package radio;

import java.util.Date;

public class Song {
    private String song;
    private String artist;
    private String date;
    private int repeated;
    private Radio radio;

    private Song(SongBuilder builder) {
        this.song = builder.song;
        this.artist = builder.artist;
        this.date = builder.date;
        this.repeated = builder.repeated;
        this.radio = builder.radio;
    }

    public String getSong() {
        return song;
    }

    public String getArtist() {
        return artist;
    }

    public String getDate() {
        return date;
    }

    public int getRepeated() {
        return repeated;
    }

    public String getRadio() {
        return radio.getRadioName();
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRepeated(int repeated) {
        this.repeated = repeated;
    }

    public void setRadio(Radio radio) {
        this.radio = radio;
    }

    static class SongBuilder {
        private String song;
        private String artist;
        private String date;
        private int repeated;
        private Radio radio;

        public SongBuilder setSong(String song) {
            if (song.contains("'")) {
                this.song = song.replace('\'', ' ');
                return this;
            }
            this.song = song;
            return this;
        }

        public SongBuilder setArtist(String artist) {
            if (artist.contains("'")) {
                this.artist = artist.replace('\'', ' ');
                return this;
            }
            this.artist = artist;
            return this;
        }

        public SongBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public SongBuilder setRepeated(int repeated) {
            this.repeated = repeated;
            return this;
        }

        public SongBuilder setRadio(Radio radio) {
            this.radio = radio;
            return this;
        }

        public Song build() {
            return new Song(this);
        }
    }
}

enum Radio {
    MEGAPOLIS("megapolis"),
    RADIO_7("radio_7");

    private final String name;

    Radio(String name) {
        this.name = name;
    }

    public String getRadioName() {
        return this.name;
    }
}
