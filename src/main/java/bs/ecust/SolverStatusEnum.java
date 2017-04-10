/**
 * 
 */
package bs.ecust;


public enum SolverStatusEnum  {

	INITIAL(0), SUBSEQUENCE(1), OK(2), ERROR(3);

	private final long elementCode;

	SolverStatusEnum(long elementCode) {
		this.elementCode = elementCode;
	}

	public long elementCode() {
		return elementCode;
	}
}
