import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public abstract class Shape {
	protected Color color;
	protected double line_stroke;
	public Shape() {
		
	}
	
	public void show() {
	}
	
	public void setStrokeSize(double size){
		this.line_stroke = size;
	}
	
	public void initStroke(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		Stroke stroke=new BasicStroke((float)line_stroke);//³]©w¼e«×
		g2d.setStroke(stroke);
	}
	public abstract void setColor(Color color);
	public abstract void draw(Graphics g);
}
