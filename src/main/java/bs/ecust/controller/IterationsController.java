package bs.ecust.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import bs.ecust.SecurityUtils;
import bs.ecust.StatusEnum;
import bs.ecust.domain.db.Altrntv;
import bs.ecust.domain.db.Anlz;
import bs.ecust.domain.db.Atrbt;
import bs.ecust.domain.db.Crtrn;
import bs.ecust.domain.db.Instnc;
import bs.ecust.domain.db.Itr;
import bs.ecust.domain.db.ItrAlter;
import bs.ecust.domain.db.ItrDots;
import bs.ecust.domain.db.ItrEntVl;
import bs.ecust.domain.db.Prblm;
import bs.ecust.repository.AltrntvRepository;
import bs.ecust.repository.AnlzRepository;
import bs.ecust.repository.CrtrnRepository;
import bs.ecust.repository.InstncRepository;
import bs.ecust.repository.ItrAlterRepository;
import bs.ecust.repository.ItrDotsRepository;
import bs.ecust.repository.ItrEntVlRepository;
import bs.ecust.repository.ItrRepository;
import bs.ecust.repository.PrblmRepository;
import bs.ecust.service.AnalysisService;
import cs.ecust.domain.view.CriteriaChart;
import cs.ecust.domain.view.jfree.DownTriangle;
import cs.ecust.domain.view.jfree.Hint;
import cs.ecust.domain.view.jfree.OverlappedXYToolTipGenerator;



/**
 * @author 任宏涛， ren@ecust.edu.cn
 *
 * @created 2016年1月4日 下午9:38:36
 *
 */
@Controller
@Transactional(readOnly=true)
public class IterationsController {
	
	private static final Log logger = LogFactory.getLog(IterationsController.class);
	
	@Autowired private PrblmRepository prblmRepository;
	@Autowired private InstncRepository instncRepository;
	@Autowired private AnlzRepository anlzRepository;
	@Autowired private AnalysisService analysisService;
	@Autowired private ItrRepository itrRepository;
	@Autowired private AltrntvRepository altrntvRepository;
	@Autowired private ItrEntVlRepository itrEntVlRepository;
	@Autowired private CrtrnRepository crtrnRepository;
	@Autowired private ItrDotsRepository itrDotsRepository;
	@Autowired private ItrAlterRepository itrAlterRepository;

