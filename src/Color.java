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

	public static Color newDarkBrown() {
		return new Color(160, 82, 55);
	}

	public static Color newGrey() {
		return new Color(150, 150, 150);
	}

	public static Color searchColor(int type) {
		if (type == 1)
			return Color.newBrown();
		else if (type == 2)
			return Color.newWhite();
		else if (type == 3)
			return Color.newBlue();
		else if (type == 4)
			return Color.newDarkBrown();
		else if (type == -1)
			return Color.newYellow();
		else if (type == -2)
			return Color.newOrange();
		else if (type == -4)
			return Color.newGrey();
		else
			return Color.newBlack();
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
