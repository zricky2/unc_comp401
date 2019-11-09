package a3;

public class VerticalStackPicture implements Picture {
	
	private Picture top;
	private Picture bottom;
	private Pixel [][] pixel_array;
	public VerticalStackPicture(Picture top, Picture bottom) {
		this.top = top;
		this.bottom = bottom;
		if (top == null || bottom == null) {
			throw new IllegalArgumentException("Empty Picture");
		}
		if (top.getWidth() != bottom.getWidth()) {
			throw new IllegalArgumentException("Uncompatible geometry");
		}
		
		pixel_array = new Pixel[top.getWidth()][top.getHeight() + bottom.getHeight()];
		for (int i=0; i < top.getWidth(); i++) {
			for (int j=0; j < top.getHeight(); j++) {
				pixel_array[i][j] = top.getPixel(i, j);
			}
		}
		for (int i=0; i < bottom.getWidth(); i++) {
			for (int j= 0; j < bottom.getHeight(); j++) {
				pixel_array[i][j + top.getHeight()] = bottom.getPixel(i, j);
			}
		}
	}
	@Override
	public int getWidth() {
		return top.getWidth();
	}

	@Override
	public int getHeight() {
		return top.getHeight() + bottom.getHeight();
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
		pixel_array[x][y] = p;
		return this;
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
		pixel_array [x][y] = pixel_array[x][y].blend(p, factor);
		return this;
	}
	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (ax < 0 || ay < 0 || bx < 0 || by < 0 || ax >= getWidth() || ay >= getHeight() || bx >= getWidth() || by >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if (i <= ax && i >= bx && j <= by && j >=ay) {
					pixel_array[i][j] = p;
				}
		
			}
		}
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (ax < 0 || ay < 0 || bx < 0 || by < 0 || ax >= getWidth() || ay >= getHeight() || bx >= getWidth() || by >= getHeight()) {
			throw new IllegalArgumentException("Out of range");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of range");
		}
		return this;
	}
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius < 0) {
			throw new IllegalArgumentException("Negative radius is prohibited");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel is Null");
		}
		for (int i = 0; i < getWidth(); i++) {
			for (int j =0; j< getHeight(); j++) {
				if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) >= radius) {
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
			throw new IllegalArgumentException("Pixel is Null");
		}
		if (factor < 0 || factor > 1) {
			throw new IllegalArgumentException("Factor is out of range");
		}
		for (int i = 0; i < getWidth(); i++) {
			for (int j =0; j< getHeight(); j++) {
				if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) >= radius) {
					pixel_array[i][j] = pixel_array[i][j].blend(p, factor);
				}
			}
			
		}
		return this;
	}
}