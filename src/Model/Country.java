package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country {
    private SimpleStringProperty dateRep;
    private SimpleIntegerProperty day;
    private SimpleIntegerProperty month;
    private SimpleIntegerProperty year;
    private SimpleStringProperty countryName;
    private SimpleIntegerProperty cases;
    private SimpleIntegerProperty death;
    private SimpleStringProperty countryId;
    private SimpleStringProperty continent;
    private SimpleIntegerProperty popData;

    public Country() {

    }

    public Integer getDay() {
        return day.get();
    }

    public void setDay(Integer value) {
        this.day.set(value);
    }

    public Integer getMonth() {
        return month.get();
    }

    public void setMonth(Integer value) {
        this.month.set(value);
    }

    public Integer getYear() {
        return year.get();
    }

    public void setYear(Integer value) {
        this.year.set(value);
    }

    public String getCountryName() {
        return countryName.get();
    }

    public void setCountryName(String value) {
        this.countryName.set(value);
    }

    public Integer getCases() {
        return cases.get();
    }

    public void setCases(Integer value) {
        this.cases.set(value);
    }

    public Integer getDeath() {
        return death.get();
    }

    public void setDeath(Integer value) {
        this.death.set(value);
    }

    public String getCountryId() {
        return countryId.get();
    }

    public void setCountryId(String value) {
        this.countryId.set(value);
    }

    public String getContinent() {
        return continent.get();
    }

    public void setContinent(String value) {
        this.continent.set(value);
    }

    public Integer getPopData() {
        return popData.get();
    }

    public void setPopData(Integer value) {
        this.popData.set(value);
    }
    public String getDateRep(){
        return dateRep.get();
    }
    public void setDateRep(String value){
        this.dateRep.set(value);
    }


    public Country(String dateRep,Integer day, Integer month, Integer year, String countryName, Integer cases, Integer death, String countryId, String continent, Integer popData) {
        this.dateRep=new SimpleStringProperty(dateRep);
        this.day = new SimpleIntegerProperty(day);
        this.month = new SimpleIntegerProperty(month);
        this.year = new SimpleIntegerProperty(year);
        this.countryName = new SimpleStringProperty(countryName);
        this.cases = new SimpleIntegerProperty(cases);
        this.death = new SimpleIntegerProperty(death);
        this.countryId = new SimpleStringProperty(countryId);
        this.continent = new SimpleStringProperty(continent);
        this.popData = new SimpleIntegerProperty(popData);
    }

    @Override
    public String toString() {
        return "Country [dateRep="+dateRep+",day="+ day+",month="+month+",year="+year+",countryName="+countryName+",cases="+cases+",death="+death+",countryId="+countryId+",continent="+continent+",popdata="+popData+"]";
    }

    public StringProperty dateRepProperty(){
        return dateRep;
    }
    public IntegerProperty dayProperty(){
        return day;
    }
    public IntegerProperty monthProperty(){
        return month;
    }
    public IntegerProperty yearProperty(){
        return year;
    }
    public StringProperty countryNameProperty(){
        return countryName;
    }
    public IntegerProperty casesProperty(){
        return cases;
    }
    public IntegerProperty deathProperty(){
        return death;
    }
    public StringProperty countryIdProperty(){
        return countryId;
    }
    public StringProperty continentProperty(){
        return continent;
    }
    public IntegerProperty popDataProperty(){
        return popData;
    }
}
