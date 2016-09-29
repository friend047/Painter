import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mousemotionlistener implements MouseMotionListener, MouseListener {
	myCanvas canvas;
	Line line;
	Rectangle rectangle;
	Path path;
	Point point;

	public Mousemotionlistener(myCanvas cs) {
		canvas = cs;
		point = new Point();
	}
	//save mouse point
	public Point getPoint(MouseEvent e) {
		point.set(e.getX(), e.getY());
		return point;
	}

	public boolean isBoundary(MouseEvent e) {
		return e.getX() > 500;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isBoundary(e)) return;
		point = getPoint(e); //use getPoint() to set point
		canvas.removeList.clear(); //clear the removeList 

		switch (canvas.shape_button) {
		case Path:
			// Path
			path = new Path();
			path.setStrokeSize(canvas.line_stroke); //set shape stroke
			path.setColor(canvas.shape_color);
			canvas.shapeList.add(path);
			break;

		case Rect:
			// Rectangle
			rectangle = new Rectangle();
			rectangle.setStrokeSize(canvas.line_stroke); //set shape stroke
			rectangle.setOrigin(point);
			rectangle.setColor(canvas.shape_color);
			canvas.shapeList.add(rectangle);
			break;

		case Line:
			// Line
			line = new Line();
			line.setStrokeSize(canvas.line_stroke); //set shape stroke
			line.setOrigin(point);
			line.setColor(canvas.shape_color);
			// canvas.shapeList.remove(canvas.shapeList.size()-1);
			canvas.shapeList.add(line);
			break;

		case Eraser:
			// Eraser
			path = new Path();
			path.setStrokeSize(canvas.line_stroke); //set shape stroke
			path.setColor(Color.WHITE);
			// canvas.shapeList.remove(canvas.shapeList.size()-1);
			canvas.shapeList.add(path);
			break;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (isBoundary(e))
			return;
		point = getPoint(e);
		switch (canvas.shape_button) {
		case Path:
			// Path
			path.addPoint(point);
			break;

		case Rect:
			// Rectangle
			rectangle.setDestination(point);
			canvas.shapeList.set(canvas.shapeList.size() - 1, rectangle);
			break;

		case Line:
			// Line
			line.setDestination(point);
			canvas.shapeList.set(canvas.shapeList.size() - 1, line);
			break;

		case Eraser:
			// Eraser
			path.addPoint(point);
			// canvas.shapeList.set(canvas.shapeList.size()-1, rectangle);
			break;
		}
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (isBoundary(e))
			return;
		/*
		 * switch(canvas.shape_button){ case Eraser: point = getPoint(e);
		 * rectangle = new Rectangle(); rectangle.setOrigin(point);
		 * point.set(point.x + 5 , point.y + 5);
		 * rectangle.setDestination(point); if(canvas.initEraser){
		 * canvas.shapeList.add(rectangle); canvas.initEraser = false; }else{
		 * canvas.shapeList.set(canvas.shapeList.size()-1, rectangle); }
		 * canvas.repaint(); break; }
		 */
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
