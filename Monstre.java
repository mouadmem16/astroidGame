/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author LenovoPc
 */
public class Monstre extends GraphicObject{
    public Monstre(Zone zone){
        Image image = null;
        try {
            image = new Image(getClass().getResource("enemie.png").toExternalForm());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        corps = new ImageView(image);
        ((ImageView)corps).setFitHeight(60);
        ((ImageView)corps).setFitWidth(60);
        ((ImageView)corps).setX(0);
        ((ImageView)corps).setY(0);
        double x = zone.getX1() + (zone.getX2()-zone.getX1())*Math.random();
        double y = zone.getY1() + (zone.getY2()-zone.getY1())*Math.random();
        
        corps.setTranslateX(x);
        corps.setTranslateY(y);
        
    }
    
    public void moving(){
        corps.setTranslateX(corps.getTranslateX()+Math.random());
        corps.setTranslateY(corps.getTranslateY()+8);
    }
}
