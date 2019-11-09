package a5;

import java.util.Iterator;

// The zigzag class produces a Pixel iterator that iterates 
// over the pixels of a Picture in zigzag order starting at 0,0.
//Note that the first move is to go from the upper-left 
//corner across to the right if possible. The pattern will 
//work for any size picture (it need not be a perfect square). 
//A good way of looking at the pattern is to think of the picture 
//as a series of diagonals that are either "even" or "odd" where 
//the coordinate (0,0) is on an even diagonal. Generally, the 
//pattern traverses the pixels going up and to the right on even 
//diagonals and down and to the left on odd diagonals.

public class ZigZagIterator implements Iterator<Pixel> {

	private Picture source;

	public ZigZagIterator(Picture source) {
		if (source == null) {
			throw new IllegalArgumentException("source should not be null");
		}
		this.source = source;

	}

	int point_x = 0;
	int point_y = 0;

	@Override
	public boolean hasNext() {
		// figure out how to check
		return (point_x < source.getWidth() && point_y < source.getHeight());
	}

	@Override
	public Pixel next() {
		if (!this.hasNext()) {
			throw new IllegalArgumentException("No Pixels left");
		}
		Pixel nextPixel = source.getPixel(point_x, point_y);
		if ((point_x + point_y) % 2 == 0) {
			if (point_x == source.getWidth() - 1) {
				point_y++;
			} else if (point_y == 0) {
				point_x++;
			} else {
				point_x++;
				point_y--;
			}
		} else {
			if (point_y == source.getHeight() - 1) {
				point_x++;
			} else if (point_x == 0) {
				point_y++;
			} else {
				point_x--;
				point_y++;
			}
		}
		return nextPixel;
	}

}
