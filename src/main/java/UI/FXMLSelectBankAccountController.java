/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Account;
import models.BankAccount;

/**
 * FXML Controller class
 *
 * @author Xclos
 */
public class FXMLSelectBankAccountController extends SceneChangeController implements Initializable {

    private Account account;
    private String option;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private GridPane BankAccountGrid;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bankaccScroll.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                rootPane.requestFocus();
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
                    if (option.equals("addbalance")) {
                        addOrWithdrawScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, i, "addbalance");
                    } else if (option.equals("withdraw")) {
                        addOrWithdrawScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, i, "withdraw");
                    }
                }
            });
            BankAccountGrid.add(temp, column, row);
            column++;
            if (column == 3) {
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
                addBankAccountScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, option);
            }
        });
        BankAccountGrid.add(temp, column, row);
    }

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
        createBankAccount();
    }

    public void setOption(String option) {
        this.option = option;
    }

    @FXML
    private void closeBtnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
