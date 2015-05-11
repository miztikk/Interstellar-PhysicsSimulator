import java.util.ArrayList;
import java.util.Iterator;

public class Engine {
	private ArrayList<Projectile> projectiles;
	private double timeSpeed;
	private static final double G = Math.pow(6.673, -11);

	public Engine() {
		projectiles = new ArrayList<Projectile>();
		timeSpeed = 1;
	}

	public void simulate() {
		for (Projectile p : projectiles) {
			for (Projectile p2 : projectiles) {
				if (p != p2) {
					double dist = Math.sqrt((p2.getX() - p.getX())
							* (p2.getX() - p.getX()) + (p2.getY() - p.getY())
							* (p2.getY() - p.getY()));
					gravAttraction(p, p2, dist);
					collision(p, p2, dist);
				}
			}
			p.move(timeSpeed);
		}
	}

	public void collision(Projectile p, Projectile p2, double dist) {
		if (dist < p.getRadius() / 2 + p2.getRadius() / 2) {
			p.setxVel(p.getxVel() * -.9);
			p.setyVel(p.getyVel() * -.9);
			p.setxAcc(p.getxAcc() * -.9);
			p.setyAcc(p.getyAcc() * -.9);
			p2.setxVel(p2.getxVel() * -.9);
			p2.setyVel(p2.getyVel() * -.9);
			p2.setxAcc(p2.getxAcc() * -.9);
			p2.setyAcc(p2.getyAcc() * -.9);
		}
	}

	public void gravAttraction(Projectile p, Projectile p2, double dist) {
		double force = G * p.getMass() * p2.getMass() / (dist * dist);
		double acc = force / p.getMass();
		double ang = Math.atan2(p2.getY() - p.getY(), p2.getX() - p.getX());
		p.changexAcc(acc * Math.cos(ang) * timeSpeed);
		p.changeyAcc(acc * Math.sin(ang) * timeSpeed);

	}

	public void addProjectile(Projectile m) {
		projectiles.add(m);
	}

	public void addRanProjectile(int width, int height) {
		addProjectile(new Projectile(Math.pow(9, 10),
				(float) (Math.random() * width),
				(float) (Math.random() * height), (float) (Math.random() - .5),
				(float) (Math.random() - .5), 0, 0));
	}

	public void addRanProjectile(float x, float y) {
		addProjectile(new Projectile(Math.pow(6, 14), x, y, 0, 0, 0, 0));
	}

	public void removeProjectile(float x, float y) {
		for (Iterator<Projectile> iter = projectiles.iterator(); iter.hasNext();) {
			Projectile p = iter.next();
			if (Math.sqrt((x - p.getX()) * (x - p.getX()) + (y - p.getY())
					* (y - p.getY())) < p.getRadius() / 2) {
				iter.remove();
			}
		}
	}

	public void changeTimeSpeed(double val) {
		timeSpeed += val;
	}

	public double getTimeSpeed() {
		return timeSpeed;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void clearProjectiles() {
		projectiles.clear();
	}

}
