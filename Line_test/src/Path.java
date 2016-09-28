import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

public class Path extends Shape implements DrawColor{
	public ArrayList<Point> points = new ArrayList<Point>();
	
	public Path() {
		
	}
	
	public void addPoint(Point point){
		Point origin = new Point();
		origin.set(point);
		points.add(origin);
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void draw(Graphics g) {
		//¥ÎdrawPoly
		/*int []xPoints = new int[points.size()];
		int []yPoints = new int[points.size()];
		for(int i = 0 ; i < points.size() ; i ++) {
			Point origin = points.get(i);
			xPoints[i] = origin.x;
			yPoints[i] = origin.y;
		}
		g.drawPolyline(xPoints, yPoints, points.size());*/
		
		initStroke(g);
		for(int i = 0 ; i < points.size()-1 ; i ++) 
		{
			 
			
			Point origin = points.get(i);
			Point destination = points.get(i+1);
			g.setColor(this.color);
			g.drawLine(origin.x, origin.y, destination.x, destination.y);
		}
	}
}
