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
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import models.Account;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLChangePasswordController extends SceneChangeController implements Initializable {

    private Stage stage;
    private Account account;

    @FXML
    private PasswordField psw, newpsw, newpsw2;

    @FXML
    private Label pswLabel, newpswLabel, newpswLabel2;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setAccount(Account account) {
        this.account = VWallet.VWallet.refreshAccount(account);
    }

    @FXML
    private void confirmButtonAction(ActionEvent event) throws IOException {
        if (psw.getText().isEmpty()) {
            pswLabel.setText("Please fill the password");
            newpswLabel.setText("");
            newpswLabel2.setText("");
            psw.clear();
            newpsw.clear();
            newpsw2.clear();
        } else if (!psw.getText().matches("[a-zA-Z0-9]*")) {
            pswLabel.setText("Password must be a-z A-Z 0-9");
            newpswLabel.setText("");
            newpswLabel2.setText("");
            psw.clear();
            newpsw.clear();
            newpsw2.clear();

        } else if (psw.getText().length() < 5) {
            pswLabel.setText("Password must have at least 6 characters");
            newpswLabel.setText("");
            newpswLabel2.setText("");
            psw.clear();
            newpsw.clear();
            newpsw2.clear();
        } else if (newpsw.getText().isEmpty()) {
            newpswLabel.setText("Please fill the password");
            pswLabel.setText("");
            newpswLabel2.setText("");
            psw.clear();
            newpsw.clear();
            newpsw2.clear();
        } else if (!newpsw.getText().matches("[a-zA-Z0-9]*")) {
            newpswLabel.setText("Password must be a-z A-Z 0-9");
            pswLabel.setText("");
            newpswLabel2.setText("");
            psw.clear();
            newpsw.clear();
            newpsw2.clear();

        } else if (newpsw.getText().length() < 6) {
            newpswLabel.setText("Password must have at least 6 characters");
            pswLabel.setText("");
            newpswLabel2.setText("");
            psw.clear();
            newpsw.clear();
            newpsw2.clear();
        } else if (!newpsw.getText().matches(newpsw2.getText())) {
            newpswLabel2.setText("Password not match");
            newpswLabel.setText("");
            psw.setText("");
            psw.clear();
            newpsw.clear();
            newpsw2.clear();
        } else {
            pswLabel.setText("");
            newpswLabel.setText("");
            newpswLabel2.setText("");
            if(VWallet.VWallet.changePassword(account, psw.getText(), newpsw.getText())){
            ((Node) event.getSource()).getScene().getWindow().hide();
            Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Success");
                info.setHeaderText("Success");
                info.setContentText("Your Password has been changed");   
                info.showAndWait();
                optionScene(stage, account);
            }
            else{
                pswLabel.setText("Invalid Password");
            }
        }
    }

    @FXML
    private void closePopupAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        TextFormatter<String> newpswformatterr = new TextFormatter<>(pswFilter);
        newpsw.setTextFormatter(newpswformatterr);
        TextFormatter<String> newpsw2formatterr = new TextFormatter<>(pswFilter);
        newpsw2.setTextFormatter(newpsw2formatterr);

        /////////////////////////////////////////////////////////////////////////////////////////////////
    }

}
