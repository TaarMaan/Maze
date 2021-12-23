package appandcontrollers.maze;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static String LOGIN_ADMIN = "Admin";
    public static int PASSWORD_ADMIN = 12345;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Окно авторизации");
        stage.getIcons().add(new Image("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Images\\icon.png"));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }

}