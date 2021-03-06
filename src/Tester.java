import processing.core.PApplet;

@SuppressWarnings("serial")
public class Tester extends PApplet {
	Display d;
	Engine e;
	boolean auto;
	double autoChance;
	boolean paused;

	public void setup() {
		size(900, 900);
		d = new Display(this, e);
		e = new Engine();
		auto = false;
		autoChance = .01;
		paused = false;
//		d.initButtons();
	}

	public void draw() {
		if (!paused) {
			background(0);
			e.simulate();
			d.drawScreen(e);
			if (auto)
				auto();
		} else
			text("PAUSED", width - width / 2, 10);
	}

	public void keyReleased() {
		if (key == CODED) {
			if (keyCode == LEFT)
				e.changeTimeSpeed(-0.1);
			if (keyCode == RIGHT)
				e.changeTimeSpeed(0.1);
		}
		if (key == ' ')
			e.addRanProjectile(width, height);
		if (key == 'd' || key == 'D')
			d.switchDebug();
		if (key == 'r' || key == 'R')
			e.clearProjectiles();
		if (key == 'a' || key == 'A')
			auto = !auto;
		if (key == 'p' || key == 'P')
			paused = !paused;
		if (key == 's' || key == 'S')
			for (int i = 0; i < 10; i++)
				e.addRanProjectile(width, height);
	}

	public void auto() {
		if (Math.random() < autoChance)
			e.addRanProjectile(width, height);
		text("Auto", width - 50, 10);
	}

	public void mouseReleased() {
		if (mouseButton == LEFT) {
			e.addProjectile(e.EARTHMASS, mouseX, mouseY);
//			if (d.isMouseFree())
//				d.getButtons().get(d.getClickedIndex()).click(this, e);
//			for (int i = 0; i < d.getButtons().size(); i++) {
//				if (d.getButtons().get(i).isHovering(this)
//						&& d.getButtons().get(i).getToggled()) {
//					d.setClickedIndex(i);
//					d.getButtons().get(i).toggleClicked();
//				}
//			}
		}
		if (mouseButton == RIGHT) {
			e.removeProjectile((float) mouseX, (float) mouseY);
		}
	}
}
