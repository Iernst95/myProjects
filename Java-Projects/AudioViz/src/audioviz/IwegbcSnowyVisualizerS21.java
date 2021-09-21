/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audioviz;

import static java.lang.Integer.min;
import java.util.Random;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author isaacernst
 */
public class IwegbcSnowyVisualizerS21 implements Visualizer{

     private String name = "Snowy Visualizer";
     private Integer numBands;
    private AnchorPane vizPane; 
    private Double width = 0.0;
    private Double height = 0.0;
    private String vizPaneInitialStyle = "";
    private int snowflakes = 101;
    
    // below starts the custom variables custom to my vizualizer
     private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
     private final Double bandHeightPercentage = 1.3;
      private final Double minEllipseRadius = 10.0; 
      Random r;
    
    private Ellipse[][] ellipses;
    private Rectangle[] rectangles; 
    private String[] colors = {"#87CEFA","#00BFFF", "#1E90FF" , "#0000FF", "#0000CD", "#00008B", "#000080"};
    
    
    public IwegbcSnowyVisualizerS21() {
        name = "Snowy Visualizer";
        width = 0.0;
        height = 0.0;
        bandWidth = 0.0;
        bandHeight = 0.0;
        halfBandHeight = 0.0;
       
    }
    
    @Override
    public void setup(Integer numBands, AnchorPane vizPane) { 
        destroy();
       vizPaneInitialStyle = vizPane.getStyle();
        
        this.numBands = numBands ; 
        this.vizPane = vizPane;
        
        
        height = vizPane.getHeight();
        width = 832.0;
        
        Rectangle clip = new Rectangle(width, height);
        clip.setLayoutX(0);
        clip.setLayoutY(0);
        vizPane.setClip(clip);
        
    
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandHeight = bandHeight / 2;
        
        ellipses = new Ellipse[snowflakes][9];
        rectangles = new Rectangle[numBands];
     
        
        
        for(int i = 0; i <snowflakes; i ++){
            r = new Random();
             double value = r.nextDouble();
            double value2 = r.nextDouble();
            
            if(i<numBands){
               
            Rectangle rectangle = new Rectangle();
            //set radius and centers
            rectangle.setX( bandWidth * i);
            rectangle.setY(460);
            rectangle.setWidth(bandWidth);
            rectangle.setHeight(minEllipseRadius);
            rectangle.setFill(Color.LIGHTSKYBLUE);
            
            vizPane.getChildren().add(rectangle);
            rectangles[i] = rectangle;
            }
            for(int k = 0; k<9; k++){
                Ellipse ellipse = new Ellipse();
           
          
            ellipse.setCenterX(850*value);
            // this puts most of the snowflakes on the screen but puts some above
            // to fall onto the screen
            if(i<snowflakes/10){
                ellipse.setCenterY(-50*value2);
            }else{
                ellipse.setCenterY(470*value2);
            }
            
            ellipse.setRadiusX(10 / 4);
            ellipse.setRadiusY(minEllipseRadius);
            ellipse.setFill(Color.WHITE);
            vizPane.getChildren().add(ellipse);
           
                
                if(k== 0){
                    ellipse.setRotate(0);
                   
                }
                if(k==1){
                    ellipse.setRotate(45);
                }
                if(k==2){
                    ellipse.setRotate(135);
                }
                
                if(k==3){
                    ellipse.setRotate(140);
                }
                if(k==4){
                    ellipse.setRotate(130);
                }
                if(k==5){
                    ellipse.setRotate(50);
                }
                if(k==6){
                    ellipse.setRotate(40);
                }
                if(k==7){
                    ellipse.setRotate(355);
                }
                if(k==8){
                    ellipse.setRotate(5);
                }
                ellipses[i][k] = ellipse;
            }
        }
        
       
            
        }

    @Override
    public void destroy() {
        
        width = 0.0;
        height = 0.0;
        bandWidth = 0.0;
        bandHeight = 0.0;
        halfBandHeight = 0.0;
        
         if (ellipses != null) {
            for (int i = 0;i<snowflakes;i++) {
                
                for(int k = 0; k <9; k++){
                
                
                vizPane.getChildren().remove(ellipses[i][k]);
                }
            }
         
            ellipses = null;
            vizPane.setClip(null);
            vizPane.setStyle(vizPaneInitialStyle);
        }
         if(rectangles != null){
             for(int j = 0; j<numBands; j++){
                    vizPane.getChildren().remove(rectangles[j]);
                }
         }
    }

    @Override
    public String getVizName() {
        return name;
    }

    @Override
    public void visualize(double timestamp, double length, float[] magnitudes, float[] phases) {
        if (ellipses == null) {
            return;
        }
        
        Integer num = min(rectangles.length, magnitudes.length);
        Integer num2 = min(ellipses.length, magnitudes.length);
        
        
        for (int i = 0; i < snowflakes; i++) {
            int r2;
            Random random = new Random();
            r2 = random.nextInt(num2);
            
            
               
            if(i<num){
               // some variables for the algorithm to decide what color to set the bars to 
               double tester = 60+ magnitudes[i];
               double sections = 60/7;
               int colorNum= 0;
               
                
              // below decides what color to set the bars to 
                   if(tester<=sections){
                       colorNum = 0;
                   }
                   if(tester>sections && tester<=(sections*2)){
                       colorNum = 1;
                   }
                   if(tester>sections*2 && tester<=(sections*3)){
                       colorNum = 2;
                   }
                   if(tester>sections*3 && tester<=(sections*4)){
                       colorNum = 3;
                   }
                   if(tester>sections*4 && tester<=(sections*5)){
                       colorNum = 4;
                   }
                   if(tester>sections*5 && tester<=(sections*6)){
                       colorNum = 5;
                   }
                   if(tester>sections*6 && tester<=(sections*7)){
                       colorNum = 6;
                   }
                         
                 rectangles[i].setY(470-(((60.0 + magnitudes[i])/45.0) * halfBandHeight + minEllipseRadius));
                 double z = 470- rectangles[i].getY();
                 rectangles[i].setHeight(z);
                 rectangles[i].setFill(Color.web(colors[colorNum]));
            }
            for(int k = 0; k <9; k++){
              //the snowflakes pulse randomly, unlike the bars
            
            
            ellipses[i][k].setRadiusY( ((60.0 + magnitudes[r2])/720.0) * halfBandHeight + minEllipseRadius);
            ellipses[i][k].setFill(Color.WHITE);
            
            double center = ellipses[i][k].getCenterY();
            ellipses[i][k].setCenterY(center+1);
            
            // this sends the snowflake back to the top once it gets to the bottom of the screen
            if(center>450){
                ellipses[i][k].setCenterY(-50);
            }
            else{
                ellipses[i][k].setCenterY(center+1);
            }
            }
        }    
    }
    
    
}
