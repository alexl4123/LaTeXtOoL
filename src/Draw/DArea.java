package Draw;

import java.util.ArrayList;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import tools.tLine;

/*TODO
 * Make LaTeX output
 * Text
 * Rectangle
 * Arrow
 * ...
 */

public class DArea extends Canvas {
	GridPane BP = new GridPane();
	GraphicsContext gc;
	/*
	 * _Y,_X...size of Window dParamX1, dParamX2...relative difference between
	 * start win and now XGrid,YGrid...Distance between Grid dXLine,
	 * dYLine...current nearest Grid dX, dY are for Dragging
	 */
	private double _Y, _X, dParamX1, dParamY1, XGrid, YGrid, dXLine, dYLine, dX, dY;
	private int iSelect;
	private Boolean underCons, bSelected;
	private ArrayList<Object> ObjectiveCList;
	private ArrayList<tLine> HorizontalList, VerticalList;
	public Boolean setAlt;

	public DArea() {

		// initialize
		setAlt = false;
		bSelected = false;
		HorizontalList = new ArrayList<tLine>();
		VerticalList = new ArrayList<tLine>();
		underCons = false;
		ObjectiveCList = new ArrayList<Object>();
		gc = this.getGraphicsContext2D();
		this.widthProperty().addListener(observable -> redraw());
		this.heightProperty().addListener(observable -> redraw());
		dX = 0;
		dY = 0;

		// more initialization
		GridHorizontal(0.1);
		GridVertical(0.1);

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				snapGrid(event);
				if ((event.getButton() == MouseButton.SECONDARY) && underCons) {
					int i = ObjectiveCList.size();
					ObjectiveCList.remove(i - 1);
					bSelected = false;
					underCons = false;
				} else if (event.getButton() == MouseButton.SECONDARY) {
					int i = 0;
					for (Object O : ObjectiveCList) {
						i++;
						double XMouse, YMouse;
						XMouse = event.getX() / dParamX1;
						YMouse = event.getY() / dParamY1;
						tLine L1 = (tLine) O;
						System.out.println("Hit-Try-X" + XMouse + ":" + YMouse + "WHy" );
						if (L1.Hit(XMouse, YMouse, XGrid, YGrid)) {
							System.out.println("Hit-Complete");
							dX = 0;
							dX = 0;
							bSelected = true;
							iSelect = i;
							break;
						}else {
							bSelected = false;
						}
					}
				} else if (underCons && (event.getButton() == MouseButton.PRIMARY)) {
					int i = ObjectiveCList.size();
					tLine LineX = (tLine) ObjectiveCList.get(i - 1);
					LineX.setX2((float) (dXLine));
					LineX.setY2((float) (dYLine));
					ObjectiveCList.set(i - 1, LineX);
					underCons = false;
					bSelected = false;
				} else if (event.getButton() == MouseButton.PRIMARY) {
					tLine Line = new tLine();
					Line.setX1((float) (dXLine));
					Line.setY1((float) (dYLine));

					ObjectiveCList.add(Line);
					bSelected = false;
					underCons = true;
				} else {

				}

				gc.clearRect(0, 0, _X, _Y);
				drawObjects();
			}

		});
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gc.clearRect(0, 0, _X, _Y);
				drawObjects();
				snapGrid(event);

				if (bSelected && (event.getButton() != MouseButton.PRIMARY)) {
					
					tLine L = (tLine) ObjectiveCList.get(iSelect - 1);
					double XDrag = event.getX() / dParamX1;
					double YDrag = event.getY() / dParamY1;
					if (L.resize1(XDrag, YDrag, XGrid, YGrid) && setAlt) {
						double X = L.getX1();
						double Y = L.getY1();
						snapGrid(event);
						dX = (dXLine);
						dY = dYLine;
						double dX2 = dX - X;
						double dY2 = dY - Y;
						L.setX1((float) ((L.getX1() + dX2)));
						L.setY1((float) ((L.getY1() + dY2)));
						gc.strokeLine((L.getX1()) * dParamX1, (L.getY1()) * dParamY1, (L.getX2()) * dParamX1,
								(L.getY2()) * dParamY1);
						
						dX = 0;
						dY = 0;

					} else if (L.resize2(XDrag, YDrag, XGrid, YGrid) && setAlt) {
						double X = L.getX2();
						double Y = L.getY2();
						snapGrid(event);
						dX = (dXLine);
						dY = dYLine;
						double dX2 = dX - X;
						double dY2 = dY - Y;
						L.setX2((float) ((L.getX2() + dX2)));
						L.setY2((float) ((L.getY2() + dY2)));
						gc.strokeLine((L.getX1()) * dParamX1, (L.getY1()) * dParamY1, (L.getX2()) * dParamX1,
								(L.getY2()) * dParamY1);
						
						dX = 0;
						dY = 0;
					} else {
						double X = dX;
						double Y = dY;
						snapGrid(event);
						dX = (dXLine);
						dY = dYLine;
						double dX2 = dX - X;
						double dY2 = dY - Y;
						
						if(Math.abs(dX2) < 10 && Math.abs(dY2) < 10)
						{
							L.setX1((float) ((L.getX1() + dX2)));
							L.setY1((float) ((L.getY1() + dY2)));
							L.setX2((float) ((L.getX2() + dX2)));
							L.setY2((float) ((L.getY2() + dY2)));
							gc.strokeLine((L.getX1()) * dParamX1, (L.getY1()) * dParamY1, (L.getX2()) * dParamX1,
									(L.getY2()) * dParamY1);
						}
						
					}

				} else if ((underCons) && (event.getButton() == MouseButton.PRIMARY)) {

					snapGrid(event);
					int i = ObjectiveCList.size();
					tLine Line1 = (tLine) ObjectiveCList.get(i - 1);
					gc.strokeLine(dParamX1 * Line1.getX1(), dParamY1 * Line1.getY1(), dParamX1 * dXLine,
							dParamY1 * dYLine);
				} else if (event.getButton() == MouseButton.PRIMARY) {
					tLine Line = new tLine();
					Line.setX1((float) (dXLine));
					Line.setY1((float) (dYLine));

					ObjectiveCList.add(Line);
					bSelected = false;
					underCons = true;
				} else {

				}

			}
		});

		this.setOnMouseDragReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (underCons && !bSelected) {
					snapGrid(event);
					int i = ObjectiveCList.size();
					tLine LineX = (tLine) ObjectiveCList.get(i - 1);
					LineX.setX2((float) (dXLine));
					LineX.setY2((float) (dYLine));
					ObjectiveCList.set(i - 1, LineX);
					underCons = false;
					bSelected = false;

				} else {
					tLine L = (tLine) ObjectiveCList.get(iSelect - 1);
					double XM = event.getX();
					double YM = event.getY();
					L.setX1((float) (L.getX1() + XM));
					L.setY1((float) (L.getY1() + YM));
					L.setX2((float) (L.getX2() + XM));
					L.setY2((float) (L.getY2() + YM));
				}

			}
		});

		this.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				gc.clearRect(0, 0, _X, _Y);
				if (underCons) {
					snapGrid(event);
					int i = ObjectiveCList.size();
					tLine Line1 = (tLine) ObjectiveCList.get(i - 1);

					gc.strokeLine(dParamX1 * Line1.getX1(), dParamY1 * Line1.getY1(), dParamX1 * dXLine,
							dParamY1 * dYLine);

				}
				drawObjects();

			}

		});

	}

	private void redraw() {
		// gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	// After Window Resize
	public void setXY(double x, double y) {
		this.setWidth(x);
		this._X = x;
		this.setHeight(y);
		this._Y = y;

		dParamX1 = (x / 480);
		dParamY1 = (y / 238);
	}

	// alternate Grid
	public void newGrid(double dG) {
		HorizontalList.clear();
		VerticalList.clear();
		GridHorizontal(dG);
		GridVertical(dG);
	}

	// Sets the Grid Layout in Horizontal
	public void GridHorizontal(double dG) {
		XGrid = (dG * 480) / 21;

		for (double d = 0; d < 238; d += XGrid) {
			tLine Line = new tLine();
			Line.setX1(0);
			Line.setX2(480);
			Line.setY1((float) d);
			Line.setY2((float) d);
			HorizontalList.add(Line);
		}

	}

	// Sets the Grid Layout in Vertical
	public void GridVertical(double dG) {
		YGrid = (dG * 238) / 10.4;

		for (double d = 0; d < 480; d += YGrid) {
			tLine Line = new tLine();
			Line.setX1((float) d);
			Line.setX2((float) d);
			Line.setY1(0);
			Line.setY2(238);
			VerticalList.add(Line);
		}
	}

	// Draw Lines and Objects
	public void drawObjects() {
		gc.setLineWidth(2);
		for (Object x : ObjectiveCList) {
			tLine Line = (tLine) x;
			if (Line.getX2() != 0) {
				gc.strokeLine(dParamX1 * Line.getX1(), dParamY1 * Line.getY1(), dParamX1 * Line.getX2(),
						dParamY1 * Line.getY2());
			}
		}
		gc.setLineWidth(0.5);
		gc.setStroke(Color.DARKORANGE);
		for (tLine L1 : HorizontalList) {
			gc.strokeLine(dParamX1 * L1.getX1(), dParamY1 * L1.getY1(), dParamX1 * L1.getX2(), dParamY1 * L1.getY2());
		}
		for (tLine L2 : VerticalList) {
			gc.strokeLine(dParamX1 * L2.getX1(), dParamY1 * L2.getY1(), dParamX1 * L2.getX2(), dParamY1 * L2.getY2());
		}
		gc.setLineWidth(5);
		gc.setStroke(Color.DARKBLUE);

		gc.strokeLine(0, _Y / 2, _X, _Y / 2);
		gc.strokeLine(_X / 2, 0, _X / 2, _Y);

		if (bSelected) {
			gc.setStroke(Color.BLUEVIOLET);
			gc.setLineWidth(3);
			try {
				tLine L = (tLine) ObjectiveCList.get(iSelect - 1);
				gc.strokeLine(L.getX1() * dParamX1, dParamY1 * L.getY1(), dParamX1 * L.getX2(), dParamY1 * L.getY2());
				gc.fillOval(dParamX1 * (L.getX1() - 2), dParamY1 * (L.getY1() - 2), 4 * dParamX1, 4 * dParamY1);
				gc.fillOval(dParamX1 * (L.getX2() - 2), dParamY1 * (L.getY2() - 2), 4 * dParamX1, 4 * dParamY1);
			} catch (Exception ex) {

			}

		}

		gc.setStroke(Color.BLACK);

	}

	// Snap Grid
	public void snapGrid(MouseEvent E) {
		double dMouseX, dMouseY;
		dXLine = 0;
		dYLine = 0;
		dMouseX = E.getX() * dParamX1;
		dMouseY = E.getY() * dParamY1;

		for (tLine L1 : VerticalList) {

			if (((L1.getX1() * dParamX1 - E.getX()) < XGrid * dParamX1)
					&& ((E.getX() - L1.getX1() * dParamX1) < XGrid * dParamX1)) {
				for (tLine L2 : HorizontalList) {
					if (((L2.getY1() * dParamY1 - E.getY()) < YGrid * dParamY1)
							&& (E.getY() - L2.getY1() * dParamY1) < YGrid * dParamY1) {
						dXLine = L1.getX1();
						dYLine = L2.getY1();
					}
				}

			}
		}

	}
	
	public ArrayList<Object> getObjectiveList()
	{
		return ObjectiveCList;
	}

	// Delete selected item
	public void Delete() {
		try {

			ObjectiveCList.remove(iSelect - 1);
			iSelect--;
			gc.clearRect(0, 0, _X, _Y);
			drawObjects();
		} catch (Exception ex) {

		}
	}

}
