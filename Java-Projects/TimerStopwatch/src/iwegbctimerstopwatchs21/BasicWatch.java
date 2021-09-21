/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbctimerstopwatchs21;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author isaacernst
 */

//source 1: https://stackoverflow.com/questions/38566638/javafx-displaying-time-and-refresh-in-every-second

public class BasicWatch {
    
     int lapCount = 0;
     private double secondsElapsed = 0;
     private double tickTimeInSeconds = 0.01; 
     private double angleDeltaPerSeconds = 6; 
     private double record1 = 0.0;
     private double record2 = 0.0;
     private double record3 = 0.0;
     
     private Timeline timeline;
     private KeyFrame keyFrame;
     
     private StackPane stackContainer;
     private ImageView dialImageView;
     private ImageView handImageView;
     private Image dialImage;
     private Image handImage;
     private String dialImageName = "clockface.png";
     private String handImageName = "hand.png";
     
    
     Button startStopButton = new Button("Start");
     Button resetRecordButton = new Button("Record"); 
     
     private GridPane root;
     private String fontStyle = "Verdana";
     private boolean wasItRunning; 
     private VBox newRoot;
     private  TextInputDialog startTextInput;
     
     private Text stopwatchText;
     private Text bigTimerText;
     private double timer;
     private boolean timerIsDone = false;
     private double timerTimeLeft;
     private int recordLap= 0;
     
     private Text recordText1;
     private Text recordText2;
     private Text recordText3;
     private Text timerText1;
     private Text timerText2;
     private Text timerText3;
     private double recordTime1 = 0.0;
     private double recordTime2 = 0.0;
     private double recordTime3 = 0.0;
     private double timeBetweenRecords= 0.0;
     
    // source 1 code starts here 
     private DateFormat timerFormatMinsSecs = new SimpleDateFormat("mm.ss");
      private DateFormat timerFormatMilliseconds = new SimpleDateFormat("SS");
      private DateFormat timerFormatDoubleSecond = new SimpleDateFormat("ss");
     private DateFormat timerFormatSingleSecond = new SimpleDateFormat("s");
    // source 1 code ends here      
     
     
     public BasicWatch(double userTimer){
        timer = userTimer;
       
        setupUI();
        setupTimer();
     }
     public void setupUI(){
        stackContainer = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();
        stackContainer.getChildren().addAll(dialImageView, handImageView);
        
       
        
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(15, 15, 15, 15));
        root.add(stackContainer, 0 ,0 ,3,1);
        
        
    
