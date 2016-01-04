import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Main extends Application {

    static TextField inputField;
    static Label dynamicLabelStatus;
    static Label dynamicLabelChecking;
    public Button buttonStart;
    public Button buttonStop;
    public Button buttonSubmitName;


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
        dynamicLabelStatus = new Label("OFFLINE");
        dynamicLabelStatus.setTranslateY(67);
        dynamicLabelStatus.setFont(Font.font("Arial", 30));
        dynamicLabelStatus.setTextFill(Color.RED);


        //to do 'looking for user X' label
        Label dynamicLabelChecking = new Label();
        dynamicLabelChecking.setFont(Font.font("Arial", FontPosture.ITALIC, 10));
        dynamicLabelChecking.setTranslateY(7);

        //Start searching button
        buttonStart = new Button("Start");
        buttonStart.setOnAction(e -> {

            if(inputField.getText().isEmpty() || inputField.getText().equals(null) || inputField.getText().trim().isEmpty())
                dynamicLabelChecking.setText("ERROR: please update text field. ");
            else {
                buttonStart.setDisable(true);
                buttonStop.setDisable(false);
                buttonSubmitName.setDisable(true);
                dynamicLabelChecking.setText("Searching for user: " + Checker.getInputString()+" ....");
                Checker.myFunction();
            }
        });
        buttonStart.setTranslateX(-67);
        buttonStart.setTranslateY(-30);

        //pause searching button
        buttonStop = new Button("Stop");
        buttonStop.setOnAction(e -> {
            Checker.stopTimer();
            buttonStart.setDisable(false);
            buttonStop.setDisable(true);
            buttonSubmitName.setDisable(false);
        });
        buttonStop.setTranslateX(67);
        buttonStop.setTranslateY(-30);


        //Button to manually check the status of the stream...
        //The plan is to have the program automatically check for streamer every minute, so if the user
        //wants to manually check at any point then that option is available
        Button buttonManuelCheck = new Button("Manual Check");
        buttonManuelCheck.setOnAction(e -> {System.out.println("Button Clicked");  Checker.myFunction();}  );
        buttonManuelCheck.setTranslateX(0);
        buttonManuelCheck.setTranslateY(100);

        //Gets input form textfield and updates the name of the user we're searching
        buttonSubmitName = new Button("Update Name");
        buttonSubmitName.setOnAction(e -> {Checker.setInputString(inputField.getText());});
        buttonSubmitName.setTranslateY(-30);

        // separators to make GUI look more clean
        Separator sep1 = new Separator();
        sep1.setTranslateY(-12);
        Separator sep2 = new Separator();
        sep2.setTranslateY(25);

        //simple static labels
        Label staticLabelStatus = new Label("Status:");
        Label staticLabelName = new Label("Enter streamer name: ");
        staticLabelName.setTranslateY(-90);


        //create new StackPane
        StackPane layout = new StackPane();
        staticLabelStatus.setTranslateX(0);
        staticLabelStatus.setTranslateY(40);
        staticLabelStatus.setFont(Font.font("Arial", 12));


        //add all components to layout
        layout.getChildren().add(buttonStart);
        layout.getChildren().add(buttonStop);
        layout.getChildren().add(inputField);
        layout.getChildren().add(dynamicLabelStatus);
        layout.getChildren().add(staticLabelName);
        layout.getChildren().add(buttonManuelCheck);
        layout.getChildren().add(buttonSubmitName);
        layout.getChildren().add(staticLabelStatus);
        layout.getChildren().add(dynamicLabelChecking);
        layout.getChildren().add(sep1);
        layout.getChildren().add(sep2);


        //create new scene
        Scene scene = new Scene(layout, 180, 220);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        buttonStop.setDisable(true);

    }

    //gets called in Checker class, which tells the dynamicLabel what to display
    public static void updateStatus(Boolean x)
    {
        if(x==true)
            dynamicLabelStatus.setText("ONLINE");
        else
            dynamicLabelStatus.setText("OFFLINE");
    }
    //under construction
    public static void updateLabel(String name)
    {
        dynamicLabelChecking.setText("Checking status of: "+ Checker.getInputString() +" ..............");
    }
}