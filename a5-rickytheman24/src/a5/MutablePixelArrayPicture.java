package a5;

import java.util.Iterator;

// Subclass. 
public class MutablePixelArrayPicture extends PixelArrayPicture implements Picture {
	
	public MutablePixelArrayPicture(Pixel[][] parray, String caption) {
		super(parray, caption);
	}

	// paints the pixel at
	// position (x,y) with the pixel value p.
	// A factor of 0.0 leaves the specified
	// pixel unchanged. A factor of 1.0 results in replacing
	// the value with the specified pixel value. Values between
	// 0.0 and 1.0 blend proportionally.
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}

		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("factor is out of bounds");
		}

		parray[x][y] = parray[x][y].blend(p, factor);

		return this;
	}


}
