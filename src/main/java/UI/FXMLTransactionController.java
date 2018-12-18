/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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
import javafx.stage.Stage;
import models.Account;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLTransactionController extends SceneChangeController implements Initializable {
    
    private Account account;

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
        
    }

    @FXML
    private void transactionButtonAction(ActionEvent event) {
        transactionScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void walletButtonAction(ActionEvent event) {
        walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void activityButtonAction(ActionEvent event) {
        activityScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void optionButtonAction(ActionEvent event) {
        optionScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void signoutButtonAction(ActionEvent event) {
        loginScene((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    private void transferButtonAction(ActionEvent event) throws IOException {
        transferScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void refillCreditButtonAction(ActionEvent event) throws IOException {
        selectCreditCardScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void refillAccButtonAction(ActionEvent event) throws IOException {
        selectBankAccountScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, "addbalance");
    }

    @FXML
    private void withdrawButtonAction(ActionEvent event) throws IOException {
        selectBankAccountScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, "withdraw");
    }

    @FXML
    private void paymentButtonAction(ActionEvent event) throws IOException {
        webcamScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void genQRCodeButtonAction(ActionEvent event) throws IOException {
        selectBankAccountScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, "genQRCode");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeBtnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
}
