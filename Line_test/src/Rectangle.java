import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Rectangle extends Shape implements DrawColor{
	Point origin, destination;
	boolean fill;
	int x, y, width, height;
	
	public Rectangle() {
		origin = new Point();
		destination = new Point();
	}
	public Rectangle(Point origin, Point destination) {
		this.origin = origin;
		this.destination = destination;
	}
	public void setOrigin(Point point) {
		origin.set(point);
	}

	public void setDestination(Point point) {
		destination.set(point);
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void draw(Graphics g) {
		//g.drawRect(origin.x, origin.y, destination.x - origin.x, destination.y - origin.y);
		// g.drawRect(origin.x, origin.y, wide > 0 ? wide : -wide, height > 0 ? height : -height);1
		/*wide > 0 ? wide : -wide
		if(wide>0) wide;
		else -wide;*/
		
		this.x = Math.min(origin.x, destination.x);
		this.y = Math.min(origin.y, destination.y);
		this.width = Math.abs(origin.x - destination.x);
		this.height = Math.abs(origin.y - destination.y);
		
		initStroke(g); //set line stroke
		g.setColor(this.color); //set shape color
		g.drawRect(x, y, width, height); //draw
	}
}
