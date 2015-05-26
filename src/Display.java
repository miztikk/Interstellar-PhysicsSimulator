import processing.core.PApplet;

public class Display {
	public static final float DEFAULT_TEXT_SIZE = 14;
	private PApplet parent;
	private Engine engine;
	private Button[] buttons;
	private boolean debug;
	private int clickedIndex;

	public Display(PApplet p, Engine e) {
		this.parent = p;
		this.engine = e;
		debug = false;
		buttons = new Button[4];

		buttons[0] = new Button("Mass", "create a massive object", p.width - 50, 100, 30, true) {
			public void click(PApplet p, Engine e) {
				e.addProjectile(inputFrame.getInputs()[0], inputFrame.getInputs()[1], p.mouseX, p.mouseY);
			}
			public void init() {
				addInputFrame(2, 20, 100);
				String[] names = {"Mass", "Radius"};
				inputFrame.setInputNames(names);
				float[] inputs = {400000, 100};
				inputFrame.setInputs(inputs);
			}
		};
		buttons[1] = new Button("Vector", "change the velocity and direction of an object", p.width - 50, 150, 30, true) {
			public void click(PApplet p, Engine e) {
				
			}
		};
		buttons[2] = new Button("Settings", "change game settings like time speed", p.width - 50, 200, 30, true) {
			public void click(PApplet p, Engine e) {
				
			}
		};
		buttons[3] = new Button("Test", "this is a test button", p.width - 50, 250, 30, true) {
			public void click(PApplet p, Engine e) {
				
			}
		};
		buttons[3].addInputFrame(4, 20, 100);
	}

	public void drawScreen(Engine e) {
		for (Projectile p : e.getProjectiles()) {
			if (debug) {
				parent.fill(255);
				parent.textAlign(parent.LEFT);
				parent.textSize(DEFAULT_TEXT_SIZE);
				parent.text(p.toString(), p.getX() - p.getDiameter(), p.getY()
						- p.getDiameter());
			}
			determineFill(p.getType());
			parent.ellipse(p.getX(), p.getY(), p.getDiameter(), p.getDiameter());
		}
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setX(parent.width - 50);
			if (buttons[i].isClicked() && i != clickedIndex) {
				buttons[i].setClicked(false);
			}
			buttons[i].display(parent);
		}
		if (debug) {
			
			
			parent.fill(255);
			parent.textAlign(parent.LEFT);
			parent.textSize(DEFAULT_TEXT_SIZE);
			parent.text("DEBUG", 5, 10);
			parent.text("timeSpeed: " + e.getTimeSpeed() + " projectiles: "
					+ e.getProjectiles().size(), 5, 20);
		}
	}

	public boolean isMouseFree() {
		for (Button b : buttons) {
			if (b.isHovering(parent))
				return false;
		}
		return true;
	}

	public void determineFill(int type) {
		extractColor(Color.searchColor(type));
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
