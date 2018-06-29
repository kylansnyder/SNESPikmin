package game;
import javafx.scene.image.ImageView;
import utilities.Direction;
import utilities.Point;

public abstract class GoodGuy extends ImageView {
	
    protected static final double scale =  2;
  //  protected  Stage stage; //could potentially handle this stuff in background
    protected double width;
    protected double height;
    protected double moveSpeed;
    protected Direction dir, secondDir;
    protected double x, y;
    protected boolean moving;
    protected int frameCount;
    protected Point center;
    boolean movingX, movingY;    
	protected GoodGuy() {//, double width, double height) {
		super();
		center = new Point();
		//this.stage = stage;
	}
	
	public void update(boolean north, boolean west, boolean south, boolean east) {
			if ((north && south) || (west && east)) {
				moving = false;
			}
			
			else if (north || south || west || east) {
	    		moving = true;
	    	}
	    	else {
	    		moving = false;
	    	}
			if(moving) {
		    	if(north) {
		    		y -= moveSpeed;
		    		if(!west && !east) {
		    			dir = Direction.North;
		    			secondDir = Direction.Still;
		    		}
		    		else if(west)
		    			secondDir = Direction.NorthWest;
		    		else if(east)
		    			secondDir = Direction.NorthEast;
		    	}
		    	if(west) {
		    		x -= moveSpeed;
		    		if(!north && !south) {
		    			dir = Direction.West;
		    			secondDir = Direction.Still;
		    		}
		    		else if(north)
		    			secondDir = Direction.NorthWest;
		    		else if(south)
		    			secondDir = Direction.SouthWest;
		    	}
		    	if(south) {
		    		y +=moveSpeed;
		    		if (!west && !east) {
		    			dir = Direction.South;
		    			secondDir = Direction.Still;
		    		}
		    		else if(west)
		    			secondDir = Direction.SouthWest;
		    		else if(east)
		    			secondDir = Direction.SouthEast;
		    	}
		    	if(east) {
		    		x += moveSpeed;
		    		if(!north && !south) {
		    			dir = Direction.East;
		    			secondDir = Direction.Still;
		    		}
		    		else if(north)
		    			secondDir = Direction.NorthEast;
		    		else if(south)
		    			secondDir = Direction.SouthEast;
		    		
		    	}
			}
			center.x = (x + width / 2);
			center.y = (y + height / 2);
	    	setX(x);
	    	setY(y);
	    }
	
	
	double getDistance(GoodGuy obj) {
		
		return this.center.distance(obj.center);
				//Math.sqrt(Math.pow(obj.center.getX() - this.center.getX(), 2) + Math.pow(obj.center.getY() - this.center.getY(), 2));

	}
	
	public boolean collision(GoodGuy obj) {
		if(obj == null)
			return false;
		double distance = getDistance(obj);
		if(distance < (width + obj.width) /2) {
			return true;
		}
		return false;
	}
	
	
	
	Direction getDirection() {
    	return dir;
    }
	
	void setInFront(GoodGuy guy) {
		if(getY() + (getFitHeight()) < guy.getY() + guy.getFitHeight()) {
    		toBack(); //Need to tweek but works for Olimar mostly
    	}
    	else {
    		toFront();
    	}
	}
	
	void setCenterX(double x) {
		center.x = x;
		this.x = x - width / 2;
		setX(this.x);
	}
	
	void setCenterY(double y) {
		center.y = y;
		this.y = y - width / 2;
		setY(this.y);
	}
	
	
	/*boolean getMoving() {
		return moving;
	}
	
	Point getCenter() {
		return center;
	}*/
}


