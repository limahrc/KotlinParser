package janela;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import parser.KotlinSyntacticAnalyzer;
import java.io.BufferedReader;
import java.io.File;

public class Main extends Application implements EventHandler<ActionEvent>
{
    private BufferedReader reader = null;
    private int lineCount = 0;

    private Button buttonSelectFile;
    private Button buttonEnviar;
    private Label labelFileName;
    private Text textLineFields;
    private ChoiceBox choiceBoxDelimiter;
    private String nameFile;
    private static final int WINDOW_WIDTH  = 500;
    private static final int WINDOW_HEIGHT = 300;


    //============================================================================================
    // This method is called once by JavaFX.launch()
    // This method sets the window (Stage primaryStage) title.
    // It also creates and the GUI components.
    //============================================================================================
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("File Reader");
        primaryStage.setResizable(false);

        buttonSelectFile = new Button("Select Input Text File");
        buttonSelectFile.setOnAction(this);


        buttonEnviar = new Button("Enviar");
        buttonEnviar.setOnAction(this);
        buttonEnviar.setDisable(true); //User cannot read line until a file has been opened.

        labelFileName = new Label();
        textLineFields = new Text();



        //Make both buttons the same width.
        buttonSelectFile.setMaxWidth(WINDOW_WIDTH/2);
        labelFileName.setMaxWidth(WINDOW_WIDTH/2);
        buttonEnviar.setMaxWidth(WINDOW_WIDTH/2);
        textLineFields.setWrappingWidth(WINDOW_WIDTH-20);

        VBox buttonBox = new VBox();
        buttonBox.setPadding(new Insets(10, 10, 10, 10));  //Sets the space around the buttonBox.
        buttonBox.setSpacing(10);  //Sets the vertical space in pixels between buttons within the box.

        buttonBox.getChildren().addAll(
                buttonSelectFile,
                labelFileName,
                buttonEnviar,
                textLineFields
        );

        StackPane root = new StackPane();
        root.getChildren().add(buttonBox);
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }





    private void openFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        buttonEnviar.setDisable(false);
        nameFile = selectedFile.getName();
    }


    private void sendFile(String nomeArquivo){
        KotlinSyntacticAnalyzer parser = new KotlinSyntacticAnalyzer (nomeArquivo);
        parser.begin();
    }





    //============================================================================================
    // Called by JavaFX when the user clicks a button.
    //============================================================================================
    @Override
    public void handle(ActionEvent event)
    {
        Object source = event.getSource();
        if (source == buttonSelectFile) openFile();
        else if (source == buttonEnviar) sendFile(nameFile);

    }




    //============================================================================================
    // Display a JavaFX Error Dialog.
    // This method will not return until the user clicks "ok" in the displayed dialog.
    //============================================================================================
    private void showErrorDialog(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }



    //============================================================================================
    // Program Entry point use to Launch JavaFX.
    // In this program, the command line arguments (args) are ignored.
    //============================================================================================
    public static void main(String[ ] args)
    {
        launch(args);
    }

}