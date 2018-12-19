package UI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Account;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLChangeNameController extends SceneChangeController implements Initializable {

     private Stage stage;
     private Account account;
     
    @FXML
    private TextField name;
    @FXML
    private Label nameLabel;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }
    
     @FXML
    private void closePopupAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (name.getText().isEmpty()) {
            nameLabel.setText("Please fill the name");
        } else if (!name.getText().matches("[A-Z][a-z]* [A-Z][a-z]*") && !name.getText().matches("[A-Z][a-z]* [A-Z][a-z]*")) {
            nameLabel.setText("Please Enter your first and surname separate with ' ', both must be alphabet and their first alphabet must be Uppercase");
        } else{
            nameLabel.setText("");
            VWallet.VWallet.editAccount(account, name.getText());
            ((Node) event.getSource()).getScene().getWindow().hide();
            Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Success");
                info.setHeaderText("Success");
                info.setContentText("Your Name has been changed");   
                info.showAndWait();
                optionScene(stage, account);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
