import processing.core.PApplet;
import processing.core.PConstants;

public class Button {
	protected String name, description;
	protected float x, y, increment;
	protected int w, l, fill;
	protected boolean clicked, toggled;
	protected InputFrame inputFrame;
	protected int clickCount; //Counts clicks and doubles as index variable (for InputFrame)

	public Button(String n, String d, float x, float y, int s, boolean toggle) {
		name = n;
		description = d;
		this.x = x;
		this.y = y;
		w = s;
		l = s;
		fill = 200;
		clicked = false;
		toggled = toggle; // true = toggle enabled
		inputFrame = null;
		clickCount = 0;
	}

	public Button(String n, String d, float x, float y, int w, int l,
			boolean toggle) {
		name = n;
		description = d;
		this.x = x;
		this.y = y;
		this.w = w;
		this.l = l;
		fill = 200;
		clicked = false;
		toggled = toggle;
		inputFrame = null;
		clickCount = 0;
	}

	public void display(PApplet p) {
		hover(p);
		p.fill(fill);
		p.rect(x, y, w, l);
		p.fill(255);
		p.textAlign(PConstants.RIGHT, PConstants.TOP);
		p.textSize(Display.DEFAULT_TEXT_SIZE);
		p.text(name, x + w, y + l);
		if (isHovering(p)) {
			p.fill(255);
			int align = (p.mouseX < 300) ? PConstants.LEFT : PConstants.RIGHT;
			p.textAlign(align);
			p.textSize(Display.DEFAULT_TEXT_SIZE);
			p.text(description, p.mouseX, p.mouseY);
		}
		if (isClicked() && inputFrame != null) {
			inputFrame.display(p);
		}
	}

	public void click(PApplet p, Engine e) {
		clickCount++;
		// to be re-instantiated
	}

	public void init() {
		// to be instantiated
	}

	public void hover(PApplet p) {
		if (clicked) {
			fill = 100;
		} else if (isHovering(p)) {
			fill = 150;
		} else {
			fill = 200;
		}
	}

	public boolean isHovering(PApplet p) {
		if (p.mouseX >= x && p.mouseX <= x + w && p.mouseY >= y
				&& p.mouseY <= y + l) {
			return true;
		}
		return false;
	}
	
	public InputFrame getInputFrame() {
		return this.inputFrame;
	}

	public void addInputFrame(InputFrame frame) {
		inputFrame = frame;
	}

	public void addInputFrame(int numInputs, float x, float y) {
		inputFrame = new InputFrame(numInputs, x, y);
		inputFrame.init();
	}

	public void toggleClicked() {
		clicked = !clicked;
	}

	public boolean isClicked() {
		return clicked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getSize() {
		if (w == l)
			return w;
		else
			return 0;
	}

	public void setSize(int s) {
		this.w = s;
		this.l = s;
	}

	public int getFill() {
		return fill;
	}

	public void setFill(int fill) {
		this.fill = fill;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	
	public boolean getToggled() {
		return toggled;
	}

	public float getIncrement() {
		return increment;
	}

	public void setIncrement(float increment) {
		this.increment = increment;
	}
}
