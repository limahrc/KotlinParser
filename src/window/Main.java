package window;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import parser.KotlinSyntacticAnalyzer;
import parser.SyntaxError;
import scanner.LexicalError;

import java.io.BufferedReader;
import java.io.File;

public class Main extends Application implements EventHandler<ActionEvent>
{
    private BufferedReader reader = null;
    private int lineCount = 0;

    private Button buttonSelectFile;
    private Button buttonEnviar;
    private Text textResult;
    private ChoiceBox choiceBoxDelimiter;
    private String nameFile;
    private static final int WINDOW_WIDTH  = 300;
    private static final int WINDOW_HEIGHT = 200;

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Grupo 4 - LFA: Kotlin Parser");
        primaryStage.setResizable(false);

        buttonSelectFile = new Button("Escolha o arquivo");
        buttonSelectFile.setOnAction(this);

        buttonEnviar = new Button("Analisar");
        buttonEnviar.setOnAction(this);
        buttonEnviar.setDisable(true); //User cannot read line until a file has been opened.

        Label labelFileName = new Label();

        Label labelResult = new Label();
        textResult = new Text();
        //Make both buttons the same width.
        buttonSelectFile.setMaxWidth(WINDOW_WIDTH/2);
        labelFileName.setMaxWidth(WINDOW_WIDTH/2);
        buttonEnviar.setMaxWidth(WINDOW_WIDTH/2);
        textResult.setWrappingWidth(WINDOW_WIDTH-20);

        VBox buttonBox = new VBox();
        buttonBox.setPadding(new Insets(10, 10, 10, 40));  //Sets the space around the buttonBox.
        buttonBox.setSpacing(10);  //Sets the vertical space in pixels between buttons within the box.

        buttonBox.getChildren().addAll(
                buttonSelectFile,
                labelFileName,
                buttonEnviar,
                textResult
        );

        StackPane root = new StackPane();
        root.getChildren().add(buttonBox);
        primaryStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }

    private void openFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha um arquivo .txt");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        buttonEnviar.setDisable(false);
        nameFile = selectedFile.getAbsolutePath();
    }


    private void sendFile(String nameFile){
        try{
            System.out.println(nameFile);
            KotlinSyntacticAnalyzer parser = new KotlinSyntacticAnalyzer (nameFile);
            parser.begin();
            textResult.setText("Análise concluída. Nenhum erro detectado.");
        } catch (LexicalError error){
            textResult.setText(error.toString());
        } catch (SyntaxError error){
            textResult.setText(error.toString());
        }

    }

    @Override
    public void handle(ActionEvent event)
    {
        Object source = event.getSource();
        if (source == buttonSelectFile) openFile();
        else if (source == buttonEnviar) sendFile(nameFile);

    }

    private void showErrorDialog(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[ ] args) {
        launch(args);
    }

}