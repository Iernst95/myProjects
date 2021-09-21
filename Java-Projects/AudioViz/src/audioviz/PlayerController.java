/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audioviz;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles 
 * 
 * Music: 
 * http://www.bensound.com/royalty-free-music
 * http://www.audiocheck.net/testtones_sinesweep20-20k.php
 * 
 * 
 * References: 
 * http://stackoverflow.com/questions/11994366/how-to-reference-primarystage
 */
public class PlayerController implements Initializable {

    @FXML
    private AnchorPane vizPane;

    @FXML
    private MediaView mediaView;

    @FXML
    private Text filePathText;

    @FXML
    private Text lengthText;

    @FXML
    private Text currentText;

    @FXML
    private Text bandsText;

    @FXML
    private Text visualizerNameText;

    @FXML
    private Text errorText;

    @FXML
    private Menu visualizersMenu;

    @FXML
    private Menu bandsMenu;

    @FXML
    private Slider timeSlider;

    @FXML
    private Button playPause;
    
    @FXML
    private Slider volumeSlider;
    
    
    private Media media;
    private MediaPlayer mediaPlayer;

    private Integer numOfBands =40;
    private final Double updateInterval = 0.05;

    private ArrayList<Visualizer> visualizers;
    private Visualizer currentVisualizer;
    private final Integer[] bandsList = {1, 2, 4, 8, 16, 40, 60, 80, 100};

    private int currentStatus = 0;
    private Integer song_duration;
    @FXML
    private AnchorPane bigPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Text volumeText;
    
    private DateFormat timerFormatMinsSecs = new SimpleDateFormat("mm.ss");
    private DateFormat timerFormatMilliseconds = new SimpleDateFormat("SS");

    
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        bandsText.setText(Integer.toString(numOfBands));
        bigPane.setStyle("-fx-background-color:#D3D3D3 ");  
        menuBar.setStyle("-fx-background-color:#D3D3D3 ");
        

        visualizers = new ArrayList<>();
        visualizers.add(new EllipseVisualizer1());
        visualizers.add(new EllipseVisualizer2());
        visualizers.add(new EllipseVisualizer3());
        visualizers.add(new IwegbcSnowyVisualizerS21());
        
        for (Visualizer visualizer : visualizers) {
            MenuItem menuItem = new MenuItem(visualizer.getVizName());
            menuItem.setUserData(visualizer);
            menuItem.setOnAction((ActionEvent event) -> {
                selectVisualizer(event);
            });
            visualizersMenu.getItems().add(menuItem);
        }
        
        currentVisualizer = visualizers.get(0);
        visualizerNameText.setText(currentVisualizer.getVizName());

