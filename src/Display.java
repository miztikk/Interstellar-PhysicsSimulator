import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class Display {
	public static final float DEFAULT_TEXT_SIZE = 14;
	private static final int NUM_BUTTONS = 3;
	private PApplet parent;
	private ArrayList<Button> buttons;
	private boolean debug;
	private int clickedIndex;

	public Display(PApplet p, Engine e) {
		this.parent = p;
		debug = false;
		buttons = new ArrayList<Button>();
		
	}
	
	public void initButtons() {
//		for (int i = 0; i < NUM_BUTTONS; i++) {
//			buttons.add(createButton(i));
//			buttons.get(i).init();
//			buttons.add(buttons.get(i).getInputFrame());
//		}
	}

	public void drawScreen(Engine e) {
		for (Projectile p : e.getProjectiles()) {
			if (debug) {
				parent.fill(255);
				parent.textAlign(PConstants.LEFT);
				parent.textSize(DEFAULT_TEXT_SIZE);
				parent.text(p.toString(), p.getX() - p.getDiameter(), p.getY()
						- p.getDiameter());
			}
			determineFill(p.getType());
			parent.ellipse(p.getX(), p.getY(), p.getDiameter(), p.getDiameter());
		}
//		for (int i = 0; i < buttons.size(); i++) {
//			if (!(buttons.get(i) instanceof InputFrame)) {
//				buttons.get(i).setX(parent.width - 50);
//				if (buttons.get(i).isClicked() && i != clickedIndex) {
//					buttons.get(i).setClicked(false);
//				}
//				buttons.get(i).display(parent);
//			}
//		}
		if (debug) {
			parent.fill(255);
			parent.textAlign(PConstants.LEFT);
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
	
	private Button createButton(int index) {
		if (index == 0) return createMassButton(parent);
		if (index == 1) return createVectorButton(parent);
		if (index == 2) return createSettingsButton(parent);
		return null;
	}
	
	private Button createMassButton(PApplet p) {
		return new Button("Mass", "create a massive object", p.width - 50,
				100, 30, true) {
			public void click(PApplet p, Engine e) {
				if (e.isMouseFree(p.mouseX, p.mouseY)) {
					e.addProjectile(inputFrame.getInputs()[0], p.mouseX, p.mouseY);
				}else{
					e.projectileAt(p.mouseX, p.mouseY).setMass(inputFrame.getInputs()[0]);
				}
			}
			public void init() {
				addInputFrame(2, 20, 100);
				inputFrame.init();
				String[] names = { "Mass", "Radius" };
				inputFrame.setInputNames(names);
				float[] inputs = { (float) Engine.EARTHMASS, 100 };
				inputFrame.setInputs(inputs);
			}
		};
	}
	
	private Button createVectorButton(PApplet p) {
		return new Button("Vector",
				"change the velocity and direction of an object", p.width - 50,
				150, 30, true) {
			public void click(PApplet p, Engine e) {
				clickCount = clickCount % 2;
				if (clickCount == 0) {
					if (!e.isMouseFree(p.mouseX, p.mouseY))
						e.selectProjectile(p.mouseX, p.mouseY);
				}
				if (clickCount == 1) {
					e.vector((int) inputFrame.getInput(0), inputFrame.getInput(1), e.projectileSelected(), p.mouseX, p.mouseY);
				}
			}
			public void init() {
				addInputFrame(2, 20, 100);
				inputFrame.init();
				String[] names = { "Type", "Scale"};
				inputFrame.setInputNames(names);
				float[] inputs = { 0, 1 };
				inputFrame.setInputs(inputs);
			}
		};
	}
	
	private Button createSettingsButton(PApplet p) {
		return new Button("Settings",
				"change game settings like time speed", p.width - 50, 200, 30,
				true) {
			public void click(PApplet p, Engine e) {

			}

			public void init() {
				addInputFrame(1, 20, 100);
				inputFrame.init();
				String[] names = { "Time Speed" };
				inputFrame.setInputNames(names);
				float[] inputs = { 1 };
				inputFrame.setInputs(inputs);
			}
		};
	}
}
