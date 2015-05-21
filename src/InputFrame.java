
import java.util.ArrayList;

import processing.core.PApplet;


public class InputFrame extends Button{
	private final int BUTTON_SIZE = 25, BORDER_SIZE = 10;
	private int[] inputs;
	private Button[] buttons;
	private String[] inputNames;
	
	public InputFrame(int numInputs, int x, int y, int w, int l) {
		super("", "", x, y, w, l);
		inputs = new int[numInputs];
		inputNames = new String[numInputs];
		buttons = new Button[numInputs * 2];
		
		super.setW((BORDER_SIZE * 2) + (BUTTON_SIZE * 3));
		super.setL( ((BORDER_SIZE * 2) * inputs.length) + (BUTTON_SIZE * inputs.length) );
		
		int index = 0;
		for(int i = y + BORDER_SIZE; i < y + l; i += BUTTON_SIZE + (BORDER_SIZE * 2)) {
			buttons[index] = new Button("", "decreases value of parameter " + inputNames[index], x + BORDER_SIZE, i, BUTTON_SIZE){
				public void click(PApplet p, Engine e) {
					
				}
			};
			index++;
			buttons[index] = new Button("", "increases value of parameter " + inputNames[index], x + BORDER_SIZE + (BUTTON_SIZE * 2), i, BUTTON_SIZE){
				public void click(PApplet p, Engine e) {
					
				}
			};
			if (index < buttons.length) index++;
		}
	}
	
	public void display(PApplet p) {
		p.fill(200);
		p.rect(x, y, w, l);
		
	}
}
