/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcmvctimerstopwatchfxmls21;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author isaacernst
 */
public abstract class IwegbcMVCAbstractModel {
    
    protected Timeline timeline;
    protected KeyFrame keyFrame;
    protected double tickTimeInSeconds; //Change resolution
    protected double secondsElapsed = 0;
    protected double angleDeltaPerSeconds = 6;
    protected double rotation;
    protected double startTime = 0;
    protected boolean first = true;
    
    protected PropertyChangeSupport propertyChangeSupport;

    public IwegbcMVCAbstractModel() {
        tickTimeInSeconds = 0.1;
        propertyChangeSupport = new PropertyChangeSupport(this);
        setupMonitor();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
     public void setupMonitor() {        
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
                updateMonitor(); 
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    public boolean isRunning(){
         if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false; 
    }
    protected void updateMonitor(){
        if(first == false){
        secondsElapsed += tickTimeInSeconds;
        rotation = secondsElapsed * angleDeltaPerSeconds;
        }
        first = false;
       
    }
    public void setStartTime(double time){
        startTime = time;
    }
        
    public void start(){
        timeline.play();
    }
    public void stop(){
        timeline.stop();
   
    }
    public void reset(){
      secondsElapsed= 0.0;
      first = true;
    }
}
