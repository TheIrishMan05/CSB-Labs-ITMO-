package Enums;

public enum Weather {
    RAINY("шёл дождь"),
    SUNNY("светит ярко солнце");
    private final String weather;

    Weather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return weather;
    }
}
