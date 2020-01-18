/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author LenovoPc
 */
public class Balle extends GraphicObject{
    private Point2D direction = new Point2D(Math.cos(Math.toRadians(270)), Math.sin(Math.toRadians(270)));
    public Balle(Player arme){
        corps = new Circle(5, 0, 4, Color.ORANGE);
        corps.setTranslateX(arme.corps.getTranslateX()+30);
        corps.setTranslateY(arme.corps.getTranslateY()+10);
        direction = direction.normalize().multiply(7);
    }
    
    public void update(){
        corps.setTranslateX(corps.getTranslateX()+direction.getX());
        corps.setTranslateY(corps.getTranslateY()+direction.getY());
    }
}
