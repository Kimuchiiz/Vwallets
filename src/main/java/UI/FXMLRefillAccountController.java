
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
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

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLRefillAccountController implements Initializable {

    @FXML
    private Label bankAccLabel, amountLabel;

    @FXML
    private TextField bankAcc, amount;

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
        } else if (!amount.getText().matches("[1-9][0-9]*")) {
            amountLabel.setText("Amount must be 0-9");
            bankAccLabel.setText("");
        } else {
            amountLabel.setText("");
            bankAccLabel.setText("");
            bankAcc.getText();
            amount.getText();
            
            Parent confirmParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLConfirm.fxml"));
            Scene confirmScene = new Scene(confirmParent);
            confirmScene.getStylesheets().add("/styles/CSS.css");
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(confirmScene);
            window.show();
        }
    }
    
    @FXML
    private void transactionButtonAction(ActionEvent event) throws IOException {
        Parent transactionParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLTransaction.fxml"));
        Scene transactionScene = new Scene(transactionParent);
        transactionScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(transactionScene);
        window.show();
    }   
    
    @FXML
    private void walletButtonAction(ActionEvent event) throws IOException {
        Parent walletParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLWallet.fxml"));
        Scene walletScene = new Scene(walletParent);
        walletScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(walletScene);
        window.show();
    }
    
    @FXML
    private void activityButtonAction(ActionEvent event) throws IOException {
        Parent activityParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLActivity.fxml"));
        Scene activityScene = new Scene(activityParent);
        activityScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(activityScene);
        window.show();
    }

    @FXML
    private void optionButtonAction(ActionEvent event) throws IOException {
        Parent optionParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLAccount.fxml"));
        Scene optionScene = new Scene(optionParent);
        optionScene.getStylesheets().add("/styles/CSS.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(optionScene);
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
        
        ///////////////////////////////////// Limit  input in TextField ////////////////////////////////
        bankAcc.setOnKeyTyped(event -> {
            int maxCharacters = 10;
            if (bankAcc.getText().length() >= maxCharacters) {
                event.consume();
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        //////////////////////////////////// Number format /////////////////////////////////////
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            // if proposed change results in a valid value, return change as-is:
            if (newText.matches("([0-9]*)")) { // "-?([1-9][0-9]*)?" Can input - and follow number
                return change;

            }
            // invalid change, veto it by returning null:
            return null;
        };

bankAcc.setTextFormatter(
    new TextFormatter<Integer>(new IntegerStringConverter(), null, integerFilter));
    
        /////////////////////////////////////////////////////////////////////////////////////////////////
        
        //////////////////////////////////// Amount format /////////////////////////////////////
        UnaryOperator<TextFormatter.Change> amountFilter = change -> {
            String newText = change.getControlNewText();
            // if proposed change results in a valid value, return change as-is:
            if (newText.matches("([1-9][0-9]*)")) { // "-?([1-9][0-9]*)?" Can input - and follow number
                return change;

            }
            // invalid change, veto it by returning null:
            return null;
        };

        
amount.setTextFormatter(
    new TextFormatter<Integer>(new IntegerStringConverter(), null, amountFilter));

/////////////////////////////////////////////////////////////////////////////////////////////////
    }

}
