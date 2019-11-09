package a5;

import java.util.Iterator;

// Extended by PixelArrayPicture and SubPictureImpl
public abstract class PictureImpl implements Picture {

	protected String caption;

	public PictureImpl(String caption) {
		if (caption == null) {
			throw new IllegalArgumentException("caption should not be null");
		}
		this.caption = caption;
	}

	// paint(int x, int y, Pixel p) paints the pixel at
	// position (x,y) with the pixel value p.
	public Picture paint(int x, int y, Pixel p) {
		return paint(x, y, p, 1.0);
	}

	// paint(int ax, int ay, int bx, int by, Pixel p) paints the
	// rectangular region defined by the positions (ax, ay) and
	// (bx, by) with the specified pixel value.
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return paint(ax, ay, bx, by, p, 1.0);
	}

	// The second form
	// should blend with the specified factor as previously described.
	// A factor of 0.0 leaves the specified
	// pixel unchanged. A factor of 1.0 results in replacing
	// the value with the specified pixel value. Values between
	// 0.0 and 1.0 blend proportionally.
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
		return result;
	}

	// paint(int cx, int cy, double radius, Pixel p) sets all pixels in the
	// picture that are within radius distance of the coordinate (cx, cy) to the
	// Pixel value p. Only positive values of radius should be allowed. Any
	// value of cx and cy should be allowed (even if negative or otherwise
	// outside of the boundaries of the frame).
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		return paint(cx, cy, radius, p, 1.0);
	}

	// same as above with factor
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
		return result;
	}

	// paint(int x, int y, Picture p) paints pixels starting at position (x,y)
	// and extending to the right and downwards
	// with pixels from the provided Picture object p starting at (0,0).
	public Picture paint(int x, int y, Picture p) {
		return paint(x, y, p, 1.0);
	}

	// same as above but with factor
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
		return result;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		if (caption == null) {
			throw new IllegalArgumentException("caption is null");
		}

		this.caption = caption;
	}

	// The extract method of a Picture object can be used
	// to create a SubPicture object that represents a rectangular
	// region within the picture.
	public SubPicture extract(int x, int y, int width, int height) {
		if (width <= 0 || width + x > this.getWidth()) {
			throw new IllegalArgumentException("caption is null");
		}
		if (height <= 0 || height + y > this.getHeight()) {
			throw new IllegalArgumentException("caption is null");
		}
		return new SubPictureImpl(this, x, y, width, height);
	}

	// The sample method of a Picture object creates a
	// Pixel iterator that produces Pixel objects from the picture.
	// The iterator begins with the specified initial pixel location
	// and then samples left to right the specified dx columns
	// and then top to bottom by the specified dy rows.
	public Iterator<Pixel> sample(int init_x, int init_y, int dx, int dy) {
		return new SampleIterator(this, init_x, init_y, dx, dy);
	}

	// The window method should produce a sequence of SubPicture objects
	// as if you were sliding a "window" that was window_width wide
	// and window_height tall across the picture starting from the
	// upper left corner and proceeding from left to right and top to
	// bottom until the window hits the lower right corner.
	public Iterator<SubPicture> window(int window_width, int window_height) {
		return new WindowIterator(this, window_width, window_height);
	}

	// The tile method should produce a sequence of SubPicture
	// objects as if you had cut the original picture into tiles
	// that were tile_width wide and tile_height tall. Again,
	// the SubPicture for the upper left tile is produced first
	// and the iterator proceeds left to right and top to bottom.
	// Partial tiles if the original picture width/height is not a
	// perfect multiple of the tile width/height are not produced.
	public Iterator<SubPicture> tile(int tile_width, int tile_height) {
		return new TileIterator(this, tile_width, tile_height);
	}

	// The zigzag method produces a Pixel iterator that iterates
	// over the pixels of a Picture in zigzag order starting at 0,0
	public Iterator<Pixel> zigzag() {
		return new ZigZagIterator(this);
	}
	
}
