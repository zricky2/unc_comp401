package a3;

public class MonochromePicture implements Picture {
	
	private int width;
	private int height;
	private Pixel value;
	
	public MonochromePicture(int width, int height, Pixel value) {
		this.width = width;
		this.height = height;
		this.value = value;
		
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Out of range");
		}
		if (value == null) {
			throw new IllegalArgumentException("Null");
		}
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		return value;
	}
	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), value);
		return newPicture.paint(x, y, p);
	}
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), value);
		return newPicture.paint(x, y, p, factor);
	}
	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (ax < 0 || ay < 0 || bx < 0 || by < 0 || ax >= getWidth() || ay >= getHeight() || bx >= getWidth() || by >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), value);
		return newPicture.paint(ax, ay, bx, by, p);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (ax < 0 || ay < 0 || bx < 0 || by < 0 || ax >= getWidth() || ay >= getHeight() || bx >= getWidth() || by >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), value);
		return newPicture.paint(ax, ay, bx, by, p, factor);
	}
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius is prohibited");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), value);
		return newPicture.paint(cx, cy, radius, p);
	}
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius is prohibited");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), value);
		return newPicture.paint(cx, cy, radius, p, factor);
	}
}