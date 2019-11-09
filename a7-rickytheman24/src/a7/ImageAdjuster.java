package a7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ImageAdjuster {
	public static void main(String[] args) throws IOException {
		Picture p = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp-in-namibia.jpg");
		p.setCaption("");
		ImageAdjusterWidget simple_widget = new ImageAdjusterWidget(p);

		// create and set up the window
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Image Adjuster");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create the panel
		JPanel panel = new JPanel(new BorderLayout());
		
		// add the sub-panel and widget to the main panel
		
		panel.add(simple_widget, BorderLayout.CENTER);
		
		

		// add the panel to the frame and show the frame

		main_frame.setContentPane(panel);
		main_frame.pack();
		main_frame.setVisible(true);
	}
}
