package sample;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.*;

import org.xml.sax.SAXException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene=new Scene(parent);
        primaryStage.setTitle("Table");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) throws Exception,SAXException,IOException {

        launch(args);

    }
    }


