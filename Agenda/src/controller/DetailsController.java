package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Veiculo;

public class DetailsController {
    private Veiculo veiculoAtual;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfHP;
    @FXML
    private TextField tfModelo;


    @FXML
    protected void initialize(){
        main.addOnChangeScreenListiner(new main.OnChangeScreen() {
            @Override
            public void onScreenChange(String newScreen, Object userData) {

                if (newScreen.equals("details")){
                    if (userData != null){
                        veiculoAtual = (Veiculo)userData;

                        tfMarca.setText(veiculoAtual.getMarca());
                        tfModelo.setText(veiculoAtual.getModelo());
                        tfHP.setText(String.valueOf(veiculoAtual.getHp()));
                    } else {
                        veiculoAtual = null;
                        tfMarca.setText("");
                        tfModelo.setText("");
                        tfHP.setText("");
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
         if (tfMarca.getText().isEmpty() && tfModelo.getText().isEmpty() && tfHP.getText().isEmpty())
             throw new RuntimeException("Algum campo se encontra vazio, por favor preencha todos");

         if (veiculoAtual != null){
            veiculoAtual.setMarca(tfMarca.getText());
            veiculoAtual.setModelo(tfModelo.getText());
            veiculoAtual.setHp(Integer.parseInt(tfHP.getText()));

            veiculoAtual.save();
         }else {
        Veiculo v = new Veiculo(
                tfMarca.getText(),
                tfModelo.getText(),
                Integer.parseInt(tfHP.getText()) );
            v.save();
         }
        main.changeScreen("main");
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao adionar um veiculo");
            alert.setContentText("O HP dev ser um valor numérico");
            alert.showAndWait();
        } catch (RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao adionar um veiculo");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}
