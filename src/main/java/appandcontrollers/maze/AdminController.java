package appandcontrollers.maze;

import Algorithms.MazeGeneration;
import GridModel.Grid;
import GridModel.Tile;
//import GridModel.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController {

    @FXML
    private ResourceBundle resources;
    private Pane parentGridPane;
    private GridPane gridPane;
    private Grid model;
    @FXML
    private ComboBox AdminMenuArrangeEntxit;
    @FXML
    private URL location;

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
    private Menu adminMenuFile;

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
    private Menu adminMenuReference;

    @FXML
    private MenuItem adminMenuReferenceApp;

    @FXML
    private MenuItem adminMenuReferenceDevelopers;

    @FXML
    private RadioButton adminPrim;

    @FXML
    private StackPane adminStackPane;

    @FXML
    private Button adminStart;

    @FXML
    private MenuButton adminTopic;

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

    @FXML
    public void initialize() {
        adminKruskal.setDisable(true);
        adminPrim.setDisable(true);
        adminArrangeAlgorithm.setDisable(true);
        adminArrangeAuto.setDisable(true);
        adminArrangeManually.setDisable(true);
        AdminMenuArrangeEntxit.setDisable(true);
        adminArrangeEntrence.setDisable(true);

        AdminMenuArrangeEntxit = new ComboBox(FXCollections.observableArrayList(Tile.Type.values()));
        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED);
        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.PATH);
        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.HIGHLIGHT);
        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED_DENSE);
        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED_LIGHT);
        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED_MAX);
        AdminMenuArrangeEntxit.getItems().remove(Tile.Type.VISITED_MEDIUM);
        AdminMenuArrangeEntxit.getSelectionModel().selectFirst();

        adminApply.setOnAction(actionEvent -> {
            String x = String.valueOf(adminWidth.getText());
            String y = String.valueOf(adminHeight.getText());
            if (y.equals("9") || y.equals("11") || y.equals("13") || y.equals("15") || y.equals("17")
                    || y.equals("19") || y.equals("21") || y.equals("23") || y.equals("25")) {
                if (x.equals("9") || x.equals("11") || x.equals("13") || x.equals("15") || x.equals("17")
                        || x.equals("19") || x.equals("21") || x.equals("23") || x.equals("25") || x.equals("27")
                        || x.equals("29") || x.equals("31")) {
                    adminHeight.setEditable(false);
                    adminWidth.setEditable(false);
                    adminTopic.setDisable(true);
                    adminKruskal.setDisable(false);
                    adminPrim.setDisable(false);
                    adminArrangeAlgorithm.setDisable(false);

                    //запуск генерации сетки
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Successful authentication");
                    alert.setHeaderText("Некорректные значения высоты или ширины");
                    alert.showAndWait();
                }


            }

        });
        //Темы
        adminTopicSpring.setOnAction(actionEvent -> {
            if (!adminImageSummer.isVisible() && !adminImageAutumn.isVisible() && !adminImageWinter.isVisible()) {
                adminImageSpring.setVisible(true);
            } else {
                adminImageSpring.setVisible(true);
                adminImageSummer.setVisible(false);
                adminImageAutumn.setVisible(false);
                adminImageWinter.setVisible(false);
            }
        });
        adminTopicSummer.setOnAction(actionEvent -> {
            if (!adminImageSpring.isVisible() && !adminImageAutumn.isVisible() && !adminImageWinter.isVisible()) {
                adminImageSummer.setVisible(true);
            } else {
                adminImageSpring.setVisible(false);
                adminImageSummer.setVisible(true);
                adminImageAutumn.setVisible(false);
                adminImageWinter.setVisible(false);
            }
        });
        adminTopicAutumn.setOnAction(actionEvent -> {
            if (!adminImageSummer.isVisible() && !adminImageSpring.isVisible() && !adminImageWinter.isVisible()) {
                adminImageAutumn.setVisible(true);
            } else {
                adminImageSpring.setVisible(false);
                adminImageSummer.setVisible(false);
                adminImageAutumn.setVisible(true);
                adminImageWinter.setVisible(false);
            }
        });
        adminTopicWinter.setOnAction(actionEvent -> {
            if (!adminImageSummer.isVisible() && !adminImageAutumn.isVisible() && !adminImageSpring.isVisible()) {
                adminImageWinter.setVisible(true);
            } else {
                adminImageSpring.setVisible(false);
                adminImageSummer.setVisible(false);
                adminImageAutumn.setVisible(false);
                adminImageWinter.setVisible(true);
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

        });

        //установка группы для радиокнопок(способ расстановки входов)
        ToggleGroup groupV = new ToggleGroup();
        adminArrangeAuto.setToggleGroup(groupV);
        adminArrangeManually.setToggleGroup(groupV);
        adminArrangeAuto.setSelected(true);

        adminArrangeEntrence.setOnAction(actionEvent ->
        {
            RadioButton selection = (RadioButton) groupV.getSelectedToggle();
        });

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
            try {
                String path = "D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\appandcontrollers\\maze\\help.html";
                File file = new File(path);
                if (file.exists()) {
                    Process process = Runtime.getRuntime().exec("rund1132 url.dll,FileProtocolHandler " + path);
                    process.waitFor();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Предупреждение");
                    alert.setHeaderText(null);
                    alert.setContentText("Файл справки поврежден или отсутствует...");
                    alert.showAndWait();
                }
            } catch (InterruptedException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Файл справки поврежден или отсутствует...");
                alert.showAndWait();
            }
        });
        //кнопка сохранить у админа

    /*    adminMenuFileSave.setOnAction(actionEvent -> {
            public void speichern(ActionEvent event)
            if (filename == null)
                speichernUnter(event);
            else {
                Properties properties = new Properties();
                FileOutputStream fos = null;
                try {
                    properties.setProperty("Высота", adminHeight.getText());
                    properties.setProperty("Ширина", adminWidth.getText());
                    if (adminPrim.isSelected()) {
                        properties.setProperty("Прима", "yes");
                    } else {
                        adminKruskal.isSelected();
                        properties.setProperty("Крускала", "yes");
                    }
                    //расстановку входа и выхода
                    fos = new FileOutputStream(filename);
                    properties.storeToXML(new FileOutputStream(filename), "Values and Settings", "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            public void speichernUnter ()
            init();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Сохранить шаблон лабиринта");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PROPERTIES Dateien (*.properties", "*.properties)");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setInitialDirectory(new File("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Samples"));
            File file = fileChooser.showSaveDialog(new Stage());
            if (file != null) {
                try {
                    FileWritter fileWritter = new FileWriter(file);
                    fileWritter.close();
                    filename = file.toString();
                    speichern();
                } catch (IOException e) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
                }
            }

                *//*fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"));*//*
               *//* if (file != null) {
                    // saveSystem(file, );
                }
                try {
                    Scanner scanner = new Scanner(file);
                    //тут написать логику того как что будет делаться или отрисовываться при загрузке хуйни
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*//*

        });*/

    }
}
