import java.awt.Graphics;
import java.awt.Color;

public class Line extends Shape implements DrawColor{
	Point origin, destination;
	
	public Line() {
		origin = new Point();
		destination = new Point();
	}
	public Line(Point origin, Point destination) {
		this.origin = new Point();
		this.destination = new Point();
	}

	public void setOrigin(Point point) {
		origin.set(point);
	}

	public void setDestination(Point point) {
		destination.set(point);
	}

	public void show() {
		System.out.println("Line:" + origin.x + "," + origin.y + ":" + destination.x + "," + destination.y);
	}
	
	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		initStroke(g);
		g.setColor(this.color);
		g.drawLine(origin.x, origin.y, destination.x, destination.y);
	}
	
}
