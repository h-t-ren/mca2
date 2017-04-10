package cs.ecust.domain.view.jfree;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class Line2D {
	private static final float SQRT2 = (float) Math.pow(2D, 0.5D);

	private Line2D() {
	}

	@Deprecated
	public static Shape clone(Shape shape) {
		if (shape instanceof Cloneable) {
			try {
				return (Shape) org.jfree.util.ObjectUtilities.clone(shape);
			} catch (CloneNotSupportedException _ex) {
			}
		}
		return null;
	}

	public static boolean contains(Rectangle2D rect1, Rectangle2D rect2) {
		double x0 = rect1.getX();
		double y0 = rect1.getY();
		double x = rect2.getX();
		double y = rect2.getY();
		double w = rect2.getWidth();
		double h = rect2.getHeight();
		// double g = rect2.getMaxX()
		return x >= x0 && y >= y0 && x + w <= x0 + rect1.getWidth()
				&& y + h <= y0 + rect1.getHeight();
	}

	public static Shape createDiagonalCross(float l, float t) {
		GeneralPath p0 = new GeneralPath();
		p0.moveTo(-l - t, -l + t);
		p0.lineTo(-l + t, -l - t);
		p0.lineTo(0.0F, -t * SQRT2);
		p0.lineTo(l - t, -l - t);
		p0.lineTo(l + t, -l + t);
		p0.lineTo(t * SQRT2, 0.0F);
		p0.lineTo(l + t, l - t);
		p0.lineTo(l - t, l + t);
		p0.lineTo(0.0F, t * SQRT2);
		p0.lineTo(-l + t, l + t);
		p0.lineTo(-l - t, l - t);
		p0.lineTo(-t * SQRT2, 0.0F);
		p0.closePath();

		return p0;
	}

	public static Shape createLine(double x0, double y0, double x1, double y1) {
		GeneralPath p0 = new GeneralPath();
		p0.moveTo(x0, y0);
		p0.lineTo(x1, y1);
		p0.closePath();
		return p0;
	}

}
