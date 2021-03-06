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
import javafx.scene.control.Alert;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Account;
import models.BankAccount;
import models.CreditCard;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLWalletController extends SceneChangeController implements Initializable {

    private Account account;
    private BankAccount bankaccount;
    private CreditCard creditcard;

    @FXML
    private GridPane BankAccountGrid, CreditCardGrid;

    @FXML
    private Label balance,name;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ScrollPane bankaccScroll, creditCardScroll;

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
        if (bankaccount != null) {
            VWallet.VWallet.removeBankAccount(account, bankaccount);
            walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error alert");
            alert.setHeaderText("Didn't Select any Bank Account");
            alert.setContentText("Please select a Bank Account");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void removeCreditCard(ActionEvent event) {
        if (creditcard != null) {
            VWallet.VWallet.removeCreditCard(account, creditcard);
            walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error alert");
            alert.setHeaderText("Didn't Select any CreditCard");
            alert.setContentText("Please select a CreditCard");

            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 

        rootPane.setOnMouseClicked((MouseEvent evt) -> {
            rootPane.requestFocus();
            bankaccount = null;
            creditcard = null;
        });

        bankaccScroll.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                rootPane.requestFocus();
                bankaccount = null;
                creditcard = null;
            }
        });

        creditCardScroll.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                rootPane.requestFocus();
                bankaccount = null;
                creditcard = null;
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
            Button temp = new Button("Account Number\n" + i.getNumber() + "\n" + i.getName());
            temp.setTextAlignment(TextAlignment.CENTER);
            temp.setId("bankccBtn");
            temp.setStyle("-fx-font: 20 Regular;");
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    bankaccount = i;
                    creditcard = null;
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
                addBankAccountScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, "wallet");
            }
        });
        BankAccountGrid.add(temp, column, row);
    }

    @FXML
    public void createCreditCard() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        Label label1 = new Label("    +    ");
        label1.setFont(new Font("Regular", 40));
        label1.setTextFill(Color.GRAY);
        Label label2 = new Label("Add Credit Card");
        label2.setFont(new Font("Regular", 18));
        label2.setTextFill(Color.GRAY);

        int column = 0;
        int row = 0;
        for (CreditCard i : account.getCreditcard()) {
            Button temp = new Button("Credit Card Number\n" + i.getCardNumber() + "\n" + i.getFirstname()+ " " +i.getLastname());
            temp.setTextAlignment(TextAlignment.CENTER);
            temp.setId("bankccBtn");
            temp.setStyle("-fx-font: 20 Regular;");
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    creditcard = i;
                    bankaccount = null;
                }
            });

            CreditCardGrid.add(temp, column, row);
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
                addCreditCardScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, "wallet");
            }
        });
        CreditCardGrid.add(temp, column, row);
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
        balance.setText(Double.toString(this.account.getBalance()) + " Baht");
        name.setText(this.account.getName());
        createBankAccount();
        createCreditCard();
    }

    @FXML
    private void closeBtnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
