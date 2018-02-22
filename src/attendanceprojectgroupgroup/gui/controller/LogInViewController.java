/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceprojectgroupgroup.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Samuel
 */
public class LogInViewController implements Initializable {

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private PasswordField txtFieldPassword;
    @FXML
    private Button btnLogIn;

    private TeacherViewController tVC = new TeacherViewController();
    private StudentViewController sVC = new StudentViewController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickLogIn(ActionEvent event) throws IOException {
        String username = txtFieldUsername.getText();
        String pass = txtFieldPassword.getText();

        if (username.equals("Teacher") && pass.equals("tpass")) {

            Stage stage = new Stage();

            // stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/attendanceprojectgroupgroup/gui/view/TeacherView.fxml"));

            Parent root = fxLoader.load();

            TeacherViewController controller = fxLoader.getController();
            controller.setParentWindowController(this);

            controller.labelTeachersName.setText(username);

            Scene scene = new Scene(root);
            stage.setTitle("Teacher");
            stage.setScene(scene);
            stage.show();

            Stage window = (Stage) btnLogIn.getScene().getWindow();
            window.close();
        } else if (username.equals("StudentA") || username.equals("StudentB") && pass.equals("spass")) {
            Stage stage = new Stage();

            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/attendanceprojectgroupgroup/gui/view/StudentView.fxml"));

            Parent root = fxLoader.load();

            StudentViewController controller = fxLoader.getController();
            controller.setParentWindowController(this);

            Random rand = new Random();
            int random = rand.nextInt(999)+1;
            controller.labelStudentName.setText(username);
            controller.idLabel.setText(""+random);

            Scene scene = new Scene(root);
            stage.setTitle("Student");
            stage.setScene(scene);
            stage.show();

            Stage window = (Stage) btnLogIn.getScene().getWindow();
            window.close();

            if (username.endsWith("A")) {
                controller.labelClass.setText("Datamatiker_2017");

            } else if (username.endsWith("B")) {
                controller.labelClass.setText("ComputerScience_2017");

            }
        } else {
            System.out.println("System could not recognize your login. Are you a teacher or studentA or studentB? Try with tpass or spass.");
        }
    }

    private void openingStudentView() {

    }

}
