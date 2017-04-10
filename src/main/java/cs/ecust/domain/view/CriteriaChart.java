package cs.ecust.domain.view;

import java.util.List;
import java.util.Map;

import org.jfree.chart.annotations.XYBoxAnnotation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;

import bs.ecust.SolverStatusEnum;
import bs.ecust.domain.db.Altrntv;
import bs.ecust.domain.db.Crtrn;
import bs.ecust.domain.db.Itr;
import cs.ecust.domain.view.jfree.Hint;
import cs.ecust.domain.view.jfree.XYLineMarker;


public class CriteriaChart {

	private Itr currentAnlzItrtn;
	private SolverStatusEnum solverStatus;
	private Itr parentAnlzItrtn;
	private Itr rootAnlzItrtn;

	private List<Crtrn> criteria;
	private Altrntv bestAltrntv;
	private Altrntv previousAltrntv;
	private String[] selectedAlternatives;
	private List<Altrntv> altrntvlist;

	// Components need to display
	private XYSeriesCollection xyseriesCollection; // criteria dot, best
	// alternative dots,
	// previous best alternative
	// dot, selected
	// alternatives dots;
	private XYLineAndShapeRenderer xyLineAndShapeRenderer; // configure shape of
	// Series

	private List<XYBoxAnnotation> boxlist;
	private List<XYLineMarker> boxMiddleLineMarkerList;

	private List<Hint> hintLst;

	// display option
	private Integer criteriaDisplay;
	private Integer displayBoxplot;
	private Integer displayLine;
	private Integer zoomIn;
	private Integer zoomOut;

	private Integer width;
	private Integer hight;
	private Integer label_width;

	// user preference
	private Map<Integer, Integer> criterionImportancePerfernceMap;
	private Map<Integer, Integer> criterionImprovementPerfernceMap;
	
	public String isActive(Long idCAlt, Long idBest)
	{
		if(idCAlt.equals(idBest))
			return "true";
		else
			return "false";
	}

	public Itr getCurrentAnlzItrtn() {
		return currentAnlzItrtn;
	}

	public void setCurrentAnlzItrtn(Itr currentAnlzItrtn) {
		this.currentAnlzItrtn = currentAnlzItrtn;
	}

	public SolverStatusEnum getSolverStatus() {
		return solverStatus;
	}

	public void setSolverStatus(SolverStatusEnum solverStatus) {
		this.solverStatus = solverStatus;
	}

	public Itr getParentAnlzItrtn() {
		return parentAnlzItrtn;
	}

	public void setParentAnlzItrtn(Itr parentAnlzItrtn) {
		this.parentAnlzItrtn = parentAnlzItrtn;
	}

	public Altrntv getBestAltrntv() {
		return bestAltrntv;
	}

	public void setBestAltrntv(Altrntv bestAltrntv) {
		this.bestAltrntv = bestAltrntv;
	}

	public Altrntv getPreviousAltrntv() {
		return previousAltrntv;
	}

	public void setPreviousAltrntv(Altrntv previousAltrntv) {
		this.previousAltrntv = previousAltrntv;
	}

	public String[] getSelectedAlternatives() {
		return selectedAlternatives;
	}

	public void setSelectedAlternatives(String[] selectedAlternatives) {
		this.selectedAlternatives = selectedAlternatives;
	}

	public XYSeriesCollection getXyseriesCollection() {
		return xyseriesCollection;
	}

	public void setXyseriesCollection(XYSeriesCollection xyseriesCollection) {
		this.xyseriesCollection = xyseriesCollection;
	}

	public XYLineAndShapeRenderer getXyLineAndShapeRenderer() {
		return xyLineAndShapeRenderer;
	}

	public void setXyLineAndShapeRenderer(
			XYLineAndShapeRenderer xyLineAndShapeRenderer) {
		this.xyLineAndShapeRenderer = xyLineAndShapeRenderer;
	}

	public List<XYBoxAnnotation> getBoxlist() {
		return boxlist;
	}

	public void setBoxlist(List<XYBoxAnnotation> boxlist) {
		this.boxlist = boxlist;
	}

	public List<XYLineMarker> getBoxMiddleLineMarkerList() {
		return boxMiddleLineMarkerList;
	}

	public void setBoxMiddleLineMarkerList(
			List<XYLineMarker> boxMiddleLineMarkerList) {
		this.boxMiddleLineMarkerList = boxMiddleLineMarkerList;
	}

	public List<Hint> getHintLst() {
		return hintLst;
	}

	public void setHintLst(List<Hint> hintLst) {
		this.hintLst = hintLst;
	}

	public Map<Integer, Integer> getCriterionImportancePerfernceMap() {
		return criterionImportancePerfernceMap;
	}

	public void setCriterionImportancePerfernceMap(
			Map<Integer, Integer> criterionImportancePerfernceMap) {
		this.criterionImportancePerfernceMap = criterionImportancePerfernceMap;
	}

	public Map<Integer, Integer> getCriterionImprovementPerfernceMap() {
		return criterionImprovementPerfernceMap;
	}

	public void setCriterionImprovementPerfernceMap(
			Map<Integer, Integer> criterionImprovementPerfernceMap) {
		this.criterionImprovementPerfernceMap = criterionImprovementPerfernceMap;
	}

	public void setRootAnlzItrtn(Itr rootAnlzItrtn) {
		this.rootAnlzItrtn = rootAnlzItrtn;
	}

	public Itr getRootAnlzItrtn() {
		return rootAnlzItrtn;
	}

	public Integer getCriteriaDisplay() {
		return criteriaDisplay;
	}

	public void setCriteriaDisplay(Integer criteriaDisplay) {
		this.criteriaDisplay = criteriaDisplay;
	}

	public Integer getDisplayBoxplot() {
		return displayBoxplot;
	}

	public void setDisplayBoxplot(Integer displayBoxplot) {
		this.displayBoxplot = displayBoxplot;
	}

	public Integer getDisplayLine() {
		return displayLine;
	}

	public void setDisplayLine(Integer displayLine) {
		this.displayLine = displayLine;
	}

	public Integer getZoomIn() {
		return zoomIn;
	}

	public void setZoomIn(Integer zoomIn) {
		this.zoomIn = zoomIn;
	}

	public Integer getZoomOut() {
		return zoomOut;
	}

	public void setZoomOut(Integer zoomOut) {
		this.zoomOut = zoomOut;
	}

	public void setAltrntvlist(List<Altrntv> altrntvlist) {
		this.altrntvlist = altrntvlist;
	}

	public List<Altrntv> getAltrntvlist() {
		return altrntvlist;
	}

	public void setWidth(Integer width) {
		this.width = width;
		this.setLabel_width((width - 70) / 2);
	}

	public Integer getWidth() {
		return width;
	}

	public void setHight(Integer hight) {
		this.hight = hight;
	}

	public Integer getHight() {
		return hight;
	}

	public void setLabel_width(Integer label_width) {
		this.label_width = label_width;
	}

	public Integer getLabel_width() {
		return label_width;
	}

	public List<Crtrn> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<Crtrn> criteria) {
		this.criteria = criteria;
	}

}
