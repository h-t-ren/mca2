package cs.ecust.domain.view;

import java.util.ArrayList;


public class TmpAltrntv extends Descriptive {
	ArrayList<Double> values;
	public long AltrntvId;
	public int rank;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setAltrntvId(long AltrntvId) {
		this.AltrntvId = AltrntvId;
	}

	public long getAltrntvId() {
		return this.AltrntvId;
	}

	public TmpAltrntv(String shrtnm, String lngnm, String dscrptn) {
		super(shrtnm, lngnm, dscrptn);
		values = new ArrayList<Double>();
	}

	public TmpAltrntv(String sn,String nm,String desc, int rank) {
		super(sn,nm,desc);
		setRank(rank);
	}

	public TmpAltrntv(long AltrntvId, String shrtnm, String lngnm,
			String dscrptn) {
		super(shrtnm, lngnm, dscrptn);
		values = new ArrayList<Double>();
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
