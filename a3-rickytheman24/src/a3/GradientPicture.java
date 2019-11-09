package a3;

public class GradientPicture implements Picture {

	private int width;
	private int height;
	private Pixel upper_left;
	private Pixel upper_right;
	private Pixel lower_left;
	private Pixel lower_right;

	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left,
			Pixel lower_right) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Out of range");
		}
		if (upper_left == null || upper_right == null || lower_left == null || lower_right == null) {
			throw new IllegalArgumentException("Out of range");
		}
		
		this.width = width;
		this.height = height;
		this.upper_left = upper_left;
		this.upper_right = upper_right;
		this.lower_left = lower_left;
		this.lower_right = lower_right;

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
		Pixel[][] pixel_array = new Pixel[width][height];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				Pixel startOfColumn = upper_left.blend(lower_left, (1.0 / (this.getHeight() - 1.0)) * j);
				Pixel endOfColumn = upper_right.blend(lower_right, (1.0 / (this.getHeight() - 1.0)) * j);
				pixel_array[i][j] = startOfColumn.blend(endOfColumn, (1.0 / (this.getWidth() - 1.0)) * i);
			}
		}
		//Pixel startOfColumn = upper_left.blend(lower_left, (1.0 / (this.getHeight() - 1.0)) * y);
		
		//Pixel endOfColumn = upper_right.blend(lower_right, (1.0 / (this.getHeight() - 1.0)) * y);
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(pixel_array);
		return newPicture.getPixel(x, y);
	}
	
	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
		}
		Pixel[][] pixel_array = new Pixel[width][height];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				Pixel startOfColumn = upper_left.blend(lower_left, (1.0 / (this.getHeight() - 1.0)) * j);
				Pixel endOfColumn = upper_right.blend(lower_right, (1.0 / (this.getHeight() - 1.0)) * j);
				pixel_array[i][j] = startOfColumn.blend(endOfColumn, (1.0 / (this.getWidth() - 1.0)) * i);
			}
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(pixel_array);
		return newPicture.paint(x, y, p);
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
		Pixel[][] pixel_array = new Pixel[width][height];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				Pixel startOfColumn = upper_left.blend(lower_left, (1.0 / (this.getHeight() - 1.0)) * j);
				Pixel endOfColumn = upper_right.blend(lower_right, (1.0 / (this.getHeight() - 1.0)) * j);
				pixel_array[i][j] = startOfColumn.blend(endOfColumn, (1.0 / (this.getWidth() - 1.0)) * i);
			}
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(pixel_array);
		return newPicture.paint(x, y, p, factor);
	}
	
	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
		}
		Pixel[][] pixel_array = new Pixel[width][height];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				Pixel startOfColumn = upper_left.blend(lower_left, (1.0 / (this.getHeight() - 1.0)) * j);
				Pixel endOfColumn = upper_right.blend(lower_right, (1.0 / (this.getHeight() - 1.0)) * j);
				pixel_array[i][j] = startOfColumn.blend(endOfColumn, (1.0 / (this.getWidth() - 1.0)) * i);
			}
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(pixel_array);
		return newPicture.paint(ax, ay, bx, by, p);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
		}
		Pixel[][] pixel_array = new Pixel[width][height];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				Pixel startOfColumn = upper_left.blend(lower_left, (1.0 / (this.getHeight() - 1.0)) * j);
				Pixel endOfColumn = upper_right.blend(lower_right, (1.0 / (this.getHeight() - 1.0)) * j);
				pixel_array[i][j] = startOfColumn.blend(endOfColumn, (1.0 / (this.getWidth() - 1.0)) * i);
			}
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(pixel_array);
		return newPicture.paint(ax, ay, bx, by, p, factor);
	}
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius is prohibited");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is null");
		}
		Pixel[][] pixel_array = new Pixel[width][height];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				Pixel startOfColumn = upper_left.blend(lower_left, (1.0 / (this.getHeight() - 1.0)) * j);
				Pixel endOfColumn = upper_right.blend(lower_right, (1.0 / (this.getHeight() - 1.0)) * j);
				pixel_array[i][j] = startOfColumn.blend(endOfColumn, (1.0 / (this.getWidth() - 1.0)) * i);
			}
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(pixel_array);
		return newPicture.paint(cx, cy, radius, p);
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
		Pixel[][] pixel_array = new Pixel[width][height];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				Pixel startOfColumn = upper_left.blend(lower_left, (1.0 / (this.getHeight() - 1.0)) * j);
				Pixel endOfColumn = upper_right.blend(lower_right, (1.0 / (this.getHeight() - 1.0)) * j);
				pixel_array[i][j] = startOfColumn.blend(endOfColumn, (1.0 / (this.getWidth() - 1.0)) * i);
			}
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(pixel_array);
		return newPicture.paint(cx, cy, radius, p, factor);
		}
}