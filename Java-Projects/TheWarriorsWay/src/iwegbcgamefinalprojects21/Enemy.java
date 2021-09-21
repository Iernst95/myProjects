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
public interface Enemy {
    
    public void weakAttack(Character character);
    public void mediumAttack(Character character);
    public void strongAttack(Character character);
    public void setHealth(Double health);
    public Double getHealth();
    public void setName(String name);
    public String getName();
    
    
            
        
    
    
}
