package cs.ecust.domain.view.jfree;

public class Hint {

	public String hint;
	public int xValue;
	public double yValue;

	public Hint(int xValue, double yValue, String hint) {

		this.xValue = xValue;
		this.yValue = yValue;
		this.hint = hint;

	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public int getXValue() {
		return this.xValue;
	}

	public void setXValue(int value) {
		this.xValue = value;
	}

	public double getYValue() {
		return this.yValue;
	}

	public void setYValue(double value) {
		this.yValue = value;
	}
}
