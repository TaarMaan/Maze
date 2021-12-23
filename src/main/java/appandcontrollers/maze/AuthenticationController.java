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
        entranceA.setOnAction(ActionEvent -> {
            String login = loginP.getText();
            if (login.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("В поле Логин пусто! Введите свой ник");
                alert.showAndWait();
            }
            if (login.equals(loginP.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое количество символов в нике");
                alert.showAndWait();
            }
            if (loginP.equals(loginP.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое количество символов в нике");
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
        });
    }





           /* loginA.getText() ;
            passwordA.getText();
            int[] array = new int[50];
            array[0] = 1;
            array[1] = 2;
            array[2] = 3;
            array[3] = 9;
            for (int i = 4; i >= 49; i++) {
                array[i] = 10;
                i++;
                решить проблему 4-8 символов
                if (loginA.getText() == null || loginA.getText().length() == array[i] ||
                        passwordA.getText() == null || passwordA.getText().length() == array[i]) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Неправильный логин или пароль");
                    alert.showAndWait();
                } else {*/

                    //loginUser(login1, password1);
                    //Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    //alert.setTitle("Welcome!");
                    //alert.setHeaderText(null);
                    //alert.setContentText("Вы зашли как Администратор");
                    //alert.showAndWait();

                    //переход в поле администратора,после нажатия войти
               /*     entranceA.getScene().getWindow().hide();
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
            }*/
        }


     /*   решить проблему 4-8 символов
        entranceP.setOnAction(actionEvent -> {

            int[] array = new int[50];
            array[0] = 1;
            array[1] = 2;
            array[2] = 3;
            array[3] = 9;
            for (int i = 4; i >= 49; i++) {
                array[i] = 10;
                i++;

                if (loginP.getText().length() == array[i]) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Недопустимое количество символов в нике");
                    alert.showAndWait();
                }
                if (loginP.getText() == null) {
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
                    stage.showAndWait();
                }

            }
        });
    }
}*/


