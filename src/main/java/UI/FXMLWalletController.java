/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Account;
import models.BankAccount;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLWalletController extends SceneChangeController implements Initializable {

    private Account account;
    private BankAccount bankaccount;

    @FXML
    private GridPane BankAccountGrid, CreditCardGrid;

    @FXML
    private Label balance;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ScrollPane bankaccScroll;

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
    private void removeBankAccount(ActionEvent event) {
        rootPane.requestFocus();
        if (bankaccount != null) {
            VWallet.VWallet.removeBankAccount(account, bankaccount);
            walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
        } else {
            System.out.println("Please Selected Bank Account");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 

        rootPane.setOnMouseClicked((MouseEvent evt) -> {
            rootPane.requestFocus();
            bankaccount = null;
        });

        bankaccScroll.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                rootPane.requestFocus();
                bankaccount = null;
            }
        });
    }

    @FXML
    public void createBankAccount() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        Label label1 = new Label("    +    ");
        label1.setFont(new Font("Regular", 40));
        label1.setTextFill(Color.GRAY);
        Label label2 = new Label("Add Bank Account");
        label2.setFont(new Font("Regular", 18));
        label2.setTextFill(Color.GRAY);

        int column = 0;
        int row = 0;
        for (BankAccount i : account.getBankaccount()) {
            Button temp = new Button("Account Number\n    " + i.getNumber() + "\n" + i.getName());
            temp.setId("bankccBtn");
            temp.setStyle("-fx-font: 20 Regular;");
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    bankaccount = i;
                }
            });
//            temp.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//                focusState(newValue);
//            });

            BankAccountGrid.add(temp, column, row);
            column++;
            if (column == 2) {
                row++;
                column = 0;
            }
        }
        Button temp = new Button();
        temp.setId("bankccBtn");
        box.getChildren().addAll(label1, label2);
        temp.setGraphic(box);
        temp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBankAccountScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
            }
        });
        BankAccountGrid.add(temp, column, row);
    }

//    private void focusState(boolean value) {
//        if (value) {
//            System.out.println("Focus Gained");
//        } else {
//            System.out.println("Focus Lost");
//        }
//    }
    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
        balance.setText(Double.toString(account.getBalance()) + " Baht");
        createBankAccount();
    }

}
