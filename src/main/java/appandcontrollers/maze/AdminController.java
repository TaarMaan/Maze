package appandcontrollers.maze;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController {

    @FXML
    private ResourceBundle resources;

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
    private RadioButton adminEuler;

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
    void initialize() {
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

        //кнопка сохранить у админа
        /*adminMenuFileSave.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(new Stage());
            if (file != null){
                saveSystem(file, );
            }
            try {
                Scanner scanner = new Scanner(file);
                //тут написать логику того как что будет делаться или отрисовываться при загрузке хуйни
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            fileChooser.setInitialDirectory(new File("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Save"));
        });*/


        //установка группы для радиокнопок(алгоритмы)
        ToggleGroup groupA = new ToggleGroup();
        adminEuler.setToggleGroup(groupA);
        adminPrim.setToggleGroup(groupA);

        //установка обработчика события для выбора алгоритма генерации
        adminArrangeAlgorithm.setOnAction(actionEvent ->

        {
            RadioButton selection = (RadioButton) groupA.getSelectedToggle();

            //включение алгоритма в сетку...
        });

        //установка группы для радиокнопок(способ расстановки входов)
        ToggleGroup groupV = new ToggleGroup();
        adminArrangeAuto.setToggleGroup(groupV);
        adminArrangeManually.setToggleGroup(groupV);

        //установка обработчика события для расстановки входа и выхода
        adminArrangeEntrence.setOnAction(actionEvent ->

        {
            RadioButton selection = (RadioButton) groupV.getSelectedToggle();

            //генерация сетки и открытие формы расставления входа
            //если выбрано (расставить вручную)...
        });
    }
}







