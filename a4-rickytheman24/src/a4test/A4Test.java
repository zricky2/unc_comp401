package a4test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a4.*;

public class A4Test {

	static public String[] getTestNames() {
		String[] test_names = new String[10];

		test_names[0] = "testSimplerIter";
		test_names[1] = "testWindowIter";
		test_names[2] = "testTileIter";
		test_names[3] = "testZigZagIter";
	//	test_names[] = "testSimplerIter_Immutable";
	//	test_names[] = "testWindowIter_Immutable";
		test_names[4] = "testTileIter_Immutable";
	//	test_names[] = "testZigZagIter_Immutable";
	//	test_names[] = "testSinglePaint";
		test_names[5] = "testSubPicture_SinglePaint";
		test_names[6] = "testSubPicture_RectangleFactorPaint";
		test_names[7] = "testSubPicture_CirclePaint";
		test_names[8] = "testSubPicture_OffsetandSource";
		test_names[9] = "testPaintPictureontoPicture";

		return test_names;
	}

	public boolean assertEqualObjects(SubPicture a, SubPicture b) {
		if (a.getWidth() == b.getWidth() && a.getHeight() == b.getHeight()) {
			for (int i = 0; i < a.getWidth(); i++) {
				for (int j = 0; j < a.getHeight(); j++) {
					assertEquals(a.getPixel(i, j), b.getPixel(i, j));
				}
			}
		}
		return true;
	}

	public boolean assertEqualPixel(Pixel a, Pixel b) {
		if (a.equals(b)) {
			return true;
		}
		return false;
	}

	@Test
	public void testSimplerIter() {

		try {
			Picture picture = new MutablePixelArrayPicture(null, "My caption ");
			fail("exception not thrown");
		} catch (IllegalArgumentException e) {
		}

		Pixel[][] parray = new Pixel[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "My caption ");

		Iterator<Pixel> iter = picture.sample(0, 0, 2, 2);
		assertTrue(iter.hasNext());
		assertEquals(parray[0][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[0][2], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][2], iter.next());
		assertFalse(iter.hasNext());
	}

