package utilities;

public class Point {
	public double x, y;
	public Direction dir;
	
	public Point() {
		dir = Direction.South;
		x = 0;
		y = 0;
	}
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double distance(Point point) {
		return Math.sqrt(Math.pow((x - point.x), 2) + Math.pow((y - point.y), 2));
	}
	
	public double xDistance(Point point) {
		return (x - point.x);
	}
	
	public double yDistance(Point point) {
		return (y - point.y);
	}
}
