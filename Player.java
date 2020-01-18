/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author LenovoPc
 */
public class Player extends GraphicObject{

    public Player(Zone zone){
        Image image = null;
        try {
            image = new Image(getClass().getResource("player.png").toExternalForm());
        } catch (Exception ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        corps = new ImageView(image);
        ((ImageView)corps).setFitHeight(80);
        ((ImageView)corps).setFitWidth(70);
        ((ImageView)corps).setX(0);
        ((ImageView)corps).setY(0);
        
        double x = zone.getX1() + (zone.getX2()-zone.getX1())*Math.random();
        double y = zone.getY1() + (zone.getY2()-zone.getY1())*Math.random();
        
        corps.setTranslateX(x);
        corps.setTranslateY(y);
    }
}
