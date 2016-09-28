import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Stroke;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
	JPanel panel;
	//button declare
	JButton blue_btn = new JButton();
	JButton black_btn = new JButton();
	JButton red_btn = new JButton();
	JButton white_btn = new JButton();
	JButton clear_btn = new JButton();
	JButton path_btn = new JButton();
	JButton rect_btn = new JButton();
	JButton line_btn = new JButton();
	JButton undo_btn = new JButton();
	JButton redo_btn = new JButton();
	JButton eraser_btn = new JButton();	
	JButton increaseStroke_btn = new JButton();
	JButton decreaseStroke_btn = new JButton();    
	Color button_color = new Color(102,204,255);
	
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

		// add black button
		black_btn.setText("Black");
		black_btn.setBackground(button_color);
		black_btn.setBounds(512, 10, 72, 30);
		black_btn.addActionListener(this);
		this.add(black_btn);

		// add blue button
		blue_btn.setText("Blue");
		blue_btn.setBackground(button_color);
		blue_btn.setBounds(512, 50, 72, 30);
		blue_btn.addActionListener(this);
		this.add(blue_btn);

		// add red button
		red_btn.setText("Red");
		red_btn.setBackground(button_color);
		red_btn.setBounds(512, 90, 72, 30);
		red_btn.addActionListener(this);
		this.add(red_btn);

		// add path button
		path_btn.setText("Path");
		path_btn.setBackground(button_color);
		path_btn.setBounds(512, 140, 72, 30);
		path_btn.addActionListener(this);
		this.add(path_btn);

		// add rect button
		rect_btn.setText("Rect");
		rect_btn.setBackground(button_color);
		rect_btn.setBounds(512, 180, 72, 30);
		rect_btn.addActionListener(this);
		this.add(rect_btn);
		
		// add line button
		line_btn.setText("Line");
		line_btn.setBackground(button_color);
		line_btn.setBounds(512, 220, 72, 30);
		line_btn.addActionListener(this);
		this.add(line_btn);
		
		// add increase stroke button
		increaseStroke_btn.setText("+");
		increaseStroke_btn.setBackground(button_color);
		increaseStroke_btn.setBounds(550, 272, 41, 30);
		increaseStroke_btn.addActionListener(this);
		this.add(increaseStroke_btn);
		
		// add increase stroke button
		decreaseStroke_btn.setText("-");
		decreaseStroke_btn.setBackground(button_color);
		decreaseStroke_btn.setBounds(505, 272, 41, 30);
		decreaseStroke_btn.addActionListener(this);
		this.add(decreaseStroke_btn);
		
		// add eraser button
		eraser_btn.setText("Eraser");
		eraser_btn.setBackground(button_color);
		eraser_btn.setBounds(512, 312, 72, 30);
		eraser_btn.addActionListener(this);
		this.add(eraser_btn);
		
		// add redo button
		redo_btn.setText("Redo");
		redo_btn.setBackground(button_color);
		redo_btn.setBounds(512, 352, 72, 30);
		redo_btn.addActionListener(this);
		this.add(redo_btn);
		
		// add undo button
		undo_btn.setText("Undo");
		undo_btn.setBackground(button_color);
		undo_btn.setBounds(512, 392, 72, 30);
		undo_btn.addActionListener(this);
		this.add(undo_btn);
		
		// add clear button
		clear_btn.setText("Clear");
		clear_btn.setBackground(button_color);
		clear_btn.setBounds(512, 432, 72, 30);
		clear_btn.addActionListener(this);
		this.add(clear_btn);

		this.add(panel); //add panel to frame
		Mousemotionlistener mouse_listener = new Mousemotionlistener(this); //add mouse listener on frame
		this.addMouseMotionListener(mouse_listener);
		this.addMouseListener(mouse_listener);
		this.setVisible(true); //open the windows
	}
	 
	Shape_button shape_button = Shape_button.Path;
	Shape remove_list;
	Color shape_color;
	double line_stroke = 1.0;
	boolean initEraser = true;
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<buttonList.size();i++){
			JButton button = buttonList.get(i);
			button.setBackground(button_color);
		}
		//blue button
		if (e.getSource() == blue_btn) {
			blue_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(blue_btn);
			shape_color = Color.BLUE;
		}
		//black button
		else if (e.getSource() == black_btn) {
			black_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(black_btn);
			shape_color = Color.BLACK;
		}
		//red button
		else if (e.getSource() == red_btn) {
			red_btn.setBackground(Color.LIGHT_GRAY);
			buttonList.add(red_btn);
			shape_color = Color.RED;
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
			initEraser = true;
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
