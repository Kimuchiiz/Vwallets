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

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLRefillCreditController implements Initializable {

    @FXML
    private Label wrongformat;

    @FXML
    private TextField firstname, lastname, address1, address2, city, state, zip, country, phone, email, cardNumber, expdate, cvv, amount;

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (firstname.getText().isEmpty()) {
            wrongformat.setText("Please fill the firstname");
        } else if (!firstname.getText().matches("[A-Z][a-z]*")) {
            wrongformat.setText("Firstname must be A-Z and follow a-z");

        } else if (lastname.getText().isEmpty()) {
            wrongformat.setText("Please fill the lastname");
        } else if (!lastname.getText().matches("[A-Z][a-z]*")) {
            wrongformat.setText("Lastname must be A-Z and follow a-z");

        } else if (address1.getText().isEmpty()) {
            wrongformat.setText("Please fill the address");
        } else if (!address2.getText().matches("[a-zA-Z0-9]*")) {
            wrongformat.setText("Address must be a-z A-Z 0-9");

        } else if (address2.getText().isEmpty()) {
            wrongformat.setText("Please fill the address");
        } else if (!address2.getText().matches("[a-zA-Z0-9]*")) {
            wrongformat.setText("Address must be a-z A-Z 0-9");

        } else if (city.getText().isEmpty()) {
            wrongformat.setText("Please fill the city");
        } else if (!city.getText().matches("[a-zA-Z]*")) {
            wrongformat.setText("City must be a-z A-Z");

        } else if (state.getText().isEmpty()) {
            wrongformat.setText("Please fill the state");
        } else if (!state.getText().matches("[a-zA-Z]*")) {
            wrongformat.setText("State must be a-z A-Z");

        } else if (zip.getText().isEmpty()) {
            wrongformat.setText("Please fill the zip code");
        } else if (!zip.getText().matches("[0-9]*")) {
            wrongformat.setText("Zip code must be 0-9");
        } else if (zip.getText().length() != 5) {
            wrongformat.setText("Zip code must have 5 digit");

        } else if (country.getText().isEmpty()) {
            wrongformat.setText("Please fill the country");
        } else if (!country.getText().matches("[a-zA-Z]*")) {
            wrongformat.setText("Country must be a-z A-Z");

        } else if (phone.getText().isEmpty()) {
            wrongformat.setText("Please fill the phone number");
        } else if (!phone.getText().matches("[0-9]*")) {
            wrongformat.setText("Phone number must be 0-9");
        } else if (phone.getText().length() != 10) {
            wrongformat.setText("Phone number must have 10 digit");

        } else if (email.getText().isEmpty()) {
            wrongformat.setText("Please fill the city");
        } else if (!email.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
            wrongformat.setText("Email must have @ and . Ex. abc@hotmail.com");

        } else if (cardNumber.getText().isEmpty()) {
            wrongformat.setText("Please fill the card number");
        } else if (!cardNumber.getText().matches("[0-9]*")) {
            wrongformat.setText("Card number must be 0-9");
        } else if (cardNumber.getText().length() != 13) {
            wrongformat.setText("Card number must have 13 digit");

        } else if (expdate.getText().isEmpty()) {
            wrongformat.setText("Please fill the expiration date");
        } else if (!expdate.getText().matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            wrongformat.setText("Expiration date must be dd/mm/yyyy");

        } else if (cvv.getText().isEmpty()) {
            wrongformat.setText("Please fill the CVV");
        } else if (!cvv.getText().matches("[0-9]{3}")) {
            wrongformat.setText("CVV date must be 0-9 and must have 3 digit");

        } else if (amount.getText().isEmpty()) {
            wrongformat.setText("Please fill the amount");
        } else if (!amount.getText().matches("[1-9][0-9]*")) {
            wrongformat.setText("amount must be 0-9");

        } else {
            wrongformat.setText(" ");
            firstname.getText();
            lastname.getText();
            address1.getText();
            address2.getText();
            city.getText();
            state.getText();
            zip.getText();
            country.getText();
            phone.getText();
            email.getText();
            cardNumber.getText();
            expdate.getText();
            cvv.getText();
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

        ///////////////////////////////////// Limit  input in TextField ////////////////////////////////
        zip.setOnKeyTyped(event -> {
            if (zip.getText().length() >= 5) {
                event.consume();
            }
        });

        phone.setOnKeyTyped(event -> {
            if (phone.getText().length() >= 10) {
                event.consume();
            }
        });

        cardNumber.setOnKeyTyped(event -> {
            if (cardNumber.getText().length() >= 12) {
                event.consume();
            }
        });

        expdate.setOnKeyTyped(event -> {
            if (expdate.getText().length() >= 10) {
                event.consume();
            }
        });

        cvv.setOnKeyTyped(event -> {
            if (cvv.getText().length() >= 3) {
                event.consume();
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////// Firstname Format ///////////////////////////////////
        UnaryOperator<TextFormatter.Change> firstnameFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[A-Z]?[a-z]* ?[A-Z]?[a-z]*")) {
                return change;
            }
            return null;

        };
        TextFormatter<String> firstnameformatter = new TextFormatter<>(firstnameFilter);
        firstname.setTextFormatter(firstnameformatter);
        
        /////////////////////////////////////////////////////////////////////////////////////////////////
        
         //////////////////////////////////////////// Lastname Format ///////////////////////////////////
         UnaryOperator<TextFormatter.Change> lastnameFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[A-Z]?[a-z]* ?[A-Z]?[a-z]*")) {
                return change;
            }
            return null;

        };
        TextFormatter<String> lastnameformatter = new TextFormatter<>(lastnameFilter);
        lastname.setTextFormatter(lastnameformatter);

        /////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Amount Format ///////////////////////////////////
        /*UnaryOperator<TextFormatter.Change> amountFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]*)?")) {
                return change;
            }
            return null;

        };
        TextFormatter<String> amountformatter = new TextFormatter<>(amountFilter);
        amount.setTextFormatter(amountformatter);*/
        /////////////////////////////////////////////////////////////////////////////////////////////////
    }

}
