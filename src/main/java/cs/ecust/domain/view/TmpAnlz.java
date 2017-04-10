package cs.ecust.domain.view;

public class TmpAnlz extends Descriptive {

	private Long problemId;
	private Long instanceId;
	private Long idAnlz;
	private String snm;
	private String nm;
	private String dscr;
	
	public Long getProblemId() {
		return problemId;
	}
	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}
	public Long getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(Long instanceId) {
		this.instanceId = instanceId;
	}
	public Long getIdAnlz() {
		return idAnlz;
	}
	public void setIdAnlz(Long idAnlz) {
		this.idAnlz = idAnlz;
	}
	public String getSnm() {
		return snm;
	}
	public void setSnm(String snm) {
		this.snm = snm;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getDscr() {
		return dscr;
	}
	public void setDscr(String dscr) {
		this.dscr = dscr;
	}


}
