package appandcontrollers.maze;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane ImageField;

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

        //Установка группы для радиокнопок(алгоритмы)
        ToggleGroup groupA = new ToggleGroup();
        playerAlgorithmBreadthFirstSearch.setToggleGroup(groupA);
        playerAlgorithmDepthFirstSearch.setToggleGroup(groupA);

        //установка аобработчика события нажатия
        playerAlgorithmApply.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupA.getSelectedToggle();
            playerAlgorithmApply.setText(selection.getText());
            // включение алгоритма в сетку...
        });

        //Установка группы для радиокнопок(Тип визуализации)
        ToggleGroup groupV = new ToggleGroup();
        playerVisualizationDynamic.setToggleGroup(groupV);
        playerVisualizationStatic.setToggleGroup(groupV);

        //установка обработчика события для выбора типа визуализации
        playerVisualizationApply.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupV.getSelectedToggle();
            playerVisualizationApply.setText(selection.getText());
            //включение алгоритма в сетку и разблокирование выбора скорости
            // персонажа(если выбран динамический)...

        });
    }

}
