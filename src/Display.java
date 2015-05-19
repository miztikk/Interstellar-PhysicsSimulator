import processing.core.PApplet;

public class Display {
	private PApplet parent;
	private Engine engine;
	private Button[] buttons;
	private boolean debug;
	private int clickedIndex;

	public Display(PApplet p, Engine e) {
		this.parent = p;
		this.engine = e;
		debug = true;
		buttons = new Button[3];

		buttons[0] = new Button("Mass", "create a massive object", p.width - 50, 100, 30) {
			public void click(PApplet p, Engine e) {
				e.addRanProjectile(p.mouseX, p.mouseY);
			}
		};
		buttons[1] = new Button("Vector", "change the velocity and direction of an object", p.width - 50, 150, 30) {

		};
		buttons[2] = new Button("Settings", "change game settings like time speed", p.width - 50, 200, 30) {
			public void click(PApplet p) {

			}
		};
	}

	public void drawScreen(Engine e) {
		for (Projectile p : e.getProjectiles()) {
			if (debug) {
				parent.fill(255);
				parent.text(p.toString(), p.getX() - p.getDiameter(), p.getY() - p.getDiameter());
			}
			determineFill(p.getType());
			parent.ellipse(p.getX(), p.getY(), p.getDiameter(), p.getDiameter());
		}
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].isClicked() && i != clickedIndex) {
				buttons[i].setClicked(false);
			}
			buttons[i].display(parent);
		}
		if (debug) {
			parent.fill(255);
			parent.textAlign(parent.LEFT);
			parent.text("DEBUG", 5, 10);
			parent.text("timeSpeed: " + e.getTimeSpeed() + " projectiles: "
					+ e.getProjectiles().size(), 5, 20);
		}
	}
	
	public boolean isMouseFree() {
		for (Button b : buttons) {
			if (b.isHovering(parent)) return false;
		}
		return true;
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
	
	public int getClickedIndex() {
		return clickedIndex;
	}
	
	public void setClickedIndex(int clickedIndex) {
		this.clickedIndex = clickedIndex;
	}

	public void switchDebug() {
		debug = !debug;
	}

}
