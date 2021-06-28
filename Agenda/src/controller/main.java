package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Veiculo;
import model.sqlite.VeiculoSQLiteDAO;

import java.util.ArrayList;

public class main extends Application {

    private static Stage stage;

    private static Scene mainsScene;
    private static Scene detailsScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        primaryStage.setTitle("Inicio");

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        mainsScene = new Scene(fxmlMain, 640, 400);

        Parent fxmlDetails = FXMLLoader.load(getClass().getResource("../view/details_creen.fxml"));
        detailsScene = new Scene(fxmlDetails, 640, 400);

        primaryStage.setScene(mainsScene);
        primaryStage.show();
    }

    public static void changeScreen(String src, Object userData) {
        switch (src) {
            case "main" :
                stage.setScene(mainsScene);
                notifyAllListiners("main", userData);
                break;
            case "details":
                stage.setScene(detailsScene);
                notifyAllListiners("details", userData);
                break;
        }
    }

    public static void changeScreen(String src){
        changeScreen(src, null);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static ArrayList<OnChangeScreen> listiners = new ArrayList<>();

    public static interface  OnChangeScreen{
        void onScreenChange(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListiner(OnChangeScreen newListiner) {
        listiners.add(newListiner);
    }

    private static void notifyAllListiners(String newScreen, Object userData){
        for (OnChangeScreen l : listiners)
            l.onScreenChange(newScreen, userData);
    }
}