        for (Integer bands : bandsList) {
            MenuItem menuItem = new MenuItem(Integer.toString(bands));
            menuItem.setUserData(bands);
            menuItem.setOnAction((ActionEvent event) -> {
                selectBands(event);
            });
            bandsMenu.getItems().add(menuItem);
        }
    }

    private void selectVisualizer(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        Visualizer visualizer = (Visualizer) menuItem.getUserData();
        changeVisualizer(visualizer);
    }

    private void selectBands(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        numOfBands = (Integer) menuItem.getUserData();
        if (currentVisualizer != null) {
            currentVisualizer.setup(numOfBands, vizPane);
        }
        if (mediaPlayer != null) {
            mediaPlayer.setAudioSpectrumNumBands(numOfBands);
        }
        bandsText.setText(Integer.toString(numOfBands));
    }

    private void changeVisualizer(Visualizer visualizer) {
        if (currentVisualizer != null) {
            currentVisualizer.destroy();
        }
        currentVisualizer = visualizer;
        currentVisualizer.setup(numOfBands, vizPane);
        visualizerNameText.setText(currentVisualizer.getVizName());
    }

    private void openMedia(File file) {
        filePathText.setText("");
        errorText.setText("");

        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }

        try {
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setOnReady(() -> {
                handleReady();
            });
            mediaPlayer.setOnEndOfMedia(() -> {
                handleEndOfMedia();
            });
            mediaPlayer.setAudioSpectrumNumBands(numOfBands);
            mediaPlayer.setAudioSpectrumInterval(updateInterval);
            mediaPlayer.setAudioSpectrumListener((double timestamp, double duration, float[] magnitudes, float[] phases) -> {
                handleVisualize(timestamp, duration, magnitudes, phases);
            });
            mediaPlayer.setAutoPlay(true);
            filePathText.setText(file.getPath());
            currentStatus = 1;
            mediaPlayer.play();
            playPause.setText("Pause");
            
            // new lines of code 
            AudioFile audioFile = AudioFileIO.read(new File(file.getPath()));
            song_duration = audioFile.getAudioHeader().getTrackLength() * 1000;
        } catch (Exception ex) {
            errorText.setText(ex.toString());
        }
    }

    private void handleReady() {
       // int millis = Integer.parseInt(timerFormatMilliseconds.format(song_duration))/10;
        lengthText.setText(timerFormatMinsSecs.format(song_duration) + "s"); //+ "."+ timerFormatMilliseconds.format(millis));
        Duration ct = mediaPlayer.getCurrentTime();
        currentText.setText(timerFormatMinsSecs.format(song_duration) + "s");
        currentVisualizer.setup(numOfBands, vizPane);
        timeSlider.setMin(0);
        timeSlider.setMax(song_duration);
        
        volumeSlider.setMin(0);
        volumeSlider.setMax(1);
        //volumeSlider.setValue(1);
    }

    private void handleEndOfMedia() {
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
        timeSlider.setValue(0);
        
        DecimalFormat volumeDouble = new DecimalFormat("##");
       String newText = volumeDouble.format(0);
       volumeText.setText(newText + "%");
    }

    private void handleVisualize(double timestamp, double duration, float[] magnitudes, float[] phases) {
        Duration ct = mediaPlayer.getCurrentTime();
        double ms = ct.toMillis();
        currentText.setText(timerFormatMinsSecs.format(ms) + "s");
        timeSlider.setValue(ms);

        currentVisualizer.visualize(timestamp, duration, magnitudes, phases);
    }

    @FXML
    private void handleOpen(Event event) {
        Stage primaryStage = (Stage) vizPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            openMedia(file);
        }
    }

    @FXML
    private void handlePlayPause(ActionEvent event) {
        if (mediaPlayer != null) {
            if (currentStatus == 0) {
                currentStatus = 1;
                mediaPlayer.play();
                DecimalFormat volumeDouble = new DecimalFormat("##.##");
                String newText = volumeDouble.format(volumeSlider.getValue()*100);
            
                volumeText.setText(newText + "%");
                playPause.setText("Pause");
            } else {
                currentStatus = 0;
                mediaPlayer.pause();
                playPause.setText("Play");
            }
        }
    }
    
    @FXML
    private void handleSliderMousePressed(Event event) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            
        }
        
    }

    @FXML
    private void handleSliderMouseReleased(Event event) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(new Duration(timeSlider.getValue()));
            currentVisualizer.setup(numOfBands, vizPane);
            mediaPlayer.play();
            // added this because after slider was released, button still said "play"
            playPause.setText("Pause");
        }
    }

    @FXML
    private void handleVolumeSliderMouseReleased(MouseEvent event) {
        if (mediaPlayer != null) {
       DecimalFormat volumeDouble = new DecimalFormat("##");
       String newText = volumeDouble.format(volumeSlider.getValue()*100);
       volumeText.setText(newText + "%");
       
        }
    }

    @FXML
    private void handleVolumeSliderMouseDragged(MouseEvent event) {
        if(mediaPlayer != null){
        DecimalFormat volumeDouble = new DecimalFormat("##");
       String newText2 = volumeDouble.format(volumeSlider.getValue()*100);
       volumeText.setText(newText2 + "%");
        }
       DecimalFormat volumeDouble = new DecimalFormat("##");
       String newText = volumeDouble.format(volumeSlider.getValue());
       volumeText.setText(newText + "%");
      
    }

    @FXML
    private void handleSliderMouseDragged(MouseEvent event) {
        if(mediaPlayer!= null){
        double newTime = timeSlider.getValue();
        currentText.setText(timerFormatMinsSecs.format(newTime) + "s");
          
        }
        
    }
    
    
}
