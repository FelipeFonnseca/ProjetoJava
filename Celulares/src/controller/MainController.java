package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Celulares;

import java.util.Optional;


public class MainController {

    @FXML
    protected ListView<Celulares> lvPhone;

    @FXML
    protected void initialize(){
        main.addOnChangeScreenListiner(new main.OnChangeScreen() {
            @Override
            public void onScreenChange(String newScreen, Object userData) {
                if (newScreen.equals("main")){
                    updateList();
                }
            }
        });
        updateList();
    }

    @FXML
    protected  void btNovoAction(ActionEvent e) {
        main.changeScreen("details");
    }

    @FXML
    protected void btEditarAction(ActionEvent e){
        ObservableList<Celulares> ol = lvPhone.getSelectionModel().getSelectedItems();
        if (ol.isEmpty()) {
            Celulares v = ol.get(0);

            main.changeScreen("details", v);
        }
    }

    @FXML
    protected  void btDeleteAction(ActionEvent e) {
        ObservableList<Celulares> ol = lvPhone.getSelectionModel().getSelectedItems();
        if (ol.isEmpty()) {
            Celulares v = ol.get(0);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Deseja realmente excluir esse celular?");
            alert.setContentText(v.toString());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                v.delete();
                updateList();
            } else {
                Alert erro = new Alert(Alert.AlertType.INFORMATION);
                erro.setTitle("Informação");
                erro.setHeaderText("Nenhum Celular selecionado");
                erro.setContentText("Selecione algum celular da tabela");
                erro.showAndWait();
            }
        }
    }

    private void updateList(){
        lvPhone.getItems().clear();
        for(Celulares v : Celulares.all()){
           lvPhone.getItems().add(v);
        }
    }
}
