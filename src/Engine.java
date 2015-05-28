import java.util.ArrayList;
import java.util.Iterator;

public class Engine {
	private ArrayList<Projectile> projectiles;
	private ArrayList<Projectile> temp;
	private double timeSpeed;
	private static final double G = Math.pow(6.673, -11);
	public static final double EARTHMASS = Math.pow(5.972, 14) / 2;
	public static final int VELOCITY = 0, ACCELERATION = 1, POSITION = 2;

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
	
	public void vector(int type, float scale, Projectile projectile, float x2, float y2) {
		if (x2 - projectile.getXValue(type) == 0)
			projectile.setYValue(type, scale * (y2 - projectile.getYValue(type)));
		if (y2 - projectile.getYValue(type) == 0)
			projectile.setXValue(type, scale * (x2 - projectile.getXValue(type)));
		double hyp = Math.tan((x2 - projectile.getXValue(type)) / y2 - projectile.getYValue(type));
		projectile.setXValue(type, scale * (Math.cos(x2 - projectile.getXValue(type) / hyp)));
		projectile.setYValue(type, scale * (Math.sin(y2 - projectile.getYValue(type) / hyp)));
	}

	public void collision(Projectile p, Projectile p2, double dist) {
		if (dist < p.getDiameter() / 2 + p2.getDiameter() / 2) {
			if (p.getMass() <= p2.getMass())
				collide(p, p2);
			else
				collide(p2, p);
		}
	}

	// p2 is the larger mass
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

	public void addProjectile(double m, float x, float y) {
		projectiles.add(new Projectile(m, x, y));
	}

	public void addProjectile(Projectile newProjectile) {
		projectiles.add(newProjectile);
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

	// For clicking
	public void addRanProjectile(float mouseX, float mouseY) {
		addProjectile(new Projectile(EARTHMASS, mouseX, mouseY, 0, 0, 0, 0),
				projectiles);
	}

	// For tiny masses
	public void removeProjectiles() {
		for (Iterator<Projectile> iter = projectiles.iterator(); iter.hasNext();) {
			Projectile p = iter.next();
			if (p.getMass() <= 1 || p.getDiameter() <= 1)
				iter.remove();
		}
	}

	// For collisions
	public void removeProjectile(float x, float y) {
		for (Iterator<Projectile> iter = projectiles.iterator(); iter.hasNext();) {
			Projectile p = iter.next();
			if (Math.sqrt((x - p.getX()) * (x - p.getX()) + (y - p.getY())
					* (y - p.getY())) < p.getDiameter() / 2) {
				iter.remove();
			}
		}
	}

	public boolean isMouseFree(float x, float y) {
		for (Iterator<Projectile> iter = projectiles.iterator(); iter.hasNext();) {
			Projectile projectile = iter.next();
			if (Math.sqrt((x - projectile.getX()) * (x - projectile.getX())
					+ (y - projectile.getY()) * (y - projectile.getY())) < projectile
					.getDiameter() / 2) {
				return false;
			}
		}
		return true;
	}

	public Projectile projectileAt(float x, float y) {
		for (Iterator<Projectile> iter = projectiles.iterator(); iter.hasNext();) {
			Projectile projectile = iter.next();
			if (Math.sqrt((x - projectile.getX()) * (x - projectile.getX())
					+ (y - projectile.getY()) * (y - projectile.getY())) < projectile
					.getDiameter() / 2) {
				return projectile;
			}
		}
		return null;
	}
	
	public Projectile projectileSelected() {
		for (Iterator<Projectile> iter = projectiles.iterator(); iter.hasNext();) {
			Projectile projectile = iter.next();
			if (projectile.getIsSelected()) return projectile;
		}
		return null;
	}
	
	public void selectProjectile(float x, float y) {
		for (Iterator<Projectile> iter = projectiles.iterator(); iter.hasNext();) {
			Projectile projectile = iter.next();
			if (projectile.getIsSelected())
				projectile.setIsSelected(false);
		}
		projectileAt(x, y).setIsSelected(true);
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
