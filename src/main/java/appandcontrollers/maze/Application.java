package appandcontrollers.maze;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Authorization.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Окно авторизации");
        stage.getIcons().add(new Image("D:\\vlad\\ideaProjects\\Maze\\src\\main\\resources\\Images\\icon.png"));
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();

    }

}
   /* File file = fileChooser.showSaveDialog(panRoot.getScene().getWindow());

        if(file != null) {
                try {
                PrintWriter writer = new PrintWriter(file);
                writer.println("Maze 1.0");
                writer.println(mFieldGrid.getRows());
                writer.println(mFieldGrid.getCols());

                int i = 1;
                for (int r = 0; r < mFieldGrid.getRows(); r++)
        {
        for (int c = 0; c < mFieldGrid.getCols(); c++)
        {
        byte val = 0;
        FieldElement elem = mFieldGrid.getCell(r, c);
        if (elem == FieldElement.Wall)
        {
        val = 1;
        }
        else if (elem == FieldElement.Start)
        {
        val = 2;
        }
        else if (elem == FieldElement.Finish)
        {
        val = 3;
        }
        writer.print(val);
        if (++i >= 40)
        {
        i = 1;
        writer.println();
        }
        }
        }

        writer.close();

        } catch (IOException ex) {
        }
        }
        }*/





 /*   @FXML
    private void butLoad_Action(Event args) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Maze files (*.maze)", "*.maze");
        fileChooser.setTitle("Load maze definition file");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showOpenDialog(panRoot.getScene().getWindow());

        if(file != null) {
            try {

                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;

                line = reader.readLine();
                if (!line.equals("Maze 1.0"))
                {
                    reader.close();
                    return;
                }

                int rows = 0;
                line = reader.readLine();
                try {
                    rows = Integer.parseInt(line);
                } catch(NumberFormatException ex) {
                    reader.close();
                    return;
                }

                int cols = 0;
                line = reader.readLine();
                try {
                    cols = Integer.parseInt(line);
                } catch(NumberFormatException ex) {
                    reader.close();
                    return;
                }

                mFieldGrid.setRows(rows);
                mFieldGrid.setCols(cols);

                mRows = rows;
                mCols = cols;

                spinRows.getValueFactory().setValue(mRows);
                spinCols.getValueFactory().setValue(mCols);

                updateGrid();

                line = reader.readLine();
                int i = 0;
                for (int r = 0; r < mFieldGrid.getRows(); r++)
                {
                    for (int c = 0; c < mFieldGrid.getCols(); c++)
                    {
                        byte val = 0;
                        try {
                            val = Byte.parseByte(line.substring(i, i + 1));
                        } catch(NumberFormatException ex) {

                        }

                        if(val == 1)
                        {
                            mFieldGrid.setCell(r, c, FieldElement.Wall);
                        }
                        else if(val == 2)
                        {
                            mFieldGrid.setCell(r, c, FieldElement.Start);
                        }
                        else if(val == 3)
                        {
                            mFieldGrid.setCell(r, c, FieldElement.Finish);
                        }
                        else
                        {
                            mFieldGrid.setCell(r, c, FieldElement.Empty);
                        }

                        if(++i >= line.length())
                        {
                            line = reader.readLine();
                            i = 0;
                        }
                    }
                }

                reader.close();

                updateGrid();

            } catch(IOException ex) {

            }
        }
    }

}
*/