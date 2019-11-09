package a5;

import java.util.Iterator;

// extended by MutablePixelArrayPicture and ImmutablePixelArrayPicture

public abstract class PixelArrayPicture extends PictureImpl {

	protected Pixel[][] parray;

	public PixelArrayPicture(Pixel[][] parray, String caption) {
		super(caption);
		this.parray = copyPixelArray(parray);

	}

	public int getWidth() {
		return parray.length;
	}

	public int getHeight() {
		return parray[0].length;
	}

	public Pixel getPixel(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}
		return parray[x][y];
	}

	// return a copy of an array after checking the properties
	private static Pixel[][] copyPixelArray(Pixel[][] pixel_array) {

		if (pixel_array == null) {
			throw new IllegalArgumentException("pixel_array is null");
		}
		int width = pixel_array.length;

		if (width == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x] == null) {
				throw new IllegalArgumentException("pixel_array includes null columns");
			}
		}

		int height = pixel_array[0].length;
		if (height == 0) {
			throw new IllegalArgumentException("pixel_array has illegal geometry");
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x].length != height) {
				throw new IllegalArgumentException("Columns in picture are not all the same height.");
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (pixel_array[x][y] == null) {
					throw new IllegalArgumentException("pixel_array includes null pixels");
				}
			}
		}

		Pixel[][] copy = new Pixel[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				copy[x][y] = pixel_array[x][y];
			}
		}

		return copy;
	}

}
