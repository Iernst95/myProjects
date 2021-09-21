/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbctimerstopwatchs21;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author isaacernst
 */
public class IwegbcTimerStopwatchS21 extends Application {
    
    private String appName = "Isaac's Stopwatch";
    
    
    @Override
    public void start(Stage primaryStage) {
        
        
        TextInputDialog startTextInput = new TextInputDialog();
        startTextInput.setHeaderText("Set up the start time: ");
        startTextInput.setTitle("Timer Start Time Set Up ");
        startTextInput.setContentText("Please set up the start time(Integer)");
        startTextInput.showAndWait();
        boolean checkInput=  BasicWatch.inputCheck(startTextInput.getResult());
        while(!checkInput){
             Alert inputAlert = new Alert(AlertType.ERROR);
            inputAlert.setTitle("Error");
            inputAlert.setContentText("The input must be an integer between 0 and 60");
            inputAlert.showAndWait();
            startTextInput.showAndWait();
            checkInput = BasicWatch.inputCheck(startTextInput.getResult());
        }
       
        
        int userTimerInput = Integer.parseInt(startTextInput.getResult());
        //BasicWatch.setTimerText(userTimerInput);
        
        BasicWatch watch = new BasicWatch(userTimerInput);
        Scene scene = new Scene(watch.getRootContainer(),
                                watch.getWidth()+105,
                                watch.getHeight()+220);
        
        
        
        
       
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
