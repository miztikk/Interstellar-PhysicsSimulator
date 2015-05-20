public class Projectile extends Mass {
	protected double xVel, yVel, xAcc, yAcc;

	public Projectile(double m, float radius, float x, float y, double xV,
			double yV, double xA, double yA) {
		super(m, radius, x, y);
		xVel = xV;
		yVel = yV;
		xAcc = xA;
		yAcc = yA;
	}

	public Projectile(double m, float x, float y, double xV, double yV,
			double xA, double yA) {
		super(m, x, y);
		xVel = xV;
		yVel = yV;
		xAcc = xA;
		yAcc = yA;
	}

	public void move(double timeSpeed) {
		xVel += xAcc * timeSpeed;
		yVel += yAcc * timeSpeed;
		x += xVel * timeSpeed;
		y += yVel * timeSpeed;
		xAcc = yAcc = 0;
	}

	public double getxVel() {
		return xVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public double getxAcc() {
		return xAcc;
	}

	public void changexAcc(double xAcc) {
		this.xAcc += xAcc;
	}

	public void setxAcc(double xAcc) {
		this.xAcc = xAcc;
	}

	public double getyAcc() {
		return yAcc;
	}

	public void changeyAcc(double yAcc) {
		this.yAcc += yAcc;
	}

	public void setyAcc(double yAcc) {
		this.yAcc = yAcc;
	}

	public String toString() {
		return "Mass: " + mass + " Radius: " + diameter + " (" + Math.round(x)
				+ "," + Math.round(y) + ") Type: " + type + " "
				+ Math.round(xVel) + " " + Math.round(yVel) + " "
				+ Math.round(xAcc) + " " + Math.round(yAcc);
	}
}
