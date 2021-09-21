/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcgamefinalprojects21;

/**
 *
 * @author isaacernst
 */
public class BasicEnemy implements Enemy{
        
        private String name;
        private Double health;
        private String weakAttack = "weak";
        private String mediumAttack = "medium";
        private String strongAttack = "strong";
    public void basicEnemy(){
        
    }

    @Override
    public void weakAttack(Character character) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mediumAttack(Character character) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void strongAttack(Character character) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHealth(Double health) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getHealth() {
        return this.health;
    }
    

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName(){
        return this.name;
    }
    
     public String getAttack(String attack){
        if(attack.equals("weak")){
            return this.weakAttack;
        }
        if(attack.equals("medium")){
            return this.mediumAttack;
        }
        else{
            return this.strongAttack;
        }
        
    }

    
}
