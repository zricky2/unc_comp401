package a3;

public class ImmutablePixelArrayPicture implements Picture {
	
	private Pixel[][] pixel_array;
	private int width;
	private int height;
	private Pixel initial_value;
	
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {
		this.pixel_array = pixel_array;

		if (pixel_array == null) {
			throw new IllegalArgumentException("Null");
		}
		if (pixel_array.length <= 0) {
			throw new IllegalArgumentException("Nonexistent row");
		}
		if (pixel_array[0].length <= 0) {
			throw new IllegalArgumentException("Nonexistent column");
		}
		// check for null row
				for (int i = 0; i < pixel_array.length; i++) {
					for (int j = 0; j < pixel_array[0].length; j++) {
						if (pixel_array[i] == null) {
							throw new IllegalArgumentException("NUll row");
						}
					}
				}
		// check for different height picture
		for (int i = 0; i < pixel_array.length; i++) {
			if (pixel_array[i].length != pixel_array[0].length) {
				throw new IllegalArgumentException("Different height");
			}
		}
		
		// check for no null pixel
		for (int i = 0; i < pixel_array.length; i++) {
			for (int j = 0; j < pixel_array[0].length; j++) {
				if (pixel_array[i][j] == null) {
					throw new IllegalArgumentException("NUll pixel");
				}
			}
		}
		
		this.width = pixel_array.length;
		this.height = pixel_array[0].length;
	}
	
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		this.width = width;
		this.height = height;
		this.initial_value = initial_value;
		
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Out of range");
		}
		if (initial_value == null) {
			throw new IllegalArgumentException("Null");
		}
		pixel_array = new Pixel[width][height];
		for (int i=0; i < width; i++) {
			for (int j=0; j < height; j++) {
				pixel_array[i][j] = initial_value;
			}
		}
	
}
	
	public ImmutablePixelArrayPicture(int width, int height) {
		this.width = width;
		this.height = height;
		
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Out of range");
		}
		
		for (int i=0; i < width; i++) {
			for (int j=0; j < height; j++) {
				pixel_array[i][j] = new GrayPixel(0.5);
			}
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
		return pixel_array[x][y];
	}
	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), getPixel(x,y));
		return newPicture.paint(x, y, p);
	}
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of Range");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), getPixel(x,y));
		return newPicture.paint(x, y, p, factor);
	}
	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), initial_value);
		return newPicture.paint(ax, ay, bx, by, p);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of Range");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), initial_value);
		return newPicture.paint(ax, ay, bx, by, p, factor);
	}
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius is prohibited");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), initial_value);
		return newPicture.paint(cx, cy, radius, p);
	}
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius is prohibited");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of Range");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), initial_value);
		return newPicture.paint(cx, cy, radius, p, factor);
	}
}