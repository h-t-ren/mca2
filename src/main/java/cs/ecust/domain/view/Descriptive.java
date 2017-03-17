package cs.ecust.domain.view;

public class Descriptive {

	public String shrtnm;
	public String lngnm;
	public String dscrptn;

	public Descriptive() {

	}

	public Descriptive(String shrtnm, String lngnm, String dscrptn) {
		this.shrtnm = shrtnm;
		this.lngnm = lngnm;
		this.dscrptn = dscrptn;
	}

	public String getDscrptn() {
		return dscrptn;
	}

	public void setDscrptn(String dscrptn) {
		this.dscrptn = dscrptn;
	}

	public String getLngnm() {
		if (lngnm == null)
			return shrtnm;
		return lngnm;
	}

	public void setLngnm(String lngnm) {
		this.lngnm = lngnm;
	}

	public String getShrtnm() {
		return shrtnm;
	}

	public void setShrtnm(String shrtnm) {
		this.shrtnm = shrtnm;
	}

	public void setAllDscrptv(String shrtnm, String lngnm, String dscrptn) {
		this.shrtnm = shrtnm;
		this.lngnm = lngnm;
		this.dscrptn = dscrptn;
	}
}
