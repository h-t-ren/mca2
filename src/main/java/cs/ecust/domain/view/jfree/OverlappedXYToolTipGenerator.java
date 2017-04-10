package cs.ecust.domain.view.jfree;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;

import org.jfree.chart.labels.AbstractXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.PublicCloneable;

/**
 * A tool tip generator for use with an
 * {@link org.jfree.chart.renderer.xy.XYItemRenderer}.
 */
public class OverlappedXYToolTipGenerator extends AbstractXYItemLabelGenerator
		implements XYToolTipGenerator, Cloneable, PublicCloneable, Serializable {

	/** For serialization. */
	private static final long serialVersionUID = -3564164459039540784L;

	/** The default tooltip format. */
	public static final String DEFAULT_TOOL_TIP_FORMAT = "{0}: ({1}, {2})";
	public List<Hint> hintLst;

	/**
	 * Returns a tool tip generator that formats the x-values as dates and the
	 * y-values as numbers.
	 * 
	 * @return A tool tip generator (never <code>null</code>).
	 */
	public static StandardXYToolTipGenerator getTimeSeriesInstance() {
		return new StandardXYToolTipGenerator(DEFAULT_TOOL_TIP_FORMAT,
				DateFormat.getInstance(), NumberFormat.getInstance());
	}

	/**
	 * Creates a tool tip generator using default number formatters.
	 */
	public OverlappedXYToolTipGenerator() {
		this(DEFAULT_TOOL_TIP_FORMAT, NumberFormat.getNumberInstance(),
				NumberFormat.getNumberInstance());
	}

	public OverlappedXYToolTipGenerator(List<Hint> hintLst) {

		this(DEFAULT_TOOL_TIP_FORMAT, NumberFormat.getNumberInstance(),
				NumberFormat.getNumberInstance());
		this.hintLst = hintLst;
	}

	/**
	 * Creates a tool tip generator using the specified number formatters.
	 * 
	 * @param formatString
	 *            the item label format string (<code>null</code> not
	 *            permitted).
	 * @param xFormat
	 *            the format object for the x values (<code>null</code> not
	 *            permitted).
	 * @param yFormat
	 *            the format object for the y values (<code>null</code> not
	 *            permitted).
	 */
	public OverlappedXYToolTipGenerator(String formatString,
			NumberFormat xFormat, NumberFormat yFormat) {

		super(formatString, xFormat, yFormat);

	}

	/**
	 * Creates a tool tip generator using the specified number formatters.
	 * 
	 * @param formatString
	 *            the label format string (<code>null</code> not permitted).
	 * @param xFormat
	 *            the format object for the x values (<code>null</code> not
	 *            permitted).
	 * @param yFormat
	 *            the format object for the y values (<code>null</code> not
	 *            permitted).
	 */
	public OverlappedXYToolTipGenerator(String formatString,
			DateFormat xFormat, NumberFormat yFormat) {

		super(formatString, xFormat, yFormat);

	}

	/**
	 * Creates a tool tip generator using the specified formatters (a number
	 * formatter for the x-values and a date formatter for the y-values).
	 * 
	 * @param formatString
	 *            the item label format string (<code>null</code> not
	 *            permitted).
	 * @param xFormat
	 *            the format object for the x values (<code>null</code>
	 *            permitted).
	 * @param yFormat
	 *            the format object for the y values (<code>null</code> not
	 *            permitted).
	 * 
	 * @since 1.0.4
	 */
	public OverlappedXYToolTipGenerator(String formatString,
			NumberFormat xFormat, DateFormat yFormat) {

		super(formatString, xFormat, yFormat);
	}

	/**
	 * Creates a tool tip generator using the specified date formatters.
	 * 
	 * @param formatString
	 *            the label format string (<code>null</code> not permitted).
	 * @param xFormat
	 *            the format object for the x values (<code>null</code> not
	 *            permitted).
	 * @param yFormat
	 *            the format object for the y values (<code>null</code> not
	 *            permitted).
	 */
	public OverlappedXYToolTipGenerator(String formatString,
			DateFormat xFormat, DateFormat yFormat) {

		super(formatString, xFormat, yFormat);

	}

	/**
	 * Generates the tool tip text for an item in a dataset.
	 * 
	 * @param dataset
	 *            the dataset (<code>null</code> not permitted).
	 * @param series
	 *            the series index (zero-based).
	 * @param item
	 *            the item index (zero-based).
	 * 
	 * @return The tooltip text (possibly <code>null</code>).
	 */
	public String generateToolTip(XYDataset dataset, int series, int item) {

		// System.out.println("x: " + dataset.getXValue(series, item) +", y: "
		// +dataset.getYValue(series, item));
		String hint = "";
		for (int k = 0; k < hintLst.size(); k++) {
			if (dataset.getXValue(series, item) == (hintLst.get(k).getXValue())
					&& dataset.getYValue(series, item) == hintLst.get(k)
							.getYValue()) {
				hint = hintLst.get(k).hint;
				// System.out.println("x value: "
				// +hintLst.get(k).getXValue()+", y value:"
				// +hintLst.get(k).getXValue() );
				break;
			}
		}

		// hint = "x: " + dataset.getXValue(series, item) +", y: "
		// +dataset.getYValue(series, item);
		return hint;

	}

	/**
	 * Tests this object for equality with an arbitrary object.
	 * 
	 * @param obj
	 *            the other object (<code>null</code> permitted).
	 * 
	 * @return A boolean.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof StandardXYToolTipGenerator)) {
			return false;
		}
		return super.equals(obj);
	}

	/**
	 * Returns an independent copy of the generator.
	 * 
	 * @return A clone.
	 * 
	 * @throws CloneNotSupportedException
	 *             if cloning is not supported.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
