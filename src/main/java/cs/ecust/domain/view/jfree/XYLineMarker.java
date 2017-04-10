package cs.ecust.domain.view.jfree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A Circle annotation that can be placed on an {@link XYPlot}. The Circle
 * coordinates are specified in data space.
 */
public class XYLineMarker extends
		org.jfree.chart.annotations.AbstractXYAnnotation implements Cloneable,
		PublicCloneable, Serializable {

	/** For serialization. */
	private static final long serialVersionUID = 6899803772526757457L;

	/** The lower x-coordinate. */
	private double x0;

	/** The lower y-coordinate. */
	private double y0;

	/** The upper x-coordinate. */
	// private double x1;
	/** The upper y-coordinate. */
	// private double y1;
	// private double width;
	// private double height;
	/** The stroke used to draw the box outline. */
	private transient Stroke stroke;

	/** The paint used to draw the box outline. */
	private transient Paint outlinePaint;

	/** The paint used to fill the box. */
	private transient Paint fillPaint;

	private boolean overlap;

	/**
	 * Creates a new annotation (where, by default, the box is drawn with a
	 * black outline).
	 * 
	 * @param x0
	 *            the lower x-coordinate of the box (in data space).
	 * @param y0
	 *            the lower y-coordinate of the box (in data space).
	 * @param x1
	 *            the upper x-coordinate of the box (in data space).
	 * @param y1
	 *            the upper y-coordinate of the box (in data space).
	 */
	public XYLineMarker(double x0, double y0) {
		this(x0, y0, new BasicStroke(1.0f), Color.black);
	}

	/**
	 * Creates a new annotation where the box is drawn as an outline using the
	 * specified <code>stroke</code> and <code>outlinePaint</code>.
	 * 
	 * @param x0
	 *            the lower x-coordinate of the box (in data space).
	 * @param y0
	 *            the lower y-coordinate of the box (in data space).
	 * @param x1
	 *            the upper x-coordinate of the box (in data space).
	 * @param y1
	 *            the upper y-coordinate of the box (in data space).
	 * @param stroke
	 *            the shape stroke (<code>null</code> permitted).
	 * @param outlinePaint
	 *            the shape color (<code>null</code> permitted).
	 */
	public XYLineMarker(double x0, double y0, Stroke stroke, Paint outlinePaint) {
		this(x0, y0, stroke, outlinePaint, null, false);
	}

	public XYLineMarker(double x0, double y0, Stroke stroke,
			Paint outlinePaint, boolean overlap) {
		this(x0, y0, stroke, outlinePaint, null, overlap);
	}

	/**
	 * Creates a new annotation.
	 * 
	 * @param x0
	 *            the lower x-coordinate of the box (in data space).
	 * @param y0
	 *            the lower y-coordinate of the box (in data space).
	 * @param x1
	 *            the upper x-coordinate of the box (in data space).
	 * @param y1
	 *            the upper y-coordinate of the box (in data space).
	 * @param stroke
	 *            the shape stroke (<code>null</code> permitted).
	 * @param outlinePaint
	 *            the shape color (<code>null</code> permitted).
	 * @param fillPaint
	 *            the paint used to fill the shape (<code>null</code>
	 *            permitted).
	 */
	public XYLineMarker(double x0, double y0, Stroke stroke,
			Paint outlinePaint, Paint fillPaint, boolean overlap) {
		this.x0 = x0;
		this.y0 = y0;
		this.stroke = stroke;
		this.outlinePaint = outlinePaint;
		this.fillPaint = fillPaint;
		this.overlap = overlap;
	}

	/**
	 * Draws the annotation. This method is usually called by the {@link XYPlot}
	 * class, you shouldn't need to call it directly.
	 * 
	 * @param g2
	 *            the graphics device.
	 * @param plot
	 *            the plot.
	 * @param dataArea
	 *            the data area.
	 * @param domainAxis
	 *            the domain axis.
	 * @param rangeAxis
	 *            the range axis.
	 * @param rendererIndex
	 *            the renderer index.
	 * @param info
	 *            the plot rendering info.
	 */
	@Override
	public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea,
			ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex,
			PlotRenderingInfo info) {

		PlotOrientation orientation = plot.getOrientation();
		RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot
				.getDomainAxisLocation(), orientation);
		RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot
				.getRangeAxisLocation(), orientation);

		double transY0 = rangeAxis.valueToJava2D(this.y0, dataArea, rangeEdge);

		double transY1 = rangeAxis.valueToJava2D(this.y0, dataArea, rangeEdge);
		double transX0, transX1;
		if (overlap) {
			transX0 = domainAxis.valueToJava2D(this.x0 - 0.25D, dataArea,
					domainEdge);
			transX1 = domainAxis.valueToJava2D(this.x0 + 0.25D, dataArea,
					domainEdge);

		} else {
			transX0 = domainAxis.valueToJava2D(this.x0 - 0.15D, dataArea,
					domainEdge);
			transX1 = domainAxis.valueToJava2D(this.x0 + 0.15D, dataArea,
					domainEdge);

		}

		if (orientation == PlotOrientation.HORIZONTAL) {
			double temp = transX0;
			transX0 = transY0;
			transY0 = temp;

			double temp1 = transX1;
			transX1 = transY1;
			transY1 = temp1;
		}

		// System.out.println("b x:"+ transX0 +",y:" +transY0 );
		if (this.fillPaint != null) {
			g2.setPaint(this.fillPaint);
			g2.fill(Line2D.createLine(transX0, transY0, transX1, transY1));
		}

		if (this.stroke != null && this.outlinePaint != null) {
			g2.setPaint(this.outlinePaint);
			g2.setStroke(this.stroke);
			g2.draw(Line2D.createLine(transX0, transY0, transX1, transY1));
		}
		addEntity(info, Line2D.createLine(transX0, transY0, transX1, transY1),
				rendererIndex, getToolTipText(), getURL());

	}

	/**
	 * Tests this annotation for equality with an arbitrary object.
	 * 
	 * @param obj
	 *            the object (<code>null</code> permitted).
	 * 
	 * @return A boolean.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		// now try to reject equality
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof XYLineMarker)) {
			return false;
		}
		XYLineMarker that = (XYLineMarker) obj;

		if (!(this.x0 == that.x0)) {
			return false;
		}
		if (!(this.y0 == that.y0)) {
			return false;
		}

		if (!ObjectUtilities.equal(this.stroke, that.stroke)) {
			return false;
		}
		if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
			return false;
		}
		if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
			return false;
		}
		// seem to be the same
		return true;
	}

	/**
	 * Returns a hash code.
	 * 
	 * @return A hash code.
	 */
	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(this.x0);
		result = (int) (temp ^ (temp >>> 32));

		temp = Double.doubleToLongBits(this.y0);
		result = 29 * result + (int) (temp ^ (temp >>> 32));

		return result;
	}

	/**
	 * Returns a clone.
	 * 
	 * @return A clone.
	 * 
	 * @throws CloneNotSupportedException
	 *             not thrown by this class, but may be by subclasses.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Provides serialization support.
	 * 
	 * @param stream
	 *            the output stream (<code>null</code> not permitted).
	 * 
	 * @throws IOException
	 *             if there is an I/O error.
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
		SerialUtilities.writeStroke(this.stroke, stream);
		SerialUtilities.writePaint(this.outlinePaint, stream);
		SerialUtilities.writePaint(this.fillPaint, stream);
	}

	/**
	 * Provides serialization support.
	 * 
	 * @param stream
	 *            the input stream (<code>null</code> not permitted).
	 * 
	 * @throws IOException
	 *             if there is an I/O error.
	 * @throws ClassNotFoundException
	 *             if there is a classpath problem.
	 */
	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {

		stream.defaultReadObject();
		this.stroke = SerialUtilities.readStroke(stream);
		this.outlinePaint = SerialUtilities.readPaint(stream);
		this.fillPaint = SerialUtilities.readPaint(stream);
	}

}
