/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcgamefinalprojects21;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 *
 * @author isaacernst
 */
public class MainMenuController extends Switchable implements Initializable {

    
    
    public Character character;
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button exitButton;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        character = new Character();
    }  
    public void setCharacter(Character character){
        this.character = character;
    }
    @FXML
    private void newGameButtonPressed(ActionEvent event) {
        
        TextInputDialog input = new TextInputDialog("Default Name");
        input.setHeaderText("Enter your Character's name");
        input.showAndWait();
        String newName = input.getResult();
        Switchable.switchTo("CreateCharacter");
        CreateCharacterController controller = (CreateCharacterController) Switchable.getControllerByName("CreateCharacter");
         
        if (controller != null){
             this.character.setName(newName);
             controller.setCharacter(character); 
        }
    }

    @FXML
    private void loadGameButtonPressed(ActionEvent event) {
        Switchable.switchTo("LoadCharacter");
        LoadCharacterController controller = (LoadCharacterController) Switchable.getControllerByName("LoadCharacter");
       
       
        if (controller != null) {
          controller.character = this.character;
        }
      
    }

    @FXML
    private void exitButtonPressed(ActionEvent event)  {
        
        // a majority of the content from this method is from Professor Wergeles Serialization Lectures
        if(character.getName().equals("Default Name")|| character == null){
            Alert saveExitAlert = new Alert(Alert.AlertType.CONFIRMATION);
            saveExitAlert.setTitle("Exited");
            saveExitAlert.setContentText("There was no Character to save, the program will now exit safely. Thanks for playing! ");
            saveExitAlert.showAndWait();
            
            System.exit(0);
            
        }
        else{
        //person = createPersonFromFormData();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(Switchable.scene.getWindow());
        
        if (file != null) {
            
            try {
                
                FileOutputStream fileOut = new FileOutputStream(file.getPath());
                
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                
                out.writeObject(character);
                
                
                //these need to be in finally
                out.close();
                fileOut.close();
              //below we are putting the most specific catches at the top and the most general at the bottom  
            } catch (FileNotFoundException ex) {
                String message = "File not found exception occured while saving to " + file.getPath(); 
                displayExceptionAlert(message, ex); 
            } catch (IOException ex) {
                String message = "IOException occured while saving to " + file.getPath();
                displayExceptionAlert(message, ex);
            }catch (Exception ex) {
                String message = "IOException occured while saving to " + file.getPath();
                displayExceptionAlert(message, ex);
            }finally{
                //need to make the variables up above 
               // out.close();
               // fileOut.close();
            }
        }        
       
        System.exit(0);
        }
    
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

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }      
    @FXML
    private void aboutButtonPressed(ActionEvent event) {
        Switchable.switchTo("About");
    }
    @FXML
    private void backToMainMenuButtonPressed(ActionEvent event) {
        Switchable.switchTo("MainMenu");
    }

   

    
}
