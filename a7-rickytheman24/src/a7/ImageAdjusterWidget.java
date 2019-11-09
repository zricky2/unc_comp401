package a7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjusterWidget extends JPanel implements ChangeListener {
	private PictureView picture_view;
	private JPanel bottom_panel;
	private JLabel labelBlur;
	private JSlider blurSlider;
	private JPanel blurPanel;
	private JLabel labelSaturation;
	private JSlider saturationSlider;
	private JPanel saturationPanel;
	private JLabel labelBrightness;
	private JSlider brightnessSlider;
	private JPanel brightnessPanel;
	private Picture pictureCopy;

	public ImageAdjusterWidget(Picture picture) {
		setLayout(new BorderLayout());

		picture_view = new PictureView(picture.createObservable());
		// copy of the original picture
		pictureCopy = picture_view.getPicture();

		add(picture_view, BorderLayout.CENTER);

		JLabel title_label = new JLabel(picture.getCaption());
		add(title_label, BorderLayout.SOUTH);

		// create a sub-panel to the left
		bottom_panel = new JPanel(new GridLayout(3, 1));
		bottom_panel.setPreferredSize(new Dimension(150, 200));
		// create labels and sliders
		labelBlur = new JLabel("Blur: ");
		blurSlider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
		blurSlider.setMajorTickSpacing(1);
		blurSlider.setPaintTicks(true);
		blurSlider.setPaintLabels(true);

		blurSlider.addChangeListener(this);
		blurPanel = new JPanel(new BorderLayout());
		blurPanel.add(labelBlur, BorderLayout.WEST);
		blurPanel.add(blurSlider, BorderLayout.CENTER);
		bottom_panel.add(blurPanel);

		labelSaturation = new JLabel("Saturation: ");
		saturationSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		saturationSlider.setMajorTickSpacing(25);
		saturationSlider.setPaintTicks(true);
		saturationSlider.setPaintLabels(true);

		saturationSlider.addChangeListener(this);
		saturationPanel = new JPanel(new BorderLayout());
		saturationPanel.add(labelSaturation, BorderLayout.WEST);
		saturationPanel.add(saturationSlider, BorderLayout.CENTER);
		bottom_panel.add(saturationPanel);

		labelBrightness = new JLabel("Brightness: ");
		brightnessSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
		brightnessSlider.setMajorTickSpacing(25);
		brightnessSlider.setPaintTicks(true);
		brightnessSlider.setPaintLabels(true);

		brightnessSlider.addChangeListener(this);
		brightnessPanel = new JPanel(new BorderLayout());
		brightnessPanel.add(labelBrightness, BorderLayout.WEST);
		brightnessPanel.add(brightnessSlider, BorderLayout.CENTER);
		bottom_panel.add(brightnessPanel);
		// add panel to the top panel
		add(bottom_panel, BorderLayout.SOUTH);

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		picture_view.setPicture(observerHelper(pictureCopy).createObservable());

	}

	// helper methods
	private Picture blur(Picture p) {
		Pixel[][] newPicture = new Pixel[p.getWidth()][p.getHeight()];

		int minX;
		int maxX;
		int minY;
		int maxY;
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				int counter = 0;
				double redTotal = 0.0;
				double greenTotal = 0.0;
				double blueTotal = 0.0;

				if (x - blurSlider.getValue() < 0) {
					minX = 0;
				} else {
					minX = x - blurSlider.getValue();
				}

				if (x + blurSlider.getValue() > p.getWidth()) {
					maxX = p.getWidth() - 1;
				} else {
					maxX = x + blurSlider.getValue();
				}

				if (y - blurSlider.getValue() < 0) {
					minY = 0;
				} else {
					minY = y - blurSlider.getValue();
				}

				if (y + blurSlider.getValue() > p.getWidth()) {
					maxY = p.getHeight() - 1;
				} else {
					maxY = y + blurSlider.getValue();
				}

				counter = 0;
				for (int i = minX; i <= maxX; i++) {
					for (int j = minY; j <= maxY; j++) {
						if (i != x && j != y) {
							try {
								redTotal += p.getPixel(i, j).getRed();
								greenTotal += p.getPixel(i, j).getGreen();
								blueTotal += p.getPixel(i, j).getBlue();
								counter++;
							} catch (RuntimeException e) {

							}
						}
					}
				}

				double redAverage = 0.0;
				double greenAverage = 0.0;
				double blueAverage = 0.0;

				if (counter != 0) {
					redAverage = redTotal / counter;
					greenAverage = greenTotal / counter;
					blueAverage = blueTotal / counter;
				} else {
					redAverage = p.getPixel(x, y).getRed();
					greenAverage = p.getPixel(x, y).getGreen();
					blueAverage = p.getPixel(x, y).getBlue();
				}

				newPicture[x][y] = new ColorPixel(redAverage, greenAverage, blueAverage);

			}
		}
		Picture new_picture = new ImmutablePixelArrayPicture(newPicture, "");
		return new_picture;
	}

	private Picture brighten(Picture p) {
		Pixel[][] newPicture = new Pixel[p.getWidth()][p.getHeight()];
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				if (brightnessSlider.getValue() < 0) {
					newPicture[x][y] = p.getPixel(x, y).darken(Math.abs(brightnessSlider.getValue() / 100.0));
				} else if (brightnessSlider.getValue() > 0) {
					newPicture[x][y] = p.getPixel(x, y).lighten(Math.abs(brightnessSlider.getValue() / 100.0));
				} else {
					newPicture[x][y] = p.getPixel(x, y);
				}
			}
		}
		Picture new_picture = new ImmutablePixelArrayPicture(newPicture, "");
		return new_picture;
	}

	private Picture saturate(Picture p) {
		Pixel[][] newPicture = new Pixel[p.getWidth()][p.getHeight()];
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				newPicture[x][y] = p.getPixel(x, y);
			}
		}
		double newRed;
		double newGreen;
		double newBlue;
		int f = saturationSlider.getValue();

		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				if (saturationSlider.getValue() < 0) {
					newRed = newPicture[x][y].getRed() * (1.0 + (f / 100.0))
							- (newPicture[x][y].getIntensity() * f / 100.0);
					newGreen = newPicture[x][y].getGreen() * (1.0 + (f / 100.0))
							- (newPicture[x][y].getIntensity() * f / 100.0);
					newBlue = newPicture[x][y].getBlue() * (1.0 + (f / 100.0))
							- (newPicture[x][y].getIntensity() * f / 100.0);
					newPicture[x][y] = new ColorPixel(newRed, newGreen, newBlue);
				} else if (saturationSlider.getValue() > 0) {
					double lc;
					if (newPicture[x][y].getRed() >= newPicture[x][y].getGreen()
							&& newPicture[x][y].getRed() >= newPicture[x][y].getBlue()) {
						lc = newPicture[x][y].getRed();
					} else if (newPicture[x][y].getGreen() >= newPicture[x][y].getBlue()) {
						lc = newPicture[x][y].getGreen();
					} else {
						lc = newPicture[x][y].getBlue();
					}

					if (lc <= 0.01) {
						newRed = newPicture[x][y].getRed();
						newGreen = newPicture[x][y].getGreen();
						newBlue = newPicture[x][y].getBlue();
						newPicture[x][y] = new ColorPixel(newRed, newGreen, newBlue);
					} else {
						newRed = newPicture[x][y].getRed() * ((lc + ((1.0 - lc) * (f / 100.0))) / lc);
						newGreen = newPicture[x][y].getGreen() * ((lc + ((1.0 - lc) * (f / 100.0))) / lc);
						newBlue = newPicture[x][y].getBlue() * ((lc + ((1.0 - lc) * (f / 100.0))) / lc);
						newPicture[x][y] = new ColorPixel(newRed, newGreen, newBlue);
					}
				} else {
					newPicture[x][y] = p.getPixel(x, y);
				}

			}
		}
		Picture new_picture = new ImmutablePixelArrayPicture(newPicture, "");
		return new_picture;
	}

	private Picture observerHelper(Picture p) {
		return saturate(brighten(blur(p).createObservable()).createObservable()).createObservable();
	}

}