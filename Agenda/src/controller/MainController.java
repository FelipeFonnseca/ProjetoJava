package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Veiculo;

import java.util.Optional;


public class MainController {

    @FXML
    protected ListView<Veiculo> lvVeiculos;

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
        ObservableList<Veiculo> ol = lvVeiculos.getSelectionModel().getSelectedItems();
        if (ol.isEmpty()) {
            Veiculo v = ol.get(0);

            main.changeScreen("details", v);
        }
    }

    @FXML
    protected  void btDeleteAction(ActionEvent e) {
        ObservableList<Veiculo> ol = lvVeiculos.getSelectionModel().getSelectedItems();
        if (ol.isEmpty()) {
            Veiculo v = ol.get(0);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Deseja realmente excluir esse veiculo?");
            alert.setContentText(v.toString());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                v.delete();
                updateList();
            } else {
                Alert erro = new Alert(Alert.AlertType.INFORMATION);
                erro.setTitle("Informação");
                erro.setHeaderText("Nenhum veiculo selecionado");
                erro.setContentText("Selecione algum veiculo da tabela");
                erro.showAndWait();
            }
        }
    }

    private void updateList(){
        lvVeiculos.getItems().clear();
        for(Veiculo v :Veiculo.all()){
           lvVeiculos.getItems().add(v);
        }
    }
}
