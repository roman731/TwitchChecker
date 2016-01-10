import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Main extends Application {

    private TextField inputField;

    private Button buttonStart;
    private Button buttonStop;
    private Button buttonSubmitName;
    private Button buttonManuelCheck;

    private Label labelStatus;
    private Label labelName;
    private Label labelDynamicChecking;
    static Label labelDynamicStatus;
    static Label labelDynamicTime;

    private Separator sep1;
    private Separator sep2;

    Checker _checker = new Checker();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Twitch Alerter");

        //input field to enter the user's name we're looking for
        inputField = new TextField();
        inputField.setTranslateY(-60);

        //label displaying status of twitch user
        labelDynamicStatus = new Label("OFFLINE");
        labelDynamicStatus.setTranslateY(67);
        labelDynamicStatus.setFont(Font.font("Arial", 30));
        labelDynamicStatus.setTextFill(Color.RED);

        //to do 'looking for user X' label
        labelDynamicChecking = new Label();
        labelDynamicChecking.setFont(Font.font("Arial", FontPosture.ITALIC, 11));
        labelDynamicChecking.setTranslateY(-1);

        labelDynamicTime = new Label();
        labelDynamicTime.setFont(Font.font("Arial", FontPosture.ITALIC, 10));
        labelDynamicTime.setTranslateY(11);

        //Start searching button
        buttonStart = new Button("Start");
        buttonStart.setOnAction(e -> {

            if(inputField.getText().isEmpty() || inputField.getText().equals(null) || inputField.getText().trim().isEmpty())
                labelDynamicChecking.setText("ERROR: please update text field. ");
            else {
                buttonStart.setDisable(true);
                buttonStop.setDisable(false);
                buttonSubmitName.setDisable(true);
                labelDynamicChecking.setText("Searching for user: " + _checker.getInputString()+" ...." );
                _checker.timerFunction();
            }
        });
        buttonStart.setTranslateX(-67);
        buttonStart.setTranslateY(-30);

        //pause searching button
        buttonStop = new Button("Stop");
        buttonStop.setOnAction(e -> {

            buttonStart.setDisable(false);
            buttonStop.setDisable(true);
            buttonSubmitName.setDisable(false);
            labelDynamicChecking.setText("Status: idle.");
            _checker.stopTimer();
        });
        buttonStop.setTranslateX(67);
        buttonStop.setTranslateY(-30);

        //Button to manually check the status of the stream...
        //The plan is to have the program automatically check for streamer every minute, so if the user
        //wants to manually check at any point then that option is available
        buttonManuelCheck = new Button("Manual Check");
        buttonManuelCheck.setOnAction(e -> {System.out.println("Button Clicked");  _checker.timerFunction();}  );
        buttonManuelCheck.setTranslateX(0);
        buttonManuelCheck.setTranslateY(100);

        //Gets input form textfield and updates the name of the user we're searching
        buttonSubmitName = new Button("Update Name");
        buttonSubmitName.setOnAction(e -> {
            _checker.setInputString(inputField.getText());
            if(_checker.getInputString() != null) {
                buttonStart.setDisable(false);
            }
            else
                labelDynamicChecking.setText("ERROR: please update text field. ");
        });
        buttonSubmitName.setTranslateY(-30);

        // separators to make GUI look more clean
        sep1 = new Separator();
        sep1.setTranslateY(-12);
        sep2 = new Separator();
        sep2.setTranslateY(25);

        //simple static labels
        labelStatus = new Label("Status:");
        labelName = new Label("Enter streamer name: ");
        labelName.setTranslateY(-90);

        //create new StackPane
        StackPane layout = new StackPane();
        labelStatus.setTranslateX(0);
        labelStatus.setTranslateY(40);
        labelStatus.setFont(Font.font("Arial", 12));

        //add all components to layout
        layout.getChildren().add(buttonStart);
        layout.getChildren().add(buttonStop);
        layout.getChildren().add(inputField);
        layout.getChildren().add(labelDynamicStatus);
        layout.getChildren().add(labelName);
        layout.getChildren().add(buttonManuelCheck);
        layout.getChildren().add(buttonSubmitName);
        layout.getChildren().add(labelStatus);
        layout.getChildren().add(labelDynamicChecking);
        layout.getChildren().add(labelDynamicTime);
        layout.getChildren().add(sep1);
        layout.getChildren().add(sep2);

        //create new scene
        Scene scene = new Scene(layout, 180, 220);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        buttonStop.setDisable(true);
        buttonStart.setDisable(true);

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    //gets called in Checker class, which tells the dynamicLabel what to display
    public static void updateStatus(Boolean x)
    {
        if(x==true) {
            labelDynamicStatus.setTextFill(Color.GREEN);
            labelDynamicStatus.setText("ONLINE");
        }
        else {
            labelDynamicStatus.setTextFill(Color.RED);
            labelDynamicStatus.setText("OFFLINE");
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        System.out.println( sdf.format(cal.getTime()) );

        labelDynamicTime.setText("Time of last search: " + sdf.format(cal.getTime()) );
    }

}