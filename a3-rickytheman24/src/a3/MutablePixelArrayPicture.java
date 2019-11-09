package a3;

public class MutablePixelArrayPicture implements Picture {

	private Pixel[][] pixel_array;
	private int width;
	private int height;
	private Pixel initial_value;

	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
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

	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
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
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixel_array[i][j] = this.initial_value;
				
			}
		}
	}

	public MutablePixelArrayPicture(int width, int height) {
		this.width = width;
		this.height = height;

		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Out of range");
		}
		pixel_array = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
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
			throw new IllegalArgumentException("Too low");
		}
		
		return pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
		}
		pixel_array[x][y] = p;
		return this;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		if (p == null) {
		throw new IllegalArgumentException("Pixel is null");
		}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of range");
		}
		pixel_array[x][y] = pixel_array[x][y].blend(p, factor);
		return this;
	}
	
		// paint(int ax, int ay, int bx, int by, Pixel p) paints the
		// rectangular region defined by the positions (ax, ay) and
		// (bx, by) with the specified pixel value. The second form
		// should blend with the specified factor as previously described.
	
	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
			}
		/*if (ax < bx && ay > by) {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if (i <= ax && i >= bx && j <= by && j >= ay) {
					
					pixel_array[i][j] = p;
				}

			}
		}
		}*/
		int max;
		int min;
		int maxTwo;
		int minTwo;
		if(ax < 0) {
			ax = 0;
		}
		if(bx < 0) {
			bx = 0;
		}
		if(ay < 0) {
			ay = 0;
		}
		if(by < 0) {
			by = 0;
		}
		if (ax > bx) {
			max = ax;
			min = bx;
		} else {
			max = bx;
			min = ax;
		} 
		if (ay > by) {
			maxTwo = ay;
			minTwo = by;
		} else {
			maxTwo = by;
			minTwo = ay;
		}
		for ( int i = min; i <= max; i++) {
			for (int j = minTwo; j <= maxTwo; j++) {
				pixel_array[i][j] = p;
			}
		}
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
			}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of range");
		}
		int max;
		int min;
		int maxTwo;
		int minTwo;
		if(ax < 0) {
			ax = 0;
		}
		if(bx < 0) {
			bx = 0;
		}
		if(ay < 0) {
			ay = 0;
		}
		if(by < 0) {
			by = 0;
		}
		if (ax > bx) {
			max = ax;
			min = bx;
		} else {
			max = bx;
			min = ax;
		} 
		if (ay > by) {
			maxTwo = ay;
			minTwo = by;
		} else {
			maxTwo = by;
			minTwo = ay;
		}
		for ( int i = min; i <= max; i++) {
			for (int j = minTwo; j <= maxTwo; j++) {
				pixel_array[i][j] = pixel_array[i][j].blend(p, factor);
			}
		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius is prohibited");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
		}
		double minX = cx - radius;
		double maxX = cx + radius;
		double minY = cy - radius;
		double maxY = cy + radius;
		if (minX < 0) {
			minX = 0;
		}
		if (minY < 0) {
			minY = 0;
		}
		if (maxX < 0) {
			maxX = 0;
		}
		if (maxY < 0) {
			maxY = 0;
		}
		for (int i = (int) minX; i <= maxX; i++) {
			for (int j = (int) minY; j <= maxY; j++) {
				if (Math.sqrt(((i - cx) * (i - cx)) + ((j - cy) * (j - cy))) <= radius) {
					pixel_array[i][j] = p;
				}
			}

		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius is prohibited");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
		}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of range");
		}
		double minX = cx - radius;
		double maxX = cx + radius;
		double minY = cy - radius;
		double maxY = cy + radius;
		if (minX < 0) {
			minX = 0;
		}
		if (minY < 0) {
			minY = 0;
		}
		if (maxX < 0) {
			maxX = 0;
		}
		if (maxY < 0) {
			maxY = 0;
		}
		for (int i = (int) minX; i <= maxX; i++) {
			for (int j = (int) minY; j <= maxY; j++) {
				if (Math.sqrt(((i - cx) * (i - cx)) + ((j - cy) * (j - cy))) <= radius) {
					pixel_array[i][j] = pixel_array[i][j].blend(p, factor);
				}
			}

		}
		return this;
	}

}
