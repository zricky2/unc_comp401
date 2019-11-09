package a5;

import java.util.Iterator;

//SampleIterator creates a 
//Pixel iterator that produces Pixel objects from the picture. 
//The iterator begins with the specified initial pixel location 
//and then samples left to right the specified dx columns 
//and then top to bottom by the specified dy rows.

public class SampleIterator implements Iterator<Pixel> {

	private Picture source;
	private int init_x;
	private int init_y;
	private int dx;
	private int dy;

	public SampleIterator(Picture source, int init_x, int init_y, int dx, int dy) {
		if (source == null) {
			throw new IllegalArgumentException("source should not be null");
		}
		if (init_x < 0 || init_x > source.getWidth() - 1) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		if (init_y < 0 || init_y > source.getHeight() - 1) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		if (dx <= 0) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		if (dy <= 0) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		this.source = source;
		this.init_x = init_x;
		this.init_y = init_y;
		this.dx = dx;
		this.dy = dy;

		checkStart();

	}

	int point_x;
	int point_y;

	public void checkStart() {
		if (init_x != 0 || init_y != 0) {
			point_x = init_x;
			point_y = init_y;
		} else {
			point_x = 0;
			point_y = 0;
		}
	}

	@Override
	public boolean hasNext() {
		return (point_y <= source.getHeight() - 1);
	}

	@Override
	public Pixel next() {
		if (!this.hasNext()) {
			throw new IllegalArgumentException("No Pixel left");
		}
		Pixel nextPixel = source.getPixel(point_x, point_y);
		if (point_x + dx > source.getWidth() - 1) {
			point_x = this.init_x;
			point_y += dy;
		} else {
			point_x += dx;
		}
		return nextPixel;
	}

}
