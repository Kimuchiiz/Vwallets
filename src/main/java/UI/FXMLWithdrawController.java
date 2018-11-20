package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Account;
import models.BankAccount;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLWithdrawController extends SceneChangeController implements Initializable {

    private Account account;
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

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }

    public void autoCompleted(BankAccount bankaccount) {
        bankAcc.setText(bankaccount.getNumber());
    }

    private Label bankAccLabel;
    @FXML
    private Label amountLabel;
    private TextField bankAcc;
    @FXML
    private TextField amount;

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (bankAcc.getText().isEmpty()) {
            bankAccLabel.setText("Please fill the bank account");
            amountLabel.setText("");
        } else if (!bankAcc.getText().matches("[0-9]*")) {
            bankAccLabel.setText("Bank account must be 0-9");
            amountLabel.setText("");
        } else if (bankAcc.getText().length() != 10) {
            bankAccLabel.setText("Bank account must have 10 digit");
            amountLabel.setText("");

        } else if (amount.getText().isEmpty()) {
            amountLabel.setText("Please fill the amount");
            bankAccLabel.setText("");
        } else if (amount.getText().matches("[0]*([\\.][0]*)?")) {
            amountLabel.setText("Amount Can't be 0");
            bankAccLabel.setText("");
        } else {
            amountLabel.setText("");
            bankAccLabel.setText("");
            bankAcc.getText();
            amount.getText();

            
//            Parent walletParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLWallet.fxml"));
//            Scene walletScene = new Scene(walletParent);
//            walletScene.getStylesheets().add("/styles/CSS.css");
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setScene(walletScene);
//            window.show();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bankAcc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}")) {
                    bankAcc.setText(oldValue);
                }
            }
        });
        
        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*([\\.]\\d{0,2})?")) {
                    amount.setText(oldValue);
                }
            }
        });
        ///////////////////////////////////// Limit  input in TextField ////////////////////////////////
//        bankAcc.setOnKeyTyped(event -> {
//            int maxCharacters = 10;
//            if (bankAcc.getText().length() >= maxCharacters) {
//                event.consume();
//            }
//        });
//        /////////////////////////////////////////////////////////////////////////////////////////////////
//
//        //////////////////////////////////// Number format/////////////////////////////////////
//        UnaryOperator<Change> integerFilter = change -> {
//            String newText = change.getControlNewText();
//            // if proposed change results in a valid value, return change as-is:
//            if (newText.matches("([0-9]*)")) { // "-?([1-9][0-9]*)?" Can input - and follow number
//                return change;
//
//            }
//            // invalid change, veto it by returning null:
//            return null;
//        };
//
//        bankAcc.setTextFormatter(
//                new TextFormatter<Integer>(new IntegerStringConverter(), null, integerFilter));
//
//        /////////////////////////////////////////////////////////////////////////////////////////////////
//        //////////////////////////////////// Amount format /////////////////////////////////////
//        UnaryOperator<Change> amountFilter = change -> {
//            String newText = change.getControlNewText();
//            // if proposed change results in a valid value, return change as-is:
//            if (newText.matches("([1-9][0-9]*)")) { // "-?([1-9][0-9]*)?" Can input - and follow number
//                return change;
//
//            }
//            // invalid change, veto it by returning null:
//            return null;
//        };
//
//        amount.setTextFormatter(
//                new TextFormatter<Integer>(new IntegerStringConverter(), null, amountFilter));

/////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////// Can't type String 1///////////////////////////////////////
        /*bankAcc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    bankAcc.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });*/
        ////////////////////////////////////////////////////////////////////////////////////////////      
    }

}
