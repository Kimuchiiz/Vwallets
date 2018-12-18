/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Account;

/**
 * FXML Controller class
 *
 * @author Xclos
 */
public class FXMLTransfer2Controller extends SceneChangeController implements Initializable {

    private Account account,account2;
    private String amount;
    
    @FXML
    private Rectangle menubar;
    @FXML
    private Button signoutBtn1;
    @FXML
    private Button walletBtn;
    @FXML
    private Button transactionBtn;
    @FXML
    private Button activityBtn;
    @FXML
    private Button optionBtn1;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label amountLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public void setLabel() {
        usernameLabel.setText("Username: " + account2.getUsername());
        nameLabel.setText("Name: " + account2.getName());
        balanceLabel.setText(account.getBalance() + " THB");
        amountLabel.setText(amount + " THB");
    }

    @FXML
    private void confirmButtonAction(ActionEvent event) {
        enterPasswordScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, null, amount, "transfer", account2);
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
    private void closeBtnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
