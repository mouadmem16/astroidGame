/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author LenovoPc
 */
public class ScorePane extends GraphicObject{
    int scr;
    public ScorePane(double width){
        corps = new Text(); 
        corps.setTranslateX(width-150);
        corps.setTranslateY(15);
        ((Text)corps).setFill(Color.WHITE);        
        ((Text)corps).setFont(new Font(20)); 
    }
    
    public void refreshScore(){
        ((Text)corps).setText("Score: "+scr);
    }
}
