import processing.core.PApplet;

public class Display {
	private PApplet parent;
	private boolean debug;

	public Display(PApplet p) {
		this.parent = p;
		debug = true;
	}

	public void drawScreen(Engine e) {
		if (debug) {
			parent.fill(255);
			parent.text("DEBUG", 5, 10);
			parent.text("timeSpeed: " + e.getTimeSpeed() + " projectiles: "
					+ e.getProjectiles().size(), 5, 20);
		}
		for (Projectile p : e.getProjectiles()) {
			if (debug) {
				parent.fill(255);
				parent.text(p.toString(), p.getX() - p.getRadius(), p.getY()
						- p.getRadius());
			}
			determineFill(p.getType());
			parent.ellipse(p.getX(), p.getY(), p.getRadius(), p.getRadius());
		}
	}

	public void determineFill(String type) {
		Color c = new Color(0, 0, 0);
		System.out.println(type);
		if (type.contains("ast"))
			c = Color.newBrown();
		if (type.contains("moo"))
			c = Color.newWhite();
		if (type.contains("pla"))
			c = Color.newBlue();
		if (type.contains("sta"))
			c = Color.newYellow();
		else
			c = Color.newBlack();
		extractColor(c);
	}

	public void extractColor(Color c) {
		parent.fill(c.getR(), c.getG(), c.getB());
	}

	public void switchDebug() {
		debug = !debug;
	}

}
