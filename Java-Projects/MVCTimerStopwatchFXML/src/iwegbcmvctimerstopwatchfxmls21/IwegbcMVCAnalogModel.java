/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcmvctimerstopwatchfxmls21;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author isaacernst
 */
public class IwegbcMVCAnalogModel extends IwegbcMVCAbstractModel{
    
    private double angle;
    
    
    
    public IwegbcMVCAnalogModel(){
        tickTimeInSeconds = 0.1;
       
       // firePropertyChange("startTime",userTimerInput,userTimerInput);
        
        
    }
    protected void updateMonitor(){
       super.updateMonitor();
       updateAnalog();
    }
    @Override
    public void reset(){
        super.reset();
        rotation = 0;
    }
    public void updateAnalog(){
        double oldAngle = angle;
        angle = rotation;
        
        firePropertyChange("Analog", oldAngle, angle);
    }
   
    
}
