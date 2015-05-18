public class Color {
	private int r, g, b;

	public Color(int red, int green, int blue) {
		r = red;
		g = green;
		b = blue;
	}

	public static Color newBrown() {
		return new Color(165, 42, 42);
	}

	public static Color newOrange() {
		return new Color(255, 69, 0);
	}

	public static Color newWhite() {
		return new Color(255, 255, 255);
	}

	public static Color newBlue() {
		return new Color(0, 0, 255);
	}

	public static Color newYellow() {
		return new Color(255, 255, 0);
	}

	public static Color newBlack() {
		return new Color(0, 0, 0);
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
}
