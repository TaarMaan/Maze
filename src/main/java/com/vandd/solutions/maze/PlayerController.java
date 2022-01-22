package com.vandd.solutions.maze;

import java.io.*;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PlayerController extends Observable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private StackPane StackPaneP;
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
/*
    public PlayerController() {
        cbAlgorithmBox = new ComboBox(FXCollections.observableArrayList(FindingExit.com.vandd.solutions.maze.Algorithms.values()));
        cbAlgorithmBox.getSelectionModel().selectFirst();
        btnRun = new Button("RUN");

        // Run pathfinding algorithms
        btnRun.setOnAction((event) ->
        {
            FindingExit.com.vandd.solutions.maze.Algorithms algorithm = null;


            for (FindingExit.com.vandd.solutions.maze.Algorithms algo : FXCollections.observableArrayList(FindingExit.com.vandd.solutions.maze.Algorithms.values())) {
                if (algo == cbAlgorithmBox.getValue()) {
                    algorithm = algo;
                }
            }


            boolean success;
            if (algorithm != null) {
                try {
                    success = controller.doShortestPathAlgorithm(algorithm);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

*/

    @FXML
    void initialize() {
        playerVisualizationApply.setDisable(true);
        playerVisualizationDynamic.setDisable(true);
        playerVisualizationStatic.setDisable(true);
        playerSpeedBall.setDisable(true);
        playerSpeedApply.setDisable(true);
        playerAlgorithmApply.setDisable(true);
        playerAlgorithmBreadthFirstSearch.setDisable(true);
        playerAlgorithmDepthFirstSearch.setDisable(true);
        playerStart.setDisable(true);
        //НАПИСАТЬ что все кнопки дизейбл,пока чел не загрузит...
        //а как только загрузил,может нажать старт с выставленными значениями "по умолчанию"


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
        //закрепляем выбранную тему
        playerTopicApply.setOnAction(actionEvent -> {
            playerTopic.setDisable(true);
            playerTopicApply.setDisable(true);
            playerAlgorithmApply.setDisable(false);
            playerAlgorithmBreadthFirstSearch.setDisable(false);
            playerAlgorithmDepthFirstSearch.setDisable(false);
        });


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
            stage.getIcons().add(new Image("file:icon.png"));
            stage.showAndWait();
        });
        playerMenuReferenceApp.setOnAction(actionEvent -> {
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

        //кнопка загрузить у игрока
      //  playerMenuFileLoad.setOnAction(actionEvent -> {
            /*FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выбрать шаблон лабиринта");
            fileChooser.setInitialDirectory(new File("Samples"));
            File file = fileChooser.showOpenDialog(new Stage());
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            fileChooser.setInitialDirectory(new File("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Samples"));
        */
           /* public void laden ()
            {
                if (filename == null)
                    ladenVon();
                else {
                    Properties prop = new Properties();
                    FileInputStream fis = null;
                    try {

                        fis = new FileInputStream(filename);

                        // Load Properties from saved XML file
                        prop.loadFromXML(fis);

                        //textField.setText (prop.getProperty ("Name") ); // load the text
*//*
                    if (prop.getProperty ("RadioButtonState").equals ("yes") ) // load rb state
                    {
                        radioButton.setSelected ( );
                    }

                    if (checkBox.isSelected ( ) ) // Load cb state
                        checkBox.setSelected (false);
                    checkBox.setSelected (true);*//*
                    } catch (InvalidPropertiesFormatException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //datePicker.setValue (LocalDate.parse (prop.getProperty ("Date") ) ); // load date

                    //textArea.setText (prop.getProperty ("Text") ); // Load long text
                    catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fis != null)
                            fis.close();
                    }
                }
            }
            public void ladenVon()
            {
                init();
                FileChooser fileChooser = new FileChooser();

                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PROPERTIES Dateien (*.properties)", "*.properties");
                fileChooser.getExtensionFilters().add(extFilter);

                //Show load file dialog
                fileChooser.setInitialDirectory(new File("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Samples"));

                File file = fileChooser.showOpenDialog(new Stage());

                if (file != null) {
                    try {
                        FileReader fileReader = new FileReader(file);
                        fileReader.close();
                        filename = file.toString();
                        laden();
                    } catch (IOException e) {
                        Logger.getLogger(PlayerController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        });*/

            //Установка группы для радиокнопок(алгоритмы)
            ToggleGroup groupA = new ToggleGroup();
            playerAlgorithmBreadthFirstSearch.setToggleGroup(groupA);
            playerAlgorithmDepthFirstSearch.setToggleGroup(groupA);
            playerAlgorithmDepthFirstSearch.setSelected(true);

            //установка обработчика события нажатия
            playerAlgorithmApply.setOnAction(actionEvent -> {
                RadioButton selection = (RadioButton) groupA.getSelectedToggle();

                // включение алгоритма в сетку...
                playerAlgorithmApply.setDisable(true);
                playerAlgorithmBreadthFirstSearch.setDisable(true);
                playerAlgorithmDepthFirstSearch.setDisable(true);
                playerVisualizationApply.setDisable(false);
                playerVisualizationDynamic.setDisable(false);
                playerVisualizationStatic.setDisable(false);
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
                playerVisualizationApply.setDisable(true);
                playerVisualizationDynamic.setDisable(true);
                playerVisualizationStatic.setDisable(true);
                if (playerVisualizationDynamic.isSelected()) {
                    playerSpeedBall.setDisable(false);
                    playerSpeedApply.setDisable(false);
                } else {
                    playerStart.setDisable(false);
                }
            });
            playerSpeedApply.setOnAction(actionEvent -> {
                playerSpeedBall.setDisable(true);
                playerSpeedApply.setDisable(true);
                playerStart.setDisable(false);
            });



    }
}
