import processing.core.PApplet;

public class Tester extends PApplet {
	Display d;
	Engine e;

	public void setup() {
		size(600, 600);
		d = new Display(this);
		e = new Engine();

		d.drawScreen(e);
	}

	public void draw() {
		background(0);
		e.simulate();
		d.drawScreen(e);
	}

	public void keyReleased() {
		if (key == CODED) {
			if (keyCode == LEFT) {
				e.changeTimeSpeed((float) -0.1);
			}
			if (keyCode == RIGHT) {
				e.changeTimeSpeed((float) 0.1);
			}
		}
		if (key == ' ')
			e.addRanProjectile(width, height);
		if (key == 'd' || key == 'D')
			d.switchDebug();
		if (key == 'r' || key == 'R')
			e.clearProjectiles();
	}

	public void mouseReleased() {
		if (mouseButton == LEFT)
			e.addRanProjectile((float) mouseX, (float) mouseY);
		else
			e.removeProjectile((float) mouseX, (float) mouseY);
	}
}
