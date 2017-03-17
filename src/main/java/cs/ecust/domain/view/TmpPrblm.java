package cs.ecust.domain.view;

import java.util.Date;
import java.util.List;


public class TmpPrblm extends Descriptive {
	private long id;

	List<TmpAtrbt> atrbts;
	List<TmpAltrntv> altrntvs;
	// added by ren
	public Date createdTS;
	public String owner;
	public String prblmtype;
	public String pname;
	public String Idprblm;
	public Boolean commit =false;
	public String getTemplateString() {
		return templateString;
	}

	public void setTemplateString(String templateString) {
		this.templateString = templateString;
	}

	public String templateString;

	public Boolean getCommit() {
		return commit;
	}

	public void setCommit(Boolean commit) {
		this.commit = commit;
	}

	public TmpPrblm() {
	}

	public TmpPrblm(String shrtnm, String lngnm, String dscrptn) {
		super(shrtnm, lngnm, dscrptn);
	}



	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}


	public Date getDate() {
		return createdTS;
	}

	public String getowner() {
		return owner;
	}

	public String getname() {
		return pname;
	}

	public String getprblmtype() {
		return prblmtype;
	}

	public String getIdprblm() {
		return Idprblm;
	}

	public void setDate(Date createdTS) {
		this.createdTS = createdTS;
	}

	public void setowner(String owner) {
		this.owner = owner;
	}

	public void setname(String pname) {
		this.pname = pname;
	}

	public void setprblmtype(String prblmtype) {
		this.prblmtype = prblmtype;
	}

	public void setIdprblm(String Idprblm) {
		this.Idprblm = Idprblm;
	}

	public List<TmpAltrntv> getAltrntvs() {
		return altrntvs;
	}

	public void setAltrntvs(List<TmpAltrntv> altrntvs) {
		this.altrntvs = altrntvs;
	}

	public List<TmpAtrbt> getAtrbts() {
		return atrbts;
	}

	public void setAtrbts(List<TmpAtrbt> atrbts) {
		this.atrbts = atrbts;
	}

}
