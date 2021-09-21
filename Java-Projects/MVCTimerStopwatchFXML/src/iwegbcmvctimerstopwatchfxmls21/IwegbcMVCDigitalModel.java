/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcmvctimerstopwatchfxmls21;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author isaacernst
 */
public class IwegbcMVCDigitalModel extends IwegbcMVCAbstractModel {
  
    private double timer;
    private boolean timerIsUp = false;
    Alert timerIsDone;
    double currentTime = 0;
    private double firstRecord = 0;
    private double secondRecord = 0;
    private double timeElapsedValue = 0;
    private double timerValue = 0;
    private double lapValue = 0;
    private double lapCounter = 0;
    private boolean checkInput;
    private String result;
    
    
     
    public IwegbcMVCDigitalModel(){
        tickTimeInSeconds = 0.1;
        TextInputDialog timerInput = new TextInputDialog();
        timerInput.setTitle("Timer");
        timerInput.setHeaderText("Set the timer");
        timerInput.setContentText("Please set up the start time as a positive Integer: ");
        timerInput.showAndWait();
        result = timerInput.getResult();
        checkInput= inputCheck(result);
        while(!checkInput){
            Alert inputAlert = new Alert(Alert.AlertType.ERROR);
            inputAlert.setTitle("Error");
            inputAlert.setContentText("The input must be an integer between 0 and 60");
            inputAlert.showAndWait();
            timerInput.showAndWait();
            result = timerInput.getResult();
            checkInput = inputCheck(result);
        }
        
        startTime = Double.parseDouble(result);
        
    }
    protected void updateMonitor(){
        super.updateMonitor();
        timer= startTime;
        if((timer - secondsElapsed)*1000<=0 && timerIsUp == false){
            timerIsDone = new Alert(Alert.AlertType.INFORMATION);
            timerIsDone.setContentText("Time is up! You can no longer record");
            timerIsDone.setTitle("Timer Done");
            timerIsDone.show();
            timerIsUp = true;
        }
        currentTime = secondsElapsed;
        
        updateTimer(currentTime);
        updateElapsedTime(currentTime);
        
    }
    @Override
    public void reset(){
        super.reset();
        firstRecord = 0;
        secondRecord = 0;
        timeElapsedValue = 0;
        timerValue = 0;
        lapValue = 0;
        lapCounter = 0;
    
        
    }
    public void updateTimer(double timePassed){
        double oldTimerValue = timerValue;
        timerValue = (startTime - timePassed)*1000;
        firePropertyChange("startTime", oldTimerValue,timerValue);
    }   
    public void updateLap(){
        double oldLapValue = lapValue;
        lapValue = lapTime()*1000;
        firePropertyChange("Lap",oldLapValue,lapValue);
    }
    public void updateElapsedTime(double timePassed){
        double oldTimeElapsedValue = timeElapsedValue;
        timeElapsedValue = timePassed*1000;
        firePropertyChange("Elapsed",oldTimeElapsedValue,timeElapsedValue);
        
    }
    public double lapTime(){
        lapCounter++;
        double difference;
        if(lapCounter%2 !=0){
            firstRecord = secondsElapsed;
            difference = (secondsElapsed - secondRecord);
        }
        else{
            secondRecord = secondsElapsed;
            difference = (secondsElapsed - firstRecord);
        }
        return difference;
    }
    
     public boolean inputCheck(String text1){
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
