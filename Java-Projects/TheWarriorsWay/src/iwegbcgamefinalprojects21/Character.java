/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iwegbcgamefinalprojects21;

import static java.lang.Math.random;

/**
 *
 * @author isaacernst
 */
public class Character implements java.io.Serializable{
    
    private String name;
    private int strength;
    private int speed;
    private int stamina;
    private Integer health;
    private int upgradePoints;
    private Double attackDamage;
    private Double staminaBar;
    private Double healthBar;
    private Double dodgeChance;
    
    public Character(){
    name = "Default Name";
    strength = 0;
    speed = 0;
    stamina = 0;
    health = 0;
    upgradePoints = 20;
    healthBar = 100.0;
    
        adjustStats();
    }
    
    public void adjustStats(){
        staminaBar= 100 + (stamina + 0.0);
        healthBar = 100.0 + (health);
        attackDamage = (strength+1) * .5;
        dodgeChance = (speed+1)*.25;
    }
    
    public void setName(String name){
        this.name = name; 
    }
    public String getName(){
        return this.name;
    }
    public void setStrength(int strength){
        this.strength = strength;
    }
    public int getStrength(){
        return this.strength;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public int getSpeed(){
        return this.speed;
    }
    public void setStamina(int stamina){
        this.stamina = stamina;
    }
    public int getStamina(){
        return this.stamina;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public int getHealth(){
        return this.health;
    }
    public void setUpgradePoints(int points){
        this.upgradePoints = points;
    }
    public int getUpgradePoints(){
        return this.upgradePoints;
    }
    public void stab(BasicEnemy enemy){
        int stab = 3;
        enemy.setHealth(enemy.getHealth()- (stab+attackDamage));
    }
    public void slice(BasicEnemy enemy){
        int slice = 5;
        enemy.setHealth(enemy.getHealth() - (slice + attackDamage));        
    }
    public void lunge(BasicEnemy enemy){
        int lunge = 10;
        enemy.setHealth(enemy.getHealth() - (lunge+attackDamage));
        
    }
    public void setHealthBar(Double health){
        if(health>=0){
        this.healthBar = health;
        }
        else{
            this.healthBar = 0.0;
        }
    }
    public Double getHealthBar(){
        return this.healthBar;
    }
    public void setStaminaBar(Double stamina){
        this.staminaBar = stamina;
    }
    public Double getStaminaBar(){
        return this.staminaBar;
    }
    public boolean dodge(){
        double randomNum =  Math.random() * 100;
        if(randomNum>dodgeChance){
            
            return false;
        }
        else{
           
            return true;
        }
  
    }
            
        
}
