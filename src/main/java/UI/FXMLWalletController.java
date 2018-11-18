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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Account;
import models.BankAccount;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLWalletController implements Initializable {

    private Account account;

    @FXML
    private GridPane BankAccountGrid, CreditCardGrid;
    
    @FXML
    private Label balance;

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
    private void addAccountButtonAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/fxml/FXMLAddBankAccount.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAddBankAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLAddBankAccountController display = Loader.getController();
        display.setAccount(account);

        Parent p = Loader.getRoot();
        Scene addBankAccountScene = new Scene(p);
        addBankAccountScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addBankAccountScene);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO       
    }

    @FXML
    public void createBankAccount() {
        int column = 0;
        int row = 0;
        for (BankAccount i : account.getBankaccount()) {
            Button temp = new Button("Account Number\n    " + i.getNumber() + "\n" + i.getName());
            temp.setPrefSize(247, 151);
            temp.setMinSize(247, 151);
            temp.setMaxSize(247, 151);
            temp.setStyle("-fx-font: 20 Regular;");
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.out.println(i.getNumber());
                }
            });
            BankAccountGrid.add(temp, column, row);
            column++;
            if (column == 2) {
                row++;
                column = 0;
            }
        }
        Button temp = new Button();
        temp.setPrefSize(247, 151);
        temp.setMinSize(247, 151);
        temp.setMaxSize(247, 151);
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        Label label1 = new Label("    +    ");
        label1.setFont(new Font("Regular", 40));
        Label label2 = new Label("Add Bank Account");
        label2.setFont(new Font("Regular", 18));
        box.getChildren().addAll(label1, label2);
        temp.setGraphic(box);
        temp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TextField banknumField = new TextField();
                TextField pinField = new TextField();
                Label banknum = new Label("Bank Account Number");
                Label pin = new Label("PIN");
                Label banknumCheck = new Label("");
                Label pinCheck = new Label("");
                Button submit = new Button("Sumbit");
                box.getChildren().clear();
                box.getChildren().addAll(banknum, banknumField, banknumCheck, pin, pinField, pinCheck, submit);
                submit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        System.out.print("Test");
                    }
                });
            }
        });
        BankAccountGrid.add(temp, column, row);
    }

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
        balance.setText(Double.toString(account.getBalance()) + " Baht");
        createBankAccount();
    }

    /*public void createAccount(ActionEvent event) throws IOException{
        Parent gridParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLWallet.fxml"));
        Button acc1Btn = new Button();
        GridPane.setConstraints(acc1Btn,1,0);
        accountGrid.getChildren().addAll(acc1Btn);
        
        
        Scene gridScene = new Scene(gridParent);
        gridScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gridScene);
        window.show();
    }*/
}
