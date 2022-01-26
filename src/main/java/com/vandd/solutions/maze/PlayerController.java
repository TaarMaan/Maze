package com.vandd.solutions.maze;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.glass.events.MouseEvent;
import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.GridModel.Painter;
import com.vandd.solutions.maze.GridModel.Tile;
import com.vandd.solutions.maze.algorithms.AlgoFactory;
import com.vandd.solutions.maze.algorithms.pathfind.FindingExit;
import com.vandd.solutions.maze.algorithms.pathfind.Mouse;
import com.vandd.solutions.maze.algorithms.pathfind.WavePropagation;
import com.vandd.solutions.maze.template.Template;
import com.vandd.solutions.maze.template.TemplateManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PlayerController extends Observable {
    private Mouse mouse;
    private Tile tile;
    private Painter painter;
    @FXML
    private Pane StackPaneP;
    @FXML
    private Button playerAlgorithmApply;
    @FXML
    private RadioButton playerAlgorithmRightHand;
    @FXML
    private RadioButton playerAlgorithmWave;
    @FXML
    private Menu playerMenuFile;
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
    private final TemplateManager templateManager = new TemplateManager();
    private Grid grid;
    private final WavePropagation wavePropagation = new WavePropagation();

    @FXML
    void initialize() {
        playerVisualizationApply.setDisable(true);
        playerVisualizationDynamic.setDisable(true);
        playerVisualizationStatic.setDisable(true);
        playerSpeedBall.setDisable(true);
        playerSpeedApply.setDisable(true);
        playerAlgorithmApply.setDisable(true);
        playerAlgorithmRightHand.setDisable(true);
        playerAlgorithmWave.setDisable(true);
        //темы
        playerTopicSpring.setOnAction(actionEvent -> {
            if (!playerImageSummer.isVisible() && !playerImageAutumn.isVisible() && !playerImageWinter.isVisible()) {
                playerImageSpring.setVisible(true);
            } else {
                playerImageSpring.setVisible(true);
                playerImageSummer.setVisible(false);
                playerImageWinter.setVisible(false);
                playerImageAutumn.setVisible(false);
            }
            if (grid != null) grid.setTheme("spring");
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
            if (grid != null) grid.setTheme("summer");

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
            if (grid != null) grid.setTheme("autumn");
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
            if (grid != null) grid.setTheme("winter");
            playerAlgorithmApply.setDisable(false);
            playerAlgorithmRightHand.setDisable(false);
            playerAlgorithmWave.setDisable(false);
        });

        //шаблоны
        templateManager.allTemplates().stream().map(t -> {
            MenuItem menuItem = new MenuItem(t.getName());
            String templateId = t.getTemplateId();
            menuItem.setOnAction(actionEvent ->
                    {
                        Template template = templateManager.load(templateId);
                        String theme = template.getTheme();
                        playerImageSpring.setVisible(false);
                        playerImageSummer.setVisible(false);
                        playerImageAutumn.setVisible(false);
                        playerImageWinter.setVisible(false);
                        if (theme.equals("summer")) {
                            playerImageSummer.setVisible(true);
                        }
                        if (theme.equals("autumn")) {
                            playerImageAutumn.setVisible(true);
                        }
                        if (theme.equals("spring")) {
                            playerImageSpring.setVisible(true);
                        }
                        if (theme.equals("winter")) {
                            playerImageWinter.setVisible(true);
                        }
                        var themeList = StackPaneP.getChildren().stream().limit(4).collect(Collectors.toList());
                        StackPaneP.getChildren().clear();
                        StackPaneP.getChildren().addAll(themeList);
                        this.grid = new Grid().fromLightweight(template.getGrid(), theme);
                        fillGrid(grid.getGrid());
                    }
            );
            return menuItem;
        }).forEach(mItem -> playerMenuFile.getItems().add(mItem));

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
        playerMenuReferenceApp.setOnAction(actionEvent -> {
            File html = new File("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\com\\vandd\\solutions\\maze\\help.html");
            try {
                Desktop.getDesktop().browse(html.toURI());
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Файл справки поврежден или отсутствует...");
                alert.showAndWait();
            }
        });
        //Установка группы для радиокнопок(алгоритмы)
        ToggleGroup groupA = new ToggleGroup();
        playerAlgorithmRightHand.setToggleGroup(groupA);
        playerAlgorithmWave.setToggleGroup(groupA);
        playerAlgorithmWave.setSelected(true);

        //установка обработчика события нажатия
        playerAlgorithmApply.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupA.getSelectedToggle();
            if (selection.equals(playerAlgorithmWave)) {
                AlgoFactory.getFindingExit(FindingExit.Algorithms.WavePropagation);
            } else if (selection.equals(playerAlgorithmRightHand)) {
                AlgoFactory.getFindingExit(FindingExit.Algorithms.RightHand);
            }
            playerVisualizationApply.setDisable(false);
            playerVisualizationDynamic.setDisable(false);
            playerVisualizationStatic.setDisable(false);
        });

        //Установка группы для радиокнопок(Тип визуализации)
        ToggleGroup groupV = new ToggleGroup();
        playerVisualizationDynamic.setToggleGroup(groupV);
        playerVisualizationStatic.setToggleGroup(groupV);
        playerVisualizationDynamic.setSelected(true);

        //установка обработчика события для выбора типа визуализации
        playerVisualizationApply.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupV.getSelectedToggle();
if(selection.equals(playerVisualizationDynamic)){
    playerSpeedBall.setDisable(false);
    playerSpeedApply.setDisable(false);
}else if(selection.equals(playerVisualizationStatic)){

}

        });

        //алгоритм старт
        playerStart.setOnAction(actionEvent -> {
            //что-то нахуй не то с Tile и координаты входа задать
            System.out.println(grid.executeFinding(AlgoFactory.getFindingExit(FindingExit.Algorithms.WavePropagation)));
            /*int EntranceX = tile.getX();
            int EntranceY = tile.getY();
            mouse = new Mouse(EntranceX, EntranceY);
            changeDirection(grid.getXSize() - 1, grid.getYSize() - 1);
            grid.setMouse(mouse);*/
            //System.out.println(grid.executeFinding(AlgoFactory.getFindingExit(FindingExit.Algorithms.RightHand)));
        });

        playerSpeed1.setOnAction(actionEvent -> {
            List<Tile>path = new ArrayList<>();
            wavePropagation.algorithm(grid, path);

        });
        playerSpeed2.setOnAction(actionEvent -> {

        });
        playerSpeed3.setOnAction(actionEvent -> {

        });
        playerSpeed4.setOnAction(actionEvent -> {

        });
        playerSpeedApply.setOnAction(actionEvent -> {

        });
    }

    private void fillGrid(Tile[][] tiles) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                StackPaneP.getChildren().add(tile.getStackPane());
            }
        }
    }

    public void changeDirection(int x_size, int y_size) {
        //down location
        if (mouse.y == y_size) {
            mouse.direction = 0;
        }
        //left location
        else if (mouse.x == 0) {
            mouse.direction = 1;
        }
        //up location
        else if (mouse.y == 0) {
            mouse.direction = 2;
        }
        //right location
        else if (mouse.x == x_size) {
            mouse.direction = 3;
        }
    }
}

