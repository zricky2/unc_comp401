package a5;

import java.util.Iterator;

//subclass
//Represents a rectangular region within source picture.

public class SubPictureImpl extends PictureImpl implements SubPicture {
	// source is the original picture that the SubPicture is a part of
	private Picture source;
	// x coordinate of the start point for SubPicture on the Source
	private int xoffset;
	// y coordinate of the start point for SubPicture on the Source
	private int yoffset;
	private int width;
	private int height;

	public SubPictureImpl(Picture source, int xoffset, int yoffset, int width, int height) {
		super(checkSource(source).getCaption());
		if (xoffset < 0 && xoffset > source.getWidth() - 1) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		if (yoffset < 0 && xoffset > source.getHeight() - 1) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		if (width <= 0 && width > source.getWidth() - xoffset) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		if (height <= 0 && height > source.getHeight() - yoffset) {
			throw new IllegalArgumentException("Cannot be negative");
		}

		this.source = source;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.width = width;
		this.height = height;

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Pixel getPixel(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}
		return source.getPixel(x + xoffset, y + yoffset);
	}

	// paint(int x, int y, Pixel p) paints the pixel at
	// position (x,y) with the pixel value p.
	// A factor of 0.0 leaves the specified
	// pixel unchanged. A factor of 1.0 results in replacing
	// the value with the specified pixel value. Values between
	// 0.0 and 1.0 blend proportionally.
	// Also checks the mutability
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

		Picture pix = source.paint(x + xoffset, y + yoffset, p, factor);
		if (! (pix == source)) {
			//return pix because it is the new picture if it is immutable
			SubPicture newSub = pix.extract(xoffset, yoffset, width, height);
			newSub.setCaption(this.getCaption());
			return newSub;
		}
		return this;
	}

	public int getXOffset() {
		return xoffset;
	}

	public int getYOffset() {
		return yoffset;
	}

	public Picture getSource() {
		return source;
	}

	private static Picture checkSource(Picture source) {
		if (source == null) {
			throw new IllegalArgumentException("source should not be null");
		}
		return source;
	}
}
