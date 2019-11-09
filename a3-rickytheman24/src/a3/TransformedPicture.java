package a3;

public class TransformedPicture implements Picture {
	
	private Picture source;
	private PixelTransformation xform;
	private Pixel [][] pixel_array;
	
	public TransformedPicture (Picture source, PixelTransformation xform) {
		this.source = source;
		this.xform = xform;
		Pixel[][]pixel_array = new Pixel[source.getWidth()][source.getHeight()];
		for (int i=0; i < source.getWidth(); i++) {
			for (int j=0; j < source.getHeight(); j++) {
				Pixel currentPixel = source.getPixel(i,j);
				pixel_array [i][j] = xform.transform(currentPixel);
				
			}
		}
		this.pixel_array = pixel_array;
	}
	
	@Override
	public int getWidth() {
		return this.pixel_array.length;
	}

	@Override
	public int getHeight() {
		return this.pixel_array[0].length;
	}
	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		return this.pixel_array[x][y];
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
		return newPicture;
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
			throw new IllegalArgumentException("Factor is out of range");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(getWidth(), getHeight(), getPixel(x,y));
		return newPicture.paint(x, y, p, factor);
	}
	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(pixel_array);
		return newPicture.paint(ax, ay, bx, by, p);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of range");
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
			throw new IllegalArgumentException("Pixel is Null");
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
			throw new IllegalArgumentException("Pixel is Null");
		}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of range");
		}
		MutablePixelArrayPicture newPicture = new MutablePixelArrayPicture(pixel_array);
		return newPicture.paint(cx, cy, radius, p, factor);
	}
}