package UI;

import bluepay.BluePay;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import models.Account;
import models.CreditCard;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLRefillCreditController extends SceneChangeController implements Initializable {

    private Account account;

    @FXML
    private Label wrongformat;

    @FXML
    private TextField firstname, lastname, address1, address2, city, state, zip, country, phone, cardNumber, expdate, cvv, amount;

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (firstname.getText().isEmpty()) {
            wrongformat.setText("Please fill the firstname");
        } else if (!firstname.getText().matches("[A-Z][a-z]*")) {
            wrongformat.setText("Firstname must be alphabet and their first alphabet must be Uppercase");

        } else if (lastname.getText().isEmpty()) {
            wrongformat.setText("Please fill the lastname");
        } else if (!lastname.getText().matches("[A-Z][a-z]*")) {
            wrongformat.setText("Lastname must be alphabet and their first alphabet must be Uppercase");

        } else if (address1.getText().isEmpty()) {
            wrongformat.setText("Please fill the address");
        } else if (!address2.getText().matches("[a-zA-Z0-9]*")) {
            wrongformat.setText("Address must be a-z A-Z 0-9");

//        } else if (address2.getText().isEmpty()) {
//            wrongformat.setText("Please fill the address");
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

//        } else if (email.getText().isEmpty()) {
//            wrongformat.setText("Please fill the city");
//        } else if (!email.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
//            wrongformat.setText("Email must have @ and . Ex. abc@hotmail.com");
        } else if (cardNumber.getText().isEmpty()) {
            wrongformat.setText("Please fill the card number");
        } else if (!cardNumber.getText().matches("[0-9]*")) {
            wrongformat.setText("Card number must be 0-9");
//        } else if (cardNumber.getText().length() != 13) {
//            wrongformat.setText("Card number must have 13 digit");

        } else if (expdate.getText().isEmpty()) {
            wrongformat.setText("Please fill the expiration date");
        } else if (!expdate.getText().matches("[0-9]{4}")) {
            wrongformat.setText("Expiration date format must be MMYY (example : 1218)");
        } else if (expdate.getText().startsWith("00") || parseInt(expdate.getText().substring(0, 2)) > 12) {
            wrongformat.setText("Invalid Month");

        } else if (cvv.getText().isEmpty()) {
            wrongformat.setText("Please fill the CVV");
        } else if (!cvv.getText().matches("[0-9]{3}")) {
            wrongformat.setText("CVV date must be 0-9 and must have 3 digit");

        } else if (amount.getText().isEmpty()) {
            wrongformat.setText("Please fill the amount");
        } else if (amount.getText().matches("[0]*([\\.][0]*)?")) {
            wrongformat.setText("Amount Can't be 0");

        } else {
            wrongformat.setText("");

            String ACCOUNT_ID = "100640816938";
            String SECRET_KEY = "TKFB40ZQ2RLANPHFAOMLU.537PENYSUT";
            String MODE = "TEST";

            BluePay payment = new BluePay(
                    ACCOUNT_ID,
                    SECRET_KEY,
                    MODE
            );

            // Set Customer Information  
            HashMap<String, String> customerParams = new HashMap<>();
            customerParams.put("firstName", firstname.getText());
            customerParams.put("lastName", lastname.getText());
            customerParams.put("address1", address1.getText());
            customerParams.put("address2", address2.getText());
            customerParams.put("city", city.getText());
            customerParams.put("state", state.getText());
            customerParams.put("zip", zip.getText());
            customerParams.put("country", country.getText());
            customerParams.put("phone", phone.getText());
            customerParams.put("email", "");
            payment.setCustomerInformation(customerParams);

            // Set Credit Card Information
            HashMap<String, String> ccParams = new HashMap<>();
            ccParams.put("cardNumber", cardNumber.getText());
            ccParams.put("expirationDate", expdate.getText());
            ccParams.put("cvv2", cvv.getText());
            payment.setCCInformation(ccParams);

            // Set sale amount: $3.00
            HashMap<String, String> saleParams = new HashMap<>();
            saleParams.put("amount", amount.getText());
            payment.sale(saleParams);

            // Makes the API Request with BluePay
            try {
                payment.process();
            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error alert");
                alert.setHeaderText("Exception");
                alert.setContentText(ex.toString());
                alert.showAndWait();
                System.out.println("Exception: " + ex.toString());
                walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
            }

            // If transaction was successful reads the responses from BluePay
            if (payment.isSuccessful()) {
                VWallet.VWallet.addbalanceCC(account, amount.getText());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Details :"
                        + "\nTransaction Status: " + payment.getStatus()
                        + "\nTransaction Message: " + payment.getMessage().replace("%20", " ")
                        + "\nTransaction ID: " + payment.getTransID()
                        + "\nAVS Response: " + payment.getAVS()
                        + "\nCVV2 Response: " + payment.getCVV2()
                        + "\nMasked Payment Account: " + payment.getMaskedPaymentAccount()
                        + "\nCard Type: " + payment.getCardType()
                        + "\nAuthorization Code: " + payment.getAuthCode());
                alert.showAndWait();
                System.out.println("Transaction Status: " + payment.getStatus());
                System.out.println("Transaction Message: " + payment.getMessage());
                System.out.println("Transaction ID: " + payment.getTransID());
                System.out.println("AVS Response: " + payment.getAVS());
                System.out.println("CVV2 Response: " + payment.getCVV2());
                System.out.println("Masked Payment Account: " + payment.getMaskedPaymentAccount());
                System.out.println("Card Type: " + payment.getCardType());
                System.out.println("Authorization Code: " + payment.getAuthCode());
                walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error alert");
                alert.setHeaderText("Error");
                alert.setContentText(payment.getMessage().replace("%20", " "));
                alert.showAndWait();
                System.out.println("Error: " + payment.getMessage());
            }
        }

    }

    @FXML
    private void transactionButtonAction(ActionEvent event
    ) {
        transactionScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void walletButtonAction(ActionEvent event
    ) {
        walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void activityButtonAction(ActionEvent event
    ) {
        activityScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void optionButtonAction(ActionEvent event
    ) {
        optionScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
    }

    @FXML
    private void signoutButtonAction(ActionEvent event
    ) {
        loginScene((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    private void closeBtnAction(ActionEvent event
    ) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
        amount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*([\\.]\\d{0,2})?")) {
                    amount.setText(oldValue);
                }
            }
        });
    }

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }

    public void autofill(CreditCard creditcard) {
        if (creditcard != null) {
            cardNumber.setText(creditcard.getCardNumber());
            firstname.setText(creditcard.getFirstname());
            lastname.setText(creditcard.getLastname());
            address1.setText(creditcard.getAddress1());
            address2.setText(creditcard.getAddress2());
            city.setText(creditcard.getCity());
            state.setText(creditcard.getCity());
            zip.setText(creditcard.getZip());
            country.setText(creditcard.getCountry());
            phone.setText(creditcard.getPhone());
            cardNumber.setText(creditcard.getCardNumber());
            expdate.setText(creditcard.getExpdate());
        }
    }

}
