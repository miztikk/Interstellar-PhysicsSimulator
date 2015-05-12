public class Color {
	int r, g, b;

	public Color(int red, int green, int blue) {
		r = red;
		g = green;
		b = blue;
	}

	public static Color newBrown() {
		return new Color(165, 42, 42);
	}

	public static Color newWhite() {
		return new Color(255, 255, 255);
	}

	public static Color newBlue() {
		return new Color(0, 0, 255);
	}
}
