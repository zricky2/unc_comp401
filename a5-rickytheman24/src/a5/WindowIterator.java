package a5;

import java.util.Iterator;

//The WindowIterator should produce a sequence of SubPicture objects 
//as if you were sliding a "window" that was window_width wide 
//and window_height tall across the picture starting from the 
//upper left corner and proceeding from left to right and top to 
//bottom until the window hits the lower right corner.

public class WindowIterator implements Iterator<SubPicture> {

	private Picture source;
	private int window_width;
	private int window_height;

	public WindowIterator(Picture source, int window_width, int window_height) {
		if (source == null) {
			throw new IllegalArgumentException("source should not be null");
		}
		if (window_width <= 0 || window_width > source.getWidth()) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		if (window_width <= 0 || window_height > source.getHeight()) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		this.source = source;
		this.window_width = window_width;
		this.window_height = window_height;
	}

	int point_x = 0;
	int point_y = 0;

	@Override
	public boolean hasNext() {
		return (point_y + window_height <= source.getHeight());
	}

	@Override
	public SubPicture next() {
		if (!this.hasNext()) {
			throw new IllegalArgumentException("No Windows left");
		}
		SubPicture nextWindow = source.extract(point_x, point_y, window_width, window_height);
		if (point_x + window_width > source.getWidth() - 1) {
			point_x = 0;
			point_y += 1;
		} else {
			point_x += 1;
		}
		return nextWindow;
	}

}
