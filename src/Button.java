import processing.core.PApplet;

public class Button {
	private String name, description;
	private float x, y;
	private int size, fill;
	boolean clicked;

	public Button(String n, String d, int x, int y, int s) {
		name = n;
		description = d;
		this.x = x;
		this.y = y;
		size = s;
		fill = 200;
		clicked = false;
	}
	
	public void display(PApplet p) {
		hover(p);
		p.fill(fill);
		p.rect(x, y, size, size);
		if (isHovering(p)) {
			p.fill(255);
			p.textAlign(p.RIGHT);
			p.text(description, p.mouseX, p.mouseY);
		}		
}

	public void click(PApplet p) {
		// to be instantiated
	}

	public void hover(PApplet p) {
		if (isHovering(p) && clicked) {
			fill = 150;
		} else if (isHovering(p)) {
			fill = 175;
		} else {
			fill = 200;
		}
	}

	public boolean isHovering(PApplet p) {
		if (p.mouseX >= x && p.mouseX <= x + size && p.mouseY >= y
				&& p.mouseY <= y + size) {
			return true;
		}
		return false;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFill() {
		return fill;
	}

	public void setFill(int fill) {
		this.fill = fill;
	}
}
