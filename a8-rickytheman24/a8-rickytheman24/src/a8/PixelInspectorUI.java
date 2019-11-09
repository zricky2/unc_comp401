package a8;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JButton copy;
	private boolean clicked;
	private double redSlider;
	private double greenSlider;
	private double blueSlider;
	private boolean sliderChange;
	
	
	public PixelInspectorUI() {
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
		copy = new JButton("Copy Pixel Value");
		
		clicked = false;
		
		setLayout(new GridLayout(3,2));
		add(x_label);
		add(y_label);
		add(pixel_info);
		add(copy);
		
	}
	
	
	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getGreen(), p.getBlue()));		
	}
	
	
	public void setCopy(Pixel p) {
		copy.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    	copy.setText("Copied Pixel Value " + Math.round(p.getRed() * 100.0) /100.0 + ", " 
		    			 + Math.round(p.getGreen()* 100.0) /100.0 + ", " + Math.round(p.getBlue()* 100.0) /100.0);
		    			redSlider = Math.round(p.getRed() * 100.0) /100.0;
		    			greenSlider = Math.round(p.getGreen()* 100.0) /100.0;
		    			blueSlider = Math.round(p.getBlue()* 100.0) /100.0;
		    }  
		    });  
	}
	
	public double getRedSlider() {
		return  redSlider;
	}
	public double getGreenSlider() {	
		return greenSlider;
	}
	public double getBlueSlider() {
		return blueSlider; 
	}
	public boolean getClicked() {
		return clicked;
	}
}
