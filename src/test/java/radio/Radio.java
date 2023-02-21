package radio;

public enum Radio {
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
