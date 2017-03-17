package cs.ecust.domain.view;

import java.util.Date;

public class ViewPrblmInstnc {

	private Date createdTS;
	private String owner;

	private String piname;
	private String idprblmInstnc;

	public ViewPrblmInstnc() {

	}

	public Date getcreatedTS() {
		return createdTS;
	}

	public String getowner() {
		return owner;
	}

	public String getpiname() {
		return piname;
	}

	public String getidprblmInstnc() {
		return idprblmInstnc;
	}

	public void setcreatedTS(Date createdTS) {

		this.createdTS = createdTS;
	}

	public void setowner(String owner) {
		this.owner = owner;
	}

	public void setpiname(String piname) {
		this.piname = piname;
	}

	public void setidprblmInstnc(String idprblmInstnc) {
		this.idprblmInstnc = idprblmInstnc;
	}

}
