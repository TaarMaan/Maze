package com.vandd.solutions.maze;

import com.vandd.solutions.maze.GridModel.Grid;
import com.vandd.solutions.maze.GridModel.Tile;
import com.vandd.solutions.maze.algorithms.AlgoFactory;
import com.vandd.solutions.maze.algorithms.generation.MazeGeneration;
import com.vandd.solutions.maze.template.Template;
import com.vandd.solutions.maze.template.TemplateManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminController {

    @FXML
    private ResourceBundle resources;
    private Pane parentGridPane;
    private GridPane gridPane;
    private Grid model;
    private final TemplateManager templateManager = new TemplateManager();
    @FXML
    private ComboBox<Tile.Type> AdminMenuArrangeEntxit;
    private String theme = null;
    @FXML
    private Button adminApply;
    @FXML
    private Button adminArrangeAlgorithm;
    @FXML
    private RadioButton adminArrangeAuto;
    @FXML
    private Button adminArrangeEntrence;
    @FXML
    private RadioButton adminArrangeManually;
    @FXML
    private RadioButton adminKruskal;
    @FXML
    private TextField adminHeight;
    @FXML
    private ImageView adminImageAutumn;
    @FXML
    private ImageView adminImageSpring;
    @FXML
    private ImageView adminImageSummer;
    @FXML
    private ImageView adminImageWinter;
    @FXML
    private MenuItem adminMenuFileSave;
    @FXML
    private MenuItem adminMenuReferenceApp;
    @FXML
    private MenuItem adminMenuReferenceDevelopers;
    @FXML
    private RadioButton adminPrim;
    @FXML
    private Pane adminStackPane;
    @FXML
    private Button adminStart;
    @FXML
    private MenuItem adminTopicAutumn;
    @FXML
    private MenuItem adminTopicSpring;
    @FXML
    private MenuItem adminTopicSummer;
    @FXML
    private MenuItem adminTopicWinter;
    @FXML
    private TextField adminWidth;
    private Grid grid;

    @FXML
    public void initialize() {
        adminKruskal.setDisable(true);
        adminPrim.setDisable(true);
        adminArrangeAlgorithm.setDisable(true);
        adminArrangeAuto.setDisable(true);
        adminArrangeManually.setDisable(true);
        AdminMenuArrangeEntxit.setDisable(true);

        AdminMenuArrangeEntxit.getItems().add(Tile.Type.ENTRANCE);
        AdminMenuArrangeEntxit.getItems().add(Tile.Type.EXIT);
//        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED);
//        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.PATH);
//        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.HIGHLIGHT);
//        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED_DENSE);
//        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED_LIGHT);
//        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED_MAX);
//        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED_MEDIUM);
          AdminMenuArrangeEntxit.getSelectionModel().selectFirst();
        AdminMenuArrangeEntxit.setOnAction(actionEvent -> {
            if (grid != null) grid.changeClickType(AdminMenuArrangeEntxit.getSelectionModel().getSelectedItem());
        });

        adminArrangeEntrence.setOnAction(actionEvent -> {
            if (grid != null) {
                grid.addRandomEntranceExit();
            }
        });

        adminArrangeAuto.setOnAction(actionEvent ->
        {
            AdminMenuArrangeEntxit.setDisable(true);
            adminArrangeEntrence.setDisable(false);
        });

        adminArrangeManually.setOnAction(actionEvent ->
        {
            AdminMenuArrangeEntxit.setDisable(false);
            adminArrangeEntrence.setDisable(true);
        });

        adminMenuFileSave.setOnAction(actionEvent ->
        {
            if (grid != null && (adminPrim.isSelected() || adminKruskal.isSelected()) && theme != null) {
                String a;
                if (adminPrim.isSelected()) {
                    a = "Prim";
                } else {
                    a = "Kruskal";
                }
                //использовать для сохранения шаблона
                templateManager.save(new Template("sample" + " " + theme + "_" + adminHeight.getText()
                        + "_" + adminWidth.getText() + " " + a, grid, theme));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка!");
                alert.setHeaderText("Нечего сохранять!\n" +
                        "Заполните сетку и инициализируйте генерацию лабиринта");
                alert.setContentText(null);
                alert.showAndWait();
            }
        });

        adminApply.setOnAction(actionEvent -> {
            int x = Integer.parseInt(adminWidth.getText());
            int y = Integer.parseInt(adminHeight.getText());
            if (y >= 9 && y <= 31 && y % 2 == 1) {
                if (x >= 9 && x <= 31 && x % 2 == 1) {
                    adminKruskal.setDisable(false);
                    adminPrim.setDisable(false);
                    adminArrangeAlgorithm.setDisable(false);
                    //запуск генерации сетки
                    var themeList = adminStackPane.getChildren().stream().limit(4).collect(Collectors.toList());
                    adminStackPane.getChildren().clear();
                    adminStackPane.getChildren().addAll(themeList);
                    grid = new Grid();
                    grid.setTheme(theme);
                    grid.gridInit(x, y, 600 / Integer.max(x, y));
                    fillGrid(grid.getGrid());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText("Некорректно введены параметры сетки!");
                    alert.showAndWait();
                }
            }
        });
        //Темы
        adminTopicSpring.setOnAction(actionEvent -> {
            if (!adminImageSummer.isVisible() && !adminImageAutumn.isVisible() && !adminImageWinter.isVisible()) {
                adminImageSpring.setVisible(true);
                theme = "spring";
                if (grid != null) grid.setTheme(theme);
            } else {
                adminImageSpring.setVisible(true);
                theme = "spring";
                if (grid != null) grid.setTheme(theme);
                adminImageSummer.setVisible(false);
                adminImageAutumn.setVisible(false);
                adminImageWinter.setVisible(false);
            }
        });
        adminTopicSummer.setOnAction(actionEvent -> {
            if (!adminImageSpring.isVisible() && !adminImageAutumn.isVisible() && !adminImageWinter.isVisible()) {
                adminImageSummer.setVisible(true);
                theme = "summer";
                if (grid != null) grid.setTheme(theme);
            } else {
                adminImageSpring.setVisible(false);
                adminImageSummer.setVisible(true);
                theme = "summer";
                if (grid != null) grid.setTheme(theme);
                adminImageAutumn.setVisible(false);
                adminImageWinter.setVisible(false);
            }
        });
        adminTopicAutumn.setOnAction(actionEvent -> {
            if (!adminImageSummer.isVisible() && !adminImageSpring.isVisible() && !adminImageWinter.isVisible()) {
                adminImageAutumn.setVisible(true);
                theme = "autumn";
                if (grid != null) grid.setTheme(theme);
            } else {
                adminImageSpring.setVisible(false);
                adminImageSummer.setVisible(false);
                adminImageAutumn.setVisible(true);
                theme = "autumn";
                if (grid != null) grid.setTheme(theme);
                adminImageWinter.setVisible(false);
            }
        });
        adminTopicWinter.setOnAction(actionEvent -> {
            if (!adminImageSummer.isVisible() && !adminImageAutumn.isVisible() && !adminImageSpring.isVisible()) {
                adminImageWinter.setVisible(true);
                theme = "winter";
                if (grid != null) grid.setTheme(theme);
            } else {
                adminImageSpring.setVisible(false);
                adminImageSummer.setVisible(false);
                adminImageAutumn.setVisible(false);
                adminImageWinter.setVisible(true);
                theme = "winter";
                if (grid != null) grid.setTheme(theme);
            }
        });
        //установка группы для радиокнопок(алгоритмы)
        ToggleGroup groupA = new ToggleGroup();
        adminKruskal.setToggleGroup(groupA);
        adminPrim.setToggleGroup(groupA);
        adminPrim.setSelected(true);
        adminArrangeAlgorithm.setOnAction(actionEvent ->
        {
            RadioButton selection = (RadioButton) groupA.getSelectedToggle();
            adminArrangeAuto.setDisable(false);
            adminArrangeEntrence.setDisable(false);
            adminArrangeManually.setDisable(false);
            MazeGeneration.MazeGen mazeGen = null;
            if (selection.equals(adminKruskal)) {
                mazeGen = MazeGeneration.MazeGen.Kruskal;
            } else if (selection.equals(adminPrim)) {
                mazeGen = MazeGeneration.MazeGen.Prim;
            }
            AlgoFactory.getMazeGenStrategy(mazeGen).generate(grid);
        });
        //установка группы для радиокнопок(способ расстановки входов)
        ToggleGroup groupV = new ToggleGroup();
        adminArrangeAuto.setToggleGroup(groupV);
        adminArrangeManually.setToggleGroup(groupV);
        adminArrangeAuto.setSelected(true);
        //справка о разработчиках
        adminMenuReferenceDevelopers.setOnAction(actionEvent ->
        {
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
            stage.getIcons().add(new Image("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Images\\icon.png"));
            stage.showAndWait();
        });
        adminMenuReferenceApp.setOnAction(actionEvent -> {
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
        adminStart.setOnAction(actionEvent -> {
            Thread t = new Thread();
            t.run();
            if (adminWidth.getText().equals("") && adminHeight.getText().equals("")) {
                int a = 25;
                adminWidth.setText(String.valueOf(a));
                adminHeight.setText(String.valueOf(a));
            }
            adminTopicWinter.fire();
            adminPrim.isSelected();
            adminApply.fire();
            adminArrangeAlgorithm.fire();
            try {
                t.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            adminArrangeAuto.isSelected();
            adminArrangeEntrence.fire();
            t.interrupt();


        });
    }

    private void fillGrid(Tile[][] tiles) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                adminStackPane.getChildren().add(tile.getStackPane());
            }
        }
    }
}