	@Test
	public void testWindowIter() {
		Pixel[][] parray = new Pixel[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		Iterator<SubPicture> window_iter = picture.window(2, 2);
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(0, 0, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(1, 0, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(2, 0, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(3, 0, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(0, 1, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(1, 1, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(2, 1, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(3, 1, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(0, 2, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(1, 2, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(2, 2, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(3, 2, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(0, 3, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(1, 3, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(2, 3, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(3, 3, 2, 2));
		assertFalse(window_iter.hasNext());

	}

	@Test
	public void testTileIter() {
		Pixel[][] parray = new Pixel[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		Iterator<SubPicture> tile_iter = picture.tile(2, 2);
		assertTrue(tile_iter.hasNext());
		assertEqualObjects(tile_iter.next(), picture.extract(0, 0, 2, 2));
		assertTrue(tile_iter.hasNext());
		assertEqualObjects(tile_iter.next(), picture.extract(2, 0, 2, 2));
		assertTrue(tile_iter.hasNext());
		assertEqualObjects(tile_iter.next(), picture.extract(0, 2, 2, 2));
		assertTrue(tile_iter.hasNext());
		assertEqualObjects(tile_iter.next(), picture.extract(2, 2, 2, 2));

		assertFalse(tile_iter.hasNext());

	}

	@Test
	public void testZigZagIter() {
		Pixel[][] parray = new Pixel[4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "My caption");
		Iterator<Pixel> zigzag_iter = picture.zigzag();
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[0][0], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[1][0], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[0][1], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[0][2], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[1][1], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[2][0], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[3][0], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[2][1], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[1][2], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[2][2], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[3][1], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[3][2], zigzag_iter.next());
		assertFalse(zigzag_iter.hasNext());

	}

	@Test
	public void testSimplerIter_Immutable() {
		Pixel[][] parray = new Pixel[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new ImmutablePixelArrayPicture(parray, "My caption ");

		Iterator<Pixel> iter = picture.sample(0, 0, 2, 2);
		assertTrue(iter.hasNext());
		assertEquals(parray[0][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][0], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[0][2], iter.next());
		assertTrue(iter.hasNext());
		assertEquals(parray[2][2], iter.next());
		assertFalse(iter.hasNext());
	}

	@Test
	public void testWindowIter_Immutable() {
		Pixel[][] parray = new Pixel[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new ImmutablePixelArrayPicture(parray, "My caption");
		Iterator<SubPicture> window_iter = picture.window(2, 2);
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(0, 0, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(1, 0, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(2, 0, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(3, 0, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(0, 1, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(1, 1, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(2, 1, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(3, 1, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(0, 2, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(1, 2, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(2, 2, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(3, 2, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(0, 3, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(1, 3, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(2, 3, 2, 2));
		assertTrue(window_iter.hasNext());
		assertEqualObjects(window_iter.next(), picture.extract(3, 3, 2, 2));
		assertFalse(window_iter.hasNext());

	}

	@Test
	public void testTileIter_Immutable() {
		Pixel[][] parray = new Pixel[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new ImmutablePixelArrayPicture(parray, "My caption");
		Iterator<SubPicture> tile_iter = picture.tile(2, 2);
		assertTrue(tile_iter.hasNext());
		assertEqualObjects(tile_iter.next(), picture.extract(0, 0, 2, 2));
		assertTrue(tile_iter.hasNext());
		assertEqualObjects(tile_iter.next(), picture.extract(2, 0, 2, 2));
		assertTrue(tile_iter.hasNext());
		assertEqualObjects(tile_iter.next(), picture.extract(0, 2, 2, 2));
		assertTrue(tile_iter.hasNext());
		assertEqualObjects(tile_iter.next(), picture.extract(2, 2, 2, 2));
		assertFalse(tile_iter.hasNext());

	}

	@Test
	public void testZigZagIter_Immutable() {
		Pixel[][] parray = new Pixel[4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new ImmutablePixelArrayPicture(parray, "My caption");
		Iterator<Pixel> zigzag_iter = picture.zigzag();
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[0][0], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[1][0], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[0][1], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[0][2], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[1][1], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[2][0], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[3][0], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[2][1], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[1][2], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[2][2], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[3][1], zigzag_iter.next());
		assertTrue(zigzag_iter.hasNext());
		assertEquals(parray[3][2], zigzag_iter.next());
		assertFalse(zigzag_iter.hasNext());

	}

	@Test
	public void testSinglePaint() {
		Pixel[][] parray = new Pixel[4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "Let Us Paint");
		Pixel randomPixel = new ColorPixel(0.3, 0.4, 0.5);
		Picture adjustedPic = picture.paint(3, 1, randomPixel);
		Pixel pix = adjustedPic.getPixel(3, 1);
		assertEqualPixel(pix, randomPixel);

	}

	@Test
	public void testSubPicture_SinglePaint() {
		Pixel[][] parray = new Pixel[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "I Love Pixels");
		SubPicture subPic = picture.extract(2, 2, 3, 2);
		assertNotEquals(picture, subPic);
		Pixel randomPixel = new ColorPixel(0.2, 0.3, 0.4);
		Picture subPix = subPic.paint(1, 1, randomPixel);
		assertEqualPixel(subPix.getPixel(1, 1), randomPixel);
	}

	@Test
	public void testSubPicture_RectangleFactorPaint() {
		Pixel[][] parray = new Pixel[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Pixel black = new ColorPixel(0, 0, 0);
		Picture picture = new ImmutablePixelArrayPicture(parray, "I Love Rectangles");
		SubPicture subPic = picture.extract(1, 1, 6, 7);
		assertNotEquals(picture, subPic);
		Pixel randomPixel = new ColorPixel(0.2, 0.3, 0.4);
		Picture subPix = subPic.paint(1, 1, 4, 4, randomPixel, 0.3);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (i >= 1 && i <= 4 && j >= 1 && j <= 4) {
					assertEqualPixel(subPix.getPixel(i, j), randomPixel);
				} else {
					assertEqualPixel(subPix.getPixel(i, j), black);
				}
			}
		}
	}

	@Test
	public void testSubPicture_CirclePaint() {
		Pixel[][] parray = new Pixel[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Pixel black = new ColorPixel(0, 0, 0);
		Picture picture = new ImmutablePixelArrayPicture(parray, "I Love Circles");
		SubPicture subPic = picture.extract(2, 3, 10, 10);
		Pixel randomPixel = new ColorPixel(0.1, 0.2, 0.3);
		assertNotEquals(picture, subPic);
		Picture circle = subPic.paint(3, 4, 3.0, randomPixel);
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (Math.sqrt((x - 3) * (x - 3) + (y - 4) * (y - 4)) <= 3.0) {
					assertEqualPixel(subPic.getPixel(x, y), randomPixel);
				} else {
					assertEqualPixel(subPic.getPixel(x, y), black);
				}
			}
		}
	}

	@Test
	public void testSubPicture_OffsetandSource() {
		Pixel[][] parray = new Pixel[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new ImmutablePixelArrayPicture(parray, "I love SubPicture");
		SubPicture subPic = picture.extract(3, 3, 8, 8);
		assertNotEquals(picture, subPic);
		assertEquals(subPic.getXOffset(), 3);
		assertEquals(subPic.getYOffset(), 3);
		assertEquals(subPic.getSource(), picture);
	}

	@Test
	public void testPaintPictureontoPicture() {
		Pixel[][] parray = new Pixel[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				parray[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Pixel[][] parrayOne = new Pixel[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				parrayOne[i][j] = new ColorPixel(0, 0, 0);
			}
		}
		Picture picture = new MutablePixelArrayPicture(parray, "I love Pictures");
		Picture pictureOne = new MutablePixelArrayPicture(parrayOne, "I love Pictures");
		Picture newPicture = picture.paint(4, 5, pictureOne);

		for (int i = 4; i < 10; i++) {
			for (int x = 0; x < 10; x++) {
				for (int j = 5; j < 10; j++) {
					for (int y = 0; y < 10; y++) {
						if (i >= 4 || j >= 5) {
							assertEqualPixel(picture.getPixel(i, j), pictureOne.getPixel(x, y));
						}
					}
				}
			}
		}
	}

}
