package game;

import javafx.scene.image.Image;
import utilities.Direction;

public class Olimar extends GoodGuy {	

	private final static Image Front[] = {new Image("file:Olimar/FrontS.png"), 
    		new Image("file:Olimar/FrontR.png"), 
    		new Image("file:Olimar/FrontL.png")};
    private final static Image Back[] = {new Image("file:Olimar/BackS.png"),
    		new Image("file:Olimar/BackR.png"),
    		new Image("file:Olimar/BackL.png")};
    private final static Image Right[] = {new Image("file:Olimar/RightS.png"),
    		new Image("file:Olimar/RightR.png"),
    		new Image("file:Olimar/RightL.png")};
    private final static Image Left[] = {new Image("file:Olimar/LeftS.png"), 
    		new Image("file:Olimar/LeftR.png"),
    		new Image("file:Olimar/LeftL.png")};
    
    int stillRate = 10;
    int moveRate = 10;
    
    Olimar() {
    	super();
    	height = 40 * scale;
        width = 23 * scale;
        moveSpeed = 2.5;
    	Front[0].isPreserveRatio(); //Don't remember what this does
    	setImage(Front[0]);
    	setFitWidth(width);
    	setFitHeight(height);
    	x = (500);
    	y = (250);
    	setX(x);
    	setY(y);
    	center.x = (x + width / 2.0);
    	center.y = (y + height / 2.0);
    	moving = false;
    	frameCount = 0;
    	dir = Direction.South;
    }
    
    public void walkSouth() {
    	if(frameCount < moveRate) {
    		setImage(Front[1]);
    		frameCount++;
        }
    		
        else if (frameCount < 2 * moveRate) {
    		setImage(Front[0]);
    		frameCount++;
        }
    		
        else if (frameCount < 3 * moveRate) {
    		setImage(Front[2]);
    		frameCount++;
        }
        else if (frameCount < 4 * moveRate) {
    		setImage(Front[0]);
    		frameCount++;
        }
        else {
        	frameCount = 0;
        }
    }
    
    public void walkEast() {
    	if(frameCount < moveRate) {  
    		setImage(Right[1]);
    		frameCount++;
        }
    		
        else if (frameCount < 2 * moveRate) {
    		setImage(Right[0]);
    		frameCount++;
        }
    		
        else if (frameCount < 3 * moveRate) {
    		setImage(Right[2]);
    		frameCount++;
        }
        else if (frameCount < 4 * moveRate) {
    		setImage(Right[0]);
    		frameCount++;
        }
        else {
        	frameCount = 0;
        }
    }
    
    public void walkWest() {
	    if(frameCount < moveRate) {  
	    	
			setImage(Left[1]);
			frameCount++;
	    }
			
	    else if (frameCount < 2 * moveRate) {
			setImage(Left[0]);
			frameCount++;
	    }
			
	    else if (frameCount < 3 * moveRate) {
			setImage(Left[2]);
			frameCount++;
	    }
	    else if (frameCount < 4 * moveRate) {
			setImage(Left[0]);
			frameCount++;
	    }
	    else {
	    	frameCount = 0;
	    }
    		
    }
    public void walkNorth() {
    	if(frameCount < moveRate) {  
    		setImage(Back[1]);
    		frameCount++;
        }
        else if (frameCount < 2 * moveRate) {
    		setImage(Back[0]);
    		frameCount++;
        }
        else if (frameCount < 3 * moveRate) {
    		setImage(Back[2]);
    		frameCount++;
        }
        else if (frameCount < 4 * moveRate) {
    		setImage(Back[0]);
    		frameCount++;
        }
        else {
        	frameCount = 0;
        }
    }
    
    void updateImage() {
    	if(!moving)
    		frameCount = 0;
    	switch(dir) {
    		case North:
    			if(moving)
    				walkNorth();
    			else
    				setImage(Back[0]);
    			break;
    			
    		case South:
    			if(moving)
    				walkSouth();
    			else
    				setImage(Front[0]);
    			break;
    		case East:
    			if(moving)
    				walkEast();
    			else
    				setImage(Right[0]);
    			break;
    			
    		case West:
    			if(moving)
    				walkWest();
    			else
    				setImage(Left[0]);
    			break;
    			
    		default:
    			break;
    	}
    }

    public boolean movingNorth() {
    	return moving && (dir == Direction.North || secondDir == Direction.NorthWest || secondDir == Direction.NorthEast);
    }
    
    public boolean movingSouth() {
    	return moving && (dir == Direction.South || secondDir == Direction.SouthWest || secondDir == Direction.SouthEast);
    }
    
    public boolean movingEast() {
    	return moving && (dir == Direction.East || secondDir == Direction.SouthEast || secondDir == Direction.NorthEast);
    }
    
    public boolean movingWest() {
    	return moving && (dir == Direction.West || secondDir == Direction.SouthWest || secondDir == Direction.NorthWest);
    }
    
    void stillAnimation() { //not used yet 
		if(frameCount < stillRate) {
    		setImage(Front[0]);
    		frameCount++;
        }
        else if (frameCount < 2 * stillRate) {
    		setImage(Front[4]);
    		frameCount++;
        }
        else {
        	frameCount = 0;
        }
	}
}