	@RequestMapping("/iterations")
	public String iterations(
			@RequestParam(value = "problemId") Long problemId,
			@RequestParam(value = "instanceId") Long instanceId,
			@RequestParam(value = "analysisId") Long analysisId,
			@RequestParam(required=false,value = "currentItrId") Long currentItrId,
			HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) throws Exception {
		
		model.put("problem", prblmRepository.findOne(problemId));
		model.put("instance", instncRepository.findOne(instanceId));
		Anlz anlz = anlzRepository.findOne(analysisId);
		model.put("analysis",anlz);
		Itr currentItr = null;
		if(currentItrId==null)
		{
			currentItr = anlz.getItrs().iterator().next();
			//System.out.println("xxxxxxx: " +currentItr.getIdItr() );
		}
		else
		{
			currentItr = itrRepository.findOne(currentItrId);
		}
		model.put("currentItr",currentItr);
		Itr rootItr = itrRepository.findRootItrByAnalysisId(analysisId);
		model.put("rootItr",rootItr);
		Boolean hasSolution = true;
		List<String> warnMsgs = new ArrayList<String>();
		List<String> errorMsgs = new ArrayList<String>();
		
		int status = currentItr.getStatus().intValue();
		switch (status) {
		case 0:
		case 1:
			 hasSolution =false;
			 errorMsgs.add("The solver is not called, please contact us by using the contact button!");
			 break;
		case 2:
			hasSolution =true;
			break;
		case 3:
			hasSolution =false;
			
			 warnMsgs.add("The solver is started, please wait...");
			break;
		case 4:
			hasSolution =false;

		}
		
		CriteriaChart criteriaChart = new CriteriaChart();
		List<Altrntv> altrntvList = altrntvRepository.findByProblemId(problemId);
		criteriaChart.setAltrntvlist(altrntvList);
		
		model.put("altrntvList", altrntvList);
		
		
		//System.out.println("current Itr: " + currentItr.getIdItr());
		// idEnt=31 best Alternatives
		ItrEntVl itrEntVl = itrEntVlRepository.findByEntIdAndItrId(31l, currentItr.getIdItr());
		
		//System.out.println("itrEnt: " + itrEntVl.getValue());
		criteriaChart.setBestAltrntv(altrntvRepository.findOne(itrEntVl.getValue().longValue()));
		
		criteriaChart.setCurrentAnlzItrtn(currentItr);
		criteriaChart.setRootAnlzItrtn(rootItr);
		List<Crtrn> criteria = crtrnRepository.findByInstanceId(instanceId);
		criteriaChart.setCriteria(criteria);
		
		int width = 530;
		int height = 50 + criteria.size() * 32;

		criteriaChart.setWidth(width);
		criteriaChart.setHight(height);

		criteriaChart= setDotsSeriesWithHints(criteriaChart,rootItr,criteria);
		criteriaChart= setBestAlternativeSeries(criteriaChart);
		
		model.put("criteriaChart",criteriaChart);
		model.put("bestAltrntv", criteriaChart.getBestAltrntv());
		JFreeChart jfreeChart = createChart(criteriaChart,criteria);

        String fileName = generateChart(jfreeChart, criteriaChart, request.getSession(), request, response.getWriter());
		
    	String graphURL = request.getContextPath()
				+ "/servlet/DisplayChart?filename=" + fileName;
    	
    	model.put("fileName","#" +fileName);
    	model.put("graphURL",graphURL);
       // System.out.println("fileName: " + graphURL);
		model.put("hasSolution",hasSolution);
		model.put("warnMsgs",warnMsgs);
		model.put("errorMsgs",errorMsgs);
		return "iterations";
	}

	
	
	
	private CriteriaChart setDotsSeriesWithHints(CriteriaChart criteriaChart,
			Itr rootItr,List<Crtrn> criteria) {
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		List<Hint> dotHintLst = new ArrayList<Hint>();
		XYSeries[] xyseries = new XYSeries[3];
		for (int i = 0; i < 3; i++) {
			xyseries[i] = new XYSeries(i, false);
		}
		int valueOfCrtrn = 0;
		for (Crtrn crtrn : criteria) {
			List<ItrDots> itrdotsList = itrDotsRepository.findByItrIdAndCriterionId(rootItr.getIdItr(), crtrn.getIdCrtrn());

			for (ItrDots itrDotLbl : itrdotsList) {

				int seriesnum = 0;
				if (itrDotLbl.getNdots() == 1) {
					seriesnum = 0;
				} else if (itrDotLbl.getNdots() == 2) {
					seriesnum = 1;
				} else {
					seriesnum = 2;
				}
				double value = itrDotLbl.getId().getPos() / 1000d;

				xyseries[seriesnum].add(valueOfCrtrn, value);
				dotHintLst.add(new Hint(valueOfCrtrn, value, itrDotLbl
						.getLabel()));

			}
			valueOfCrtrn++;
		}

		for (int i = 0; i < 3; i++) {
			xySeriesCollection.addSeries(xyseries[i]);
		}

		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(
				true, true);
		xylineandshaperenderer
				.setBaseToolTipGenerator(new OverlappedXYToolTipGenerator(
						dotHintLst));
		xylineandshaperenderer.setAutoPopulateSeriesShape(false);

		for (int i = 0; i < xySeriesCollection.getSeriesCount(); i++) {
			xylineandshaperenderer.setSeriesLinesVisible(i, false);
			xylineandshaperenderer.setSeriesShape(i,
					new java.awt.geom.Ellipse2D.Double(-3D, -3D, 6D, 6D), true);
			java.awt.Color color = null;
			if (i == 0) {
				// light sky blue
				color = new java.awt.Color(135, 206, 250);
			}
			if (i == 1) {
				// royal blue
				color = new java.awt.Color(65, 105, 225);
			}
			if (i == 2) {
				// MidnightBlue
				color = new java.awt.Color(25, 25, 112);
			}
			xylineandshaperenderer.setSeriesPaint(i, color);
			xylineandshaperenderer.setSeriesVisibleInLegend(i, false);

		}

		criteriaChart.setXyseriesCollection(xySeriesCollection);
		criteriaChart.setHintLst(dotHintLst);
		criteriaChart.setXyLineAndShapeRenderer(xylineandshaperenderer);
		
		return criteriaChart;
	}
	

