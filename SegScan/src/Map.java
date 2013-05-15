import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.*;

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

private final static IntWritable one = new IntWritable(1);
//private Text word = new Text();

public void map(LongWritable key, Text value, 
                OutputCollector<Text, IntWritable> output, 
                Reporter reporter) throws IOException {
	String line = value.toString();
	
	if (	!line.trim().equals("") && 
			!line.trim().equals(" ") &&
			!line.trim().contains("ParseText::") && 
			!line.trim().contains("Recno::") && 
			!line.trim().contains("URL::")
		)
	{
		String x = value.toString();
		x = 	x.replaceAll("[^a-z^A-Z^0-9\\.\\s+^$^,]"," ").
				replaceAll("[$]+[\\s]+[$]*[\\s]+[$]*[$]*[^0-9]", "").
				replaceAll("[$][$]+"e,"").
				replaceAll("[\\.][\\s]*[\\.]", "");
		
		x = x.replaceAll("[\\s]+", " ").trim();
		
		Pattern pattern = Pattern.compile("[^a-zA-Z][m](i?)[l](e?)(s?)([^a-zA-Z]|[\\s])");
		Matcher matcher = pattern.matcher(x);
		
		if (x.contains("$")&& matcher.find())
		{
			output.collect(new Text(x), one);
		}
		
	}
	     
 
}
}