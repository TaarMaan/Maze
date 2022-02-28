package com.vandd.solutions.maze;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.GridModel.Tile;
import com.vandd.solutions.maze.algorithms.AlgoFactory;
import com.vandd.solutions.maze.algorithms.pathfind.AStar;
import com.vandd.solutions.maze.algorithms.pathfind.FindingExit;
import com.vandd.solutions.maze.algorithms.pathfind.HeuristicStrategy;
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
    private int sleepDuration;

    @FXML
    void initialize() {
        playerSpeedApply.setDisable(true);
        playerSpeedBall.setDisable(true);
        playerVisualizationStatic.setDisable(true);
        playerVisualizationApply.setDisable(true);
        playerVisualizationDynamic.setDisable(true);
        playerAlgorithmWave.setDisable(true);
        playerAlgorithmRightHand.setDisable(true);
        playerAlgorithmApply.setDisable(true);
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
            playerAlgorithmWave.setDisable(false);
            playerAlgorithmRightHand.setDisable(false);
            playerAlgorithmApply.setDisable(false);
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
            playerAlgorithmWave.setDisable(false);
            playerAlgorithmRightHand.setDisable(false);
            playerAlgorithmApply.setDisable(false);

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
            playerAlgorithmWave.setDisable(false);
            playerAlgorithmRightHand.setDisable(false);
            playerAlgorithmApply.setDisable(false);
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
                        /*Tile ent = grid.getRoot();
                        int cost = ent.direction;
                        grid.setDirection(cost,grid);*/
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
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Images\\icon.png"));
                alert.showAndWait();
            }
        });
        //Установка группы для радиокнопок(алгоритмы)
        ToggleGroup groupA = new ToggleGroup();
        playerAlgorithmRightHand.setToggleGroup(groupA);
        playerAlgorithmWave.setToggleGroup(groupA);
        playerAlgorithmRightHand.setSelected(true);

        //установка обработчика события нажатия
        playerAlgorithmApply.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupA.getSelectedToggle();
            if (selection.equals(playerAlgorithmWave)) {
                AlgoFactory.getFindingExit(FindingExit.Algorithms.WavePropagation,AlgoFactory.getHeuristicStrategy(AStar.Heuristic.Manhattan));
                //,AlgoFactory.getHeuristicStrategy(RightHand.Heuristic.Manhattan)
            } else if (selection.equals(playerAlgorithmRightHand)) {
                AlgoFactory.getFindingExit(FindingExit.Algorithms.AStar,AlgoFactory.getHeuristicStrategy(AStar.Heuristic.Manhattan));
                //,AlgoFactory.getHeuristicStrategy(RightHand.Heuristic.Manhattan)
            }
            playerVisualizationApply.setDisable(false);
            playerVisualizationDynamic.setDisable(false);
            playerVisualizationStatic.setDisable(false);
        });

        //Установка группы для радиокнопок(Тип визуализации)
        ToggleGroup groupV = new ToggleGroup();
        playerVisualizationDynamic.setToggleGroup(groupV);
        playerVisualizationStatic.setToggleGroup(groupV);
        playerVisualizationStatic.setSelected(true);
        playerVisualizationApply.setOnAction(actionEvent -> {
            if (playerVisualizationDynamic.isSelected()) {
                playerSpeedBall.setDisable(false);
                playerSpeedApply.setDisable(false);
            }
        });

        //алгоритм старт
        playerStart.setOnAction(actionEvent -> {
            if(playerAlgorithmWave.isSelected())
            System.out.println(grid.executeFinding(sleepDuration, AlgoFactory.getFindingExit(FindingExit.Algorithms.WavePropagation, AlgoFactory.getHeuristicStrategy(AStar.Heuristic.Manhattan))));
            //, AlgoFactory.getHeuristicStrategy(RightHand.Heuristic.Manhattan)
else if(playerAlgorithmRightHand.isSelected())

    System.out.println(grid.executeFinding(sleepDuration, AlgoFactory.getFindingExit(FindingExit.Algorithms.AStar, AlgoFactory.getHeuristicStrategy(AStar.Heuristic.Manhattan))));
//, AlgoFactory.getHeuristicStrategy(RightHand.Heuristic.Manhattan)
        });

        playerSpeed1.setOnAction(actionEvent -> {
            playerSpeed1.setDisable(true);
            playerSpeed2.setDisable(false);
            playerSpeed3.setDisable(false);
            playerSpeed4.setDisable(false);
        });
        playerSpeed2.setOnAction(actionEvent -> {
            playerSpeed1.setDisable(false);
            playerSpeed2.setDisable(true);
            playerSpeed3.setDisable(false);
            playerSpeed4.setDisable(false);
        });
        playerSpeed3.setOnAction(actionEvent -> {
            playerSpeed1.setDisable(false);
            playerSpeed2.setDisable(false);
            playerSpeed3.setDisable(true);
            playerSpeed4.setDisable(false);
        });
        playerSpeed4.setOnAction(actionEvent -> {
            playerSpeed1.setDisable(false);
            playerSpeed2.setDisable(false);
            playerSpeed3.setDisable(false);
            playerSpeed4.setDisable(true);
        });
        playerSpeedApply.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupV.getSelectedToggle();
            if (selection.equals(playerVisualizationStatic)) {
                sleepDuration = 0;
            } else if (selection.equals(playerVisualizationDynamic)) {
                if (playerSpeed1.isDisable()) {
                    sleepDuration = 100;
                } else if (playerSpeed2.isDisable()) {
                    sleepDuration = 50;
                } else if (playerSpeed3.isDisable()) {
                    sleepDuration = 25;
                } else if (playerSpeed4.isDisable()) {
                    sleepDuration = 5;
                }
            }
        });
    }

    private void fillGrid(Tile[][] tiles) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                StackPaneP.getChildren().add(tile.getStackPane());
            }
        }
    }
    public boolean doShortestPathAlgorithm(FindingExit.Algorithms algorithm, AStar.Heuristic heuristic) throws InterruptedException
    {
        HeuristicStrategy heuristicStrategy = AlgoFactory.getHeuristicStrategy(heuristic);
        FindingExit pathfindingStrategy = AlgoFactory.getFindingExit(algorithm, heuristicStrategy);
        return this.grid.executePathfinding(sleepDuration,pathfindingStrategy);
    }
}

