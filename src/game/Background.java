package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utilities.Direction;


public class Background extends Pane{

	/*implement pikmin horde
	 * whenever Pikmin is rendered active, add to horde
	 * need to keep track of all pikmin in field to limit their number
	 * horde moves as a unit
	 */
	//Olimar olimar;
	static double stageWidth = 1200; 
	static double stageHeight = 625;
	int scale = 2;
	double moveSpeed;
	PikminHorde horde;
    Scene scene;
    double tolerance = 10;
    int numPik = 0;
    boolean pikDir[] = new boolean[4];
    ImageView background;
    double backgroundScrollSpeed = 0.5;

    
    Background() {
    	super();
        setStyle("-fx-border-color: black"); 
        
       // olimar = new Olimar();
       // pikmin = new Pikmin();
        horde = new PikminHorde();
        background = new ImageView(new Image("file:background3.png"));
        background.relocate(-(background.getImage().getWidth()/2.0), -(background.getImage().getHeight()/2.0));
        
        horde.getChildren().add(background);
        background.toBack();
       // background.setViewport(new Rectangle2D(0,0,100,100));
       
    
        getChildren().add(horde);   
        moveSpeed = horde.getOlimar().moveSpeed;
    }
    
    protected void checkBounds(GoodGuy guy) {
    	if(guy.x <= 0 ) {
    		guy.x = 0;
    		//guy.center.setX(guy.width/2);
    	}
    	else if (guy.x + guy.width >= getWidth()) {   
    		guy.x = getWidth() - guy.width;
    	}
    	if(guy.y < 0)
    		guy.y = 0;
    	else if (guy.y + guy.height > getHeight())
    		guy.y = getHeight() - guy.height;
    }
    void updateBackground(boolean north, boolean west, boolean south, boolean east) {
    	horde.getOlimar().update(north, west, south, east);
    	horde.getOlimar().updateImage();
    	checkBounds(horde.getOlimar());
    	horde.moveHorde2();

        if(horde.getOlimar().center.y > stageHeight/2.0 + 100 && horde.getOlimar().movingSouth()) {
        	
           	background.setLayoutY(background.getLayoutY() - moveSpeed);
           	if(background.getLayoutY() <= stageHeight-background.getImage().getHeight())
           		background.setLayoutY(stageHeight-background.getImage().getHeight());
           	else {
           		horde.getOlimar().y-=moveSpeed;
           	}
        }
        else if(horde.getOlimar().center.y < stageHeight/2.0 - 100 && horde.getOlimar().movingNorth()) {
        	background.setLayoutY(background.getLayoutY() + moveSpeed);
        	if(background.getLayoutY() >= 0)
           		background.setLayoutY(0);
           	else
           		horde.getOlimar().y+=moveSpeed;
        	
        }
        
        if(horde.getOlimar().center.x > stageWidth/2.0 + stageWidth/4.0 && horde.getOlimar().movingEast()) {
        	
               	background.setLayoutX(background.getLayoutX() - moveSpeed);
               	if(background.getLayoutX() <= stageWidth-background.getImage().getWidth())
               		background.setLayoutX(stageWidth-background.getImage().getWidth());
               	else {
               		horde.getOlimar().x-=moveSpeed;
               	}
            }
            else if(horde.getOlimar().center.x < stageWidth/2.0 - stageWidth/4.0 && horde.getOlimar().movingWest()) {
            	background.setLayoutX(background.getLayoutX() + moveSpeed);
            	if(background.getLayoutX() >= 0)
               		background.setLayoutX(0);
               	else
               		horde.getOlimar().x+=moveSpeed;
            	
            }
        
    }
    
    void spawnPikmin() {
	   //System.out.println("HEY");
	   horde.addPikmin(new Pikmin());
   }
   
   void removePikmin() {
	   horde.removePikmin();
   }
   
    PikminHorde getHorde() {
    	return horde;
    }
    
}
