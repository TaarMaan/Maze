package appandcontrollers.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PlayerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button playerAlgorithmApply;

    @FXML
    private RadioButton playerAlgorithmBreadthFirstSearch;

    @FXML
    private RadioButton playerAlgorithmDepthFirstSearch;

    @FXML
    private Menu playerMenuFile;

    @FXML
    private MenuItem playerMenuFileLoad;

    @FXML
    private Menu playerMenuReference;

    @FXML
    private MenuItem playerMenuReferenceApp;

    @FXML
    private MenuItem playerMenuReferenceDevelopers;

    @FXML
    private MenuItem playerSpeed1;

    @FXML
    private MenuItem playerSpeed2;

    @FXML
    private MenuItem playerSpeed3;

    @FXML
    private MenuItem playerSpeed4;

    @FXML
    private Button playerSpeedApply;

    @FXML
    private MenuButton playerSpeedBall;

    @FXML
    private ImageView playerImageSpring;
    @FXML
    private ImageView playerImageSummer;
    @FXML
    private ImageView playerImageWinter;
    @FXML
    private ImageView playerImageAutumn;

    @FXML
    private Group playerTopicGroup;

    @FXML
    private Button playerStart;

    @FXML
    private MenuButton playerTopic;

    @FXML
    private Button playerTopicApply;

    @FXML
    private MenuItem playerTopicAutumn;

    @FXML
    private MenuItem playerTopicSpring;

    @FXML
    private MenuItem playerTopicSummer;

    @FXML
    private MenuItem playerTopicWinter;

    @FXML
    private Button playerVisualizationApply;

    @FXML
    private RadioButton playerVisualizationDynamic;

    @FXML
    private RadioButton playerVisualizationStatic;

    @FXML
    void initialize() {
        //темы
        playerTopicSpring.setOnAction(actionEvent -> {
            if (!playerImageSummer.isVisible() && !playerImageAutumn.isVisible() && !playerImageWinter.isVisible()) {
                playerImageSpring.setVisible(true);
            } else {
                playerImageSpring.setVisible(true);
                playerImageSummer.setVisible(false);
                playerImageAutumn.setVisible(false);
                playerImageWinter.setVisible(false);
            }
        });
        playerTopicSummer.setOnAction(actionEvent -> {
            if (!playerImageSpring.isVisible() && !playerImageAutumn.isVisible() && !playerImageWinter.isVisible()) {
                playerImageSummer.setVisible(true);
            } else {
                playerImageSpring.setVisible(false);
                playerImageSummer.setVisible(true);
                playerImageAutumn.setVisible(false);
                playerImageWinter.setVisible(false);
            }
        });
        playerTopicAutumn.setOnAction(actionEvent -> {
            if (!playerImageSummer.isVisible() && !playerImageSpring.isVisible() && !playerImageWinter.isVisible()) {
                playerImageAutumn.setVisible(true);
            } else {
                playerImageSpring.setVisible(false);
                playerImageSummer.setVisible(false);
                playerImageAutumn.setVisible(true);
                playerImageWinter.setVisible(false);
            }
        });
        playerTopicWinter.setOnAction(actionEvent -> {
            if (!playerImageSummer.isVisible() && !playerImageAutumn.isVisible() && !playerImageSpring.isVisible()) {
                playerImageWinter.setVisible(true);
            } else {
                playerImageSpring.setVisible(false);
                playerImageSummer.setVisible(false);
                playerImageAutumn.setVisible(false);
                playerImageWinter.setVisible(true);
            }
        });


//        iv.fitWidthProperty().bind(root.widthProperty());
        //      iv.fitHeightProperty().bind(root.heightProperty());

        //справка о разработчиках
        playerMenuReferenceDevelopers.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AboutDevelopers.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("О разработчиках");
            stage.setResizable(false);
            stage.getIcons().add(new Image("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Images\\icon.png"));
            stage.showAndWait();
        });

        //кнопка загрузить у игрока
        playerMenuFileLoad.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            try {
                Scanner scanner = new Scanner(file);
                //тут написать логику того как что будет делаться или отрисовываться при загрузке хуйни
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            fileChooser.setInitialDirectory(new File("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Load"));
        });


        //Установка группы для радиокнопок(алгоритмы)
        ToggleGroup groupA = new ToggleGroup();
        playerAlgorithmBreadthFirstSearch.setToggleGroup(groupA);
        playerAlgorithmDepthFirstSearch.setToggleGroup(groupA);

        //установка обработчика события нажатия
        playerAlgorithmApply.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupA.getSelectedToggle();

            // включение алгоритма в сетку...
        });

        //Установка группы для радиокнопок(Тип визуализации)
        ToggleGroup groupV = new ToggleGroup();
        playerVisualizationDynamic.setToggleGroup(groupV);
        playerVisualizationStatic.setToggleGroup(groupV);

        //установка обработчика события для выбора типа визуализации
        playerVisualizationApply.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupV.getSelectedToggle();

            //включение алгоритма в сетку и разблокирование выбора скорости
            // персонажа(если выбран динамический)...

        });
    }

}
