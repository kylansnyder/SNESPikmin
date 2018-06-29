package game;
import java.util.concurrent.TimeUnit;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utilities.Direction;
import utilities.Point;

public class PikminHorde extends Group {
	int pikNum = 100;
	
	private Pikmin pikmin[] = new Pikmin[pikNum];
	boolean pikDir[] = new boolean[4];
	Direction dir;
	private Olimar olimar;
	Pikmin curr;
	Point center;
    boolean horizontal;
	Point pikPoints[] = new Point[pikNum];
	static int capacity, centerCap;
	double followDistance = 20;
	Rectangle rec1, rec2;
	double moveSpeed = 2;
	int count = 0;
	int rowSize = 5;
	Rectangle rec[] = new Rectangle[pikNum];

	
	PikminHorde() {
		super(); 
		horizontal = true;
		olimar = new Olimar();
		rec1 = new Rectangle(olimar.center.x, olimar.center.y, 5, 5);
		rec1.setFill(Color.BLUE);
		capacity = 0;
		dir = olimar.getDirection();
		getChildren().addAll(olimar);//, rec1);	
		
		centerCap = 0;
		//pikPoints[0] = new Point(olimar.center.x, olimar.center.y - 10);
		//center = pikPoints[0];
		//rec[0] = new Rectangle(pikPoints[0].x, pikPoints[0].y,5,5);
		//rec[0].setFill(Color.CYAN);
		//getChildren().add(rec[0]);
		
		
		for(int i = 0; i < 9; ++i) {
			pikPoints[i] = new Point(olimar.center.x + 80 - (20 * i), olimar.center.y - followDistance);
			rec[i] = new Rectangle(pikPoints[i].x, pikPoints[i].y, 5, 5);
			//getChildren().add(rec[i]);
		}
		
		center = new Point(pikPoints[4].x, pikPoints[4].y);
		rec2 = new Rectangle(pikPoints[4].x, pikPoints[4].y, 5, 5);
		rec2.setFill(Color.GOLD);
		//getChildren().add(rec2);
		
		
	}
	
	void addPikmin(Pikmin pik) {
		if (!isFull()) {
			if(capacity >= centerCap + 10) {
				centerCap += 10;
				switch(olimar.dir) {
				case North:
					center.setPoint(center.x, center.y + followDistance / 2 + pikmin[0].width / 2);
					break;
				case West:
					center.setPoint(center.x + 10 + pikmin[0].width / 2, center.y);
					break;
				case South:
					center.setPoint(center.x, center.y - 10 - pikmin[0].width / 2);
					break;
				case East:
					center.setPoint(center.x - 10 - pikmin[0].width / 2, center.y);
					break;
				default:
					break;
				}
				rec2.setX(center.x);
				rec2.setY(center.y);
				//center.setPoint(center.x , center.y );
			}
			
			//System.out.println(capacity);
			getChildren().add(pik);
			//pik.toBack();
			if(capacity < 9) {
				if(capacity == 0) {
					pik.pursue = pikPoints[4];
				}
				else if(capacity % 2 == 0) {
					for (int i = capacity - 1; i > 0; --i) {
						pikmin[i].pursue = pikmin[i - 1].pursue;
					}
					pikmin[0].pursue = pikPoints[4 - capacity/2];
					pik.pursue = pikPoints[capacity/2 + 4];
				}
				else {
					pik.pursue = pikPoints[capacity/2 + 5];
				}
			}
			
			else
				pik.follows = pikmin[capacity - 9];
			pikmin[capacity] = pik;
			capacity++; 
			//changeShape();
		}
	}
	
	void removePikmin() { //Probably needs to be updated
		if(!isEmpty()) {
			if (capacity <= centerCap){
				centerCap -= 10;
				switch(olimar.dir) {
				case North:
					center.setPoint(center.x, center.y - 10 - pikmin[0].width / 2);
					break;
				case West:
					center.setPoint(center.x - 10 - pikmin[0].width / 2, center.y);
					break;
				case South:
					center.setPoint(center.x, center.y + 10 + pikmin[0].width / 2);
					break;
				case East:
					center.setPoint(center.x + 10 + pikmin[0].width / 2, center.y);
					break;
				}
				rec2.setX(center.x);
				rec2.setY(center.y);
			}
			capacity--;
			getChildren().remove(pikmin[capacity]);
			pikmin[capacity] = null;
		}
	}
	
	void changeShape() { //can use to set center when number of pikmin crosses certain thresholds 
		if(capacity == 1) {
			
		}
	}
	
