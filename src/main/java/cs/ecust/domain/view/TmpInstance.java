package cs.ecust.domain.view;

import java.util.ArrayList;
import java.util.List;


public class TmpInstance extends Descriptive {
	
	private Long problemId;

	private List<Long> atrbtIds = new ArrayList<Long>(0);
	private List<String> crtrnSnms = new ArrayList<String>(0);
	private List<String> crtrnNms = new ArrayList<String>(0);
	private List<String> types = new ArrayList<String>(0);
	
	
	public List<Long> getAtrbtIds() {
		return atrbtIds;
	}
	public void setAtrbtIds(List<Long> atrbtIds) {
		this.atrbtIds = atrbtIds;
	}
	public List<String> getCrtrnSnms() {
		return crtrnSnms;
	}
	public void setCrtrnSnms(List<String> crtrnSnms) {
		this.crtrnSnms = crtrnSnms;
	}
	public List<String> getCrtrnNms() {
		return crtrnNms;
	}
	public void setCrtrnNms(List<String> crtrnNms) {
		this.crtrnNms = crtrnNms;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public Long getProblemId() {
		return problemId;
	}
	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}

	
}
