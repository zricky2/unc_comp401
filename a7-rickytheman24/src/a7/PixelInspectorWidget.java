package a7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorWidget extends JPanel implements MouseListener {

	private PictureView picture_view;
	private JPanel left_panel;
	private JLabel labelX;
	private JLabel labelY;
	private JLabel labelRed;
	private JLabel labelGreen;
	private JLabel labelBlue;
	private JLabel labelBrightness;

	public PixelInspectorWidget(Picture picture) {
		setLayout(new BorderLayout());

		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);

		JLabel title_label = new JLabel(picture.getCaption());
		add(title_label, BorderLayout.SOUTH);

		// create a sub-panel to the left
		JPanel left_panel = new JPanel(new GridLayout(6, 1));
		left_panel.setPreferredSize(new Dimension(150, 100));

		labelX = new JLabel("X: ");
		left_panel.add(labelX);
		labelY = new JLabel("Y: ");
		left_panel.add(labelY);
		labelRed = new JLabel("Red: ");
		left_panel.add(labelRed);
		labelGreen = new JLabel("Green: ");
		left_panel.add(labelGreen);
		labelBlue = new JLabel("Blue: ");
		left_panel.add(labelBlue);
		labelBrightness = new JLabel("Brightness: ");
		left_panel.add(labelBrightness);

		add(left_panel, BorderLayout.WEST);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		labelX.setText("X: " + e.getX());
		labelY.setText("Y: " + e.getY());
		labelRed.setText("Red: "
				+ Math.round((picture_view.getPicture().getPixel(e.getX(), e.getY()).getRed()) * 100.0) / 100.0);
		labelGreen.setText("Green: "
				+ Math.round((picture_view.getPicture().getPixel(e.getX(), e.getY()).getGreen()) * 100.0) / 100.0);
		labelBlue.setText("Blue: "
				+ Math.round((picture_view.getPicture().getPixel(e.getX(), e.getY()).getBlue()) * 100.0) / 100.0);
		labelBrightness.setText("Brightnes: "
				+ Math.round((picture_view.getPicture().getPixel(e.getX(), e.getY()).getIntensity()) * 100.0) / 100.0);
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

}
