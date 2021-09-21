/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcgamefinalprojects21;

import static iwegbcgamefinalprojects21.IwegbcGameFinalProjectS21.mstage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
 * FXML Controller class
 *
 * @author isaacernst
 */
public class BattleSelectorController extends Switchable implements Initializable {
        public Character character;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setCharacter(Character character){
        this.character= character;
    }

    @FXML
    private void easyLevelButtonPressed(ActionEvent event) {
        Switchable.switchTo("DarkWoodsBattle");        
        BattleController controller = (BattleController) Switchable.getControllerByName("DarkWoodsBattle");
          
        if (controller != null){
            mstage.sizeToScene();
            EasyEnemy enemy = new EasyEnemy();
            controller.setEnemy(enemy);
            controller.setCharacter(character); 
             
             
        }
        
    }

    @FXML
    private void mediumLevelButtonPressed(ActionEvent event) {
        Switchable.switchTo("GraveyardBattle");
        BattleController controller = (BattleController) Switchable.getControllerByName("GraveyardBattle");
             
        if (controller != null){
            mstage.sizeToScene();
            MediumEnemy enemy = new MediumEnemy();
            controller.setEnemy(enemy);
            controller.setCharacter(character); 
             
             
        }
    }

    @FXML
    private void hardLevelButtonPressed(ActionEvent event) {
      Switchable.switchTo("LavaPitsBattle");
      BattleController controller = (BattleController) Switchable.getControllerByName("LavaPitsBattle");
         
        if (controller != null){
            mstage.sizeToScene();
             HardEnemy enemy = new HardEnemy();
            controller.setEnemy(enemy);
            controller.setCharacter(character); 
        }
    }

    @FXML
    private void backButtonPressed(ActionEvent event) {
        Switchable.switchTo("CreateCharacter");
        CreateCharacterController controller = (CreateCharacterController) Switchable.getControllerByName("CreateCharacter");

        if (controller != null){
           
             controller.setCharacter(character); 
        }
    }
    
}
