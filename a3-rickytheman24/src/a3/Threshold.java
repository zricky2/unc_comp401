package a3;

public class Threshold implements PixelTransformation {

	private double threshold;
	
	public Threshold(double threshold) {
		if (threshold < 0 || threshold > 1) {
			throw new IllegalArgumentException("Threshold is out of range");
		}
		this.threshold = threshold;
	}
	
	
	@Override
	public Pixel transform(Pixel p) {
		if (p.getIntensity() > threshold) {
			p = new ColorPixel(1,1,1);
			return p;
		} else {
			p = new ColorPixel(0,0,0);
			return p;
		}
	}
}
