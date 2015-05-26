import java.util.ArrayList;

import processing.core.PApplet;

public class Display {
	public static final float DEFAULT_TEXT_SIZE = 14;
	private PApplet parent;
	private Engine engine;
	private ArrayList<Button> buttons;
	private boolean debug;
	private int clickedIndex;

	public Display(PApplet p, Engine e) {
		this.parent = p;
		this.engine = e;
		debug = false;
		buttons = new ArrayList<Button>();

		buttons.add(new Button("Mass", "create a massive object", p.width - 50, 100, 30, true) {
			public void click(PApplet p, Engine e) {
				e.addProjectile(inputFrame.getInputs()[0], inputFrame.getInputs()[1], p.mouseX, p.mouseY);
			}
			public void init() {
				addInputFrame(2, 20, 100);
				String[] names = {"Mass", "Radius"};
				inputFrame.setInputNames(names);
				float[] inputs = {400000, 100};
				inputFrame.setInputs(inputs);
				inputFrame.init();
			}
		});
		buttons.add(new Button("Vector", "change the velocity and direction of an object", p.width - 50, 150, 30, true) {
			public void click(PApplet p, Engine e) {
//				e.vector(inputFrame.getInputs()[0]);
			}
			public void init() {
				addInputFrame(1, 20, 100);
				String[] names = {"Scale"};
				inputFrame.setInputNames(names);
				float[] inputs = {1};
				inputFrame.setInputs(inputs);
				inputFrame.init();
			}
		});
		buttons.add(new Button("Settings", "change game settings like time speed", p.width - 50, 200, 30, true) {
			public void click(PApplet p, Engine e) {
				
			}
			public void init() {
				addInputFrame(1, 20, 100);
				String[] names = {"Time Speed"};
				inputFrame.setInputNames(names);
				float[] inputs = {1};
				inputFrame.setInputs(inputs);
				inputFrame.init();
			}
		});
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
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setX(parent.width - 50);
			if (buttons.get(i).isClicked() && i != clickedIndex) {
				buttons.get(i).setClicked(false);
			}
			buttons.get(i).display(parent);
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

	public ArrayList<Button> getButtons() {
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