	private CriteriaChart setBestAlternativeSeries(CriteriaChart criteriaChart) {
		Itr rootItr = criteriaChart.getRootAnlzItrtn();
		Altrntv bestAltrntv = criteriaChart.getBestAltrntv();
		if (bestAltrntv == null)
			return criteriaChart;

	//	System.out.println("rootItrId: " + rootItr.getIdItr() +", bestAltrntvId: " +bestAltrntv.getIdAltrntv());
		List<ItrAlter> itrAlters = itrAlterRepository.findByItrIdAndAltrntvId(rootItr.getIdItr(), bestAltrntv.getIdAltrntv());
			
		XYSeries[] xyseries = new XYSeries[1];
		xyseries[0] = new XYSeries(bestAltrntv.getSnm(), false);
		int valueOfCrtrn = 0;
		for (ItrAlter itrAlter : itrAlters) {
			//System.out.println("cccc:" + itrDotPos.getId().getPos());
			double value = itrAlter.getPos() / 1000d;
			xyseries[0].add(valueOfCrtrn, value);
			valueOfCrtrn++;
		}

		criteriaChart.getXyseriesCollection().addSeries(xyseries[0]);

		int seriesNum = criteriaChart.getXyseriesCollection().getSeriesCount() - 1;
		criteriaChart.getXyLineAndShapeRenderer().setSeriesLinesVisible(
				seriesNum, false);
		criteriaChart.getXyLineAndShapeRenderer().setSeriesShape(seriesNum,
				DownTriangle.createDownTriangle(6f), true);
		java.awt.Color orange = new java.awt.Color(255, 69, 0);
		criteriaChart.getXyLineAndShapeRenderer().setSeriesPaint(seriesNum,
				orange);
		criteriaChart.getXyLineAndShapeRenderer().setSeriesVisibleInLegend(
				seriesNum, true);

//		if (criteriaChart.getDisplayLine() == 1) {
			criteriaChart.getXyLineAndShapeRenderer().setSeriesLinesVisible(
					criteriaChart.getXyseriesCollection().getSeriesCount() - 1,
					true);
			criteriaChart.getXyLineAndShapeRenderer().setSeriesStroke(
					criteriaChart.getXyseriesCollection().getSeriesCount() - 1,
					new java.awt.BasicStroke(1.0F, 1, 1, 1.0F, new float[] {
							3F, 3F }, 0.0F));
//
//		}

		return criteriaChart;
	}

	public  JFreeChart createChart(CriteriaChart criteriaChart,
			List<Crtrn> criteria) {
		String[] crtrnShortNms = new String[criteria.size()];
		int i=0;
		for(Crtrn crtrn: criteria)
		{
			crtrnShortNms[i] = crtrn.getSnm();
			i++;
		}
		SymbolAxis symbolaxis = new SymbolAxis(null, crtrnShortNms);
	//	symbolaxis.setUpperMargin(1.0D);
		String[] unit = new String[2];
		unit[0] = "Worst";
		unit[1] = "Best";
		SymbolAxis rangeAxis = new SymbolAxis(null, unit);
		// rangeAxis.setLowerMargin(50);
		rangeAxis.setFixedAutoRange(1D);
		rangeAxis.setGridBandsVisible(true);
		rangeAxis.setRange(-0.050000000000000003D, 1.05D);

		XYPlot xyplot = new XYPlot(criteriaChart.getXyseriesCollection(),
				symbolaxis, rangeAxis, criteriaChart
						.getXyLineAndShapeRenderer());
		xyplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		xyplot.setDomainAxisLocation(AxisLocation.TOP_OR_LEFT);
		xyplot.setOrientation(PlotOrientation.HORIZONTAL);
		AxisSpace space = new AxisSpace();
		space.setLeft(100.0);

		// space.setRight(4.0);
		xyplot.setFixedDomainAxisSpace(space);
		
		
		AxisSpace space1 = new AxisSpace();
		space1.setBottom(10.0);
		xyplot.setFixedRangeAxisSpace(space1);
		JFreeChart jfreechart = new JFreeChart(null, xyplot);

		return jfreechart;
	}

	public  String generateChart(JFreeChart jfreechart,
			CriteriaChart criteriaChart, HttpSession session,
			HttpServletRequest request, PrintWriter pw) throws IOException {

		String filename = null;
		ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());
		filename = ServletUtilities.saveChartAsPNG(jfreechart, criteriaChart
				.getWidth(), criteriaChart.getHight(), info, session);
		//System.out.println(ServletUtilities.getTempFilePrefix());
		ChartUtilities.writeImageMap(pw, filename, info, true);
		// ChartUtilities.
		//String str=System.getProperty("user.home");
		//System.out.println(str);
		return filename;

	}

}
