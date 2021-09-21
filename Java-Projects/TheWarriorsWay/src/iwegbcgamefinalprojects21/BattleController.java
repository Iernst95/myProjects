/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcgamefinalprojects21;

import static iwegbcgamefinalprojects21.IwegbcGameFinalProjectS21.mstage;
import static java.lang.Math.random;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author isaacernst
 */
public class BattleController extends Switchable implements Initializable {
    
    @FXML
    private TextArea textArea;
    @FXML
    private ImageView warriorImage;
    @FXML
    private ImageView EnemyImage;
    
    public Character character;
    public BasicEnemy enemy;
    @FXML
    private Text characterHealthText;
    @FXML
    private Text enemyHealthText;
    @FXML
    private Text staminaText;
 
    
    public boolean userTurn;
    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTurn = true;
       
        
       
    }  
    public void setCharacter(Character character){
        this.character= character;
        character.adjustStats();
        textArea.setText(character.getName() + ", You have encountered the " + enemy.getName() + "! \n");
        characterHealthText.setText("Health: " + character.getHealthBar());
        staminaText.setText("Stamina: " + character.getStaminaBar());
        enemyHealthText.setText("Health: " + enemy.getHealth());
    }
    public void setEnemy(BasicEnemy enemy){
        this.enemy= enemy;
    }

    @FXML
    private void stabAttackButtonPressed(ActionEvent event) {
        attackDecider("stab", 5.0);
    }

    @FXML
    private void sliceAttackButtonPressed(ActionEvent event) {
         attackDecider("slice", 10.0);
    }

    @FXML
    private void lungeAttackButtonPressed(ActionEvent event) {
        attackDecider("lunge",20.0);
    }

    @FXML
    private void fleeButtonPressed(ActionEvent event) {
        if(character.getStaminaBar()>=50){
         Switchable.switchTo("BattleSelector");
         BattleSelectorController controller = (BattleSelectorController) Switchable.getControllerByName("BattleSelector");

        if (controller != null){
            mstage.sizeToScene();
            controller.setCharacter(character); 
        }
        }
        else{
            textArea.appendText("You do not have enough stamina! Use a lower stamina attack and wait for the next round to get your stamina back!\n");
        }
    }
    public void attackDecider(String attack, Double stamina){
        if(userTurn){
            if(character.getStaminaBar()>=stamina){
                character.setStaminaBar(character.getStaminaBar() - stamina);
                staminaText.setText("Stamina: " + character.getStaminaBar());
                if(attack.equals("stab")){
                    character.stab(enemy);
                    enemyHealthText.setText("Health: " + enemy.getHealth());
                    userTurn = false;
                    
                }
                if(attack.equals("slice")){
                    character.slice(enemy);
                    enemyHealthText.setText("Health: " + enemy.getHealth());
                    userTurn = false;
                }
                if(attack.equals("lunge")){
                    character.lunge(enemy);
                    enemyHealthText.setText("Health: " + enemy.getHealth());
                    userTurn = false;
                }
                if(enemy.getHealth() == 0){
                    userWon();
                    
                }
                enemyTurn();
            }
            else{
                textArea.appendText("You do not have enough stamina! Use a lower stamina attack and wait for the next round to get your stamina back!\n");
            }
        }
        else{
            textArea.appendText("Not your turn!\n");
        }
    
    }
    public void enemyTurn(){

        if(character.dodge()){
            
            textArea.appendText("Your speed allowed you to dodge the attack!\n");
            textArea.appendText("You gained 8 Stamina! \n");
            character.setStaminaBar(character.getStaminaBar() + 8.0);
            userTurn = true;
            textArea.appendText("It is now your turn\n");
        }
        else{
            double attackType = (Math.random()*100);
            if(attackType>=0 && attackType<45){
                enemy.weakAttack(character);
                characterHealthText.setText("Health: " +Double.toString(character.getHealthBar()));
                textArea.appendText(enemy.getName() + " used " + enemy.getAttack("weak")+ "! \n");
            }
            if(attackType>=35 && attackType <80){
               enemy.mediumAttack(character);
               characterHealthText.setText("Health: " +Double.toString(character.getHealthBar()));
               textArea.appendText(enemy.getName() +" used " + enemy.getAttack("medium")+ "! \n");
            }
            if(attackType>=80){
                enemy.strongAttack(character);
                characterHealthText.setText("Health: " +Double.toString(character.getHealthBar()));
                textArea.appendText(enemy.getName() + " used " + enemy.getAttack("strong")+ "! \n" );
            }
            character.setStaminaBar(character.getStaminaBar() + 8.0);
            if(character.getHealthBar()==0){
                userLost();
            }
            textArea.appendText("You gained 8 Stamina! \n");
            userTurn = true;
            textArea.appendText("It is now your turn \n");
            
        }
       
    }
    public void userWon(){
        Alert wonAlert = new Alert(AlertType.CONFIRMATION);
        wonAlert.setTitle("You Won!");
        wonAlert.setContentText("You won the Battle! You have been awarded 10 upgrade points");
        wonAlert.showAndWait();
        character.setHealthBar(90.0);
        character.setUpgradePoints(character.getUpgradePoints() + 10);
        Switchable.switchTo("BattleSelector");
                
         BattleSelectorController controller = (BattleSelectorController) Switchable.getControllerByName("BattleSelector");

        if (controller != null){
            mstage.sizeToScene();
            controller.setCharacter(character); 
 
        }
        
    }
    public void userLost(){
        Alert wonAlert = new Alert(AlertType.CONFIRMATION);
        wonAlert.setTitle("You Lost");
        wonAlert.setContentText("You lost the battle! Go back and try a different strategy now!");
                wonAlert.showAndWait();
        Switchable.switchTo("BattleSelector");
         BattleSelectorController controller = (BattleSelectorController) Switchable.getControllerByName("BattleSelector");

        if (controller != null){
            mstage.sizeToScene();
            controller.setCharacter(character); 
         
        }
    }
   
    
}
