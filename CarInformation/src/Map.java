import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.*;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;




public   class Map extends MapReduceBase
implements Mapper<LongWritable, Text, Text, IntWritable> {
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

private final static IntWritable one = new IntWritable(1);
//private Text word = new Text();

public void map(LongWritable key, Text value, 
                OutputCollector<Text, IntWritable> output, 
                Reporter reporter) throws IOException {
	
	String line = value.toString();
	
	String [] parts = line.split(" ");
	
	String name="";
	String price="";
	String miles="";
	int nameflag=1;
	int priceflag=1;
	int milesflag=1;
	
	String content = new Scanner(new File("/home/vaibhav/carmodels.txt")).useDelimiter("\\Z").next();;
	String carnames[] = content.split("[\\s|\\n]");
	for(int i = 0; i<parts.length; i++)
	{
		for(int m=0; m<=carnames.length-1 ; m++)
		{
			if (i<=parts.length-3)
			if ((carnames[m].equalsIgnoreCase(parts[i])) && nameflag < 2)
			{
				name = name + parts[i] +" "+parts[i+1];
				i++;
				nameflag++;
			}
		}
		
		if(i>parts.length-1)
			continue;
		
		
			if (parts[i].contains("$") && priceflag < 2)
			{
				if(parts[i].length()>1)
				{
					for (int j=0; j<=parts[i].length()-1; j++)
					{
						if((byte)parts[i].charAt(j) >= 48 && (byte)parts[i].charAt(j) <= 57)
						{
							if (parts[i].equals("."))
								break;
							
							price = price + parts[i].charAt(j);
							priceflag++;
						}
					}
				}
				else
				{	
					if(i>0)
					{
						parts[i-1] = parts[i-1].replaceAll(",", "");
						parts[i-1] = parts[i-1].toLowerCase().replaceAll("k", "000");
						if ( isNumeric(parts[i-1]))
						{
							price = parts[i-1];
							priceflag++;
							continue;
						}	
					}
									
				}
				
				if(i!= parts.length-1)
				{
					parts[i+1] = parts[i+1].replaceAll(",", "");
					parts[i+1] = parts[i+1].toLowerCase().replaceAll("k", "000");
					if( isNumeric(parts[i+1]))
					{
						price = parts[i+1];
						i++;
						priceflag++;
					}
				}
				
			}
			
			if ((parts[i].contains("miles")||parts[i].contains("mile")||parts[i].contains("mls") )&& milesflag < 2)
			{
				if(parts[i].length()>6)
				{
					parts[i] = parts[i].toLowerCase().replaceAll("k", "000");
					
					for (int j=0; j<=parts[i].length()-1; j++)
					{
						if((byte)parts[i].charAt(j) >= 48 && (byte)parts[i].charAt(j) <= 57)
						{
							if (parts[i].equals("."))
								break;
							
							miles = miles+ parts[i].charAt(j);
							milesflag++;
						}
					}
				}
				else
				{	
					if (i>0)
					{
						parts[i-1] = parts[i-1].replaceAll(",", "");
						parts[i-1] = parts[i-1].toLowerCase().replaceAll("k", "000");				
						if ( isNumeric(parts[i-1]))
						{
							
							miles = parts[i-1];
							milesflag++;
							continue;
						}
					}
										
				}
				
				if (i!= parts.length-1)
				{
					parts[i+1] = parts[i+1].replaceAll(",", "");
					parts[i+1] = parts[i+1].toLowerCase().replaceAll("k", "000");
					if(isNumeric(parts[i+1]))
					{
						
						miles = parts[i+1];
						i++;
						milesflag++;
					}
				}
				
			}	
		
	}
	
	if(!name.equals("") && !price.equals("") && !miles.equals(""))
	output.collect(new Text(name+"\t"+miles+"\t"+price),new IntWritable(1));
 
}
}