/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
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
        dateColumn.setSortType(TableColumn.SortType.DESCENDING);

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

        amountColumn.setCellFactory(new Callback<TableColumn<ActivityHistory, String>, TableCell<ActivityHistory, Double>>() {
            @Override
            public TableCell<ActivityHistory, Double> call(
                    TableColumn<ActivityHistory, String> param) {
                return new TableCell<ActivityHistory, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        if (!empty) {
                            int currentIndex = indexProperty()
                                    .getValue() < 0 ? 0
                                            : indexProperty().getValue();
                            String type = param
                                    .getTableView().getItems()
                                    .get(currentIndex).getType();
                            String fromuser = param
                                    .getTableView().getItems()
                                    .get(currentIndex).getFromuser();
                            if (type.equals("Withdraw")) {
                                setTextFill(Color.RED);
                                setText("- " + item + " THB");
                            } else if (type.equals("Transfer") || type.equals("Payment")) {
                                if (fromuser.matches(account.getUsername())) {
                                    setTextFill(Color.RED);
                                    setText("- " + item + " THB");
                                } else {
                                    setTextFill(Color.GREEN);
                                    setText("+ " + item + " THB");
                                }
                            } else {
                                setTextFill(Color.GREEN);
                                setText("+ " + item + " THB");
                            }
                        }
                    }
                };
            }
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        dateColumn.setCellFactory(tc -> new TableCell<ActivityHistory, Date>() {
            @Override
            protected void updateItem(Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(dateFormat.format(date));
                }
            }
        });

        activitytable.getColumns().addAll(dateColumn, fromUserColumn, fromNameColumn, toUserColumn, toNameColumn, amountColumn, typeColumn);
        for (ActivityHistory i : this.account.getActivityHistory()) {
            activitytable.getItems().add(i);

        }
        activitytable.getSortOrder().add(dateColumn);
    }
}