	void moveHorde() {
		dir = olimar.dir;
		rec1.setX(olimar.center.x);
		rec1.setY(olimar.center.y);
		

		for(int i = 0; i < 9; ++i) {
			pikPoints[i].dir = olimar.dir;
		}

		if (olimar.moving) {
		//	int j = 1, k = 1;
			switch(olimar.dir) {
			case North:
				for(int i = 0; i < 9; ++i) {
					pikPoints[i].setPoint(olimar.center.x + 80 - (20 * i), olimar.center.y + followDistance);
					rec[i].setX(pikPoints[i].x);
			    	rec[i].setY(pikPoints[i].y);
				}
			    break;
			    
			case West:
				for(int i = 0; i < 9; ++i) {
					pikPoints[i].setPoint(olimar.center.x + followDistance, olimar.center.y + 80 - (20 * i));
					rec[i].setX(pikPoints[i].x);
			    	rec[i].setY(pikPoints[i].y);
				}
				break;
				
			case South:
				for(int i = 0; i < 9; ++i) {
					pikPoints[i].setPoint(olimar.center.x + 80 - (20 * i), olimar.center.y - followDistance);
					rec[i].setX(pikPoints[i].x);
			    	rec[i].setY(pikPoints[i].y);
				}
			    break;
			    
			case East:
				for(int i = 0; i < 9; ++i) {
					pikPoints[i].setPoint(olimar.center.x - followDistance, olimar.center.y + 80 - (20 * i));
					rec[i].setX(pikPoints[i].x);
			    	rec[i].setY(pikPoints[i].y);
				}
			    break;
			}
		}
		
		
		rec2.setX(center.x);
		rec2.setY(center.y);
		for(int i = 0; i < capacity; i++) {
				pikmin[i].movePikmin3();
		}
	}
	
	void moveHorde2() {
		if(center.distance(olimar.center) > 10 + (centerCap/10 * 30)) {
			switch(olimar.dir) {
			case North:
				center.setPoint(olimar.center.x, olimar.center.y  + (centerCap/10 * 30));
				for(int i = 0; i < 9; ++i) {
					pikPoints[i].setPoint(olimar.center.x + 80 - (20 * i), olimar.center.y + followDistance);
					rec[i].setX(pikPoints[i].x);
			    	rec[i].setY(pikPoints[i].y);
				}
				break;
				
			case West:
				center.setPoint(olimar.center.x + ( + (centerCap/10 * 30)) , olimar.center.y);
				for(int i = 0; i < 9; ++i) {
					pikPoints[i].setPoint(olimar.center.x + followDistance, olimar.center.y + 80 - (20 * i));
					rec[i].setX(pikPoints[i].x);
			    	rec[i].setY(pikPoints[i].y);
				}
				break;
				
			case South:
				center.setPoint(olimar.center.x, olimar.center.y - ( (centerCap/10 * 30)));
				for(int i = 0; i < 9; ++i) {
					pikPoints[i].setPoint(olimar.center.x + 80 - (20 * i), olimar.center.y - followDistance);
					rec[i].setX(pikPoints[i].x);
			    	rec[i].setY(pikPoints[i].y);
				}
				break;
			
			case East:
				center.setPoint(olimar.center.x - ( (centerCap/10 * 30)), olimar.center.y);
				for(int i = 0; i < 9; ++i) {
					pikPoints[i].setPoint(olimar.center.x - followDistance, olimar.center.y + 80 - (20 * i));
					rec[i].setX(pikPoints[i].x);
			    	rec[i].setY(pikPoints[i].y);
				}
				break;
			}
		}
		
		/*if(count < 100) {
			pikPoints[0].x++;
			pikPoints[0].y = olimar.center.y - Math.sqrt(Math.pow(50,2) - Math.pow(olimar.center.x - pikPoints[0].x,2));	
		}
		else if(count < 200) {
			pikPoints[0].x--;
			pikPoints[0].y = olimar.center.y + Math.sqrt(Math.pow(50,2) - Math.pow(olimar.center.x - pikPoints[0].x,2));
		}
		else {
			count = -1;
		}
		count++;
		rec[0].setX(pikPoints[0].x);
		rec[0].setY(pikPoints[0].y);*/
		
	
		for(int i = 0; i < 9; ++i) {
			pikPoints[i].dir = olimar.dir;
		}
		
		for(int i = 0; i < capacity; i++) {
			pikmin[i].movePikmin3();
		}
		
		rec2.setX(center.x);
		rec2.setY(center.y);
	}
	
	
	Olimar getOlimar() {
		return olimar;
	}
	
	boolean isFull() {
		return capacity == pikNum;
	}
	
	boolean isEmpty() {
		return capacity == 0;
	}
	
	void updateOverlap() {
		for(int i = 0; i < capacity; ++i) {
			for(int j = 0; j < capacity; ++j) {
				
			}	
		}
	}
	/*void findClosest() {	
	int closest = 0;
	for(int i = 0; i < capacity; i++) {
		if(olimar.getDistance(pikmin[closest]) > olimar.getDistance(pikmin[i])) { Difficult to implement, will focus later
			closest = i;
		}
	}
	pikmin[closest].setFollows(olimar);
}*/
}