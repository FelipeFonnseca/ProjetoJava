package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Celulares;

public class DetailsController {
    private Celulares marcaAtual;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfAno;
    @FXML
    private TextField tfModelo;


    @FXML
    protected void initialize(){
        main.addOnChangeScreenListiner(new main.OnChangeScreen() {
            @Override
            public void onScreenChange(String newScreen, Object userData) {

                if (newScreen.equals("details")){
                    if (userData != null){
                        marcaAtual = (Celulares)userData;

                        tfMarca.setText(marcaAtual.getMarca());
                        tfModelo.setText(marcaAtual.getModelo());
                        tfAno.setText(String.valueOf(marcaAtual.getAno()));
                    } else {
                        marcaAtual = null;
                        tfMarca.setText("");
                        tfModelo.setText("");
                        tfAno.setText("");
                    }
                }
            }
        });
    }

    @FXML
    protected void btCancelarAction(ActionEvent e){
        main.changeScreen("main");
    }
    @FXML
    protected void btOKAction(ActionEvent e){
        try {
         if (tfMarca.getText().isEmpty() && tfModelo.getText().isEmpty() && tfAno.getText().isEmpty())
             throw new RuntimeException("Algum campo se encontra vazio, por favor preencha todos");

         if (marcaAtual != null){
            marcaAtual.setMarca(tfMarca.getText());
            marcaAtual.setModelo(tfModelo.getText());
            marcaAtual.setAno(Integer.parseInt(tfAno.getText()));

            marcaAtual.save();
         }else {
        Celulares v = new Celulares(
                tfMarca.getText(),
                tfModelo.getText(),
                Integer.parseInt(tfAno.getText()) );
            v.save();
         }
        main.changeScreen("main");
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao adionar um celular");
            alert.setContentText("O ano dev ser um valor numérico");
            alert.showAndWait();
        } catch (RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao adionar um celular");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}
