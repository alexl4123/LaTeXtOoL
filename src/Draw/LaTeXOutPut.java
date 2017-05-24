package Draw;

import java.lang.reflect.Array;
import java.util.ArrayList;

import tools.tLine;
import tools.tText;

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
			if(O instanceof tLine)
			{
				tLine tL = (tLine) O;
				double X1 = (tL.getX1() * 21) / 480;
				double Y1 = (tL.getY1() * 10.4) / 238;
				double X2 = (tL.getX2() * 21) / 480;
				double Y2 = (tL.getY2() * 10.4) / 238;
				
				String x;
				x = "\\draw ("+X1+","+Y1+") -- ("+X2+","+Y2+");";
				
				StringList.add(x);
			}else if(O instanceof tText){
				tText tT = (tText) O;
				double X1 = (tT.getX() * 21) / 480;
				double Y1 = (tT.getY() * 10.4) / 238;
				String S = tT.getST();
				
				String x;
				x = "\\draw ("+X1+","+Y1+") node {"+S+"};";
				
				StringList.add(x);
			}
			
			
		}
		
		StringList.add("\\end{tikzpicture}");
		
		
		
		
		
	}
	
}
