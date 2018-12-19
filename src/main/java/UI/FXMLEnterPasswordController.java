/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Account;
import models.BankAccount;

/**
 * FXML Controller class
 *
 * @author Xclos
 */
public class FXMLEnterPasswordController extends SceneChangeController implements Initializable {

    private Account account, account2;
    private String option;
    private BankAccount bankaccount;
    private String amount;

    private Stage stage;

    @FXML
    private void closePopupAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }

    public void setBankAccount(BankAccount bankaccount) {
        this.bankaccount = bankaccount;
    }

    @FXML
    private Button confirmBtn;

    @FXML
    private Label wrongformat;

    @FXML
    private PasswordField psw;

    @FXML
    private void confirmButtonAction(ActionEvent event) {
        //"[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+" for email
        if (psw.getText().isEmpty()) {
            wrongformat.setText("Please fill the password");
        } else if (!psw.getText().matches("[a-zA-Z0-9]*")) {
            wrongformat.setText("password must be a-z A-Z 0-9");
            psw.clear();
        } else {
            wrongformat.setText("");

            int i = 3;
            if (option.equals("addbalance")) {
                i = VWallet.VWallet.addBalance(account, bankaccount, amount, psw.getText());
            } else if (option.equals("withdraw")) {
                i = VWallet.VWallet.withdraw(account, bankaccount, amount, psw.getText());
            } else if (option.equals("Transfer")) {
                i = VWallet.VWallet.transfer(account, account2, amount, psw.getText());
            } else if (option.equals("Payment")) {
                i = VWallet.VWallet.payment(account, account2, amount, psw.getText(), bankaccount);
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            switch (i) {
                case 0:
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Success");
                    info.setHeaderText("Success");
                    if (option.equals("addbalance")) {
                        info.setContentText("Details :"
                                + "\nTransaction Status: Success"
                                + "\nTransaction Type: " + option
                                + "\nTransaction From(bankaccount): " + bankaccount.getNumber()
                                + "\nTransaction to(username): " + account.getUsername()
                                + "\nTransaction From(name): " + bankaccount.getName()
                                + "\nTransaction to(name): " + account.getName()
                                + "\nTransaction Amount: " + amount);
                    } else if(option.equals("Transfer")) {
                        info.setContentText("Details :"
                                + "\nTransaction Status: Success"
                                + "\nTransaction Type: " + option
                                + "\nTransaction From(username): " + account.getUsername()
                                + "\nTransaction to(username): " + account2.getUsername()
                                + "\nTransaction From(name): " + account.getName()
                                + "\nTransaction to(name): " + account2.getName()
                                + "\nTransaction Amount: " + amount);
                    }else{
                        info.setContentText("Details :"
                                + "\nTransaction Status: Success"
                                + "\nTransaction Type: " + option
                                + "\nTransaction From(username): " + account.getUsername()
                                + "\nTransaction to(bankaccount): " + bankaccount.getNumber()
                                + "\nTransaction From(name): " + account.getName()
                                + "\nTransaction to(name): " + bankaccount.getName()
                                + "\nTransaction Amount: " + amount + " THB");
                    }

                    info.showAndWait();
                    walletScene(stage, account);
                    break;
                case 1:
                    wrongformat.setText("Invalid Password");
                    psw.clear();
                    break;
                case 2:
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    alert.setTitle("Error alert");
                    alert.setHeaderText("Insufficient Fund");
                    alert.setContentText("Please enter new amount");

                    alert.showAndWait();
                    break;
                default:
                    alert.setTitle("Error alert");
                    alert.setHeaderText("Unknown Error");
                    alert.setContentText("Please try again");

                    alert.showAndWait();
                    psw.clear();
                    wrongformat.setText("");
                    break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //////////////////////////////////// Password format /////////////////////////////////////
        UnaryOperator<TextFormatter.Change> passwordFilter = change -> {
            String newText = change.getControlNewText();
            // if proposed change results in a valid value, return change as-is:
            if (newText.matches("[a-zA-Z0-9]*")) { // "-?([1-9][0-9]*)?" Can input - and follow number
                return change;

            }
            // invalid change, veto it by returning null:
            return null;
        };

        psw.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(), null, passwordFilter));

        /////////////////////////////////////////////////////////////////////////////////////////////////
    }
}
