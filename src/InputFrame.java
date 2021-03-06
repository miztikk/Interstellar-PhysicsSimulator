import processing.core.PApplet;
import processing.core.PConstants;

public class InputFrame extends Button {
	private static final float DEFAULT_INCREMENT = 1;
	private final int BUTTON_SIZE = 30, BORDER_SIZE = 6, MID_GAP_SIZE = 50;
	private float[] inputs, minMax;
	private Button[] buttons;
	private String[] inputNames;

	public InputFrame(int numInputs, float x, float y) {
		super("", "", x, y, 0, 0, false);

		inputs = new float[numInputs];
		inputNames = new String[numInputs];
		buttons = new Button[numInputs * 2];
		minMax = new float[numInputs * 2];

		super.setW((BORDER_SIZE * 2) + (BUTTON_SIZE * 2) + MID_GAP_SIZE);
		super.setL(((BORDER_SIZE * 2) * inputs.length)
				+ (BUTTON_SIZE * inputs.length));

		for (int i = 0; i < inputs.length; i++)
			inputs[i] = 0;
		for (int i = 0; i < inputNames.length; i++)
			inputNames[i] = "Input " + i;
		for (int i = 0; i < minMax.length; i++) {
			if (i % 2 == 0)
				minMax[i] = Float.MIN_VALUE;
			else
				minMax[i] = Float.MAX_VALUE;
		} 
	}
	
	public void click(PApplet p, Engine e) {
		for (Button b : buttons) {
			if (b.isHovering(p))
				b.click(p, e);
		}
	}

	public void init() {
		int index = 0;
		int index1 = 0;
		for (int i = (int) (y + BORDER_SIZE); i < y + l; i += BUTTON_SIZE
				+ (BORDER_SIZE * 2)) {
			buttons[index] = new Button("", inputNames[index1] + "-", x
					+ BORDER_SIZE, i, BUTTON_SIZE, false) {
				public void click(PApplet p, Engine e) {
					float temp = inputFrame.getInputs()[clickCount];
					float min = inputFrame.getMin(clickCount);
					float max = inputFrame.getMax(clickCount);
					inputFrame.setInput(clickCount, ((temp - increment) % ((max + 1) - min) + min));
				}
			};
			buttons[index].setClickCount(index1);
			buttons[index].addInputFrame(this);
			buttons[index].setIncrement(DEFAULT_INCREMENT);
			if (index < buttons.length)
				index++;
			buttons[index] = new Button("", inputNames[index1] + "+", x
					+ BORDER_SIZE + BUTTON_SIZE + MID_GAP_SIZE, i, BUTTON_SIZE,
					false) {
				public void click(PApplet p, Engine e) {
					float temp = inputFrame.getInputs()[clickCount];
					float min = inputFrame.getMin(clickCount);
					float max = inputFrame.getMax(clickCount);
					inputFrame.setInput(clickCount, ((temp + increment) % ((max + 1) - min) + min));
				}
			};
			buttons[index].setClickCount(index1);
			buttons[index].addInputFrame(this);
			buttons[index].setIncrement(DEFAULT_INCREMENT);
			if (index < buttons.length)
				index++;
			if (index1 < inputNames.length)
				index1++;
		}
	}

	public void display(PApplet p) {
		p.fill(175);
		p.rect(x, y, w, l);
		p.fill(0);
		p.textAlign(PConstants.CENTER, PConstants.CENTER);
		p.textSize(9);
		for (int i = 0; i < inputs.length; i++) {
			p.text(inputNames[i], x + BORDER_SIZE + BUTTON_SIZE, y
					+ ((BORDER_SIZE * (i + 1)) + (BORDER_SIZE * i))
					+ (BUTTON_SIZE * i), MID_GAP_SIZE, BUTTON_SIZE / 2);
			String display = Float.toString(inputs[i]);
			p.text(display, x + BORDER_SIZE + BUTTON_SIZE, y
					+ (BORDER_SIZE * i)
					+ ((BORDER_SIZE + BUTTON_SIZE) * (i + 1))
					- (BUTTON_SIZE / 2), MID_GAP_SIZE, BUTTON_SIZE / 2);
		}
		for (Button b : buttons) {
			b.display(p);
		}
	}

	public float getInput(int index) {
		return inputs[index];
	}
	
	public float[] getInputs() {
		return inputs;
	}

	public void setInput(int index, float value) {
		inputs[index] = value;
	}

	public void setInputs(float[] inputs) {
		this.inputs = inputs;
	}

	public Button[] getButtons() {
		return buttons;
	}

	public String[] getInputNames() {
		return inputNames;
	}

	public void setInputName(int index, String value) {
		inputNames[index] = value;
	}

	public void setInputNames(String[] inputNames) {
		this.inputNames = inputNames;
	}
	
	public void setIncrement(int index, float value) {
		if (!(index < inputs.length && index >= 0)) return;
		buttons[(index * 2) - 1].setIncrement(value);
		buttons[index * 2].setIncrement(value);
	}
	
	public void setIncrements(float[] values) {
		if (values.length > inputs.length) return;
		for (int i = 0; i < values.length; i++) {
			buttons[(i * 2) - 1].setIncrement(values[i]);
			buttons[i * 2].setIncrement(values[i]);
		}
	}
	
	public float getMin(int index) {
		if (index >= inputs.length || index < 0) return Float.MIN_VALUE;
		return minMax[index * 2];
	}
	
	public float getMax(int index) {
		if (index >= inputs.length || index < 0) return Float.MAX_VALUE;
		return minMax[(index * 2) + 1];
	}
}
