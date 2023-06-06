import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author : Gathsara
 * created : 6/6/2023 -- 8:22 PM
 **/

public class Login extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("/view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Login Form");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
