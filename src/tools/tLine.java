package tools;

import javafx.scene.input.MouseEvent;

public class tLine {
	private float _x1, _x2, _y1, _y2;

	public tLine() {

	}

	// getter
	public float getX1() {
		return _x1;
	}

	public float getX2() {
		return _x2;
	}

	public float getY1() {
		return _y1;
	}

	public float getY2() {
		return _y2;
	}

	// setter
	public void setX1(float x) {
		_x1 = x;
	}

	public void setX2(float x) {
		_x2 = x;
	}

	public void setY1(float y) {
		_y1 = y;
	}

	public void setY2(float y) {
		_y2 = y;
	}

	/*
	 * To see if a Object has been selected
	 */
	public Boolean Hit(double X, double Y, double XPX, double YPX) {
		Boolean H = false;
		double XMin, XMax, YMin, YMax, dX, dY;

		dX = (_x2 - _x1);
		dY = (_y2 - _y1);
		if (dX >= 0) {
			XMin = _x1 - (XPX);
			XMax = _x2 + (XPX);
			if (dY >= 0) {
				YMin = _y1 - (YPX);
				YMax = _y2 + (YPX);
			} else {
				YMin = _y2 - (YPX);
				YMax = _y1 + (YPX);
			}
		} else {
			XMin = _x2 - (XPX);
			XMax = _x1 + (XPX);
			if (dY >= 0) {
				YMin = _y1 - (YPX);
				YMax = _y2 + (YPX);
			} else {
				YMin = _y2 - (YPX);
				YMax = _y1 + (YPX);
			}
		}
		// System.out.println("X: "+ X + " XMin: " + XMin + " XMax:" + XMax);
		// System.out.println("Y: " + Y + " YMin: " + YMin + " YMax: " + YMax);
		if ((XMin < X) && (X < XMax) && (Y < YMax) && (YMin < Y)) {

			double lenght, f;
			f = 0;
			if (Math.abs(dY / dX) > 1) {
				lenght = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
				for (double d2 = 0; d2 < Math.abs(dY); d2 += YPX) {
					f = dX * d2;
					f = (f) / (dY);
					System.out.println(f + ":" + dX + ":" + d2 + ":" + dY);
					double y = (f * dY);
					y = y / dX;
					System.out.println(d2 + ":" + y);
					if (dY > 0) {
						YMax = _y1 + YPX + d2;
						YMin = _y1 - YPX + d2;

						if (dX >= 0) {
							XMax = _x1 + XPX + f;
							XMin = _x1 - XPX + f;
						} else {
							XMax = _x1 + XPX - f;
							XMax = _x1 - XPX - f;
						}

					} else {
						YMax = _y1 + YPX - d2;
						YMin = _y1 - YPX - d2;
						if (dX >= 0) {
							XMax = _x1 + XPX - f;
							// double y = (dY * f) / dX;
							// System.out.println(y + ":" + d );
							XMin = _x1 - XPX - f;
						} else {
							XMax = _x1 + XPX - f;
							XMin = _x1 - XPX - f;
						}
					}
					// System.out.println("XMin: " + XMin + ":" + XMax + ":X:" +
					// X);
					// System.out.println("YMin: " + YMin + ":" + YMax + ":Y:" +
					// Y);
					if ((XMin <= X) && (X <= XMax) && (Y <= YMax) && (YMin <= Y)) {
						System.out.println("True");
						return true;
					}
				}
			} else {
				for (double d = 0; d < Math.abs(dX); d += XPX) {
					try {
						f = (dY / dX) * d;
						// System.out.println(f + ":" + dX + ":" + d + ":" +
						// dY);
						double y = (f * dY);
						y = y / dX;
						// Syst
					} catch (Exception ex) {
						f = d;
					}

					if (Math.abs(dY / dX) <= 1) {
						if (dX > 0) {
							XMin = _x1 + d - (XPX);
							XMax = _x1 + d + (XPX);
							if (dY >= 0) {
								YMin = _y1 + f - (YPX);
								YMax = _y1 + f + (YPX);

							} else {
								YMin = _y1 + f - (YPX);
								YMax = _y1 + f + (YPX);
							}
						} else if (dX == 0) {
							XMin = _x1 - XPX;
							XMax = _x1 + XPX;
							if (dY >= 0) {
								YMin = _y1 + f - (YPX);
								YMax = _y1 + f + (YPX);

							} else {
								YMin = _y1 - f - (YPX);
								YMax = _y1 - f + (YPX);
							}
						} else {
							XMin = _x1 - d - (XPX);
							XMax = _x1 - d + (XPX);
							if (dY >= 0) {
								YMin = _y1 - f - (YPX);
								YMax = _y1 - f + (YPX);

							} else {
								YMin = _y1 - f - (YPX);
								YMax = _y1 - f + (YPX);

							}
						}
						System.out.println("XMin: " + XMin + ":" + XMax + ":X:" + X);
						System.out.println("YMin: " + YMin + ":" + YMax + ":Y:" + Y);
						if ((XMin <= X) && (X <= XMax) && (Y <= YMax) && (YMin <= Y)) {
							System.out.println("True");
							return true;
						}
					}
				}

			}

		}

		return false;
	}

	public Boolean resize1(double X, double Y, double XPX, double YPX) {
		double X1Max, X1Min, Y1Max, Y1Min;
		X1Max = _x1 + (3 * XPX);
		X1Min = _x1 - (3 * XPX);
		Y1Max = _y1 + (3 * YPX);
		Y1Min = _y1 - (3 * YPX);

		if (X1Min < X && (X1Max > X) && (Y1Min < Y) && (Y1Max > Y)) {
			return true;
		}
		return false;

	}

	public Boolean resize2(double X, double Y, double XPX, double YPX) {
		double X2Max, X2Min, Y2Max, Y2Min;
		X2Max = _x2 + (3 * XPX);
		X2Min = _x2 - (3 * XPX);
		Y2Max = _y2 + (3 * YPX);
		Y2Min = _y2 - (3 * YPX);

		if ((X2Min < X) && (X2Max > X) && (Y2Min < Y) && (Y2Max > Y)) {
			return true;
		}
		return false;
	}

}
