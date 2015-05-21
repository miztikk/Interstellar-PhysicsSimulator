
import java.util.ArrayList;

import processing.core.PApplet;


public class InputFrame extends Button{
	private final int BUTTON_SIZE = 25;
	private int[] inputs;
	private Button[] buttons;
	
	public InputFrame(int numInputs, int x, int y, int w, int l) {
		super("", "", x, y, w, l);
		inputs = new int[numInputs];
		buttons = new Button[numInputs * 2];
	}
	
	public void display(PApplet p) {
		p.rect(x, y, w, l);
	}
}
