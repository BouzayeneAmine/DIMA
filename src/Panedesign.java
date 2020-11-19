
import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sec
 */
public class Panedesign extends JLabel{
    JLabel title ; 
    JLabel prix ; 
    Button btn; 

    public Panedesign(String title, String prix, Button btn) {
        this.title = new JLabel(title);
        this.prix = new JLabel(prix);
        ;
        this.setBackground(Color.red);
        this.setSize(200, 200);
        this.add(this.title);
        this.add(this.prix);
         
                
        //this.setBackground();
        
    }
    
    
}
