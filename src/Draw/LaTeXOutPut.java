package Draw;

import java.lang.reflect.Array;
import java.util.ArrayList;

import tools.tLine;

public class LaTeXOutPut {
	
	private ArrayList<Object> ObjectiveCList;
	private ArrayList<String> StringList = new ArrayList<String>();
	
	public LaTeXOutPut(){
		
	}
	
	public void setObjectiveList( ArrayList<Object> ObjectList)
	{
		this.ObjectiveCList = ObjectList;
	}
	
	public ArrayList<String> getStringList()
	{
		return StringList;
	}
	
	public void setStringList()
	{
		try{

			StringList.clear();
		}catch(Exception ex)
		{
			
		}
		
		StringList.add("\\begin{tikzpicture}");
		
		for (Object O : ObjectiveCList )
		{
			tLine tL = (tLine) O;
			
			double X1 = (tL.getX1() * 21) / 480;
			double Y1 = (tL.getY1() * 10.4) / 238;
			double X2 = (tL.getX2() * 21) / 480;
			double Y2 = (tL.getY2() * 10.4) / 238;
			
			String x;
			x = "\\draw ("+X1+","+Y1+") -- ("+X2+","+Y2+");";
			
			StringList.add(x);
			
		}
		
		StringList.add("\\end{tikzpicture}");
		
		
		
		
		
	}
	
}
