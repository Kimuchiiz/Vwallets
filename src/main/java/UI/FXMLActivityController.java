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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Account;
import models.ActivityHistory;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLActivityController extends SceneChangeController implements Initializable {
    
    @FXML
    private TableView activitytable;
    
    private Account account;

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
        createActivityTable();
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void createActivityTable() {
        
        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        TableColumn fromUserColumn = new TableColumn("From");
        fromUserColumn.setCellValueFactory(new PropertyValueFactory<>("fromuser"));
        
        TableColumn fromNameColumn = new TableColumn("From Name");
        fromNameColumn.setCellValueFactory(new PropertyValueFactory<>("fromname"));
        
        TableColumn toUserColumn = new TableColumn("To");
        toUserColumn.setCellValueFactory(new PropertyValueFactory<>("touser"));

        TableColumn toNameColumn = new TableColumn("To Name");
        toNameColumn.setCellValueFactory(new PropertyValueFactory<>("toname"));
        
        TableColumn amountColumn = new TableColumn("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        
        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        activitytable.getColumns().addAll(dateColumn, fromUserColumn, fromNameColumn, toUserColumn, toNameColumn, amountColumn, typeColumn);
        for(ActivityHistory i : this.account.getActivityHistory()){
            activitytable.getItems().add(i);
        }
    }
}
