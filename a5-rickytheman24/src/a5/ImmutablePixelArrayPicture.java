package a5;

import java.util.Iterator;

//subclass
//Immutable Picture will return a new copy of itself

public class ImmutablePixelArrayPicture extends PixelArrayPicture implements Picture {

	public ImmutablePixelArrayPicture(Pixel[][] parray, String caption) {
		super(parray, caption);
	}

	// paint(int x, int y, Pixel p) paints the pixel at
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

		return (new MutablePixelArrayPicture(parray, getCaption())).paint(x, y, p, factor);
	}

	// paint(int ax, int ay, int bx, int by, Pixel p) paints the
	// rectangular region defined by the positions (ax, ay) and
	// (bx, by) with the specified pixel value with factor.
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		int min_x = (ax < bx) ? ax : bx;
		int max_x = (ax > bx) ? ax : bx;
		int min_y = (ay < by) ? ay : by;
		int max_y = (ay > by) ? ay : by;

		min_x = (min_x < 0) ? 0 : min_x;
		min_y = (min_y < 0) ? 0 : min_y;
		max_x = (max_x > getWidth() - 1) ? getWidth() - 1 : max_x;
		max_y = (max_y > getHeight() - 1) ? getHeight() - 1 : max_y;

		Picture result = this;
		for (int x = min_x; x <= max_x; x++) {
			for (int y = min_y; y <= max_y; y++) {
				result = result.paint(x, y, p, factor);
			}
		}
		return copyAsImmutable(result);
	}

	// paint(int cx, int cy, double radius, Pixel p) sets all pixels in the
	// picture that are within radius distance of the coordinate (cx, cy) to the
	// Pixel value p. Only positive values of radius should be allowed. Any
	// value of cx and cy should be allowed (even if negative or otherwise
	// outside of the boundaries of the frame). Factor is taken into account.
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		if (radius < 0) {
			throw new IllegalArgumentException("radius is negative");
		}

		int min_x = (int) (cx - (radius + 1));
		int max_x = (int) (cx + (radius + 1));
		int min_y = (int) (cy - (radius + 1));
		int max_y = (int) (cy + (radius + 1));

		min_x = (min_x < 0) ? 0 : min_x;
		min_y = (min_y < 0) ? 0 : min_y;
		max_x = (max_x > getWidth() - 1) ? getWidth() - 1 : max_x;
		max_y = (max_y > getHeight() - 1) ? getHeight() - 1 : max_y;

		Picture result = this;
		for (int x = min_x; x <= max_x; x++) {
			for (int y = min_y; y <= max_y; y++) {
				if (Math.sqrt((cx - x) * (cx - x) + (cy - y) * (cy - y)) <= radius) {
					result = result.paint(x, y, p, factor);
				}
			}
		}
		return copyAsImmutable(result);
	}

	// paint(int x, int y, Picture p) paints pixels starting at position (x,y)
	// and extending to the right and downwards
	// with pixels from the provided Picture object p starting at (0,0).
	// factor is taken into account.
	public Picture paint(int x, int y, Picture p, double factor) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of range");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		if (p == null) {
			throw new IllegalArgumentException("Picture p is null");
		}

		Picture result = this;
		for (int px = 0; px < p.getWidth() && px + x < getWidth(); px++) {
			for (int py = 0; py < p.getHeight() && py + y < getHeight(); py++) {
				result = result.paint(px + x, py + y, p.getPixel(px, py), factor);
			}
		}
		return copyAsImmutable(result);
	}

	// Creates an Immutable version
	private static Picture copyAsImmutable(Picture p) {
		if (p == null) {
			throw new IllegalArgumentException("Picture p is null");
		}

		Pixel[][] parray = new Pixel[p.getWidth()][p.getHeight()];
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				parray[x][y] = p.getPixel(x, y);
			}
		}
		return new ImmutablePixelArrayPicture(parray, p.getCaption());
	}

}
