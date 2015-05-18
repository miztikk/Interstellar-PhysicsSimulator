public class Mass {
	protected double mass;
	protected int type;
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

	public int getType() {
		return type;
	}

	public void updateType() {
		if (type >= 0 && diameter < 10)
			this.type = 1;
		else if (type >= 0 && diameter < 20)
			this.type = 2;
		else if (type >= 0 && diameter < 80)
			this.type = 3;
		else if (type <= 0 && diameter < 400)
			this.type = -1;
		else if (type <= 0 && diameter < 800)
			this.type = -2;
		else if (diameter >= 800)
			this.type = -3;
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
