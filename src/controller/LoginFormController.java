/**
 * @author : Gathsara
 * created : 6/6/2023 -- 7:47 PM
 **/

package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginAp;
    public TextField txtUserName;
    public JFXButton btnLogin;

    private String client1 = "client1";
    private String client2 = "client2";
    private String client3 = "client3";
    String userName = "";

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        userName = txtUserName.getText();

        /*check client 1*/
        if (userName.equalsIgnoreCase(client1)) {
            runServer();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Client1.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            txtUserName.clear();

            /*check client 2*/
        } else if (userName.equalsIgnoreCase(client2)) {
            runServer();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Client2.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            txtUserName.clear();

            /*check client 3*/
        } else if (userName.equalsIgnoreCase(client3)) {
            runServer();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Client3.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            txtUserName.clear();
        }
    }

    /*run server parallel because without server client will not run*/
    private void runServer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Server.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

