package UI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import models.Account;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLChangeEmailController extends SceneChangeController implements Initializable {

    private Stage stage;
    private Account account;

    @FXML
    private PasswordField psw;

    @FXML
    private TextField email;
    @FXML
    private Label pswLabel, emailLabel;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }

    @FXML
    private void closePopupAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (email.getText().isEmpty()) {
            emailLabel.setText("Please fill the email");
            pswLabel.setText("");
            psw.clear();
        } else if (!email.getText().matches("[a-zA-Z0-9_.-]*@[a-zA-Z0-9_.-]*")) {
            emailLabel.setText("Invalid email");
            pswLabel.setText("");
            psw.clear();
        } else if (psw.getText().isEmpty()) {
            pswLabel.setText("Please fill the password");
            emailLabel.setText("");
            psw.clear();
        } else if (!psw.getText().matches("[a-zA-Z0-9]*")) {
            pswLabel.setText("Password must be a-z A-Z 0-9");
            emailLabel.setText("");
            psw.clear();
        } else if (psw.getText().length() < 6) {
            pswLabel.setText("Password must have at least 6 characters");
            emailLabel.setText("");
            psw.clear();
        } else {
            pswLabel.setText("");
            emailLabel.setText("");
            int i = VWallet.VWallet.changeEmail(account, psw.getText(), email.getText());
            switch (i) {
                case 0:
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Success");
                    info.setHeaderText("Success");
                    info.setContentText("Your Name has been changed");
                    info.showAndWait();
                    optionScene(stage, account);
                    break;
                case 1:
                    pswLabel.setText("Invalid Password");
                    psw.clear();
                    break;
                case 2:
                    emailLabel.setText("Email Already Taken");
                    psw.clear();
                    break;
                default:
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error alert");
                    alert.setHeaderText("Unknown Error");
                    alert.setContentText("Please try again");

                    alert.showAndWait();
                    psw.clear();
                    break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

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

        /////////////////////////////////////////////////////////////////////////////////////////////////
    }

}
