import java.util.ArrayList;
import java.util.Iterator;

public class Engine {
	private ArrayList<Projectile> projectiles;
	private ArrayList<Projectile> temp;
	private double timeSpeed;
	private static final double G = Math.pow(6.673, -11);
	private static final double EARTHMASS = Math.pow(5.972, 13);

	public Engine() {
		projectiles = new ArrayList<Projectile>();
		temp = new ArrayList<Projectile>();
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
		addProjectiles();
		removeProjectiles();
	}

	public void collision(Projectile p, Projectile p2, double dist) {
		if (dist < p.getDiameter() / 2 + p2.getDiameter() / 2) {
			if (p.getMass() <= p2.getMass())
				collide(p, p2);
			else
				collide(p2, p);
		}
	}

	public void collide(Projectile p, Projectile p2) {
		int limit = (int) (Math.random() * 9) + 1;
		if (p.getMass() > p2.getMass() * .5)
			for (int i = 0; i < limit; i++)
				addRanProjectile(p.getMass() * .5 / limit, p.getX(), p.getY(),
						p2.getxVel(), p2.getyVel(), p.getDiameter());
		p2.setxVel(.9 * (p2.getxVel() + (p.getxVel() * (p.getMass() / p2
				.getMass()))));
		p2.setyVel(.9 * (p2.getyVel() + (p.getyVel() * (p.getMass() / p2
				.getMass()))));
		p2.setMass(p2.getMass() + p.getMass() * .25);
		p.setMass(1);
	}

	public double gravAttraction(Projectile p, Projectile p2, double dist) {
		if (dist != 0) {
			double force = G * p.getMass() * p2.getMass() / (dist * dist);
			double acc = force / p.getMass();
			double ang = Math.atan2(p2.getY() - p.getY(), p2.getX() - p.getX());
			p.changexAcc(acc * Math.cos(ang) * timeSpeed);
			p.changeyAcc(acc * Math.sin(ang) * timeSpeed);
			return force;
		}
		return 0;
	}

	public void addProjectile(Projectile m, ArrayList<Projectile> list) {
		list.add(m);
	}

	public void addProjectiles() {
		for (Projectile p : temp)
			projectiles.add(p);
		temp.clear();
	}

	// For button presses
	public void addRanProjectile(int width, int height) {
		double mass = Math.random() * 2 + 6;
		double scale = 12;
		addProjectile(
				new Projectile(Math.pow(mass, scale),
						(float) (Math.random() * width),
						(float) (Math.random() * height),
						(float) (Math.random() * 2 - 1),
						(float) (Math.random() * 2 - 1), 0, 0), projectiles);
	}

	// For collisions
	public void addRanProjectile(double m, float x, float y, double xv,
			double yv, float d) {
		addProjectile(
				new Projectile(m, (float) (x + Math.random() * d - d / 2),
						(float) (y + Math.random() * d - d / 2), (float) (xv
								+ Math.random() * xv - xv / 2), (float) (yv
								+ Math.random() * yv - yv / 2), 0, 0), temp);
	}

	// For Clicking
	public void addRanProjectile(float mouseX, float mouseY) {
		addProjectile(new Projectile(EARTHMASS, mouseX, mouseY, 0, 0, 0, 0),
				projectiles);
	}

	public void removeProjectiles() {
		for (Iterator<Projectile> iter = projectiles.iterator(); iter.hasNext();) {
			Projectile p = iter.next();
			if (p.getMass() <= 1 || p.getDiameter() <= 1)
				iter.remove();
		}
	}

	public void removeProjectile(float x, float y) {
		for (Iterator<Projectile> iter = projectiles.iterator(); iter.hasNext();) {
			Projectile p = iter.next();
			if (Math.sqrt((x - p.getX()) * (x - p.getX()) + (y - p.getY())
					* (y - p.getY())) < p.getDiameter() / 2) {
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
