public class Mass {
	protected double mass;
	protected String type;
	protected float x, y, radius;
	protected static final double SCALE = Math.pow(5.5, 12);

	public Mass(double m, float radius, float x, float y) {
		mass = m;
		this.radius = radius;
		updateType();
		this.x = x;
		this.y = y;
	}

	public Mass(double m, float x, float y) {
		mass = m;
		updateRadius();
		updateType();
		this.x = x;
		this.y = y;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
		updateRadius();
	}

	public String getType() {
		return type;
	}

	public void updateType() {
		if (radius < 10)
			type = "asteroid";
		else if (radius < 20)
			type = "moon";
		else if (radius < 60)
			type = "planet";
		else if (radius < 200)
			type = "star";
		else
			type = "hole";
	}

	public void updateRadius() {
		radius = (float) (mass / SCALE);
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
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
