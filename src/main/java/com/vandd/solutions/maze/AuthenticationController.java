package com.vandd.solutions.maze;

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


public class AuthenticationController{

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
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("file:icon.png"));
                    stage.showAndWait();
                }
            }
        });
        //Задание и проверка логина и пароля админа
        entranceA.setOnAction(actionEvent -> {
            String loginAd = loginA.getText();
            String passwordAd = passwordA.getText();

            if (loginAd.equals("Admin") && passwordAd.equals("12345")) {

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
                Stage stage1 = new Stage();
                Scene scene1 = new Scene(root);
                scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                stage1.setScene(scene1);
                stage1.setTitle("Окно создания нового лабиринта");
                stage1.setResizable(false);
                stage1.getIcons().add(new Image("file:icon.png"));
                stage1.showAndWait();
            } else {
                if (loginAd.equals("") || passwordAd.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("В поле логина или пароля пусто");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Неправильный логин или пароль");
                    alert.showAndWait();
                }
            }
        });
    }
}



