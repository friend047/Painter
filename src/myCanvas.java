import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Stroke;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class myCanvas extends JFrame implements ActionListener// extends Canvas// //implements// MouseListener,// MouseMotionListener
{
	//test
	JPanel panel;
	//button declare
	JButton color_select_btn = new JButton();
	JButton clear_btn = new JButton();
	JButton path_btn = new JButton();
	JButton rect_btn = new JButton();
	JButton line_btn = new JButton();
	JButton undo_btn = new JButton();
	JButton redo_btn = new JButton();
	JButton eraser_btn = new JButton();	
	JButton increaseStroke_btn = new JButton();
	JButton decreaseStroke_btn = new JButton();    
	Color button_background = new Color(102,204,255);
	/*public void Print(String str) {
		System.out.println(str);
	}*/
	myCanvas() {
		// create frame
		this.setTitle("小畫家"); //set title
		this.setLayout(null);
		this.setSize(600, 500); //set frame size
		this.getContentPane().setBackground(Color.WHITE); //set frame white background
		this.setResizable(false); //can not adjust window size
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel(); //new a Panel
		panel.setBounds(500, 0, 94, 472); //set panel size
		
		// add select color button
		initbutton("Color", button_background, color_select_btn);
		color_select_btn.setBounds(512, 10, 72, 30);
		// add path button
		initbutton("Path", button_background, path_btn);
		path_btn.setBounds(512, 140, 72, 30);
		// add rect button
		initbutton("Rect", button_background, rect_btn);
		rect_btn.setBounds(512, 180, 72, 30);
		// add line button
		initbutton("Line", button_background, line_btn);
		line_btn.setBounds(512, 220, 72, 30);
		// add increase stroke button
		initbutton("+", button_background, increaseStroke_btn);
		increaseStroke_btn.setBounds(550, 272, 41, 30);
		// add increase stroke button
		initbutton("-", button_background, decreaseStroke_btn);
		decreaseStroke_btn.setBounds(505, 272, 41, 30);
		// add eraser button
		initbutton("Eraser", button_background, eraser_btn);
		eraser_btn.setBounds(512, 312, 72, 30);
		// add redo button
		initbutton("Redo", button_background, redo_btn);
		redo_btn.setBounds(512, 352, 72, 30);
		// add undo button
		initbutton("Undo", button_background, undo_btn);
		undo_btn.setBounds(512, 392, 72, 30);
		// add clear button
		initbutton("clear", button_background, clear_btn);
		clear_btn.setBounds(512, 432, 72, 30);
		
		this.add(panel); //add panel to frame
		Mousemotionlistener mouse_listener = new Mousemotionlistener(this); //add mouse listener on frame
		this.addMouseMotionListener(mouse_listener);
		this.addMouseListener(mouse_listener);
		this.setVisible(true); //open the windows
	}
	// initialize button
	public void initbutton(String button_name, Color button_background, JButton button){
		button.setText(button_name);
		button.setBackground(button_background);
		button.addActionListener(this);
		this.add(button);
	} 
	Shape_button shape_button = Shape_button.Path; //set the first draw shape
	Shape remove_list;
	Color shape_color;
	double line_stroke = 1.0; //line stroke
	boolean initEraser = true; 
	
	//button action listener
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<buttonList.size();i++){
			JButton button = buttonList.get(i);
			button.setBackground(button_background);
		}
		//color select button
		if (e.getSource() == color_select_btn) {
			Color color_selected = JColorChooser.showDialog(this,"Choose a color...", getBackground());
			color_select_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(color_select_btn);
			if(color_selected != null){
				shape_color = color_selected;
				repaint();
			}
		}
		//path button
		else if (e.getSource() == path_btn) {
			path_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(path_btn);
			shape_button = Shape_button.Path;
		}
		//rectangle button
		else if (e.getSource() == rect_btn) {
			rect_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(rect_btn);
			shape_button = Shape_button.Rect;
		}
		//line button
		else if (e.getSource() == line_btn) {
			line_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(line_btn);
			shape_button = Shape_button.Line;
		}
		
		//increase stroke button
		else if (e.getSource() == increaseStroke_btn) {
			increaseStroke_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(increaseStroke_btn);
			if(line_stroke<15){
				this.line_stroke = line_stroke+1;
			}
			else line_stroke = 15;
		}
		
		//decrease stroke button
		else if (e.getSource() == decreaseStroke_btn) {
			decreaseStroke_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(decreaseStroke_btn);
			if(line_stroke > 1)
			{
				this.line_stroke = line_stroke-1;
			}
			else line_stroke = 1;
		}
		
		//eraser button
		else if (e.getSource() == eraser_btn) {
			eraser_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(eraser_btn);
			shape_button = Shape_button.Eraser;
		}
		//redo button
		else if (e.getSource() == redo_btn) {
			redo_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(redo_btn);
			if(removeList.size() != 0){
				remove_list = removeList.get(removeList.size()-1);
				shapeList.add(remove_list);
				removeList.remove(removeList.size()-1);
				repaint();
			}
		}
		//undo button
		else if (e.getSource() == undo_btn) {
			undo_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(undo_btn);
			if(shapeList.size() != 0){
				remove_list = shapeList.get(shapeList.size()-1);
				removeList.add(remove_list);
				shapeList.remove(shapeList.size()-1);
				repaint();
			}
		}
		//clear button
		else if (e.getSource() == clear_btn) {
			clear_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(clear_btn);
			shapeList.clear();
			repaint();
		}
	}
	
	public ArrayList<Shape> shapeList = new ArrayList<Shape>();  //declare a shape list 
	public ArrayList<Shape> removeList = new ArrayList<Shape>(); //declare a remove list when undo
	public ArrayList<JButton> buttonList = new ArrayList<JButton>(); //declare a button list when undo
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		
		g.setColor(shape_color); //set selected color in rectangle
		g.fillRect(515, 80, 72, 60);
		g.setColor(Color.BLACK); //set rectangle frame
		g.drawRect(515, 80, 72, 60);
		
		for(int i=0; i<shapeList.size();i++){
			Shape shape = shapeList.get(i);
			shape.draw(g);
		}
		/*
		for (Shape shape : shapeList) {
			shape.draw(g);
		}*/
	}
}
//declare shape object to select
enum Shape_button {
	Path, Rect, Line, Clear, Eraser,
}
