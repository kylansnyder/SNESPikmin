package game;
import java.util.Random;

import javafx.scene.image.Image;
import utilities.Direction;
import utilities.Point;

public class Pikmin extends GoodGuy{
	boolean pikDir[] = new boolean[4];
	private final Image Front[] = {new Image("file:Pikmin/front0.png"), new Image("file:Pikmin/front0R.png")};
    boolean inactive;
	protected double primaryDistance = 16 * scale, secondDistance = 10 * scale;
	protected GoodGuy follows;//, avoids;
	protected Point pursue;
	
	Pikmin() {
		super();
		movingX = false; movingY = false;
		follows = this;
		pursue = center;
		Random rand = new Random();
		x =0;// rand.nextInt();//stage.getWidth() /2.0;
		y =0;// rand.nextInt();//stage.getHeight()/2.0;
		width = 15 * scale;
		height = 30 * scale;
	    moveSpeed = 2;//a(double)rand.nextInt(100) /100.0 + 1 ;
    	moving = false;
    	dir = Direction.South;
    	frameCount = 0;
    	setFitWidth(width);
    	setFitHeight(height);
    	setImage(Front[0]);
    	setX(x);
    	setY(y);
    	center.x = (x + width / 2.0);
    	center.y = (y + height / 2.0);
    	
    }
	
	Pikmin(String color) {
		super();
		follows = this;
		//avoids = null;
		Random rand = new Random();
		x =0;// rand.nextInt();//stage.getWidth() /2.0;
		y =0;// rand.nextInt();//stage.getHeight()/2.0;
		width = 15 * scale;
		height = 30 * scale;
	    moveSpeed = 2;//a(double)rand.nextInt(100) /100.0 + 1 ;
    	moving = false;
    	dir = Direction.South;
    	frameCount = 0;
    	setFitWidth(width);
    	setFitHeight(height);
    	if (color == "red") {
    		setImage(Front[1]);
    	}
    	
    	// rand.nextInt((int)stage.getWidth());
    	//rand.nextInt((int)stage.getHeight());
    	setX(x);
    	setY(y);
    	center.x = (x + width / 2.0);
    	center.y = (y + height / 2.0);
    }
	
	/*public double getDistance() {
		
		return distanceFromfollows;
	}*/
	
	public GoodGuy getFollows( ) {
		return follows;
	}
	
	public void setFollows(GoodGuy guy) {
		follows = guy;
	}
	
	
	
