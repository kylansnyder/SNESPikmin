package game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

public class Events extends Scene{
	Olimar olimar;
	PikminHorde horde;
	Background background;
	boolean wasd[] = new boolean[4]; 
	boolean pikDir[] = new boolean[4];
	int scale = 2;
	//Pikmin pikmen[];
    Scene scene;
    private Timeline timeline;
    double tolerance = 10;
    int numPik = 100;
    KeyFrame frame;
        
	Events(Background background) {
		super(background);
        setRoot(background);
		this.background = background;
		horde = background.getHorde();
		olimar = horde.getOlimar();
		
		timeline = new Timeline();
		frame = new KeyFrame(Duration.seconds(1.0/60.0), 
				event -> {
					handle();
					update();
					//collision();
				});
	    
	    timeline.getKeyFrames().add(frame);
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
	}
	
	private void handle() { //handles of user input events
    	//System.out.println(olimar.getX() + olimar.getFitWidth());
    	 this.setOnKeyPressed(		
 	    		event -> { 	    			
 	    			switch (event.getCode()) {
 	    			
 	    			case W:
 	    				wasd[0] = true;
 	    				break;
 	    			case A:
 	    				wasd[1] = true;
 	    				break;
 	    			case S:
 	    				wasd[2] = true;
 	    				break;
 	    			case D:
 	    				wasd[3] = true;
 	    				break;
 	    			case P:
 	    				background.spawnPikmin();
 	    				break;
 	    			case O:
 	    				background.removePikmin();
 	    				break;
					default:
						break;
 	    			}
 	    			olimar.moving = true;
 	    		}
     		);
    	 
         this.setOnKeyReleased(
         		event -> {
         			switch (event.getCode()) {
 	    			case W:
 	    				wasd[0] = false;
 	    				break;
 	    			case A:
 	    				wasd[1] = false;
 	    				break;
 	    			case S:
 	    				wasd[2] = false;
 	    				break;
 	    			case D:
 	    				wasd[3] = false;
 	    				break;
 	    			default: 
 	    				break;
         			}
         		}
         	);
    }
	
	private void update() {
    	background.updateBackground(wasd[0], wasd[1], wasd[2], wasd[3]);
    	
    	/*for(int i = 0; i < pikmen.length; i++) {
    		checkPikmin(pikmen[i]);
    		//System.out.println(pikDir[0]+" " +pikDir[1]+" "+ pikDir[2] + " "+pikDir[3]);
    		pikmen[i].update(pikDir[0], pikDir[1], pikDir[2],pikDir[3]);
    		olimar.collision(pikmen[i]);
    		for(int j = 0; j < pikmen.length; j++) {
    			pikmen[i].collision(pikmen[j]);
    		}
    	}*/
    	//checkPikmin(pikmin);
		//pikmin.update(pikDir[0], pikDir[1], pikDir[2],pikDir[3]);    
	//	moveIfCollision(pikmin);
    	//System.out.println("Center: (" + pikmin.getCenter().getX() + ", "+pikmin.getCenter().getY() + ")");

    }
}
