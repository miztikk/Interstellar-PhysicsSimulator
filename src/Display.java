import processing.core.PApplet;

public class Display {
	private PApplet parent;
	private Button[] buttons;
	private boolean debug;
	private int clickedIndex;

	public Display(PApplet p) {
		this.parent = p;
		debug = true;
		buttons = new Button[3];

		buttons[0] = new Button("Mass", "create a massive object",
				p.width - 50, 100, 30) {
			public void click(PApplet p) {

			}
		};
		buttons[1] = new Button("Vector",
				"change the velocity and direction of an object", p.width - 50,
				150, 30) {

		};
		buttons[2] = new Button("Settings",
				"change game settings like time speed", p.width - 50, 200, 30) {
			public void click(PApplet p) {

			}
		};
	}

	public void drawScreen(Engine e) {
		for (Projectile p : e.getProjectiles()) {
			if (debug) {
				parent.fill(255);
				parent.text(p.toString(), p.getX() - p.getDiameter(), p.getY()
						- p.getDiameter());
			}
			determineFill(p.getType());
			parent.ellipse(p.getX(), p.getY(), p.getDiameter(), p.getDiameter());
		}
		for (Button b : buttons) {
			b.display(parent);
		}
		if (debug) {
			parent.fill(255);
			parent.textAlign(parent.LEFT);
			parent.text("DEBUG", 5, 10);
			parent.text("timeSpeed: " + e.getTimeSpeed() + " projectiles: "
					+ e.getProjectiles().size(), 5, 20);
		}
	}

	public void determineFill(int type) {
		Color c = new Color(0, 0, 0);
		if (type == 1)
			c = Color.newBrown();
		else if (type == 2)
			c = Color.newWhite();
		else if (type == 3)
			c = Color.newBlue();
		else if (type == -1)
			c = Color.newYellow();
		else if (type == -2)
			c = Color.newOrange();
		else
			c = Color.newBlack();
		extractColor(c);
	}

	public void extractColor(Color c) {
		parent.fill(c.getR(), c.getG(), c.getB());
	}

	public Button[] getButtons() {
		return buttons;
	}

	public void switchDebug() {
		debug = !debug;
	}

}