	void movePikmin3() {
		//moving = false;
		dir = pursue.dir;
		dir = follows.dir;
		if(pursue != this.center) {
			if (center.xDistance(pursue) > moveSpeed) { //move left
				x -= moveSpeed;
			}
			else if (center.xDistance(pursue) < 0) { //move right
				x += moveSpeed;
			}
			if (center.yDistance(pursue) > moveSpeed) { //move up
				y -= moveSpeed;
			}
			else if(center.yDistance(pursue) < 0 ) { //move down
				y += moveSpeed;
			}
			
		}
		else if (follows != this) {
			switch(follows.dir) {
			case North:
				if(Math.abs(y - (follows.y + primaryDistance)) < moveSpeed) {
					y = follows.y + primaryDistance;
				}
				else if(y > follows.y + primaryDistance) { //move up
					y -= moveSpeed;
				}
				else if(y < follows.y + primaryDistance) { //move down
					y += moveSpeed;
				}
				
				if(follows.movingX) {
					if(x > follows.x + secondDistance) { //move left
						if (Math.abs(x - follows.x - secondDistance) < moveSpeed)
							x = follows.x + secondDistance;
						else
							x -= moveSpeed;
					}
					else if(x < follows.x - secondDistance) { //move right
						if (Math.abs(x - follows.x + secondDistance) < moveSpeed)
							x = follows.x - secondDistance;
						else
							x += moveSpeed;
					}
					
				}
				else { //Not moving
					if (Math.abs(x - follows.x) < moveSpeed)
						x = follows.x;
					else if(x > follows.x) { //move left
						x -= moveSpeed;
					}
					else if(x < follows.x) { //move right
						x += moveSpeed;
					}
				}
				break;
				
			case West:
				if(Math.abs(x - (follows.x + primaryDistance)) < moveSpeed) {
					x = follows.x + primaryDistance;
				}
				else if(x > follows.x + primaryDistance) { 
					x -= moveSpeed;
				}
				else if(x < follows.x + primaryDistance) { 
					x += moveSpeed;
				}
				
				if(follows.movingY) {
					if(y > follows.y + secondDistance) {
						if (Math.abs(y - follows.y - secondDistance) < moveSpeed)
							y = follows.y + secondDistance;
						else
							y -= moveSpeed;
					}
					else if(y < follows.y - secondDistance) { 
						if (Math.abs(y - follows.y + secondDistance) < moveSpeed)
							y = follows.y - secondDistance;
						else
							y += moveSpeed;
					}
					
				}
				else { //Not moving
					if (Math.abs(y - follows.y) < moveSpeed)
						y = follows.y;
					else if(y > follows.y) { 
						y -= moveSpeed;
					}
					else if(y < follows.y) { 
						y += moveSpeed;
					}
				}
				break;
				
			case South:
				if(Math.abs(y - (follows.y - primaryDistance)) < moveSpeed) {
					y = follows.y - primaryDistance;
				}
				else if(y > follows.y - primaryDistance) { //move up
					y -= moveSpeed;
				}
				else if(y < follows.y - primaryDistance) { //move down
					y += moveSpeed;
				}
				
				if(follows.movingX) {
					if(x > follows.x + secondDistance) { //move left
						if (Math.abs(x - follows.x - secondDistance) < moveSpeed)
							x = follows.x + secondDistance;
						else
							x -= moveSpeed;
					}
					else if(x < follows.x - secondDistance) { //move right
						if (Math.abs(x - follows.x + secondDistance) < moveSpeed)
							x = follows.x - secondDistance;
						else
							x += moveSpeed;
					}
					
				}
				else { //Not moving
					if (Math.abs(x - follows.x) < moveSpeed)
						x = follows.x;
					else if(x > follows.x) { //move left
						x -= moveSpeed;
					}
					else if(x < follows.x) { //move right
						x += moveSpeed;
					}
				}
				break;
				
			case East:
				if(Math.abs(x - (follows.x - primaryDistance)) < moveSpeed) {
					x = follows.x - primaryDistance;
				}
				else if(x > follows.x - primaryDistance) { 
					x -= moveSpeed;
				}
				else if(x < follows.x - primaryDistance) { 
					x += moveSpeed;
				}
				
				if(follows.movingY) {
					if(y > follows.y + secondDistance) {
						if (Math.abs(y - follows.y - secondDistance) < moveSpeed)
							y = follows.y + secondDistance;
						else
							y -= moveSpeed;
					}
					else if(y < follows.y - secondDistance) { 
						if (Math.abs(y - follows.y + secondDistance) < moveSpeed)
							y = follows.y - secondDistance;
						else
							y += moveSpeed;
					}
					
				}
				else { //Not moving
					if (Math.abs(y - follows.y) < moveSpeed)
						y = follows.y;
					else if(y > follows.y) { 
						y -= moveSpeed;
					}
					else if(y < follows.y) { 
						y += moveSpeed;
					}
				}
				break;
			}
		}
		if(center.x != (x + width / 2.0)) {
			movingX = true;
		}
		else {
			movingX = false;
		}
		if(center.y != (y + height / 2.0)) {
			movingY = true;
		}
		else {
			movingY = false;
		}
		center.x = (x + width / 2.0);
    	center.y = (y + height / 2.0);
    	
    	setX(x);
    	setY(y);
	}
	
	
	
	void sideMove() {
		
	}
	
}
