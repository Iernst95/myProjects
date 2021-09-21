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
public class EasyEnemy extends BasicEnemy implements Enemy{
    
        private String name;
        private Double health;
        public String weakAttack = "Spoon Slap";
        public String mediumAttack = "Evil Spell";
        public String strongAttack = "Witches Potion";
        
    
    
    public EasyEnemy(){
        name = "Witch of the Dark Woods" ;
        health = 100.0;
    }

    @Override
    public void weakAttack(Character character) {
        Double attack  = 5.0;
        character.setHealthBar(character.getHealthBar() - attack);
    }

    @Override
    public void mediumAttack(Character character) {
        Double attack = 8.0;
        character.setHealthBar(character.getHealthBar() - attack);
    }

    @Override
    public void strongAttack(Character character) {
        Double attack = 12.0;
        character.setHealthBar(character.getHealthBar() - attack);
    }

    @Override
    public Double getHealth() {
        return this.health;
    }
    @Override
    public void setHealth(Double health) {
        if(health>=0){
            this.health = health;
        }
        else{
            this.health = 0.0;
        }
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    @Override
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
