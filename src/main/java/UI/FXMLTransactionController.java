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
        Parent transferParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLTransfer.fxml"));
        Scene transferScene = new Scene(transferParent);
        transferScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(transferScene);
        window.show();
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
    private void shop1ButtonAction(ActionEvent event) throws IOException {
        Parent shop1Parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLShop1.fxml"));
         Scene shop1Scene = new Scene(shop1Parent);
         shop1Scene.getStylesheets().add("/styles/CSS.css");
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(shop1Scene);
         window.show();
    }

    @FXML
    private void shop2ButtonAction(ActionEvent event) throws IOException {
        Parent shop2Parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLShop2.fxml"));
         Scene shop2Scene = new Scene(shop2Parent);
         shop2Scene.getStylesheets().add("/styles/CSS.css");
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(shop2Scene);
         window.show();
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
