package appandcontrollers.maze;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdminController {

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
    private MenuItem adminMenuFileSave;

    @FXML
    private Menu adminMenuReference;

    @FXML
    private AnchorPane ImageField;

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

    //public void saveSystem( File file, какая-то переменная, отвечающая за то ){
       // try {
        //    PrintWriter printWriter = new PrintWriter(file);
        //    PrintWriter.write();
     //       PrintWriter.close();
     //   } catch (FileNotFoundException e) {
     //       e.printStackTrace();
       // }
//
   // }
    @FXML
    void initialize() {
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
        adminArrangeAlgorithm.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupA.getSelectedToggle();

            //включение алгоритма в сетку...
        });

        //установка группы для радиокнопок(способ расстановки входов)
        ToggleGroup groupV = new ToggleGroup();
        adminArrangeAuto.setToggleGroup(groupV);
        adminArrangeManually.setToggleGroup(groupV);

        //установка обработчика события для расстановки входа и выхода
        adminArrangeEntrence.setOnAction(actionEvent -> {
            RadioButton selection = (RadioButton) groupV.getSelectedToggle();

            //генерация сетки и открытие формы расставления входа
            //если выбрано (расставить вручную)...
        });




    }

}

