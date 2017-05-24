package tools;

public class tText {
	
	private float _x,_y;
	private String SText;
	
	public tText()
	{
		
	}
	
	public void setX(float X) { _x = X;}
	public void setY(float Y) { _y = Y;}
	public void setText(String S) { SText = S;}
	
	public float getX(){return _x;}
	public float getY(){return _y;}
	public String getST(){return SText;}
	
	
	public Boolean Hit(double X, double Y, double XPX, double YPX)
	{
		double X1, Y1, X2, Y2;
		X1 = _x;
		Y1 = _y - 13;
		X2 = X1 + (6.5*SText.length());
		Y2 = Y1;
		
		if(_x < X &&  X < X2 && _y > Y && Y > Y2){
			return true;
		}
		
		return false;
	}
	
	
}
