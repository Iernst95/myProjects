/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcmvctimerstopwatchfxmls21;

import com.sun.prism.paint.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import static javafx.scene.paint.Color.color;
import javafx.scene.text.Text;

/*
 * @author isaacernst
 *  //source 1: https://stackoverflow.com/questions/38566638/javafx-displaying-time-and-refresh-in-every-second
 */
public class IwegbcMVCTimerStopwatchFXMLController implements Initializable, PropertyChangeListener {

    @FXML
    private StackPane stackpane;
    @FXML
    private Text digitalTimerText;
    @FXML
    private Text timerText;
    @FXML
    private Text lapText;
    @FXML
    private Text averageLapTimeText;
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private NumberAxis lineChartYAxis;
    @FXML
    private CategoryAxis lineChartXAxis;
    @FXML
    private AreaChart<String, Number> areaChart;
    @FXML
    private NumberAxis areaChartYAxis;
    @FXML
    private CategoryAxis areaChartXAxis;
        
    @FXML
    private Button startStopButton;
    @FXML
    private Button resetRecordButton;
    @FXML
    private ImageView clockfaceImage;
    @FXML
    private ImageView handImage;
    
    IwegbcMVCAnalogModel analogModel;
    IwegbcMVCDigitalModel digitalModel;
    
     private XYChart.Series<String,Number> lineChartSeries;
     private XYChart.Series<String,Number> areaChartSeries;
    
    // source 1 code starts here 
     private DateFormat timerFormatMinsSecs = new SimpleDateFormat("mm.ss");
     private DateFormat timerFormatMilliseconds = new SimpleDateFormat("SS");
     private DateFormat timerFormatDoubleSecond = new SimpleDateFormat("ss");
     private DateFormat timerFormatSingleSecond = new SimpleDateFormat("s");
    // source 1 code ends here    
     
     private int lapCounter;
     private boolean resetCheck = false;
     double lapAverage;
     double totalLapTime = 0;
     double biggestLapNumber = 0;

   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // creating model objects
        analogModel = new IwegbcMVCAnalogModel();
        digitalModel = new IwegbcMVCDigitalModel();
        
        analogModel.setupMonitor();
       
        handImage.setRotate(0);
        
        //connecting the model to the property change listener
        analogModel.addPropertyChangeListener(this);
        digitalModel.addPropertyChangeListener(this);
        
        lineChartSeries = new XYChart.Series();
        lineChart.getData().add(lineChartSeries);
        areaChartSeries = new XYChart.Series();
        areaChart.getData().add(areaChartSeries);
        lineChart.setStyle("-fx-background-color:#FFF5EE");
        areaChart.setStyle("-fx-background-color:#FFF5EE");
        anchorPane.setStyle("-fx-background-color:#FFF5EE");
        
    }
    

    @FXML
    private void startStopButtonAction(ActionEvent event) {
         if((analogModel.isRunning() && digitalModel.isRunning())){
            analogModel.stop();
            digitalModel.stop();
            startStopButton.setText("Start");
            
            //setting the start button to green 
            startStopButton.setStyle("-fx-background-color:#32CD32");
            resetRecordButton.setText("Reset");
            resetCheck = true;
            }
            else{
            analogModel.start();
            digitalModel.start();
            
            //setting the stop button to red
            startStopButton.setStyle("-fx-background-color:FF0000 ");
            
            startStopButton.setText("Stop");
            resetRecordButton.setText("Record");
            }
    }
    
    @FXML
    private void recordResetButtonAction(ActionEvent event) {
         if((analogModel.isRunning()&& digitalModel.isRunning())){
            if(!(timerText.getText().equals("Time is up!!"))){
                digitalModel.updateLap();
            }
            else{
                Alert timerIsDone = new Alert(Alert.AlertType.ERROR);
                timerIsDone.setContentText("Time is up! You can no longer record");
                timerIsDone.setTitle("Timer Done");
                timerIsDone.show();
                }   
            }
         else{
            if(resetCheck){
            lineChartSeries.getData().clear();
            areaChartSeries.getData().clear();
            digitalTimerText.setText("--:--.--");
            lapText.setText("Lap -: --:--.--");
            timerText.setText("Timer --:--");
            averageLapTimeText.setText("Average Lap Time --:--.--");
            lineChartYAxis.upperBoundProperty().set(5);
            areaChartYAxis.upperBoundProperty().set(5);
            lapCounter = 0;
            handImage.setRotate(0);
            analogModel.reset();
            digitalModel.reset();
            resetCheck = false;
            lapAverage = 0;
            totalLapTime = 0;
            biggestLapNumber = 0;
             }
         }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Analog")){
             handImage.setRotate(Double.parseDouble(evt.getNewValue().toString()));
        }
        //code from source 1 in the blocks below using the timerFormat variables
        else if(evt.getPropertyName().equals("startTime")){
            double startTime = Double.parseDouble(evt.getNewValue().toString());
            int millis = Integer.parseInt(timerFormatMilliseconds.format(startTime))/10;
            
            if(startTime<= 0){
                timerText.setText("Time is up!!");
            }
            else if(startTime>=10000){
                timerText.setText("Timer " + timerFormatDoubleSecond.format(startTime) + "." + timerFormatMilliseconds.format(millis));
            }
            else{
                timerText.setText("Timer " + timerFormatSingleSecond.format(startTime) + "." + timerFormatMilliseconds.format(millis));
            }
        }
        else if(evt.getPropertyName().equals("Lap")){
            lapCounter++;
            double lapTime = Double.parseDouble(evt.getNewValue().toString());
            totalLapTime = totalLapTime + lapTime;
            int millis = Integer.parseInt(timerFormatMilliseconds.format(lapTime))/10;
            //function that will raise the Y axis bounds if the value is larger than the current bounds
            if(biggestLapNumber<(lapTime/1000)){
            lineChartYAxis.upperBoundProperty().set(Math.ceil((lapTime/1000+5)));
            areaChartYAxis.upperBoundProperty().set(Math.ceil(lapTime/1000+5));
            biggestLapNumber = lapTime/1000;
            }
            
            lapText.setText("Lap " + lapCounter + " " + timerFormatMinsSecs.format(lapTime) + "."+ timerFormatMilliseconds.format(millis));
            lineChartSeries.getData().add(new XYChart.Data(Integer.toString(lapCounter), lapTime/1000 ));
            lapAverage = totalLapTime/lapCounter;
            areaChartSeries.getData().add(new XYChart.Data(Integer.toString(lapCounter), lapAverage/1000));
            averageLapTimeText.setText("Average Lap Time " + timerFormatMinsSecs.format(lapAverage) + "."+ timerFormatMilliseconds.format(millis));
            
        }
        else if (evt.getPropertyName().equals("Elapsed")){
            double timeElapsed =  Double.parseDouble(evt.getNewValue().toString());
            int millis = Integer.parseInt(timerFormatMilliseconds.format(timeElapsed))/10;
            
            digitalTimerText.setText(timerFormatMinsSecs.format(timeElapsed) + "." + timerFormatMilliseconds.format(millis));
        }
        
        
        
        
        
    }
    
    
}
