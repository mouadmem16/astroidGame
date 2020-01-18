/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.Node;

/**
 *
 * @author LenovoPc
 */
public class GraphicObject {
    protected Node corps;

    public Node getCorps() {
        return corps;
    }

    public void setCorps(Node corps) {
        this.corps = corps;
    }
    
    public boolean touch(GraphicObject obj){
        return corps.getBoundsInParent().intersects(obj.corps.getBoundsInParent());
    }
}