        Label lapTimeLabel = new Label("Lap Time");
        lapTimeLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 16));
        root.add(lapTimeLabel, 1,0 , 1, 1);
        
        Label totalTimeLabel = new Label("Total Time");
        lapTimeLabel.setFont(Font.font(fontStyle, FontWeight.NORMAL, 16));
        root.add(totalTimeLabel, 2, 0, 1, 1);
        
        timerText1 = new Text("00:00.00");
        timerText1.setFont(Font.font(fontStyle, FontWeight.NORMAL, 16));
        root.add(timerText1, 2, 1 , 1, 1);
        
        timerText2 = new Text("00:00.00");
        timerText2.setFont(Font.font(fontStyle, FontWeight.NORMAL, 16));
        root.add(timerText2, 2, 2, 1, 1);
        
        timerText3 = new Text("00:00.00");
        timerText3.setFont(Font.font(fontStyle, FontWeight.NORMAL, 16));
        root.add(timerText3, 2, 3, 1, 1);
        
        recordText1 = new Text("Rec 00 + 00:00.00");
        recordText1.setFont(Font.font(fontStyle, FontWeight.NORMAL, 16));
        root.add(recordText1, 1, 1, 1, 1);
       
        recordText2 = new Text("Rec 00 + 00:00.00");
        recordText2.setFont(Font.font(fontStyle, FontWeight.NORMAL, 16));
        root.add(recordText2, 1, 2, 1, 1);
       
        recordText3 = new Text("Rec 00 + 00:00.00");
        recordText3.setFont(Font.font(fontStyle, FontWeight.NORMAL, 16));
        root.add(recordText3, 1, 3, 1, 1);
        
        stopwatchText = new Text("00:00.00");
        stopwatchText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 16));
        root.add(stopwatchText, 0, 1, 1, 1);
        
        bigTimerText = new Text("Timer: " + (int)timer + ":00");
        bigTimerText.setFont(Font.font(fontStyle, FontWeight.NORMAL, 12));
        root.add(bigTimerText, 0, 2, 1, 1);
        
        
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        handImage = new Image(getClass().getResourceAsStream(handImageName));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        
        HBox controlsButtons = new HBox();
      
        startStopButton.setMaxWidth(Double.MAX_VALUE);
        resetRecordButton.setMaxWidth(Double.MAX_VALUE);
        
        controlsButtons.setAlignment(Pos.CENTER);
        controlsButtons.setSpacing(10);
        controlsButtons.setPadding(new Insets(25,25,25,25));
        controlsButtons.getChildren().addAll(resetRecordButton,startStopButton);
        controlsButtons.toFront();//this is to make sure they are never blocked by anything
        
        newRoot= new VBox();
        newRoot.getChildren().addAll(stackContainer, root, controlsButtons);
        
        
        resetRecordButton.setOnAction((ActionEvent event) -> {
           if(isRunning()){
               record();
               
           }
           else{
               reset();   
           }
           
        });
       
        startStopButton.setOnAction((ActionEvent event) -> {
            if(isRunning()){
            stop();
            }
            else{
            start();
    
            }
        });
     }
     public void setupTimer(){
         if(isRunning()){
             timeline.pause();
             wasItRunning = true;
         }
            keyFrame = new KeyFrame(Duration.millis(1000* tickTimeInSeconds), (ActionEvent event) -> {
                timerTimeLeft = (timer- secondsElapsed)*1000;
                //Code from source 1 in the line below
                int milliseconds = Integer.parseInt(timerFormatMilliseconds.format(secondsElapsed*1000));
                milliseconds = milliseconds/10;
                
                updateTimer(milliseconds);
                int millisecondsCountDown = Integer.parseInt(timerFormatMilliseconds.format(timerTimeLeft));
                millisecondsCountDown = millisecondsCountDown/10;
            
            update();
                       
            if(timerTimeLeft > 0 ){
                 if(timer>=10) timerFormatL10(timerTimeLeft, millisecondsCountDown);
               
            else timerFormatG10(timerTimeLeft, millisecondsCountDown);

            }
            if(timerTimeLeft <= 0 && timerIsDone == false){

                bigTimerText.setText("Time's Up!");
                timerIsDone = true;
            }
                
            
            
        });
            
    
        
        timeline = new Timeline(keyFrame);
        
        timeline.setCycleCount(Animation.INDEFINITE);
        
        if(wasItRunning){
            timeline.play();
            wasItRunning = false;
        }
     }
     
     private void timerFormatL10(double timer, int milliseconds){
         //Code from source 1 in the line below
         bigTimerText.setText("Timer: "+ timerFormatDoubleSecond.format(timer) + "." + timerFormatMilliseconds.format(milliseconds));
         
     }
      private void timerFormatG10(double timer, int milliseconds ){
         //Code from source 1 in the line below
         bigTimerText.setText("Timer: "+ timerFormatSingleSecond.format(timer) + "." + timerFormatMilliseconds.format(milliseconds));
         
     }
     private double currentTime(){
         secondsElapsed += tickTimeInSeconds;
         return secondsElapsed;
     }
     private void updateTimer(int milliSeconds){
         //Code from source 1 in the line below
         stopwatchText.setText(timerFormatMinsSecs.format(secondsElapsed*1000)+ "." + timerFormatMilliseconds.format(milliSeconds));
            
     }
     
     private void update(){
         //360 degrees / 60 tick marks == each tick is 6 degrees
            
         secondsElapsed += tickTimeInSeconds;
         double rotation = secondsElapsed * angleDeltaPerSeconds;
         handImageView.setRotate(rotation);
         
     }
     
     
     public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
     }
      public Parent getRootContainer(){
          
          return this.newRoot;
      }
      
      public Double getWidth(){
          if(dialImage !=null){
              return dialImage.getWidth();
          }
          else {
              return 0.0;
          }
      }
      public Double getHeight(){
          if(dialImage != null) return dialImage.getHeight();
          else return 0.0;
      }
      
      public void setTickTimeInSeconds(Double tickTimeInSeconds){
          this.tickTimeInSeconds = tickTimeInSeconds;
         
              
          
          setupTimer();
      }
      
    
      public void start(){
          timeline.play();
          startStopButton.setText("Stop");
          resetRecordButton.setText("Record");
      }
      
      public void stop(){
          timeline.pause();
          startStopButton.setText("Play");
          resetRecordButton.setText("Reset");
      }
      public void reset(){
          timeline.stop();
          timerIsDone = false;
          handImageView.setRotate(0);
          secondsElapsed = 0;
          recordLap = 0;
          startStopButton.setText("Start");
          resetRecordButton.setText("Record");
          stopwatchText.setText("00:00.00");
          bigTimerText.setText("Timer: " + (int)timer + ":00");
          recordText1.setText("Rec 00 + 00:00.00");
          recordText2.setText("Rec 00 + 00:00.00");
          recordText3.setText("Rec 00 + 00:00.00");
          timerText1.setText("00:00.00");
          timerText2.setText("00:00.00");
          timerText3.setText("00:00.00");
          recordTime1 = 0;
          recordTime2 = 0;
          recordTime3 = 0;

      }
      public void record(){
          if(!timerIsDone){
            recordLap++;
            double timePassed = secondsElapsed*1000;
            int milliseconds = 0;
            int millisecondsForTime = 0;
           
        if(recordLap%3==1){
            recordTime1 = timePassed;
            timeBetweenRecords = (timePassed - recordTime3);
            milliseconds = Integer.parseInt(timerFormatMilliseconds.format(timeBetweenRecords));
            milliseconds = milliseconds/10;
            millisecondsForTime = Integer.parseInt(timerFormatMilliseconds.format(timePassed));
            millisecondsForTime = millisecondsForTime / 10;
            if(recordLap<10){
                recordText1.setText("Rec 0" + recordLap + " + " + timerFormatMinsSecs.format(timeBetweenRecords) + "." + timerFormatMilliseconds.format(milliseconds) );
                timerText1.setText(timerFormatMinsSecs.format(secondsElapsed*1000) + ":" + timerFormatMilliseconds.format(millisecondsForTime));
            }
            else{
            recordText1.setText("Rec " + recordLap + " + " + timerFormatMinsSecs.format(timeBetweenRecords) + "." + timerFormatMilliseconds.format(milliseconds) );
            timerText1.setText(timerFormatMinsSecs.format(secondsElapsed*1000) + ":" + timerFormatMilliseconds.format(millisecondsForTime));
            }
        }
         if(recordLap%3==2){
            recordTime2 = timePassed;
            timeBetweenRecords = (timePassed - recordTime1);
            milliseconds = Integer.parseInt(timerFormatMilliseconds.format(timeBetweenRecords));
            milliseconds = milliseconds/10;
            millisecondsForTime = Integer.parseInt(timerFormatMilliseconds.format(timePassed));
            millisecondsForTime = millisecondsForTime / 10;
           if(recordLap<10){
                recordText2.setText("Rec 0" + recordLap + " + " + timerFormatMinsSecs.format(timeBetweenRecords) + "." + timerFormatMilliseconds.format(milliseconds) );
                
                timerText2.setText(timerFormatMinsSecs.format(secondsElapsed*1000) + ":" + timerFormatMilliseconds.format(millisecondsForTime));
            }
            else{
            recordText2.setText("Rec " + recordLap + " + " + timerFormatMinsSecs.format(timeBetweenRecords) + "." + timerFormatMilliseconds.format(milliseconds) );
            timerText2.setText(timerFormatMinsSecs.format(secondsElapsed*1000) + ":" + timerFormatMilliseconds.format(millisecondsForTime));
            }
         }
          if(recordLap%3==0){
            recordTime3 = timePassed;
            timeBetweenRecords = (timePassed - recordTime2);
            milliseconds = Integer.parseInt(timerFormatMilliseconds.format(timeBetweenRecords));
            milliseconds = milliseconds/10;
            millisecondsForTime = Integer.parseInt(timerFormatMilliseconds.format(timePassed));
            millisecondsForTime = millisecondsForTime / 10;
            if(recordLap<10){
                recordText3.setText("Rec 0" + recordLap + " + " + timerFormatMinsSecs.format(timeBetweenRecords) + "." + timerFormatMilliseconds.format(milliseconds) );
                timerText3.setText(timerFormatMinsSecs.format(secondsElapsed*1000) + ":" + timerFormatMilliseconds.format(millisecondsForTime));
            }
            else{
            recordText3.setText("Rec " + recordLap + " + " + timerFormatMinsSecs.format(timeBetweenRecords) + "." + timerFormatMilliseconds.format(milliseconds) );
            timerText3.setText(timerFormatMinsSecs.format(secondsElapsed*1000) + ":" + timerFormatMilliseconds.format(millisecondsForTime));
            }
          }
        }
        else{
                Alert noRecord = new Alert(Alert.AlertType.INFORMATION);
                noRecord.setTitle("Time is up!!");
                noRecord.setHeaderText("Alert");
                noRecord.setContentText("Time is up... No more records...");
                noRecord.show();
          }
      }
      public static boolean inputCheck(String text1){
          if(text1.matches("[0-9]+")){
              int num1 = Integer.parseInt(text1);
              if(num1 >= 0 && num1 <= 60){
                  return true;
              }
              else return false;
          }
          else return false;
              
      }
     
}