public class Mass {
	protected double mass;
	protected String type;
	protected float x, y, diameter;
	protected static final double SCALE = Math.pow(5.5, 12);

	public Mass(double m, float diameter, float x, float y) {
		mass = m;
		this.diameter = diameter;
		updateType();
		this.x = x;
		this.y = y;
	}

	public Mass(double m, float x, float y) {
		mass = m;
		updateDiameter();
		updateType();
		this.x = x;
		this.y = y;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
		updateDiameter();
		updateType();
	}

	public String getType() {
		return type;
	}

	public void updateType() {
		if (diameter < 10)
			type = "asteroid";
		else if (diameter < 20)
			type = "moon";
		else if (diameter < 80)
			type = "planet";
		else if (diameter < 400)
			type = "star";
		else
			type = "hole";
	}

	public void updateDiameter() {
		diameter = (float) (mass / SCALE);
	}

	public float getDiameter() {
		return diameter;
	}

	public void setDiameter(float diameter) {
		this.diameter = diameter;
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

}
