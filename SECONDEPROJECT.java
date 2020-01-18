/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author LenovoPc
 */
public class SECONDEPROJECT extends Application {
    private final double width = 800;
    private final double height = 800;
    private Pane container = new Pane();
    private Scene scene = null;
    private final Line line = new Line(0, 200, width, 200);
    private final Zone zone1 = new Zone(0,0,line.getEndX()-50,line.getEndY()-50);
    private final Zone zone2 = new Zone(line.getStartX(),line.getStartY(), line.getEndX()-50, height-50);
    private Player player = null;
    private List<Monstre> monstres = new ArrayList<>();
    private List<Balle> balles = new ArrayList<>();
    private ScorePane score = new ScorePane(width);
    private EventHandler<KeyEvent> event = new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event) {
        	if(event.getCode() == KeyCode.SPACE){
                Balle balle = new Balle(player);
                container.getChildren().add(balle.corps);
                balles.add(balle);
            }
            else if(event.getCode() == KeyCode.LEFT) moveXY(player, -15, 0);
            else if(event.getCode() == KeyCode.RIGHT) moveXY(player, 15, 0);
            else if(event.getCode() == KeyCode.UP) moveXY(player, 0, -15);
            else if(event.getCode() == KeyCode.DOWN) moveXY(player, 0, 15);
        }
    };
    private AnimationTimer animTime = new AnimationTimer(){
        @Override
        public void handle(long now){
        	for(Monstre monstre: monstres)
            	monstre.moving();    
           refreshContent();
        }
    };;
    
    protected void moveXY(GraphicObject g1, double x, double y){
    	g1.corps.setTranslateX(g1.corps.getTranslateX()+x);
    	g1.corps.setTranslateY(g1.corps.getTranslateY()+y);
    }
    
    private void refreshContent(){
        score.refreshScore();
        for(Monstre monstre: monstres){
            if (!container.getBoundsInLocal().intersects(monstre.corps.getBoundsInLocal())){
            	monstres.remove(monstre);
            	container.getChildren().remove(monstre);
            }
            
            if(player.touch(monstre)){
                container.getChildren().removeAll(player.corps, monstre.corps);
                finishSession();
            }
        }
        
        for(Balle balle: balles){
            balle.update();
            if (!container.getBoundsInLocal().intersects(balle.corps.getBoundsInLocal())){
            	balles.remove(balle);
            	container.getChildren().remove(balle);
            }
        }
        
        for(Monstre monstre: monstres){
            for(Balle balle: balles){
                if(balle.touch(monstre)){
                    score.scr++;
                    container.getChildren().removeAll(balle.corps, monstre.corps);
                    monstres.remove(monstre);
                    balles.remove(balle);
                }
            }
        }
       
        if(Math.random() < 0.01){
            Monstre monstre = new Monstre(zone1);
            monstres.add(monstre);
            container.getChildren().add(monstre.corps);
        }
    }
    
    private void finishSession(){
    	container.getChildren().clear();
        VBox finishbox = new VBox();
        finishbox.setTranslateX(180);
        finishbox.setTranslateY(250);
        finishbox.setAlignment(Pos.CENTER);
        finishbox.setSpacing(30);
    	Label finish = new Label("CONGRATULATION YOUR SCORE IS : "+score.scr);
        finish.setAlignment(Pos.CENTER);
    	finish.getStyleClass().add("box");
        finishbox.getChildren().add(finish);
//    	container.setBackground(null);
        animTime.stop();
        Button btn = new Button("Play Again");
        btn.setAlignment(Pos.CENTER);
        btn.getStyleClass().add("custom-button");
        finishbox.getChildren().add(btn);

    	btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				InitContent();
			}    		
    	});
    	container.getChildren().add(finishbox);
    	((Stage)scene.getWindow()).setScene(new Scene(container));
    }
    
    private void changeContent(){
    	container.getChildren().clear();
        player = new Player(zone2);
        monstres.clear();
        balles.clear();
        score.scr = 0;
        container.getChildren().add(line);
        container.getChildren().add(player.corps);
        container.getChildren().add(score.corps);
        System.gc();
    }
    
    private void InitContent(){
    	animTime.start();
    	BackgroundImage bg=null;
		try {
			bg = new BackgroundImage(new Image(getClass().getResource("bg.jpg").toExternalForm()),
			        BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.DEFAULT,
			          BackgroundSize.DEFAULT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	container.setBackground(new Background(bg));
    	changeContent();
    }
    
    @Override
    public void start(Stage primaryStage) {
        
    	InitContent();
        scene = new Scene(container);
        scene.setOnKeyPressed(event);

		scene.getStylesheets().add(SECONDEPROJECT.class.getResource("MyCss.css").toExternalForm());
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Asteroid game");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
