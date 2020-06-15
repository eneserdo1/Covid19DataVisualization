package sample;

import Model.Country;
import Model.Grafik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnGet;

    @FXML
    private TableView<Country> tableCountry;

    @FXML
    private TableColumn<Country,String> columnId;

    @FXML
    private TableColumn<Country,String> columnName;

    @FXML
    private TableColumn<Country,String> columnContinent;
    @FXML
    private TableColumn<Country,Integer> columnCases;

    @FXML
    private TableColumn<Country,Integer> columnDeath;

    @FXML
    private TableColumn<Country,Integer> columnPopulation;

    @FXML
    private TableColumn<Country,Integer> columnDay;

    @FXML
    private TableColumn<Country,Integer> columnMonth;

    @FXML
    private TableColumn<Country,Integer> columnYear;

    @FXML
    private TableColumn<Country,String> columnDateRep;

    @FXML
    private TextField urlName;

    @FXML
    private ListView listviewData;

    @FXML
    private Button btnListele;

    @FXML
    private LineChart<String,Number> lineChart;

    @FXML
    private Button btnTemizle;

    @FXML
    private BarChart<String,Number> barChart;

    @FXML
    private LineChart<String,Number> lineChartDeath;

    @FXML
    private BarChart<String,Number> barChartDeath;



    private ObservableList<Country> data; //Genel verileri tutacağız
    private ObservableList<String> listData;  //Ülke isimlerini tutacağız


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initializ");

    }

    @FXML
    private void getData(ActionEvent event){
        try {
            data=FXCollections.observableArrayList();//data listesi oluşturuluyor
            listData=FXCollections.observableArrayList();//listviewda gösterilecek ülke isimleri için oluşturdum

            String format="xml";
            String url=urlName.getText().toString();
            URL obj=new URL(url);
            HttpURLConnection con=(HttpURLConnection)obj.openConnection();//Bağlantı açılıyor
            int responseCode=con.getResponseCode();
            System.out.println("Response code"+responseCode);

            BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputline;
            StringBuffer response=new StringBuffer();
            while ((inputline=in.readLine()) !=null){
                response.append(inputline);
            }
            in.close();
            System.out.println("response string"+response.toString());
            Document doc= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response.toString())));

            NodeList errNodes=doc.getElementsByTagName("record");//Hangi düğüm ismine bakacağını belirtiyoruz
            System.out.println("length ="+errNodes.getLength());

            for (int temp=0 ;temp< errNodes.getLength(); temp++){
            //Bütün düğümlerin arasını okumak için döngü oluşturuyoruz

                Node node=errNodes.item(temp);//Düğün node değişkenine atılıyor
                System.out.println("for başlangıcı");

                if (node.getNodeType() == node.ELEMENT_NODE){//Düğümün türüne bakılıyor element,root vs.

                    Element eror=(Element) node;
                    System.out.println("For Girdi");
                    //Burda düğümün içindeki değerleri alarak değişkenlere  atıyoruz
                    //Değerler string türünde alındığı için ınteger değerleri parserInt ile ınteger'a çeviriyoruz.

                    String date=eror.getElementsByTagName("dateRep").item(0).getTextContent();
                    System.out.println("date="+date);

                    Integer day=Integer.parseInt(eror.getElementsByTagName("day").item(0).getTextContent());
                    System.out.println("day="+day);

                    Integer month=Integer.parseInt(eror.getElementsByTagName("month").item(0).getTextContent());
                    System.out.println("month="+month);

                    Integer year=Integer.parseInt(eror.getElementsByTagName("year").item(0).getTextContent());
                    System.out.println("year="+year);

                    String territories=eror.getElementsByTagName("countriesAndTerritories").item(0).getTextContent();
                    System.out.println("territories="+territories);

                    Integer cases=Integer.parseInt(eror.getElementsByTagName("cases").item(0).getTextContent());
                    System.out.println("cases="+cases);

                    Integer death=Integer.parseInt(eror.getElementsByTagName("deaths").item(0).getTextContent());
                    System.out.println("death="+death);

                    String geoId=eror.getElementsByTagName("geoId").item(0).getTextContent();
                    System.out.println("geoıd="+geoId);

                    String continent=eror.getElementsByTagName("continentExp").item(0).getTextContent();
                    System.out.println("continent="+continent);


                    //Bazı ülkelerin popData verisi bulunmuyor burada onu kontrol ediyoruz eğer yoksa 0 değerini veriyoruz
                    String popStr =eror.getElementsByTagName("popData2018").item(0).getTextContent();
                    if (popStr.equals("")){
                        popStr="0";
                    }
                    Integer popdata=Integer.parseInt(popStr);


                    //Verileri Country tipinde modelleyerek yukarıda oluşturduğumuz "data" listesine ekliyoruz
                    data.add(new Country(date, day, month, year, territories, cases, death, geoId, continent, popdata));
                    listData.add(territories);

                    System.out.println("size =" + data.size());
                    System.out.println("değer çekti");




                    }else {

                }
            }


        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println("set kısmı geldi");

        //burda tablodaki kolonlara verileri set ediyoruz.Modelde ki hangi ver hangi kolonun belirtiyoruz
        columnDateRep.setCellValueFactory(countryStringCellDataFeatures -> countryStringCellDataFeatures.getValue().dateRepProperty());
        columnDay.setCellValueFactory(countryIntegerCellDataFeatures -> countryIntegerCellDataFeatures.getValue().dayProperty().asObject());
        columnMonth.setCellValueFactory(countryIntegerCellDataFeatures -> countryIntegerCellDataFeatures.getValue().monthProperty().asObject());
        columnYear.setCellValueFactory(countryIntegerCellDataFeatures -> countryIntegerCellDataFeatures.getValue().yearProperty().asObject());
        columnName.setCellValueFactory(countryStringCellDataFeatures -> countryStringCellDataFeatures.getValue().countryNameProperty());
        columnCases.setCellValueFactory(countryIntegerCellDataFeatures -> countryIntegerCellDataFeatures.getValue().casesProperty().asObject());
        columnDeath.setCellValueFactory(countryIntegerCellDataFeatures -> countryIntegerCellDataFeatures.getValue().deathProperty().asObject());
        columnId.setCellValueFactory(countryStringCellDataFeatures -> countryStringCellDataFeatures.getValue().countryIdProperty());
        columnContinent.setCellValueFactory(countryStringCellDataFeatures -> countryStringCellDataFeatures.getValue().continentProperty());
        columnPopulation.setCellValueFactory(countryIntegerCellDataFeatures -> countryIntegerCellDataFeatures.getValue().popDataProperty().asObject());

        listviewData.setItems(listData);//Listviewa listdata listesini set ediyoruz


        tableCountry.setItems(null);
        tableCountry.setItems(data);//data'yı tabloya set ediyoruz

    }

    @FXML
    private void grafikCiz(ActionEvent event){

        int index= listviewData.getSelectionModel().getSelectedIndex();//listViewdaki indexi döndürüyor
        String ülke= listData.get(index);//burdada yukardaki indexe göre listedeki ülke adını döndürüyor
        List<Grafik> grafiks=new ArrayList<>();//Chart'larda kullanılacak veriler için Grafik modelinde liste oluşturuyoruz

        for (Country currentValue : data){//Genel verileri tuttuğumuz Country türündeki data listesini döngüye sokarak inceliyoruz

            if (currentValue.getCountryName().equals(ülke)){
                //CurrentValueda'ki ülke ismi listviewda seçtiğimizle aynıysa currentValueda'ki Grafik modelindeki verilere göre grafiks listesine  ekliyoruz

                grafiks.add(new Grafik(currentValue.getCases(),currentValue.getCountryName(),currentValue.getDateRep(),currentValue.getDeath()));
                System.out.println("Size grafik ="+grafiks.size());
            }
            System.out.println("if dışı");

        }

        XYChart.Series<String,Number> series=new XYChart.Series<String, Number>();//Case chartları için series oluşturuldu
        series.setName(ülke);
        for (Grafik grafikDeger : grafiks){
            series.getData().add(new XYChart.Data<>(grafikDeger.getDate(),grafikDeger.getTotalCase()));//Chart için değerler set ediliyor

        }
        XYChart.Series<String,Number> series1=new XYChart.Series<>();
        series.setName(ülke);
        for (Grafik grafikDeger2 :grafiks){
            series1.getData().add(new XYChart.Data<>(grafikDeger2.getDate(),grafikDeger2.getDeath()));
        }

        lineChart.getData().add(series);//series'ler chartlara set ediliyor
        barChart.getData().add(series);
        lineChartDeath.getData().add(series1);
        barChartDeath.getData().add(series1);




    }

    @FXML
    private void Temizle(ActionEvent event){
        //Grafiklerdeki veriler temizleniyor
        lineChart.getData().clear();
        barChart.getData().clear();
        barChartDeath.getData().clear();
        lineChartDeath.getData().clear();

    }

}
