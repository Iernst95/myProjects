/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcgamefinalprojects21;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * some methods were taken from Professor Wergeles serialization lecutres, they are cited below
 * 
 * @author isaacernst
 */
public class LoadCharacterController extends Switchable implements Initializable {

    @FXML
    private Button loadCharacterButton;
    @FXML
    private Button backButton;
    @FXML
    private TextArea characterDisplayArea;
    
    public Character character;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    public void setCharacter(Character character){
        this.character= character;
    }

    @FXML
    private void loadCharacterButtonPressed(ActionEvent event) {
        if(character.getName()!= "Default Name"){
        Switchable.switchTo("CreateCharacter");
        CreateCharacterController controller = (CreateCharacterController) Switchable.getControllerByName("CreateCharacter");

        if (controller != null){
             
             controller.setCharacter(character); 
        }
        }
        else{
            characterDisplayArea.setText("There is no character loaded!");
        
    }
    }

    @FXML
    private void backButtonPressed(ActionEvent event) {
        Switchable.switchTo("MainMenu");
    }

    @FXML
    private void loadCharacterFileOpened(ActionEvent event) {
        //A majority of the content from this method is from Professor Wergeles Serialization Lectures
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Switchable.scene.getWindow());
        
        if (file != null) {
            
            try {
               
                //the file.getpath needs to be the name the person enters
                FileInputStream fileIn = new FileInputStream(file.getPath());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                
                this.character = (Character) in.readObject();
                characterDisplay(this.character);
                
                in.close();
                fileIn.close();
                
            } catch (FileNotFoundException ex) {
                String message = "File not found exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex); 
                
            } catch (IOException ex) {
                String message = "IO exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex);
                
            } catch (ClassNotFoundException ex) {
                String message = "Class not found exception occured while opening " + file.getPath(); 
                displayExceptionAlert(message, ex); 
            }  
        }
        else{
            System.out.println("file doesn't exist");
        }
    }
    
    //This method came from Professor Wergeles Serialization Lecture
    private void displayErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        //alert.setHeaderText("Error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    //This method came from Professor Wergeles Serialization Lecture
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText(ex.getClass().getSimpleName());      
        alert.setContentText(message + "\n\n" + ex.getMessage());

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

      
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }      
    public void characterDisplay(Character character){
        characterDisplayArea.setText("Name: " + character.getName() + "\n" + 
                                     "Strength: " + character.getStrength() + "\n" +
                                     "Speed: " + character.getSpeed() + "\n" +
                                      "Stamina: " + character.getStamina() + "\n" +
                                        "Health: " + character.getHealth() );
        

        
        
    }
    
}
