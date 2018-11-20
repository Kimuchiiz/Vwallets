/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Account;
import models.BankAccount;

/**
 * FXML Controller class
 *
 * @author Xclos
 */
public class FXMLAddorWithdrawController extends SceneChangeController implements Initializable {

    private Account account;
    private BankAccount bankaccount;
    private String option;

    @FXML
    private TextField amount;
    @FXML
    private Label amountLabel;
    @FXML
    private Button walletBtn;
    @FXML
    private Button transactionBtn;
    @FXML
    private Button activityBtn;
    @FXML
    private Button optionBtn;
    @FXML
    private Button signoutBtn;
    @FXML
    private Label titleLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label balanceLabel;

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }

    public void setBankaccount(BankAccount bankaccount) {
        this.bankaccount = bankaccount;
    }

    public void setLabel(String option) {
        this.option = option;
        if (option.equals("addbalance")) {
            titleLabel.setText("Add Balance");
        } else if (option.equals("withdraw")) {
            titleLabel.setText("Withdraw");
        }
        numberLabel.setText(bankaccount.getNumber());
        nameLabel.setText(bankaccount.getName());
        balanceLabel.setText(account.getBalance() + " THB");
    }

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (amount.getText().isEmpty()) {
            amountLabel.setText("Please fill the amount");
        } else if (amount.getText().matches("[0]*([\\.][0]*)?")) {
            amountLabel.setText("Amount Can't be 0");
        } else {
            amountLabel.setText("");
            enterPasswordScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, bankaccount, amount.getText(), option);
        }
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*([\\.]\\d{0,2})?")) {
                    amount.setText(oldValue);
                }
            }
        });
    }
}
