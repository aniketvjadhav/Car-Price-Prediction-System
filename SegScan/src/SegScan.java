import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;


public class SegScan {

	public static void main(String[] args) {
		JobClient client = new JobClient();
		JobConf conf = new JobConf(SegScan.class);

		// TODO: specify output types
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		// TODO: specify input and output DIRECTORIES (not files)
	

		// TODO: specify a mapper
		conf.setMapperClass(Map.class);

		// TODO: specify a reducer
		conf.setReducerClass(Reduce.class);
		 FileInputFormat.setInputPaths(conf, args[0]);
		   FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		client.setConf(conf);
		try {
			JobClient.runJob(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
