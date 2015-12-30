//GUI IMPORTS
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


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Twitch Alerter");

        dynamicLabelStatus = new Label("OFFLINE");
        dynamicLabelStatus.setTranslateY(67);
        dynamicLabelStatus.setFont(Font.font("Arial", 30));
        dynamicLabelStatus.setTextFill(Color.RED);

        Label dynamicLabelChecking = new Label();

        Button buttonManuelCheck = new Button("Manual Check");
        Button buttonSubmitName = new Button("Update Name");

        Separator sep1 = new Separator();
        sep1.setTranslateY(-12);
        Separator sep2 = new Separator();
        sep2.setTranslateY(25);
        Label staticLabelStatus = new Label("Status:");
        Label staticLabelName = new Label("Enter streamer name: ");

        //needs update method


        inputField = new TextField("Enter steamer name here");
        inputField.setTranslateY(-60);

        buttonSubmitName.setOnAction(e -> {Checker.setInputString(inputField.getText());});
        buttonManuelCheck.setOnAction(e -> {System.out.println("Button Clicked");  Checker.myFunction();}  );

        StackPane layout = new StackPane();


        buttonManuelCheck.setTranslateX(0);
        buttonManuelCheck.setTranslateY(100);
        staticLabelStatus.setTranslateX(0);
        staticLabelStatus.setTranslateY(40);
        staticLabelStatus.setFont(Font.font("Arial", 12));
        dynamicLabelChecking.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
        dynamicLabelChecking.setTranslateY(7);
        staticLabelName.setTranslateY(-90);
        buttonSubmitName.setTranslateY(-30);
        layout.getChildren().add(inputField);
        layout.getChildren().add(dynamicLabelStatus);
        layout.getChildren().add(staticLabelName);
        layout.getChildren().add(buttonManuelCheck);
        layout.getChildren().add(buttonSubmitName);
        layout.getChildren().add(staticLabelStatus);

        layout.getChildren().add(dynamicLabelChecking);
        layout.getChildren().add(sep1);
        layout.getChildren().add(sep2);
        Scene scene = new Scene(layout, 250, 220);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Checker.setInputString("aphromoo");
        Checker.myFunction();

    }

    public static void updateStatus(Boolean x)
    {
        if(x==true)
         //   txtField.setText("online bro");
            dynamicLabelStatus.setText("ONLINE BRO");
        else
        //    txtField.setText("offlien bro");
            dynamicLabelStatus.setText("OFFLINE");
    }
    public static void updateLabel(String name)
    {
        dynamicLabelChecking.setText("Checking status of: "+ Checker.getInputString() +" ..............");
    }
}