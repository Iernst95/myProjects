
// source 1 : https://mkyong.com/java/how-to-format-a-double-in-java/


package iwegbcfxmlcpumonitors21;

import static java.lang.Double.isNaN;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author isaacernst
 */
public class IwegbcFXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private StackPane dialStackPane;
    
    @FXML
    private Button startStopButton;
    @FXML
    private Button recordResetButton;
  
    @FXML
    private Label peakCPULabel;
    @FXML
    private Label meanCPULabel;
    @FXML
    private Text CPUPercentageUsed;
    
    
    @FXML
    private ImageView dialImage;
    @FXML
    private ImageView handImage;
    
    
    @FXML
    private LineChart<String, Number> recordedCPUlineChart;
    @FXML
    private NumberAxis lineChartYAxis;
    @FXML
    private CategoryAxis lineChartXAxis;
    
    private XYChart.Series<String,Number> lineChartSeries;
    
    
    private int lineChartCount = 0;
    
    
    @FXML
    private AreaChart<String, Number> areaChart;
    @FXML
    private NumberAxis areaChartYAxis;
    @FXML
    private CategoryAxis areaChartXAxis;
    
    private XYChart.Series<String,Number> areaChartSeries;
    
    private KeyFrame keyFrame;
    private Timeline timeline;
    
    private double speed = 1.0;
    private double CPUUsage;
    private double meanUsage = 0;
    private double peakUsage = 0;
    private double mean = 0;
    private int meanCounter = 0;
    
    //source 1 code here 
     DecimalFormat df = new DecimalFormat("##.##");
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startTimeline();
        lineChartSeries = new XYChart.Series();
        areaChartSeries = new XYChart.Series();
        recordedCPUlineChart.getData().add(lineChartSeries);
        recordedCPUlineChart.setAnimated(false);
        areaChart.getData().add(areaChartSeries);
        areaChart.setAnimated(false);
         
    } 
    
    
    public void startTimeline(){
        keyFrame = new KeyFrame(Duration.millis(1000 * speed), (ActionEvent event) -> {
         CPUUsage = getCPUUsage();
         if(!Double.isNaN(CPUUsage) ) {
           
         update();
         }
        timeline.play();
        
           
    });
          timeline = new Timeline(keyFrame);
          timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    private void update(){
        double rotation = 195 + (CPUUsage * 300);
        handImage.setRotate(rotation);
        updateDialPercentage(CPUUsage);
        updatePeakUsage(CPUUsage);
        updateMeanUsage(CPUUsage);
        
    }
  
   
    @FXML
    public void startStopButton(){    
         if(isRunning()){
            stop();
            }
            else{
            start();
    
            }
}
    @FXML
    public void recordResetButton(){
           if(isRunning()){
               record();
               
           }
           else{
               reset();   
           }
    }
    public void updatePeakUsage(double percentage){
        if(percentage > peakUsage){
            peakUsage = percentage ;
            peakCPULabel.setText("Peak CPU Usage: " +df.format(percentage*100)+ "%");
        }
         
        
    }
    public void updateMeanUsage(double percentage){
        meanCounter++;
        mean = mean + percentage;
        meanUsage = mean/meanCounter;
        if(meanUsage ==0){
            meanCPULabel.setText("Mean CPU Usage: 00.00%");
        }else{
        meanCPULabel.setText("Mean CPU Usage: " +df.format(meanUsage*100)+ "%");
        }
        addToAreaChart(meanUsage);
    }
    
    public void updateDialPercentage(double percentage){
       
        CPUPercentageUsed.setText(df.format(percentage*100)+ "%");

    }

    public double getCPUUsage(){
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double value = 0;
        
        for(Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
        if (method.getName().startsWith("getSystemCpuLoad") && Modifier.isPublic(method.getModifiers())) { 
            try{
             value = (double) method.invoke(operatingSystemMXBean); } 
            catch (Exception e) {
             value = 0; }
            
            return value;
             
         }
}
     return value;
    }
    
    public boolean isRunning(){
         if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false; 
    }
    
    
    public void start(){
         timeline.play();
         startStopButton.setText("Stop");
         recordResetButton.setText("Record");
    }
    public void stop(){
        
        timeline.pause();
        startStopButton.setText("Play");
        recordResetButton.setText("Reset");
    }
    public void reset(){
        timeline.stop();
        handImage.setRotate(180);
        startStopButton.setText("Start");
        recordResetButton.setText("Record");
        lineChartSeries.getData().clear();
        areaChartSeries.getData().clear();
        lineChartCount = 0;
        peakCPULabel.setText("Peak CPU Usage: 00.00%");
        meanCPULabel.setText("Mean CPU Usage: 00.00%");
        CPUPercentageUsed.setText("0.00%");
        peakUsage = 0;
        meanUsage = 0;
        mean = 0;
        meanCounter = 0;
        
    }
    public void record(){
        
         addToLineChart(CPUUsage);
    }
    
    public void addToLineChart(double CPUUsage){
        lineChartCount++;
        lineChartSeries.getData().add(new XYChart.Data(Integer.toString(lineChartCount), (int)(CPUUsage*100)));
        
     
    }
    public void addToAreaChart(double mean){
        areaChartSeries.getData().add(new XYChart.Data(Integer.toString(meanCounter),(int)(mean*100)));
    }
    
}
