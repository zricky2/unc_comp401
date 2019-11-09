package a8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PaintBrushTool implements Tool {

	private PaintBrushToolUI ui;
	private ImageEditorModel model;
	private int brush_size = 5;
	private JPanel top_panel;
	
	private double red;
	private double green;
	private double blue;
	
	public PaintBrushTool(ImageEditorModel model) {
		this.model = model;
		ui = new PaintBrushToolUI();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		red = model.getCurrent().getPixel(e.getX(), e.getY()).getRed();
		green = model.getCurrent().getPixel(e.getX(), e.getY()).getGreen();
		blue = model.getCurrent().getPixel(e.getX(), e.getY()).getBlue();
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), brush_size);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), brush_size);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Paint Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}
	
	public boolean getSliderChange() {
		return ui.getSliderChange();
	}
	public void setSliderChange() {
		ui.setSliderChange();
	}

}
