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
public class FXMLTransactionController implements Initializable {
    
    private Account account;

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
        
    }

    @FXML
    private void transactionButtonAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLTransaction.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLTransactionController display = Loader.getController();
        display.setAccount(account);

        Parent p = Loader.getRoot();
        Scene transactionScene = new Scene(p);
        transactionScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(transactionScene);
        window.show();
    }

    @FXML
    private void walletButtonAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLWallet.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLWalletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLWalletController display = Loader.getController();
        display.setAccount(account);

        Parent p = Loader.getRoot();
        Scene walletScene = new Scene(p);
        walletScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(walletScene);
        window.show();
    }

    @FXML
    private void activityButtonAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLActivity.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLActivityController display = Loader.getController();
        display.setAccount(account);

        Parent p = Loader.getRoot();
        Scene activityScene = new Scene(p);
        activityScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(activityScene);
        window.show();
    }

    @FXML
    private void optionButtonAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLAccount.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLAccountController display = Loader.getController();
        display.setAccount(account);

        Parent p = Loader.getRoot();
        Scene accountScene = new Scene(p);
        accountScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(accountScene);
        window.show();
    }

    @FXML
    private void signoutButtonAction(ActionEvent event) throws IOException {
        Parent signoutParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLLogin.fxml"));
        Scene signoutScene = new Scene(signoutParent);
        signoutScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signoutScene);
        window.show();
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
        Parent refillCreditParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLRefillCredit.fxml"));
         Scene refillCreditScene = new Scene(refillCreditParent);
         refillCreditScene.getStylesheets().add("/styles/CSS.css");
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(refillCreditScene);
         window.show();
    }

    @FXML
    private void refillAccButtonAction(ActionEvent event) throws IOException {
        Parent refillAccParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLRefillAccount.fxml"));
         Scene refillAccScene = new Scene(refillAccParent);
         refillAccScene.getStylesheets().add("/styles/CSS.css");
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(refillAccScene);
         window.show();
    }

    @FXML
    private void withdrawButtonAction(ActionEvent event) throws IOException {
        Parent withdrawParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLWithdraw.fxml"));
         Scene withdrawScene = new Scene(withdrawParent);
         withdrawScene.getStylesheets().add("/styles/CSS.css");
         Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(withdrawScene);
         window.show();
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

}
