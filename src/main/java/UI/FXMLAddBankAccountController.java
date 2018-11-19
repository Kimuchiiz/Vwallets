package UI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
public class FXMLAddBankAccountController extends SceneChangeController implements Initializable {

    private Account account;
    
    private Stage stage;

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }

    @FXML
    private Label bankAccLabel, pinLabel;

    @FXML
    private TextField bankAcc;

    @FXML
    private PasswordField pin;

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (bankAcc.getText().isEmpty()) {
            bankAccLabel.setText("Please fill the bank account");
            pinLabel.setText("");
        } else if (!bankAcc.getText().matches("[0-9]*")) {
            bankAccLabel.setText("Bank account must be 0-9");
            pinLabel.setText("");
        } else if (bankAcc.getText().length() != 10) {
            bankAccLabel.setText("Bank account must have 10 digit");
            pinLabel.setText("");

        } else if (pin.getText().isEmpty()) {
            pinLabel.setText("Please fill the pin");
            bankAccLabel.setText("");
        } else if (!pin.getText().matches("[0-9]*")) {
            pinLabel.setText("PIN must be 0-9");
            bankAccLabel.setText("");
        } else if (pin.getText().length() != 4) {
            pinLabel.setText("PIN must have 4 digit");
            bankAccLabel.setText("");

        } else {
            pinLabel.setText("");
            bankAccLabel.setText("");

            int i = VWallet.VWallet.addBankAccount(account, bankAcc.getText(), pin.getText());
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/fxml/FXMLWallet.fxml"));
            try {
                Loader.load();
            } catch (IOException ex) {
                Logger.getLogger(FXMLWalletController.class.getName()).log(Level.SEVERE, null, ex);
            }
            FXMLWalletController display = Loader.getController();
            display.setAccount(account);
            Alert alert = new Alert(AlertType.ERROR);
            switch (i) {
                case 0:
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    changeScene(stage, Loader);
                    break;
                case 1:
                    alert.setTitle("Error alert");
                    alert.setHeaderText("Already Add This Bank Account");
                    alert.setContentText("");

                    alert.showAndWait();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    break;
                case 2:                 
                    bankAccLabel.setText("");
                    pinLabel.setText("Invalid PIN!");
                    break;
                case 3:
                    bankAccLabel.setText("Invalid Bank Account Number!");
                    pinLabel.setText("");
                    break;
                default:
                    alert.setTitle("Error alert");
                    alert.setHeaderText("Unknown Error");
                    alert.setContentText("Please try again");

                    alert.showAndWait();
                    pinLabel.setText("");
                    bankAccLabel.setText("");
                    break;
            }
        }
    }

    @FXML
    private void closePopupAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
