package Model;

public class Grafik {

    private Integer totalCase;
    private String countryName;
    private String date;
    private Integer death;



    public Grafik(Integer totalCase, String countryName, String date, Integer death) {
        this.totalCase = totalCase;
        this.countryName = countryName;
        this.date = date;
        this.death=death;
    }

    public Integer getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(Integer totalCase) {
        this.totalCase = totalCase;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }
}
