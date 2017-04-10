package bs.ecust.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.servlet.ServletUtilities;

import bs.ecust.domain.db.Crtrn;
import cs.ecust.domain.view.CriteriaChart;


public class CriteriaChartService {

	public static JFreeChart createChart(CriteriaChart criteriaChart,
			Map<String, Crtrn> crtrnMap) {
		String[] crtrnShortNms = new String[crtrnMap.size()];
		crtrnMap.keySet().toArray(crtrnShortNms);
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
		if (criteriaChart.getDisplayBoxplot() == 1) {
			for (int i = 0; i < criteriaChart.getBoxlist().size(); i++) {
				xyplot.addAnnotation(criteriaChart.getBoxlist().get(i));
				xyplot.addAnnotation(criteriaChart.getBoxMiddleLineMarkerList()
						.get(i));
			}
		}

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

	public static String generateChart(JFreeChart jfreechart,
			CriteriaChart criteriaChart, HttpSession session,
			HttpServletRequest request, PrintWriter pw) throws IOException {

		String filename = null;
		ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());
		filename = ServletUtilities.saveChartAsPNG(jfreechart, criteriaChart
				.getWidth(), criteriaChart.getHight(), info, session);
		ChartUtilities.writeImageMap(pw, filename, info, true);
		// ChartUtilities.

		return filename;

	}

}
