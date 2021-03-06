/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import VWallet.VWallet;
import java.util.function.UnaryOperator;
import javafx.scene.control.TextFormatter;
import models.Account;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLRegistrationController extends SceneChangeController implements Initializable {

    @FXML
    private Label usernameLabel, pswLabel, pswLabel2, nameLabel, emailLabel;

    @FXML
    private TextField username, name, email;

    @FXML
    private PasswordField psw, psw2;

    @FXML
    private void signupButtonAction(ActionEvent event) throws IOException {
        //"[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+" for email
        if (username.getText().isEmpty()) {
            usernameLabel.setText("Please fill the username");
            pswLabel.setText("");
            pswLabel2.setText("");
            nameLabel.setText("");
            emailLabel.setText("");
            psw.clear();
            psw2.clear();
        } else if (!username.getText().matches("[a-zA-Z0-9]*")) {
            usernameLabel.setText("Username must be a-z A-Z 0-9");
            pswLabel.setText("");
            pswLabel2.setText("");
            nameLabel.setText("");
            emailLabel.setText("");
            psw.clear();
            psw2.clear();
        } else if (username.getText().length() < 4 || username.getText().length() > 12) {
            usernameLabel.setText("Username must have 4-12 characters");
            pswLabel.setText("");
            pswLabel2.setText("");
            nameLabel.setText("");
            emailLabel.setText("");
            psw.clear();
            psw2.clear();

        } else if (name.getText().isEmpty()) {
            nameLabel.setText("Please fill the name");
            usernameLabel.setText("");
            pswLabel.setText("");
            pswLabel2.setText("");
            emailLabel.setText("");
            psw.clear();
            psw2.clear();
        } else if (!name.getText().matches("[A-Z][a-z]* [A-Z][a-z]*") && !name.getText().matches("[A-Z][a-z]* [A-Z][a-z]*")) {
            nameLabel.setText("Please Enter your first and surname separate with ' ', both must be alphabet and their first alphabet must be Uppercase");
            usernameLabel.setText("");
            pswLabel.setText("");
            pswLabel2.setText("");
            emailLabel.setText("");
            psw.clear();
            psw2.clear();

        } else if (psw.getText().isEmpty()) {
            pswLabel.setText("Please fill the password");
            usernameLabel.setText("");
            nameLabel.setText("");
            pswLabel2.setText("");
            emailLabel.setText("");
            psw.clear();
            psw2.clear();
        } else if (!psw.getText().matches("[a-zA-Z0-9]*")) {
            pswLabel.setText("Password must be a-z A-Z 0-9");
            usernameLabel.setText("");
            nameLabel.setText("");
            pswLabel2.setText("");
            emailLabel.setText("");
            psw.clear();
            psw2.clear();

        } else if (psw.getText().length() < 6) {
            pswLabel.setText("Password must have at least 6 characters");
            usernameLabel.setText("");
            pswLabel2.setText("");
            nameLabel.setText("");
            emailLabel.setText("");
            psw.clear();
            psw2.clear();
        }
        else if (!psw.getText().matches(psw2.getText())) {
            pswLabel2.setText("Password not match");
            usernameLabel.setText("");
            pswLabel.setText("");
            nameLabel.setText("");
            emailLabel.setText("");
            psw.clear();
            psw2.clear();
            
        } else if (email.getText().isEmpty()) {
            emailLabel.setText("Please fill the email");
            pswLabel.setText("");
            pswLabel2.setText("");
            nameLabel.setText("");
            usernameLabel.setText("");
            psw.clear();
            psw2.clear();
        } else if (!email.getText().matches("[a-zA-Z0-9_.-]*@[a-zA-Z0-9_.-]*")) {
            emailLabel.setText("Invalid email");
            pswLabel.setText("");
            pswLabel2.setText("");
            nameLabel.setText("");
            usernameLabel.setText("");
            psw.clear();
            psw2.clear();

        } else {
            usernameLabel.setText("");
            pswLabel.setText("");
            nameLabel.setText("");
            pswLabel2.setText("");
            emailLabel.setText("");

            int i = VWallet.setRegister(username.getText(), psw.getText(), name.getText(), email.getText());
            switch (i) {
                case 0:
                    Account account = VWallet.isLogin(username.getText(), psw.getText());
                    walletScene((Stage) ((Node) event.getSource()).getScene().getWindow(), account);
                    break;
                case 1:
                    usernameLabel.setText("Username Already Taken!!");
                    psw.clear();
                    psw2.clear();
                    break;
                case 2:
                    emailLabel.setText("Email Already Taken!!");
                    psw.clear();
                    psw2.clear();
                    break;
                default:
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error alert");
                    alert.setHeaderText("Unknown Error");
                    alert.setContentText("Please try again");

                    alert.showAndWait();
                    psw.clear();
                    psw2.clear();
                    break;
            }

        }
    }

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        loginScene((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    private void closeBtnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
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
        //////////////////////////////////////////// name Format ///////////////////////////////////
        UnaryOperator<TextFormatter.Change> nameFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[A-Z]?[a-z]* ?[A-Z]?[a-z]* ?[A-Z]?[a-z]*")) {
                return change;
            }
            return null;

        };
        TextFormatter<String> nameformatter = new TextFormatter<>(nameFilter);
        name.setTextFormatter(nameformatter);

        /////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////// Password Format ///////////////////////////////////
        UnaryOperator<TextFormatter.Change> pswFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[A-Za-z0-9]*")) {
                return change;
            }
            return null;

        };
        TextFormatter<String> pswformatterr = new TextFormatter<>(pswFilter);
        psw.setTextFormatter(pswformatterr);
        TextFormatter<String> psw2formatterr = new TextFormatter<>(pswFilter);
        psw2.setTextFormatter(psw2formatterr);

        /////////////////////////////////////////////////////////////////////////////////////////////////
        
        //////////////////////////////////////////// Email Format ///////////////////////////////////
        UnaryOperator<TextFormatter.Change> emailFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z0-9_@.-]*")) {
                return change;
            }
            return null;

        };
        TextFormatter<String> emailformatter = new TextFormatter<>(emailFilter);
        email.setTextFormatter(emailformatter);

        /////////////////////////////////////////////////////////////////////////////////////////////////
    }

}
