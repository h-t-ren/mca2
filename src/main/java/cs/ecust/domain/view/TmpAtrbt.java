package cs.ecust.domain.view;

import java.util.ArrayList;



public class TmpAtrbt extends Descriptive {
	String unit;
	public String type;
	public long atrbtId;
	ArrayList<Double> values = new ArrayList<Double>();;

	public TmpAtrbt(String shrtnm, String lngnm, String dscrptn, String unit) {
		super(shrtnm, lngnm, dscrptn);
		this.unit = unit;

	}


	public TmpAtrbt(long atrbtId) {
		this.atrbtId = atrbtId;

	}

	public TmpAtrbt(String shrtnm, String lngnm, String dscrptn, String unit,long atrbtId) {
		super(shrtnm, lngnm, dscrptn);
		this.unit = unit;
		this.atrbtId = atrbtId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setAllDscrptv(String shrtnm, String lngnm, String dscrptn,
			String unit) {
		this.shrtnm = shrtnm;
		this.lngnm = lngnm;
		this.dscrptn = dscrptn;
		this.unit = unit;
	}

	public ArrayList<Double> getValues() {
		return values;
	}

	public void addValue(Double value) {
		values.add(value);
	}

	public void addValues(ArrayList<Double> vls) {
		values = vls;
	}

}
