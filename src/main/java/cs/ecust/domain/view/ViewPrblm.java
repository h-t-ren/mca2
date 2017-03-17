package cs.ecust.domain.view;

import java.util.Date;

public class ViewPrblm {

	private Date createdTS;
	private String owner;
	private String prblmtype;
	private String pname;
	private String idprblm;

	public ViewPrblm() {

	}

	public Date getcreatedTS() {
		return createdTS;
	}

	public String getowner() {
		return owner;
	}

	public String getpname() {
		return pname;
	}

	public String getprblmtype() {
		return prblmtype;
	}

	public String getidprblm() {
		return idprblm;
	}

	public void setcreatedTS(Date createdTS) {

		this.createdTS = createdTS;
	}

	public void setowner(String owner) {
		this.owner = owner;
	}

	public void setpname(String pname) {
		this.pname = pname;
	}

	public void setprblmtype(String prblmtype) {
		this.prblmtype = prblmtype;
	}

	public void setidprblm(String idprblm) {
		this.idprblm = idprblm;
	}

}
