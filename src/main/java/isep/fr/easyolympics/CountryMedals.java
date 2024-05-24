package isep.fr.easyolympics;

public class CountryMedals {

    private String country;
    private int goldCount;
    private int silverCount;
    private int bronzeCount;

    public CountryMedals(String country, int goldCount, int silverCount, int bronzeCount) {
        this.country = country;
        this.goldCount = goldCount;
        this.silverCount = silverCount;
        this.bronzeCount = bronzeCount;
    }

    public String getCountry() {
        return country;
    }

    public int getGoldCount() {
        return goldCount;
    }

    public int getSilverCount() {
        return silverCount;
    }

    public int getBronzeCount() {
        return bronzeCount;
    }
}
