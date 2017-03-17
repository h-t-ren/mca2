package cs.ecust.domain.view;

import java.util.List;

import javax.swing.tree.DefaultTreeModel;



public class TmpPrblmInstnc extends Descriptive {
	long prblmid;
	List<TmpAtrbt> attrbts;
	List<TmpAltrntv> altrntvs;
	private List<TmpCrtrn> crtria;
	private String htmlCriteriaTree;
	private DefaultTreeModel crtriaTree;
	public Boolean commit;
	private Integer hierarchy = 0;

	public TmpPrblmInstnc() {

	}

	public TmpPrblmInstnc(String shrtnm, String lngnm, String dscrptn,
			long prblmid) {
		super(shrtnm, lngnm, dscrptn);
		this.prblmid = prblmid;
	}



	public Boolean getCommit() {
		return commit;
	}

	public void setCommit(Boolean commit) {
		this.commit = commit;
	}

	public long getPrblmid() {
		return prblmid;
	}


	public List<TmpAltrntv> getAltrntvs() {
		return altrntvs;
	}

	public void setAltrntvs(List<TmpAltrntv> altrntvs) {
		this.altrntvs = altrntvs;
	}

	public List<TmpAtrbt> getAttrbts() {
		return attrbts;
	}

	public void setAttrbts(List<TmpAtrbt> attrbts) {
		this.attrbts = attrbts;
	}

	public void setHtmlCriteriaTree(String htmlCriteriaTree) {
		this.htmlCriteriaTree = htmlCriteriaTree;
	}

	public String getHtmlCriteriaTree() {
		return htmlCriteriaTree;
	}

	public void setCrtriaTree(DefaultTreeModel crtriaTree) {
		this.crtriaTree = crtriaTree;
	}

	public DefaultTreeModel getCrtriaTree() {
		return crtriaTree;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

	public Integer getHierarchy() {
		return hierarchy;
	}

	public void setCrtria(List<TmpCrtrn> crtria) {
		this.crtria = crtria;
	}

	public List<TmpCrtrn> getCrtria() {
		return crtria;
	}

}
