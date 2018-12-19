/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLEnterEmailController extends SceneChangeController implements Initializable {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void closePopupAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private Label wrongformat;

    @FXML
    private TextField email;

    @FXML
    private void confirmButtonAction(ActionEvent event) throws MessagingException {
        if (email.getText().isEmpty()) {
            wrongformat.setText("Please fill the email");;
        } else if (!email.getText().matches("[a-zA-Z0-9_.-]*@[a-zA-Z0-9_.-]*")) {
            wrongformat.setText("Invalid email");
        } else {
            wrongformat.setText("");
            if (VWallet.VWallet.resetPassword(email.getText())) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Success");
                info.setHeaderText("Success");
                info.setContentText("Your password has been sent to your email"
                        + "\nPlease Change your password after login");
                ((Node) event.getSource()).getScene().getWindow().hide();
                info.showAndWait();
                loginScene(stage);
            } else {
                wrongformat.setText("Invalid email");
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
