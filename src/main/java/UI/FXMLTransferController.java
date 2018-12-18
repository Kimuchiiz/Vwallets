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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.Account;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLTransferController extends SceneChangeController implements Initializable {
    
    private Account account;
    
    @FXML
    private Label balanceLabel;
    
    @FXML
    private Label userLabel, amountLabel;

    @FXML
    private TextField username, amount;

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
        balanceLabel.setText(account.getBalance() + " THB");
    }
    
    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (username.getText().isEmpty()) {
            userLabel.setText("Please fill the username");
            amountLabel.setText("");
        } else if (!username.getText().matches("[a-zA-Z0-9]*")) {
            userLabel.setText("Username must be a-z A-Z 0-9");
            amountLabel.setText("");

        } else if (amount.getText().isEmpty()) {
            amountLabel.setText("Please fill the amount");
            userLabel.setText("");
        } else if (amount.getText().matches("[0]*([\\.][0]*)?")) {
            amountLabel.setText("Amount Can't be 0");
            userLabel.setText("");
        } else {
            amountLabel.setText("");
            userLabel.setText("");
            
            Account account2 = VWallet.VWallet.checkUsername(account, username.getText());
            if (account2 != null) {
                transfer2Scene((Stage) ((Node) event.getSource()).getScene().getWindow(), account, account2, amount.getText());
            }
            else{
                userLabel.setText("Invalid Username");    
            }
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

        ///////////////////////////////////// Limit  input in TextField ////////////////////////////////
        username.setOnKeyTyped(event -> {
            int maxCharacters = 12;
            if (username.getText().length() >= maxCharacters) {
                event.consume();
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////// Username Format ///////////////////////////////////
        UnaryOperator<TextFormatter.Change> usernameFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[A-Za-z0-9]*")) {
                return change;
            }
            return null;

        };
        TextFormatter<String> usernameformatter = new TextFormatter<>(usernameFilter);
        username.setTextFormatter(usernameformatter);

        /////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Amount Format ///////////////////////////////////
        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*([\\.]\\d{0,2})?")) {
                    amount.setText(oldValue);
                }
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @FXML
    private void closeBtnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}
