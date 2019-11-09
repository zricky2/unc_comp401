package a7;

import java.util.Iterator;

import a7.Picture;
import a7.SubPicture;

//The TileIterator should produce a sequence of SubPicture 
//objects as if you had cut the original picture into tiles 
//that were tile_width wide and tile_height tall. Again, 
//the SubPicture for the upper left tile is produced first 
//and the iterator proceeds left to right and top to bottom. 
//Partial tiles if the original picture width/height is not a 
//perfect multiple of the tile width/height are not produced.

public class TileIterator implements Iterator<SubPicture> {

	private Picture source;
	private int tile_width;
	private int tile_height;

	public TileIterator(Picture source, int tile_width, int tile_height) {
		if (source == null) {
			throw new IllegalArgumentException("source should not be null");
		}
		if (tile_width <= 0 || tile_width > source.getWidth()) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		if (tile_height <= 0 || tile_height > source.getHeight()) {
			throw new IllegalArgumentException("Cannot be negative");
		}
		this.source = source;
		this.tile_width = tile_width;
		this.tile_height = tile_height;
	}

	int point_x = 0;
	int point_y = 0;

	@Override
	public boolean hasNext() {

		return (point_y + tile_height <= source.getHeight());
	}

	@Override
	public SubPicture next() {
		if (!this.hasNext()) {
			throw new IllegalArgumentException("No Tiles left");
		}
		SubPicture nextTile = source.extract(point_x, point_y, tile_width, tile_height);
		point_x += tile_width;
		if (point_x + tile_width > source.getWidth()) {
			point_x = 0;
			point_y += tile_height;
		} 
		return nextTile;
	}

}