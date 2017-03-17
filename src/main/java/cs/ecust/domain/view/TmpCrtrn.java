package cs.ecust.domain.view;

public class TmpCrtrn extends Descriptive {

	String parentNm;
	String atrbtNm;
	String type;
	String trgt;
	int line;

	public TmpCrtrn(String shrtnm, String lngnm, String attrbtShrtNm,
			String parentShrtNm, String type, String target, int line) {
		// TODO Auto-generated constructor stub
		super(shrtnm, lngnm, null);
		parentNm = parentShrtNm;
		atrbtNm = attrbtShrtNm;
		this.type = type;
		trgt = target;
		this.line = line;
	}

	public String getAtrbtNm() {
		return atrbtNm;
	}

	public String getParentnm() {
		return parentNm;
	}

	public String getTrgt() {
		return trgt;
	}

	public String getType() {
		return type;
	}

	public int getLine() {
		return line;
	}

	@Override
	public String toString() {
		return shrtnm;
	}
}
