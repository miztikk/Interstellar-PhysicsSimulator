public class Mass {
	protected double mass;
	protected float x, y, radius;

	public Mass(double m, float radius, float x, float y) {
		mass = m;
		this.radius = radius;
		this.x = x;
		this.y = y;
	}

	public Mass(double m, float x, float y) {
		mass = m;
		radius = (float) ((mass / 6000.0) / Math.pow(4.3, 8));
		this.x = x;
		this.y = y;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
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
