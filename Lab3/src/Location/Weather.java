package Location;

public enum Weather
{
    RAINY("шёл дождь");
    private String weather;
    Weather(String weather){
        this.weather = weather;
    }

    @Override
    public String toString() {
        return weather;
    }
}
