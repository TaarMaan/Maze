package appandcontrollers.maze;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class AuthenticationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button entranceA;

    @FXML
    private Button entranceP;

    @FXML
    private TextField loginA;

    @FXML
    private TextField loginP;

    @FXML
    private PasswordField passwordA;

    @FXML
    private Tab tabA;

    @FXML
    private Tab tabP;

    @FXML
    void initialize() {
        //Задание и проверка логина игрока
        entranceP.setOnAction(ActionEvent -> {
            String loginPl = loginP.getText();

            if ((loginPl.length() > 8) || loginPl.length() < 4) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое количество символов в нике");
                alert.showAndWait();
            } else {
                if (loginPl.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("В поле Логин пусто! Введите свой ник");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successful authentication");
                    alert.setHeaderText("Welcome, " + loginP.getText());
                    alert.showAndWait();

                    //переход в поле игрока,после нажатия войти
                    entranceP.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("PlayerFiled.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Окно создания нового лабиринта");
                    stage.getIcons().add(new Image("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Images\\icon.png"));
                    stage.showAndWait();
                }
            }
        });
        //Задание и проверка логина и пароля админа
        //Не хватает проверки на другой тип данных, так как при вводе 1234 и.т.д. он выдает ошибку в консоли,а не окном
        entranceA.setOnAction(actionEvent -> {
            String loginAd = loginA.getText();
            String passwordAd = passwordA.getText();
            String LOGIN_ADMIN = "Admin";
            String PASSWORD_ADMIN = "12345";
            if ((!(loginAd.equals(LOGIN_ADMIN)) || !(passwordAd.equals(PASSWORD_ADMIN)))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Некорректные Логин и Пароль");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Welcome!");
                alert.setHeaderText(null);
                alert.setContentText("Вы зашли как Администратор");
                alert.showAndWait();
                //переход в поле администратора,после нажатия войти
                entranceA.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AdminField.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Окно создания нового лабиринта");
                stage.getIcons().add(new Image("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Images\\icon.png"));
                stage.showAndWait();
            }
        });
    }
}


