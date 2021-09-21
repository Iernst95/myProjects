/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcgamefinalprojects21;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author isaacernst
 */
public class CreateCharacterController extends Switchable implements Initializable {

    @FXML
    private ImageView characterImageGif;
    @FXML
    private Text pointsLeftText;
    @FXML
    private Text speedPoints;
    @FXML
    private Text strengthPoints;
    @FXML
    private Text healthPoints;
    @FXML
    private Text staminaPoints;
    @FXML
    public Text characterNameText;
    
        
    public Character character;
    private int pointsAllowed;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }
    public void setCharacter(Character character){
        this.character= character;
        updateUI(healthPoints,character.getHealth());
        updateUI(strengthPoints,character.getStrength());
        updateUI(speedPoints,character.getSpeed());
        updateUI(staminaPoints,character.getStamina());
        updateUI(pointsLeftText,character.getUpgradePoints());
        if(character.getName()==null){
        characterNameText.setText("Default Name");
        }
        characterNameText.setText(character.getName());
        pointsAllowed = character.getUpgradePoints();
        
    }

    @FXML
    private void playGameButtonPressed(ActionEvent event) {
        Switchable.switchTo("BattleSelector");
        BattleSelectorController controller = (BattleSelectorController) Switchable.getControllerByName("BattleSelector");
                
        if (controller != null){
             controller.setCharacter(character); 
             
        }
    }

    @FXML
    private void backButtonPressed(ActionEvent event) {
        Switchable.switchTo("MainMenu");
        MainMenuController controller = (MainMenuController) Switchable.getControllerByName("MainMenu");
        if (controller != null){
             System.out.println(character.getName());
             controller.setCharacter(character); 
            
        }
    }
    @FXML
    private void HealthPlusPressed(ActionEvent event) {
        if(character.getUpgradePoints()>0 && character.getUpgradePoints() <= pointsAllowed ){
            
        character.setHealth(character.getHealth()+1);
        updateUI(healthPoints,character.getHealth());
        pointsMinus();
        }
    }
    @FXML
    private void HealthMinusPressed(ActionEvent event) {
        if(character.getUpgradePoints()>=0 && character.getUpgradePoints() < pointsAllowed && character.getHealth()>0){
        character.setHealth(character.getHealth()-1);
        updateUI(healthPoints,character.getHealth());
        pointsPlus();
        }
    }
    
    @FXML
    private void strengthPlusPressed(ActionEvent event) {
        if(character.getUpgradePoints()>0 && character.getUpgradePoints() <= pointsAllowed){
        character.setStrength(character.getStrength()+1);
        updateUI(strengthPoints,character.getStrength());
        pointsMinus();
        }
    }
    
    @FXML
    private void strengthMinusPressed(ActionEvent event) {
        if(character.getUpgradePoints()>=0 && character.getUpgradePoints() < pointsAllowed && character.getStrength()>0){
        character.setStrength(character.getStrength()-1);
        updateUI(strengthPoints,character.getStrength());
        pointsPlus();
        }
    }
    
    @FXML
    private void speedPlusPressed(ActionEvent event) {
        if(character.getUpgradePoints()>0 && character.getUpgradePoints() <= pointsAllowed){
        character.setSpeed(character.getSpeed()+1);
        updateUI(speedPoints,character.getSpeed());
        pointsMinus();
        }
    }
    
    @FXML
    private void speedMinusPressed(ActionEvent event) {
        if(character.getUpgradePoints()>=0 && character.getUpgradePoints() < pointsAllowed && character.getSpeed()>0){
        character.setSpeed(character.getSpeed()-1);
        updateUI(speedPoints,character.getSpeed());
        pointsPlus();
        }
    }
    
    @FXML
    private void staminaPlusPressed(ActionEvent event) {
        if(character.getUpgradePoints()>0 && character.getUpgradePoints() <= pointsAllowed){
        character.setStamina(character.getStamina()+1);
        updateUI(staminaPoints,character.getStamina());
        pointsMinus();
        }
    }
    
    @FXML
    private void staminaMinusPressed(ActionEvent event) {
        if(character.getUpgradePoints()>=0 && character.getUpgradePoints() < pointsAllowed && character.getStamina()>0){
        character.setStamina(character.getStamina()-1);
        updateUI(staminaPoints,character.getStamina());
        pointsPlus();
        }
    }
    public void pointsPlus(){
        character.setUpgradePoints(character.getUpgradePoints() +1);
        updateUI(pointsLeftText,character.getUpgradePoints());
    }
    public void pointsMinus(){
        character.setUpgradePoints(character.getUpgradePoints() -1);
        updateUI(pointsLeftText,character.getUpgradePoints());

    }
    public void updateUI(Text text, int value ){
        text.setText(Integer.toString(value));
    }

    
   
}
